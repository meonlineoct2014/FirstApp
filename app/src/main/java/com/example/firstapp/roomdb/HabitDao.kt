package com.example.firstapp.roomdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface HabitDao {
    @Insert
    suspend fun addHabit(habit: Habit)

    @Query("SELECT * FROM habit")
    suspend fun getHabits(): List<Habit>

}