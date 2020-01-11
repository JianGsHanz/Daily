package com.zyh.dagger2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.zyh.dagger2.di.commponent.DaggerActivityCommponent
import com.zyh.dagger2.di.commponent.DaggerAppCommponent
import com.zyh.dagger2.di.module.ActivityModule
import com.zyh.dagger2.di.module.AppModule
import javax.inject.Inject

class MainActivity : FragmentActivity() {

    @Inject
    lateinit var test: Test

    @Inject
    lateinit var context: Context


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MyApp.app!!.activityCommponent.inject(this)

        Log.e("MainActivity",test.name)
        Toast.makeText(this,"MainActivity = ${context.toString()}", Toast.LENGTH_SHORT).show()
    }

    fun onClick(view: View) {
        startActivity(Intent(this,SecondActivity::class.java))
    }

}
