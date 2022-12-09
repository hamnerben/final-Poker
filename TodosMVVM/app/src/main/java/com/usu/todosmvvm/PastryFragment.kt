package com.usu.todosmvvm

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.usu.todosmvvm.databinding.FragmentPastryBinding



class PastryFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        val binding = FragmentPastryBinding.inflate(inflater, container, false)
        val viewModel = PastryViewModel()
        viewModel.update()
        val counter = object : CountUpTimer(Int.MAX_VALUE, 1) {
            override fun onCount(count: Int) {
                viewModel.autoClick()
            }
            override fun onFinish() {
            }


        }
        counter.start()


        viewModel.pastries.observe(viewLifecycleOwner) {
            if(it!=null){}
            binding.pastryNumberDisplay.text = "${it.pastries}"
            viewModel.update()
        }


        viewModel.errorMessage.observe(viewLifecycleOwner) {errorMessage ->
            binding.errorOutput.text = errorMessage
        }

        binding.goToUpgrade.setOnClickListener {
            findNavController().navigate(R.id.action_pastryFragment_to_upgradeFragment)
            viewModel.update()
        }

        binding.pastryClicker.setOnClickListener(){
            viewModel.click()
            viewModel.pastries.observe(viewLifecycleOwner) {
                if(it!=null){}
                binding.pastryNumberDisplay.text = "${it.pastries}"
            }
            //viewModel.update()
        }






        return binding.root
    }

    abstract class CountUpTimer(private val secondsInFuture: Int, countUpIntervalSeconds: Int) : CountDownTimer(secondsInFuture.toLong() * 1000, countUpIntervalSeconds.toLong() * 1000) {

        abstract fun onCount(count: Int)

        override fun onTick(msUntilFinished: Long) {
            onCount(((secondsInFuture.toLong() * 1000 - msUntilFinished) / 1000).toInt())
        }
    }




}

