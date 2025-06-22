package com.example.bankapp_mvvm_register.ui.login

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
import com.example.bankapp_mvvm_register.databinding.FragmentLoginBinding
import com.example.bankapp_mvvm_register.db.AppDatabase
import com.example.bankapp_mvvm_register.repository.UserRepository
import com.example.bankapp_mvvm_register.utils.SessionManager
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: LoginViewModel
    private lateinit var sessionManager: SessionManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        sessionManager = SessionManager(requireContext())

        val userDao = AppDatabase.getInstance(requireContext()).userDao()
        val repository = UserRepository(userDao)
        viewModel = ViewModelProvider(this, LoginViewModelFactory(repository))[LoginViewModel::class.java]

        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(requireContext(), "Please enter all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            lifecycleScope.launch {
                val user = viewModel.loginUser(email, password)
                if (user != null) {
                    sessionManager.saveLogin(user.id)

                    val navOptions = NavOptions.Builder()
                        .setPopUpTo(R.id.loginFragment, true) // clear login from stack
                        .build()

                    findNavController().navigate(R.id.homeFragment, null, navOptions)

                } else {
                    val userExists = viewModel.checkUserExists(email)
                    if (userExists) {
                        Toast.makeText(requireContext(), "Incorrect password", Toast.LENGTH_SHORT).show()
                    } else {
                        AlertDialog.Builder(requireContext())
                            .setTitle("User Not Registered")
                            .setMessage("Would you like to register?")
                            .setPositiveButton("Register") { _, _ ->
                                findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
                            }
                            .setNegativeButton("Cancel", null)
                            .show()
                    }
                }
            }
        }

        binding.tvRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        binding.tvForgotPassword.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_forgotPasswordFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
