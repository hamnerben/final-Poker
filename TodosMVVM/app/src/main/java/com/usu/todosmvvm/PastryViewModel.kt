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

class TodosViewModel: ViewModel() {
    val todos = ObservableArrayList<Pastry>()
    val errorMessage = MutableLiveData("")
    fun click(){

    }
    fun createTodo(todoInput: String) {
        errorMessage.value = ""
        if (todoInput.isEmpty()) {
            errorMessage.value = "Todo input cannot be blank."
            viewModelScope.launch {
                //dealy(3000)
                errorMessage.value =""
            }
            return
        }
        if(todoInput.trim().isEmpty()) {
            errorMessage.value = "Todo input must contain at least one alpha numerica character"
            return
        }

        // create todo
        viewModelScope.launch{
            val block = async(Dispatchers.IO) {
            var i = 0;
            while(i<100000){
                i++
                print(i)
            }
            }
        }
        todos.add(Pastry(idCounter++, todoInput, idCounter % 2 == 0, pastries = 0, clickMultiplier = 0, offLineProduction = 0))



    }
}