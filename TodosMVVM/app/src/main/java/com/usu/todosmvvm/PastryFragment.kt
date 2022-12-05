package com.usu.todosmvvm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.usu.todosmvvm.databinding.FragmentPastryBinding


class PastryFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentPastryBinding.inflate(inflater, container, false)
        val viewModel = PastryViewModel()

        binding.todosList.adapter = PastryAdapter(viewModel.todos)
        binding.todosList.layoutManager = LinearLayoutManager(context)
        viewModel.errorMessage.observe(viewLifecycleOwner) {errorMessage ->
            binding.errorOutput.text = errorMessage
        }
        binding.goToUpgrade.setOnClickListener {
            findNavController().navigate(R.id.action_pastryFragment_to_upgradeFragment)
        }

        binding.pastryClicker.setOnClickListener(){
            //Todo: add the increment to the button
        }
        return binding.root
    }
}