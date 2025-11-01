package com.example.traveler

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.traveler.fragments.ExploreFragment
import com.example.traveler.fragments.FavoriteFragment
import com.example.traveler.fragments.HomeFragment
import com.example.traveler.fragments.ProfileFragment
import com.example.traveler.fragments.SearchFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

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
                R.id.search -> replaceWithFragment(SearchFragment())
                R.id.explore -> replaceWithFragment(ExploreFragment())
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