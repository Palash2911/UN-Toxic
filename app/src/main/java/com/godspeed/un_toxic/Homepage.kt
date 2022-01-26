package com.godspeed.un_toxic

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import android.widget.Toast.makeText
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.godspeed.un_toxic.databinding.ActivityHomepageBinding

class Homepage : AppCompatActivity() {

    private lateinit var binding: ActivityHomepageBinding
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomepageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView


        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment_activity_homepage) as NavHostFragment
        navController = navHostFragment.navController

        
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_progress, R.id.navigation_rewards, R.id.navigation_tips
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
       val intent1 = Intent(this,Profile::class.java)
       val intent2 = Intent(this,MainActivity::class.java)
        when(item.itemId)
        {
            R.id.profile -> startActivity(intent1)
            R.id.logout ->{

                startActivity(intent2)

                 }
        }



        return super.onOptionsItemSelected(item)
    }
}