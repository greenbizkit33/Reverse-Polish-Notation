package com.nathanhaze.snapcalculator.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.nathanhaze.snapcalculator.R
import com.nathanhaze.snapcalculator.databinding.FragmentIntroductionBinding
import com.nathanhaze.snapcalculator.ui.fragment.viewmodel.IntroductionViewModel

class IntroductionFragment : Fragment() {

    companion object {
        fun newInstance() = IntroductionFragment()
    }

    private lateinit var viewModel: IntroductionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentIntroductionBinding.inflate(inflater, container, false)

        binding.btnNext.setOnClickListener {
            val navController =
                requireActivity().findNavController(R.id.nav_host_fragment_content_single)
            navController.navigate(R.id.TerminalFragment)
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(IntroductionViewModel::class.java)
        // TODO: Use the ViewModel
    }

}