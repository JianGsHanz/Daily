package com.zyh.pwdview

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.zyh.pwdview.widget.PasswordInputView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        passwordView.setInputListener {
            toast(it)
        }

        bt_plaintext.setOnClickListener {
            passwordView.setPwdStyle(PasswordInputView.PwdStyle.PLAINTEXT)
        }

        bt_clear.setOnClickListener {
            passwordView.setText(R.string.clear)
        }
    }


}
