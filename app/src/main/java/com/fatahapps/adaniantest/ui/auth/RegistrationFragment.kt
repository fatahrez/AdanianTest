package com.fatahapps.adaniantest.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.fatahapps.adaniantest.R
import com.fatahapps.adaniantest.databinding.FragmentRegistrationBinding
import com.fatahapps.domain.entities.Resource
import com.fatahapps.presentation.model.User
import com.fatahapps.presentation.viewmodels.registration.PostUserRegistrationVM
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.catch

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
        // Inflate the layout for this fragment
        _binding = FragmentRegistrationBinding.inflate(inflater, container, false)
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

            lifecycleScope.launchWhenStarted {
                postUserRegistrationVM.postUserRegistration(user)
                    .catch {e ->
                        e.message?.let { it1 ->
                            Snackbar.make(view,
                                it1, Snackbar.LENGTH_SHORT).show()
                        }
                    }.collect { result ->
                        when(result) {
                            is Resource.Success -> {
                                binding.progressBar.visibility = View.GONE
                                Navigation.findNavController(view).navigate(R.id.action_registrationFragment_to_loginFragment)
                            }
                            is Resource.Loading -> {
                                binding.progressBar.visibility = View.VISIBLE
                            }
                            is Resource.Error -> {
                                binding.progressBar.visibility = View.GONE
                                Snackbar.make(view, result.data?.errors?.get(0).toString(), Snackbar.LENGTH_SHORT).show()
                            }
                        }
                    }
            }
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val TAG = "RegistrationFragment"
    }
}