package com.usu.todosmvvm.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Pastry(
    @PrimaryKey val id: Int,
    @ColumnInfo var pastries: Int,
    @ColumnInfo var clickPower: Int,
    @ColumnInfo var offLineProduction: Int,
    @ColumnInfo var autoClicker: Int,
    @ColumnInfo var clickUpgradeCost: Int,
    @ColumnInfo var autoClickUpgradeCost: Int,
    @ColumnInfo var offLineProductionUpgradeCost: Int
    )