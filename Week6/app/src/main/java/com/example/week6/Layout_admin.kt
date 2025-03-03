package com.example.week6

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat



class Layout_admin : AppCompatActivity(), View.OnClickListener {
    lateinit var txtOldPwd : EditText
    lateinit var txtNewPwd : EditText
    lateinit var txtConfirmPwd : EditText
    lateinit var btnSave : Button

    lateinit var currentPwd : String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.layout_admin)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        txtOldPwd = findViewById(R.id.txtOldPwd)
        txtNewPwd = findViewById(R.id.txtNewPwd)
        txtConfirmPwd = findViewById(R.id.txtConfirmPwd)
        btnSave = findViewById(R.id.btnSave)

        btnSave.setOnClickListener(this)

        var  PASSWORD = intent.getStringExtra("password")
        currentPwd = PASSWORD.toString()


        }

    override fun onClick(v: View?) {
        when (v?.id){
            R.id.btnSave -> {
                var oldPwd = txtOldPwd.text.toString()
                var newPwd = txtNewPwd.text.toString()
                var confirmPwd = txtConfirmPwd.text.toString()

                if (oldPwd.equals(currentPwd) && newPwd.equals(confirmPwd)){
//                  Toast.makeText(this, "success", Toast.LENGTH_SHORT).show()

                var intent = Intent();
                intent.putExtra("Test1", newPwd.toString())
                intent.putExtra("Test2", "value2")
                setResult(RESULT_OK, intent)

                finish();
            }
                else {
                    Toast.makeText(this, currentPwd, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}