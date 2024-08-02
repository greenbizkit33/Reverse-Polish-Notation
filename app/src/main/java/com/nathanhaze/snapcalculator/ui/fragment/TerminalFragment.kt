package com.nathanhaze.snapcalculator.ui.activity.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.nathanhaze.snapcalculator.databinding.FragmentTerminalBinding
import com.nathanhaze.snapcalculator.ui.adapter.TerminalAdapter
import com.nathanhaze.snapcalculator.ui.fragment.viewmodel.TerminalViewModel

class TerminalFragment : Fragment() {

    private lateinit var binding: FragmentTerminalBinding

    private val viewModel: TerminalViewModel by activityViewModels()

    companion object {
        fun newInstance() = TerminalFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentTerminalBinding.inflate(inflater, container, false)

        val terminalAdapter = TerminalAdapter(requireActivity(), listOf<String>())
        binding.rvTerminalInput.adapter = terminalAdapter

        val recyclerViewLayout = LinearLayoutManager(requireActivity())
        binding.rvTerminalInput.layoutManager = recyclerViewLayout

        binding.btnTerminalEnter.setOnClickListener {
            var userInput = binding.etUserTerminalInput.text.toString()
            if (userInput.isNullOrBlank()) {
                return@setOnClickListener
            }
            userInput = userInput.trim()
            if (userInput == "q") {
                requireActivity().finish()
            }
            val added = viewModel.addToStack(userInput = userInput)
            if (added) {
                binding.etUserTerminalInput.text.clear()
            }
        }

        viewModel.terminalOutputList.observe(viewLifecycleOwner) {
            terminalAdapter.setData(it)
            Log.d("nathanx", "got the observer ${it.size}")
            val position = it.size
            terminalAdapter.notifyItemInserted(position);
            binding.rvTerminalInput.smoothScrollToPosition(position)

        }

        viewModel.terminalOutputError.observe(viewLifecycleOwner) { error ->
            error?.let {
                binding.tvErrorMessage.visibility = View.VISIBLE
                binding.tvErrorMessage.text = error.message
            } ?: run {
                binding.tvErrorMessage.visibility = View.GONE
                binding.tvErrorMessage.text = ""
            }
        }
        return binding.root
    }

}