package com.zyh.databinding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_second.*


class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        //navigation
        val hostFragment =
            supportFragmentManager.findFragmentById(R.id.second_fragment) as NavHostFragment
        val controller = hostFragment.navController

        bnv_view.setupWithNavController(controller)
    }

}
