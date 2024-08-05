package com.nathanhaze.snapcalculator.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.nathanhaze.snapcalculator.R
import com.nathanhaze.snapcalculator.databinding.FragmentIntroductionBinding
import com.nathanhaze.snapcalculator.ui.fragment.viewmodel.IntroductionViewModel
import com.nathanhaze.snapcalculator.util.UserPreference

class IntroductionFragment : Fragment() {

    private val viewModel: IntroductionViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentIntroductionBinding.inflate(inflater, container, false)

        binding.btnNext.setOnClickListener {
            val navController =
                requireActivity().findNavController(R.id.nav_host_fragment_content_single)
            navController.navigate(R.id.TerminalFragment)
        }

        UserPreference.getInstance(requireActivity()).isFirstTimeUser = false
        return binding.root
    }
}