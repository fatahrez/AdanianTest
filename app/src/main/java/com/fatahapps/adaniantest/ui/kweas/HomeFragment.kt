package com.fatahapps.adaniantest.ui.kweas

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.preference.PreferenceManager
import com.fatahapps.adaniantest.R
import com.fatahapps.adaniantest.databinding.FragmentHomeBinding
import com.fatahapps.adaniantest.ui.auth.LoginFragment
import com.fatahapps.domain.entities.Resource
import com.fatahapps.presentation.viewmodels.kweas.GetKweaItemsVM
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect

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
            lifecycleScope.launchWhenCreated {
                getKweaItemsVM.showKweas("Bearer $token")
                    .catch { e ->
                        e.message?.let { Snackbar.make(view, it, Snackbar.LENGTH_SHORT).show() }
                    }
                    .collect{ result ->
                        when(result){
                            is Resource.Loading -> {
                                binding.progressBar4.visibility = View.VISIBLE
                            }
                            is Resource.Success -> {
                                binding.progressBar4.visibility = View.GONE
                                Log.i(TAG, "onCreateView: ${result.data}")
                                val kweaListAdapter = result.data?.let {
                                    KweaListAdapter(
                                        requireContext(),
                                        it
                                    )
                                }
                                binding.gridView.adapter = kweaListAdapter
                            }
                            is Resource.Error -> {
                                binding.progressBar4.visibility = View.GONE
                                Snackbar.make(
                                    view,
                                    "Error, check internet connection",
                                    Snackbar.LENGTH_LONG)
                                    .show()
                                val kweaListAdapter = result.data?.let {
                                    KweaListAdapter(
                                        requireContext(),
                                        it
                                    )
                                }
                                binding.gridView.adapter = kweaListAdapter
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
        private const val TAG = "HomeFragment"
    }
}