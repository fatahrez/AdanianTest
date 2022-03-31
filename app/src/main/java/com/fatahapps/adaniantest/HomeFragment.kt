package com.fatahapps.adaniantest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
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
        val token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiI5NTAyMTU5My05NzBjLTQzYmYtOTVjMC1lMzg4YTI1OGNkNzAiLCJqdGkiOiI3ZmMwMTA0ZDBkMTg1YzEzZmZiMzdmNTYwZDVkZDEwNDVhZmZmNDM4MmUyOGFlYTNiNTkyMDYyYWJhOThhNTAyOTQxZTM2MDNjZmMzNzZjOSIsImlhdCI6MTY0ODY2NzA0NCwibmJmIjoxNjQ4NjY3MDQ0LCJleHAiOjE2ODAyMDMwNDQsInN1YiI6IjkiLCJzY29wZXMiOltdfQ.jdhjiSR_MtT5Rv152ceqa9QOjvQduMJVtqdN4K3gIcZhLiD7wu1s0EBneFwgb1jXjd2B3IlQ21JSR4nvt7zoDAk9IjrWnKdRHQfZUI3rB2iGtHNbNxcbtF0ZZJ5iAhhPAb8AhGfz2EG_rw8I2sMvin2cTipXG4J1gJcg2l_vMxYMlPHs0kiLjA9Dx2wyQAnl0QrEUuwBHCvUoJlH1BSHKqeFcqgvugfyaU4du1MJmVybsA1euccGihYl75GkP4pREY5K-HveBbYc-OwfCqqMFIJI5snFXipNbgt6vPsGYLCIqKMv9K_vCPBgoMU3AxYyU602GZecgQI_KBGFvn7yll-Z51x_eW0u4v3_JJVXI4BX6j2IBpvF5lMburwmBlFxgiq_8Rhg8yQ3ruILOdZk2QglfHm1i-Ytdh3QM3M3j-bUky8SiVoeK8FFhlYrpT1WuMU_DyeSxwcy9eHuKlPUVB_gGFYoQLdZgdHv0CW5qojpHs3HzyDqPhBnjIYt2GU_8eufmgNEowjkyhQa-3MY8K7FeUuqOERldccw_ZoGb10OWOsWJLsURgeA7C2JodJEzl0uT0kJxCrrGrM4b8nLS7xPNUM6JOw98GW7E1q69JIjMdMyX5OUHi4RC_rvfTVRn484OrdpDriPIw8fvP27j5gObsksiDsYUUCRt5O2F8A"

        getKweaItemsVM.showKweas(token)

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    companion object {
    }
}