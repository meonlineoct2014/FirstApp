package com.example.firstapp.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Habit::class],
    version = 1
)
abstract class HabitDatabase : RoomDatabase() {
    abstract fun getHabitDao(): HabitDao

    companion object {
        @Volatile
        private var instance: HabitDatabase? = null
        private val lock = Any()
        operator fun invoke(context: Context) = instance ?: synchronized(lock) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            HabitDatabase::class.java,
            "habitdatabase"
        ).build()
    }
}