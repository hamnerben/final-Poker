package com.usu.todosmvvm

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.usu.todosmvvm.models.Pastry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

var idCounter = 0

class PastryViewModel: ViewModel() {
    val todos = ObservableArrayList<Pastry>()
    val errorMessage = MutableLiveData("")
    var pastry = Pastry( pastries = 0, clickMultiplier = 1, offLineProduction = 0, autoClicker = 0, clickUpgradeCost = 100, autoClickUpgradeCost = 500, offLineProductionUpgradeCost = 1000)
    fun click(){
        pastry.pastries += 1 * pastry.clickMultiplier
    }

    fun upgradeclick(){
        pastry.clickMultiplier ++
        pastry.autoClickUpgradeCost *= 3
    }

    fun upgradeOfflineproduction(){
        pastry.offLineProduction += 5
        pastry.offLineProductionUpgradeCost *=3
    }

    fun upgradeAutoClicker(){
        pastry.autoClicker += (pastry.autoClickUpgradeCost / 10000)
        pastry.autoClickUpgradeCost += pastry.autoClickUpgradeCost/4
    }

}