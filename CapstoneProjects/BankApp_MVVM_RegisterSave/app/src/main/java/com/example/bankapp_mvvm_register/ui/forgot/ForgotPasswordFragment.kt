package com.example.bankapp_mvvm_register.ui.forgot

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.bankapp_mvvm_register.R
import com.example.bankapp_mvvm_register.databinding.FragmentForgotPasswordBinding
import com.example.bankapp_mvvm_register.db.AppDatabase
import com.example.bankapp_mvvm_register.repository.UserRepository
import kotlinx.coroutines.launch


/**Created by Raviteja Emandi on 22,June,2025 **/
class ForgotPasswordFragment : Fragment() {
    private var _binding: FragmentForgotPasswordBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ForgotPasswordViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentForgotPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val dao = AppDatabase.getInstance(requireContext()).userDao()
        val repository = UserRepository(dao)
        viewModel = ViewModelProvider(this, ForgotPasswordViewModelFactory(repository))[ForgotPasswordViewModel::class.java]

        binding.btnCheckEmail.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()

            if (email.isEmpty()) {
                Toast.makeText(requireContext(), "Enter email", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            lifecycleScope.launch {
                val user = viewModel.getUserByEmail(email)
                if (user != null) {
                    binding.layoutNewPassword.visibility = View.VISIBLE
                    binding.btnResetPassword.setOnClickListener {
                        val newPassword = binding.etNewPassword.text.toString().trim()
                        if (newPassword.isEmpty()) {
                            Toast.makeText(requireContext(), "Enter new password", Toast.LENGTH_SHORT).show()
                        } else {
                            val updatedUser = user.copy(password = newPassword)
                            lifecycleScope.launch {
                                viewModel.updateUser(updatedUser)
                                Toast.makeText(requireContext(), "Password updated!", Toast.LENGTH_SHORT).show()
                                findNavController().navigate(R.id.action_forgotPasswordFragment_to_loginFragment)
                            }
                        }
                    }
                } else {
                    Toast.makeText(requireContext(), "Email not registered", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
