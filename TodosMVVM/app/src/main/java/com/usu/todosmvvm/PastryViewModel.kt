package com.usu.todosmvvm

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.usu.todosmvvm.models.Pastry
import kotlinx.coroutines.launch
import android.os.CountDownTimer


class PastryViewModel: ViewModel() {
    val pastries = MutableLiveData<Pastry>()
    val errorMessage = MutableLiveData("")





    init{
        loadPastries()
        //counter.start()

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
        }
    }
    
    fun click(){
        if(pastries.value!=null) {
            pastries.value = pastries.value!!.copy(pastries = pastries.value!!.clickPower + pastries.value!!.pastries) //look into later
        }
        //update()
    }

    fun upgradeclick(){
        if(pastries.value!=null) {
            if (pastries.value!!.pastries >= pastries.value!!.clickUpgradeCost) {
                pastries.value = pastries.value!!.copy(clickPower = pastries.value!!.clickPower + 2)
                pastries.value = pastries.value!!.copy(pastries = pastries.value!!.pastries - pastries.value!!.clickUpgradeCost)
                pastries.value = pastries.value!!.copy(clickUpgradeCost = pastries.value!!.clickUpgradeCost * 2)
            }
        }
        //update()
    }

    fun offlineClick(){
        if(pastries.value!=null){

        }

    }

    fun upgradeOfflineproduction(){
        if(pastries.value!=null) {
            if (pastries.value!!.pastries >= pastries.value!!.offLineProductionUpgradeCost) {
                pastries.value = pastries.value!!.copy(offLineProduction = pastries.value!!.offLineProduction + 5)
                pastries.value = pastries.value!!.copy(offLineProductionUpgradeCost = pastries.value!!.offLineProductionUpgradeCost * 3)
                pastries.value = pastries.value!!.copy(pastries = pastries.value!!.pastries - pastries.value!!.offLineProductionUpgradeCost)
            }
        }

    }

    fun upgradeAutoClicker(){
        if(pastries.value!=null) {
            if (pastries.value!!.pastries >= pastries.value!!.autoClickUpgradeCost) {
                pastries.value = pastries.value!!.copy(autoClicker = pastries.value!!.autoClicker + pastries.value!!.autoClickUpgradeCost/10000 + 1)
                pastries.value = pastries.value!!.copy(pastries = pastries.value!!.pastries - pastries.value!!.autoClickUpgradeCost)
                pastries.value = pastries.value!!.copy(autoClickUpgradeCost = pastries.value!!.autoClickUpgradeCost + pastries.value!!.autoClickUpgradeCost /4 )
            }
        }
        //update()
    }

    fun autoClick(){
        if(pastries.value!=null) {
            pastries.value = pastries.value!!.copy(pastries = pastries.value!!.pastries+ pastries.value!!.autoClicker)
        }
    }

}
