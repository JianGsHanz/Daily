package com.zyh.dagger2

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import javax.inject.Inject

class SecondActivity : AppCompatActivity() {

    @Inject
    lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        MyApp.app.activityCommponent.inject(this)

        Toast.makeText(this,"SecondActivity = ${context.toString()}",Toast.LENGTH_SHORT).show()
    }
}
