package com.usu.todosmvvm

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.usu.todosmvvm.models.Todo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

var idCounter = 0

class TodosViewModel: ViewModel() {
    val todos = ObservableArrayList<Todo>()
    val errorMessage = MutableLiveData("")
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
        todos.add(Todo(idCounter++, todoInput, idCounter % 2 == 0))



    }
}