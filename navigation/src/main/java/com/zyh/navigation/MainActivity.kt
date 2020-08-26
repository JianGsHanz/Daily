package com.zyh.navigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //fix navigation返回重新创建
        val controller = Navigation.findNavController(this, R.id.fragment)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
        val fragmentNavigator =
            FixFragmentNavigator(this, navHostFragment.childFragmentManager, navHostFragment.id)
        controller.navigatorProvider.addNavigator(fragmentNavigator)
        controller.setGraph(R.navigation.nav_graph)
    }

}
