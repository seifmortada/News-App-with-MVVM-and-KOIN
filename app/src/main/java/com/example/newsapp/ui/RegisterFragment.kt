package com.example.newsapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentRegisterBinding
import com.example.newsapp.models.User
import com.example.newsapp.viewmodel.RegisterViewModel
import org.koin.android.ext.android.inject

class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    private val viewModel: RegisterViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.registerBtn.setOnClickListener {
            if (!checkFields())
                Toast.makeText(
                    requireContext(),
                    "Please enter all fields correct",
                    Toast.LENGTH_SHORT
                ).show()
            else {
                val user =
                    User(
                        name = binding.editEmail.text.toString(),
                        password = binding.editPassword.text.toString()
                    )
                viewModel.addUser(user)
                findNavController()
                    .navigate(R.id.action_registerFragment_to_loginFragment)
            }
        }


    }

    private fun checkFields(): Boolean {
        binding.apply {
            val name = editEmail.text.toString()
            val password = editPassword.text.toString()
            if (name.isEmpty() || password.isEmpty())
                return false
            else
                return true
        }
    }
}