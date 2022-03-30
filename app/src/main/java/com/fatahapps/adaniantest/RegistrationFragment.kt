package com.fatahapps.adaniantest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.fatahapps.adaniantest.databinding.FragmentRegistrationBinding
import com.fatahapps.domain.entities.Resource
import com.fatahapps.presentation.model.User
import com.fatahapps.presentation.viewmodels.registration.PostUserRegistrationVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegistrationFragment : Fragment() {

    private val postUserRegistrationVM: PostUserRegistrationVM by viewModels()

    private var _binding: FragmentRegistrationBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        val view = binding.root

        binding.signInTextView.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_registrationFragment_to_loginFragment)
        }

        binding.registerButton.setOnClickListener {
            val name = binding.nameEditText.text.toString()
            val otherName = binding.otherNameEditText.text.toString()
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            val passwordConfirmation = binding.confirmPasswordEditText.text.toString()
            val msisdnText = binding.msisdnEditText.text.toString()

            val user = User(name, otherName, email, password, passwordConfirmation, msisdnText)

            postUserRegistrationVM.postUserRegistration(user)

            Navigation.findNavController(view).navigate(R.id.action_registrationFragment_to_loginFragment)
//            if (postUserRegistrationVM.state.value.isLoading) {
//
//            }
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

    }
}