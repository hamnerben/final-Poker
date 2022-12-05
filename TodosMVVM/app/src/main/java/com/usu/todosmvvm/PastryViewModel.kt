package com.usu.todosmvvm

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.usu.todosmvvm.models.Pastry
import kotlinx.coroutines.launch

var idCounter = 0

class PastryViewModel: ViewModel() {
    val pastries = ObservableArrayList<Pastry>()
    val errorMessage = MutableLiveData("")

    suspend fun createPastry(){
        viewModelScope.launch{
            var pastry = Pastry(id = 1, pastries = 0, clickPower = 1, offLineProduction = 0, autoClicker = 0, clickUpgradeCost = 100, autoClickUpgradeCost = 500, offLineProductionUpgradeCost = 1000)
            PastryRepository.createPastry(pastry)
            pastries.add(pastry)
        }
    }




    fun click(){
        pastries[0].pastries +=  pastries[0].clickPower
    }

    fun upgradeclick(){
        if(pastry.pastries >= pastry.clickUpgradeCost) {
            pastry.clickPower *= 2
            pastry.autoClickUpgradeCost *= 3
            pastry.pastries -= pastry.clickUpgradeCost
        }
    }

    fun upgradeOfflineproduction(){
        if(pastry.pastries >= pastry.offLineProductionUpgradeCost) {
            pastry.offLineProduction += 5
            pastry.offLineProductionUpgradeCost *= 3
            pastry.pastries -= pastry.offLineProductionUpgradeCost
        }

    }

    fun upgradeAutoClicker(){
        if(pastry.pastries >= pastry.autoClickUpgradeCost) {
            pastry.autoClicker += (pastry.autoClickUpgradeCost / 10000)
            pastry.autoClickUpgradeCost += pastry.autoClickUpgradeCost / 4
            pastry.pastries -= pastry.autoClickUpgradeCost
        }
    }

    fun getpastries(): Int {
        return pastry.pastries
    }

    fun getUpgradeClickCost(): Int{
        return pastry.clickUpgradeCost
    }

}