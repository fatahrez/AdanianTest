package com.fatahapps.adaniantest.ui.auth

import android.os.Bundle
import androidx.preference.PreferenceManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.fatahapps.adaniantest.R
import com.fatahapps.adaniantest.databinding.FragmentLoginBinding
import com.fatahapps.domain.entities.Resource
import com.fatahapps.presentation.viewmodels.SignIn.PostSignInVM
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.catch

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private val postUserSignInVM: PostSignInVM by viewModels()

    private var _binding: FragmentLoginBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root

        val token = PreferenceManager.getDefaultSharedPreferences(requireContext())
            .getString(TOKEN, null)

//        if (token != null) {
//            Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_homeFragment)
//        }

        binding.signUpTextView.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_registrationFragment)
        }

        binding.signInButton.setOnClickListener {
            val email = binding.emailSignInEditText.text.toString()
            val password = binding.passwordSignInEditText.text.toString()

            lifecycleScope.launchWhenStarted {
                postUserSignInVM.postUserSign(email, password)
                    .catch { e ->
                        e.message?.let { it1 ->
                            Snackbar.make(view,
                                it1, Snackbar.LENGTH_SHORT).show()
                        }
                    }.collect { result ->
                        when(result) {
                            is Resource.Loading -> {
                                binding.progressBar2.visibility = View.VISIBLE
                            }
                            is Resource.Success -> {
                                binding.progressBar2.visibility = View.GONE
                                PreferenceManager.getDefaultSharedPreferences(requireContext())
                                    .edit().putString(TOKEN, result.data?.token).apply()
                                Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_homeFragment)
                            }
                            is Resource.Error -> {
                                binding.progressBar2.visibility = View.GONE
                                result.message?.let { it1 -> Snackbar.make(view, it1, Snackbar.LENGTH_SHORT).show() }
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
        const val TOKEN = "TOKEN"
    }
}