package com.usu.todosmvvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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

        binding.autoClickerUpgrade.setOnClickListener(){

            //Todo: add the upgrade code
        }
        binding.clickStrengthUpgrade.setOnClickListener(){
            //Todo: add the click strength code
        }
        binding.offlinePercentageUpgrade.setOnClickListener(){
            //Todo: add percentage upgrade

        }

        binding.goToPastryPage.setOnClickListener(){
            findNavController().navigate(R.id.action_upgradeFragment_to_pastryFragment)
        }


        return binding.root
    }
}