package com.example.traveler

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlin.text.category

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        replaceWithFragment(HomeFragment())
        bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home -> replaceWithFragment(HomeFragment())
                R.id.fevoIcon -> replaceWithFragment(FavoriteFragment())
                R.id.profileIcon -> replaceWithFragment(ProfileFragment())
                else->{

                }
            }
            true

        }
    }
    private fun replaceWithFragment(fragment : Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,fragment)
        fragmentTransaction.commit()
    }
}