package com.example.trudoctask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val navController: NavController by lazy {
        Navigation.findNavController(this, R.id.nav_host_fragment)
    }
    lateinit var toolbar: androidx.appcompat.widget.Toolbar

    lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolBar)
    }

}