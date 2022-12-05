package com.usu.todosmvvm.models

data class Pastry(
    var pastries: Int,
    var clickMultiplier: Int,
    var offLineProduction: Int,
    var autoClicker: Int,
    var clickUpgradeCost: Int,
    var autoClickUpgradeCost: Int,
    var offLineProductionUpgradeCost: Int
    )