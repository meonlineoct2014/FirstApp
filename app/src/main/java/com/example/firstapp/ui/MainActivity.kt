package com.example.firstapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.firstapp.MainViewModel
import com.example.firstapp.R

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.termsAndConditions -> {
                /*startActivity(
                    Intent(
                        this,
                        TermsAndConditionsActivity::class.java
                    )
                )*/
                var value = viewModel.selectedItem.value
                if (value != null) {
                    value += 10
                    viewModel.changeValue(value)
                    viewModel.printValue()
                }

                return true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }


}


