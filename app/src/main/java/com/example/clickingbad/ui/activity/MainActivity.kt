package com.example.clickingbad.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.clickingbad.R
import com.example.clickingbad.ui.scenes.manufacturing.ManufacturingViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val sharedViewModel: SharedViewModel by viewModels()
    private val manufacturingViewModel: ManufacturingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.hide()
        setupNavGraph()

        sharedViewModel.saveStatus.observe(this, Observer { status ->
            status?.let {
                Toast.makeText(this, "Game Saved", Toast.LENGTH_SHORT)
                    .show()
            }
        })

//        sharedViewModel.maLive.observe(this, Observer {
//            sharedViewModel.calcConstants()
//        })

        events_notification.isVisible = false
    }

    private fun setupNavGraph() {
        val navController = findNavController(R.id.nav_host_fragment)

        //TODO:"Verificar mais funcionalidade na barra"
        NavigationUI.setupActionBarWithNavController(
            this, navController,
            AppBarConfiguration.Builder(
                R.id.gameFragment,
                R.id.manufacturingFragment,
                R.id.distributionFragment,
                R.id.launderingFragment,
                R.id.upgradesFragment
            ).build()
        )

        NavigationUI.setupWithNavController(bottomNav, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_host_fragment).navigateUp()
    }
}
