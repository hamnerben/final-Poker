package com.usu.todosmvvm.models

data class Pastry(
    val id: Int,
    val description: String,
    val completed: Boolean,
    val pastries: Int,
    val clickMultiplier: Int,
    val offLineProduction: Int,

)