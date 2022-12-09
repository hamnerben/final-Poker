package com.usu.todosmvvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.findNavController
import com.usu.todosmvvm.databinding.FragmentUpgradeBinding


class UpgradeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{
        val binding = FragmentUpgradeBinding.inflate(inflater, container, false)
        val viewModel = PastryViewModel()
        //viewModel.update()



        viewModel.pastries.observe(viewLifecycleOwner) {
            if(it!=null){
                binding.clickStrengthUpgradeCost.text = "${it.clickUpgradeCost}"
                binding.autoClickerUpgradeCost.text = "${it.autoClickUpgradeCost}"
                binding.pastryNumberDisplay2.text = "${it.pastries}"
                viewModel.update()

            }


        }


        binding.autoClickerUpgrade.setOnClickListener(){
            viewModel.upgradeAutoClicker()
            viewModel.pastries.observe(viewLifecycleOwner) {
                if(it!=null){
                    binding.autoClickerUpgradeCost.text = "${it.autoClickUpgradeCost}"
                    binding.pastryNumberDisplay2.text = "${it.pastries}"
                }
            }
            viewModel.update()
        }

        binding.clickStrengthUpgrade.setOnClickListener(){
            viewModel.upgradeclick()
            viewModel.pastries.observe(viewLifecycleOwner) {
                if(it!=null){
                    binding.clickStrengthUpgradeCost.text = "${it.clickUpgradeCost}"
                    binding.pastryNumberDisplay2.text = "${it.pastries}"
                }
            }
            viewModel.update()
        }



        binding.goToPastryPage.setOnClickListener(){
            findNavController().navigate(R.id.action_upgradeFragment_to_pastryFragment)
            viewModel.update()
        }
        return binding.root
    }
}