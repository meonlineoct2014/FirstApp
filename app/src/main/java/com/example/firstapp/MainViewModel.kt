package com.example.firstapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val mutableSelectedItem = MutableLiveData<Int>()
    val selectedItem: LiveData<Int> get() = mutableSelectedItem

    init {
        mutableSelectedItem.value = 90
    }

    fun changeValue(newVal: Int) {
        mutableSelectedItem.value = newVal
    }

    fun printValue() {
        println("The mutableSelectedItem = ${mutableSelectedItem.value} ")
    }


}