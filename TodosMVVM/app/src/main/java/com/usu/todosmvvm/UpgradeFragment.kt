package com.usu.todosmvvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.usu.todosmvvm.databinding.FragmentPastryBinding
import com.usu.todosmvvm.databinding.FragmentUpgradeBinding

class UpgradeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{
        val binding = FragmentUpgradeBinding.inflate(inflater, container, false)
        val viewModel = TodosViewModel()


        return binding.root
    }
}