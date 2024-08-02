package com.nathanhaze.snapcalculator.ui.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nathanhaze.snapcalculator.R
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
        return inflater.inflate(R.layout.fragment_introduction, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(IntroductionViewModel::class.java)
        // TODO: Use the ViewModel
    }

}