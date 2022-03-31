package com.fatahapps.adaniantest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.preference.PreferenceManager
import com.fatahapps.adaniantest.databinding.FragmentHomeBinding
import com.fatahapps.presentation.viewmodels.kweas.GetKweaItemsState
import com.fatahapps.presentation.viewmodels.kweas.GetKweaItemsVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val getKweaItemsVM: GetKweaItemsVM by viewModels()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        val token = PreferenceManager.getDefaultSharedPreferences(requireContext())
            .getString(LoginFragment.TOKEN, null)

        if (token == null) {
            Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_loginFragment)
        } else {
            getKweaItemsVM.showKweas("Bearer $token")
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