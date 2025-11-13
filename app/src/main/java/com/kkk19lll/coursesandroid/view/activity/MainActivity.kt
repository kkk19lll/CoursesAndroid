package com.kkk19lll.coursesandroid.view.activity

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.kkk19lll.coursesandroid.R
import com.kkk19lll.coursesandroid.databinding.ActivityMainBinding
import com.kkk19lll.coursesandroid.view.fragment.AccountFragment
import com.kkk19lll.coursesandroid.view.fragment.FavoritesFragment
import com.kkk19lll.coursesandroid.view.fragment.HomeFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val homeFragment = HomeFragment()
    private val favoritesFragment = FavoritesFragment()
    private val accountFragment = AccountFragment()

    private var activeFragment: Fragment = homeFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupFragments()
        setupBottomNavigation()
        setupBackPress()
    }

    private fun setupFragments() {
        supportFragmentManager.beginTransaction()
            .add(R.id.frameLayout, accountFragment, "account")
            .hide(accountFragment)
            .add(R.id.frameLayout, favoritesFragment, "favorites")
            .hide(favoritesFragment)
            .add(R.id.frameLayout, homeFragment, "home")
            .commit()
    }

    private fun setupBottomNavigation() {
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> switchFragment(homeFragment)
                R.id.navigation_favorites -> switchFragment(favoritesFragment)
                R.id.navigation_account -> switchFragment(accountFragment)
            }
            true
        }
    }

    private fun switchFragment(target: Fragment) {
        if (activeFragment == target) return

        supportFragmentManager.beginTransaction()
            .hide(activeFragment)
            .show(target)
            .commit()

        activeFragment = target
    }

    private fun setupBackPress() {
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (activeFragment != homeFragment) {
                    binding.bottomNavigationView.selectedItemId = R.id.navigation_home
                    switchFragment(homeFragment)
                } else {
                    finish()
                }
            }
        })
    }
}
