package com.example.bankapp_mvvm_register.ui.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.bankapp_mvvm_register.R
import com.example.bankapp_mvvm_register.databinding.FragmentRegisterBinding
import com.example.bankapp_mvvm_register.db.AppDatabase
import com.example.bankapp_mvvm_register.model.User
import com.example.bankapp_mvvm_register.repository.UserRepository
import kotlinx.coroutines.launch

class RegisterFragment : Fragment() {
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: RegisterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val userDao = AppDatabase.getInstance(requireContext()).userDao()
        val repository = UserRepository(userDao)
        viewModel = ViewModelProvider(this, RegisterViewModelFactory(repository))[RegisterViewModel::class.java]

        binding.btnRegister.setOnClickListener {
            val name = binding.etName.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            val phone = binding.etPhone.text.toString().trim()

            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || phone.isEmpty()) {
                Toast.makeText(requireContext(), "All fields are required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            lifecycleScope.launch {
                val existingUser = viewModel.checkUserExists(email)
                if (existingUser) {
                    Toast.makeText(requireContext(), "User already exists", Toast.LENGTH_SHORT).show()
                } else {
                    val user = User(name = name, email = email, password = password, phone = phone)
                    viewModel.registerUser(user)
                    Toast.makeText(requireContext(), "Registered successfully", Toast.LENGTH_SHORT).show()

                    val navOptions = NavOptions.Builder()
                        .setPopUpTo(R.id.registerFragment, true)
                        .build()

                    findNavController().navigate(R.id.loginFragment, null, navOptions)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
