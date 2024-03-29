package com.example.newsapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentLoginBinding
import com.example.newsapp.models.User
import com.example.newsapp.utils.Constants.TAG
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
            allUsers = it
            allUsers.forEach {
                Log.e(TAG, "Users are $it." )
            }
        }
        binding.apply {
            loginBtn.setOnClickListener {
                if (checkValuesAreTheSame())
                    findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
            }
            registerBtn.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
            }
        }
    }

    private fun checkValuesAreTheSame(): Boolean {
        val name = binding.editEmail.text.toString().trim()
        val password = binding.editPassword.text.toString().trim()

        if (allUsers.isEmpty()) {
            Toast.makeText(requireContext(), "There are no users", Toast.LENGTH_SHORT).show()
            return false
        } else {
            var found = false
            for (user in allUsers) {
                if (user.name == name && user.password == password) {
                    found = true
                    break
                }
            }
            if (found) {
                return true
            } else {
                Toast.makeText(requireContext(), "Name or password is wrong", Toast.LENGTH_SHORT).show()
                return false
            }
        }
    }



}