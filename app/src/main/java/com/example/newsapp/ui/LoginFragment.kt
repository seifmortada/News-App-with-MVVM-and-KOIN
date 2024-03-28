package com.example.newsapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentLoginBinding
import com.example.newsapp.models.User
import com.example.newsapp.viewmodel.RegisterViewModel
import org.koin.android.ext.android.inject

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val viewModel: RegisterViewModel by inject()

    private lateinit var allUsers: List<User>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.allUsers.observe(viewLifecycleOwner) {
            if (it.isNullOrEmpty()) allUsers = emptyList() else allUsers = it
        }
        binding.apply {
            loginBtn.setOnClickListener {
                checkValuesAreTheSame()
            }
            registerBtn.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
            }
        }
    }

    private fun checkValuesAreTheSame() {
        binding.apply {
            val name = editEmail.text.toString()
            val password = editPassword.text.toString()
            if (allUsers.isEmpty()) {
                Toast.makeText(requireContext(), "There are no users", Toast.LENGTH_SHORT).show()
                return
            } else {
                allUsers.forEach {
                    if (it.name == name && it.password == password) {
                        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                        return
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "name or password is wrong",
                            Toast.LENGTH_SHORT
                        ).show()
                        return
                    }
                }
            }
        }
    }

}