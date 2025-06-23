package com.example.a33navigationcomponent

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val args: HomeFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<TextView>(R.id.txtWelcome).text = "Welcome, ${args.username}"

        view.findViewById<Button>(R.id.btnLogout).setOnClickListener {
            val navOptions = NavOptions.Builder()
                .setPopUpTo(R.id.loginFragment, true) // Clear back stack up to root
                .build()

            findNavController().navigate(R.id.loginFragment, null, navOptions)
        }
    }
}
