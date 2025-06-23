package com.example.a33navigationcomponent

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController


class LoginFragment : Fragment(R.layout.fragment_login) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val edtUsername = view.findViewById<EditText>(R.id.edtUsername)
        val btnLogin = view.findViewById<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener {
            val username = edtUsername.text.toString()
            if (username.isNotBlank()) {
                val action = LoginFragmentDirections.actionLoginFragmentToHomeFragment(username)
                findNavController().navigate(action)
            } else {
                Toast.makeText(requireContext(), "Enter username", Toast.LENGTH_SHORT).show()
            }
        }
    }
}