package com.example.a51_viewmodellivedata


/**Created by Raviteja Emandi on 24,June,2025 **/
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.a51_viewmodellivedata.databinding.FragmentEditProfileBinding

class EditProfileFragment : Fragment() {

    private lateinit var binding: FragmentEditProfileBinding
    private val viewModel: ProfileViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.user.observe(viewLifecycleOwner) { user ->
            binding.etName.setText(user.name)
            binding.etEmail.setText(user.email)
            binding.etAge.setText(user.age.toString())
        }

        binding.btnSave.setOnClickListener {
            val name = binding.etName.text.toString()
            val email = binding.etEmail.text.toString()
            val age = binding.etAge.text.toString().toIntOrNull() ?: 0

            viewModel.updateUser(name, email, age)
            parentFragmentManager.popBackStack()
        }
    }
}
