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
    val counter = object : CountUpTimer(100, 1) {

        override fun onCount(count: Int) {
            autoClick()
            viewModelScope.launch{
                updatePastry()
            }
        }

        override fun onFinish() {
        }
    }


    init{
        loadPastries()
        counter.start()
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
    }

    fun upgradeclick(){
        if(pastries.value!=null) {
            if (pastries.value!!.pastries >= pastries.value!!.clickUpgradeCost) {
                pastries.value = pastries.value!!.copy(clickPower = pastries.value!!.clickPower + 2)

                pastries.value = pastries.value!!.copy(pastries = pastries.value!!.pastries - pastries.value!!.clickUpgradeCost)
                //pastries[0].pastries -= pastries[0].clickUpgradeCost
                pastries.value = pastries.value!!.copy(clickUpgradeCost = pastries.value!!.clickUpgradeCost * 2)
            }
        }
    }

    fun offlineClick(){
        if(pastries.value!=null){

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
                pastries.value = pastries.value!!.copy(autoClicker = pastries.value!!.autoClicker + pastries.value!!.autoClickUpgradeCost/10000 + 1)
                //pastries[0].autoClicker += (pastries[0].autoClickUpgradeCost / 10000)
                pastries.value = pastries.value!!.copy(pastries = pastries.value!!.pastries - pastries.value!!.autoClickUpgradeCost)
                //pastries[0].pastries -= pastries[0].autoClickUpgradeCost
                pastries.value = pastries.value!!.copy(autoClickUpgradeCost = pastries.value!!.autoClickUpgradeCost + pastries.value!!.autoClickUpgradeCost /4 )
                //pastries[0].autoClickUpgradeCost += pastries[0].autoClickUpgradeCost / 4


            }
        }
    }

    fun autoClick(){
        if(pastries.value!=null) {
            pastries.value = pastries.value!!.copy(pastries = pastries.value!!.pastries+ pastries.value!!.autoClicker)
        }


    }
    abstract class CountUpTimer(private val secondsInFuture: Int, countUpIntervalSeconds: Int) : CountDownTimer(secondsInFuture.toLong() * 1000, countUpIntervalSeconds.toLong() * 1000) {

        abstract fun onCount(count: Int)

        override fun onTick(msUntilFinished: Long) {
            onCount(((secondsInFuture.toLong() * 1000 - msUntilFinished) / 1000).toInt())
        }
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
