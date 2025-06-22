package com.example.bankapp_mvvm_register.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.bankapp_mvvm_register.R
import com.example.bankapp_mvvm_register.databinding.FragmentProfileBinding
import com.example.bankapp_mvvm_register.db.AppDatabase
import com.example.bankapp_mvvm_register.model.User
import com.example.bankapp_mvvm_register.repository.UserRepository
import com.example.bankapp_mvvm_register.utils.SessionManager
import kotlinx.coroutines.launch

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ProfileViewModel
    private lateinit var sessionManager: SessionManager
    private var currentUser: User? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        sessionManager = SessionManager(requireContext())

        val userDao = AppDatabase.getInstance(requireContext()).userDao()
        val repository = UserRepository(userDao)
        viewModel = ViewModelProvider(this, ProfileViewModelFactory(repository))[ProfileViewModel::class.java]

        val userId = sessionManager.getUserId()
        lifecycleScope.launch {
            val user = viewModel.getUserById(userId)
            if (user != null) {
                currentUser = user
                binding.etName.setText(user.name)
                binding.etEmail.setText(user.email)
                binding.etPhone.setText(user.phone)
            }
        }

        binding.btnUpdate.setOnClickListener {
            val name = binding.etName.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()
            val phone = binding.etPhone.text.toString().trim()

            if (name.isEmpty() || email.isEmpty() || phone.isEmpty()) {
                Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            currentUser?.let {
                val updatedUser = it.copy(name = name, email = email, phone = phone)
                lifecycleScope.launch {
                    viewModel.updateUser(updatedUser)
                    Toast.makeText(requireContext(), "Profile updated", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.btnLogout.setOnClickListener {
            sessionManager.logout()

            val navOptions = NavOptions.Builder()
                .setPopUpTo(R.id.splashFragment, true) // clears back stack
                .build()

            findNavController().navigate(R.id.loginFragment, null, navOptions)
        }

        binding.btnDeleteAccount.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Delete Account")
                .setMessage("Are you sure you want to delete your account?")
                .setPositiveButton("Yes") { _, _ ->
                    currentUser?.let {
                        lifecycleScope.launch {
                            viewModel.deleteUser(it)
                            sessionManager.logout()

                            val navOptions = NavOptions.Builder()
                                .setPopUpTo(R.id.splashFragment, true)
                                .build()

                            findNavController().navigate(R.id.registerFragment, null, navOptions)
                        }
                    }
                }
                .setNegativeButton("Cancel", null)
                .show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
