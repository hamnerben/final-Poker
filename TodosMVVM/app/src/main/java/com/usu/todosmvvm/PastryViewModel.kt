package com.usu.todosmvvm

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.usu.todosmvvm.models.Pastry

var idCounter = 0

class PastryViewModel: ViewModel() {
    val todos = ObservableArrayList<Pastry>()
    val errorMessage = MutableLiveData("")

    var pastry = Pastry(id = 1, pastries = 0, clickPower = 1, offLineProduction = 0, autoClicker = 0, clickUpgradeCost = 100, autoClickUpgradeCost = 500, offLineProductionUpgradeCost = 1000)

    fun click(){
        pastry.pastries +=  pastry.clickPower
    }

    fun upgradeclick(){
        pastry.clickPower *= 2
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

    fun getpastries(): Int {
        return pastry.pastries
    }

}