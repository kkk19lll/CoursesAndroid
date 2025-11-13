package com.kkk19lll.coursesandroid.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.kkk19lll.coursesandroid.R
import com.kkk19lll.coursesandroid.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupBottomNavigation()
    }

    private fun setupBottomNavigation() {
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {

                }
                R.id.navigation_favorites -> {

                }
                R.id.navigation_account -> {

                }
            }
            true
        }

        binding.bottomNavigationView.setOnItemReselectedListener {
            // Можно добавить логику при повторном нажатии на выбранный элемент
        }
    }
}
