package com.dev.joks.countrieslist.screens.main

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.MenuItem
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.dev.joks.countrieslist.R
import com.dev.joks.countrieslist.screens.base.BaseActivity
import org.jetbrains.anko.toast

class MainActivity : BaseActivity() {

    private var firstBackPressed = false
    override fun getLayoutRes(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        findNavController(R.id.nav_host_fragment).addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.countriesFragment -> supportActionBar?.setTitle(R.string.countries)
                R.id.countryDetailsFragment -> supportActionBar?.setTitle(R.string.details)
            }
        }
    }

    override fun onBackPressed() {
        val host = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        when {
            host.navController.currentDestination?.id == R.id.countriesFragment -> {
                if (firstBackPressed) {
                    finish()
                    return
                }

                firstBackPressed = true
                Handler(Looper.getMainLooper()).postDelayed({ firstBackPressed = false }, 2000)
                toast(getString(R.string.press_back_more))
            }
            else -> super.onBackPressed()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == android.R.id.home) {
            onBackPressed()
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}
