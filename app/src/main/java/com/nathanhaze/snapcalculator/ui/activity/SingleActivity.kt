package com.nathanhaze.snapcalculator.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import com.nathanhaze.snapcalculator.R
import com.nathanhaze.snapcalculator.databinding.ActivitySingleBinding
import com.nathanhaze.snapcalculator.ui.util.UserPreference


class SingleActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivitySingleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySingleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = findNavController(R.id.nav_host_fragment_content_single)
        appBarConfiguration = AppBarConfiguration(navController.graph)

        if (UserPreference.getInstance(this).isFirstTimeUser) {
            navController.navigate(R.id.IntroductionFragment)
        }
    }

//    override fun onSupportNavigateUp(): Boolean {
//        val navController = findNavController(R.id.nav_host_fragment_content_single)
//        return navController.navigateUp(appBarConfiguration)
//                || super.onSupportNavigateUp()
//    }
}