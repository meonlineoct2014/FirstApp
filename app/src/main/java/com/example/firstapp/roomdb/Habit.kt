package com.example.firstapp.roomdb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Habit(
    val date: String,
    val habitDetails: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}