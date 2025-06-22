package com.example.bankapp_mvvm_register.ui.home

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bankapp_mvvm_register.R
import com.example.bankapp_mvvm_register.data.repository.PostRepository
import com.example.bankapp_mvvm_register.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val repository = PostRepository()
        viewModel = ViewModelProvider(this, HomeViewModelFactory(repository))[HomeViewModel::class.java]

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Check Internet
        if (!isConnected()) {
            binding.tvNoInternet.visibility = View.VISIBLE
            binding.progressBar.visibility = View.GONE
            binding.recyclerView.visibility = View.GONE
            Toast.makeText(requireContext(), "No Internet Connection", Toast.LENGTH_SHORT).show()
        } else {
            binding.tvNoInternet.visibility = View.GONE
            binding.progressBar.visibility = View.VISIBLE
            binding.recyclerView.visibility = View.GONE

            viewModel.posts.observe(viewLifecycleOwner) { posts ->
                binding.progressBar.visibility = View.GONE
                binding.recyclerView.visibility = View.VISIBLE
                binding.recyclerView.adapter = PostAdapter(posts)
            }

            viewModel.fetchPosts()
        }

        // Profile icon
        binding.ivProfile.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
        }
    }

    private fun isConnected(): Boolean {
        val cm = requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo?.isConnectedOrConnecting == true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
