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

    init{
        loadPastries()

    }

    fun loadPastries(){
        viewModelScope.launch {
            //val loadedPastries = PastryRepository.getAllPastries()
            //pastries.addAll(loadedPastries)

        }
    }




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
        if(pastries[0].pastries >= pastries[0].clickUpgradeCost) {
            pastries[0].clickPower *= 2
            pastries[0].autoClickUpgradeCost *= 3
            pastries[0].pastries -= pastries[0].clickUpgradeCost
        }
    }

    fun upgradeOfflineproduction(){
        if(pastries[0].pastries >= pastries[0].offLineProductionUpgradeCost) {
            pastries[0].offLineProduction += 5
            pastries[0].offLineProductionUpgradeCost *= 3
            pastries[0].pastries -= pastries[0].offLineProductionUpgradeCost
        }

    }

    fun upgradeAutoClicker(){
        if(pastries[0].pastries >= pastries[0].autoClickUpgradeCost) {
            pastries[0].autoClicker += (pastries[0].autoClickUpgradeCost / 10000)
            pastries[0].autoClickUpgradeCost += pastries[0].autoClickUpgradeCost / 4
            pastries[0].pastries -= pastries[0].autoClickUpgradeCost
        }
    }

    fun getpastries(): Int {
        return pastries[0].pastries
    }

    fun getUpgradeClickCost(): Int{
        return pastries[0].clickUpgradeCost
    }

    fun getUpgradeAutoClickCost():Int{
        return pastries[0].autoClickUpgradeCost
    }

    fun getUpgradeOfflineProductionCost():Int{
        return pastries[0].offLineProductionUpgradeCost
    }


}