package com.example.paging.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.paging.R
import kotlinx.android.synthetic.main.activity_select.*

class SelectActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select)

        goLocal.setOnClickListener { startActivity(Intent(this@SelectActivity,
            MainActivity::class.java)) }

        goRv.setOnClickListener { startActivity(Intent(this@SelectActivity,
            Main2Activity::class.java)) }

        goNetWork.setOnClickListener { startActivity(Intent(this@SelectActivity,
            Main3Activity::class.java)) }
    }
}
