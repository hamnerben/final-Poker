package com.usu.todosmvvm

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.usu.todosmvvm.models.Pastry
import kotlinx.coroutines.launch

var idCounter = 0

class PastryViewModel: ViewModel() {
    val pastries = MutableLiveData<Pastry>()
    val errorMessage = MutableLiveData("")

    init{
        loadPastries()
    }

    fun loadPastries(){
        viewModelScope.launch {
            val loadedPastries = PastryRepository.getAllPastries()

            //if empty load data if not empty create pastry
            if(loadedPastries.isEmpty()){
                createPastry()
            }
            else {
                pastries.value = loadedPastries[0]
            }


        }
    }
    fun update(){
        viewModelScope.launch{
            updatePastry()
        }
    }




    suspend fun createPastry(){
        viewModelScope.launch{
            var pastry = Pastry(id = 1, pastries = 0, clickPower = 1, offLineProduction = 0, autoClicker = 0, clickUpgradeCost = 100, autoClickUpgradeCost = 500, offLineProductionUpgradeCost = 1000)
            PastryRepository.createPastry(pastry)
            pastries.value = pastry
        }
    }
    suspend fun updatePastry(){
        viewModelScope.launch {
            pastries.value?.let { PastryRepository.updatePastryCount(pastry = it) }
            //PastryDao.updatePastry(pastries

        }
    }
    
    fun click(){
        if(pastries.value!=null) {
            pastries.value = pastries.value!!.copy(pastries = pastries.value!!.clickPower + pastries.value!!.pastries) //look into later


        }
    }

    fun upgradeclick(){
        if(pastries.value!=null) {
            if (pastries.value!!.pastries >= pastries.value!!.clickUpgradeCost) {
                pastries.value = pastries.value!!.copy(clickPower = pastries.value!!.clickPower + 2)
                //pastries[0].clickPower *= 2

                //pastries[0].autoClickUpgradeCost *= 3
                pastries.value = pastries.value!!.copy(pastries = pastries.value!!.pastries - pastries.value!!.clickUpgradeCost)
                //pastries[0].pastries -= pastries[0].clickUpgradeCost
                pastries.value = pastries.value!!.copy(clickUpgradeCost = pastries.value!!.clickUpgradeCost * 2)
            }
        }
    }

    fun upgradeOfflineproduction(){
        if(pastries.value!=null) {
            if (pastries.value!!.pastries >= pastries.value!!.offLineProductionUpgradeCost) {
                pastries.value = pastries.value!!.copy(offLineProduction = pastries.value!!.offLineProduction + 5)
                //pastries[0].offLineProduction += 5
                pastries.value = pastries.value!!.copy(offLineProductionUpgradeCost = pastries.value!!.offLineProductionUpgradeCost * 3)
                //pastries[0].offLineProductionUpgradeCost *= 3
                pastries.value = pastries.value!!.copy(pastries = pastries.value!!.pastries - pastries.value!!.offLineProductionUpgradeCost)
                //pastries[0].pastries -= pastries[0].offLineProductionUpgradeCost
            }
        }

    }

    fun upgradeAutoClicker(){
        if(pastries.value!=null) {
            if (pastries.value!!.pastries >= pastries.value!!.autoClickUpgradeCost) {
                pastries.value = pastries.value!!.copy(autoClicker = pastries.value!!.pastries + pastries.value!!.autoClickUpgradeCost/10000)
                //pastries[0].autoClicker += (pastries[0].autoClickUpgradeCost / 10000)
                pastries.value = pastries.value!!.copy(autoClickUpgradeCost = pastries.value!!.autoClickUpgradeCost + pastries.value!!.autoClickUpgradeCost /4 )
                //pastries[0].autoClickUpgradeCost += pastries[0].autoClickUpgradeCost / 4
                pastries.value = pastries.value!!.copy(pastries = pastries.value!!.pastries - pastries.value!!.autoClickUpgradeCost)
                //pastries[0].pastries -= pastries[0].autoClickUpgradeCost
            }
        }
    }

    fun getpastries(): Int {
        return pastries.value!!.pastries
    }

    fun getUpgradeClickCost(): Int{
        return pastries.value!!.clickUpgradeCost
    }

    fun getUpgradeAutoClickCost():Int{
        return pastries.value!!.autoClickUpgradeCost
    }

    fun getUpgradeOfflineProductionCost():Int{
        return pastries.value!!.offLineProductionUpgradeCost
    }


}