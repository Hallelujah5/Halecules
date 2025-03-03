package com.example.week6

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat



class Layout_login : AppCompatActivity(), View.OnClickListener {

    lateinit var txtUser : EditText
    lateinit var txtPwd : EditText
    lateinit var btnLogin : Button
    lateinit var btnClose : Button
    lateinit var current_pwd : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.layout_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        current_pwd = "123"
        txtUser = findViewById(R.id.txtUser)
        txtPwd = findViewById(R.id.txtPwd)
        btnLogin = findViewById(R.id.btnLogin)
        btnClose = findViewById(R.id.btnClose)


        btnLogin.setOnClickListener(this)
        btnClose.setOnClickListener(this)

    }




    var getResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == Activity.RESULT_OK){
            var value = it.data?.getStringExtra("Test1")
            current_pwd = value.toString()
            Toast.makeText(this, value, Toast.LENGTH_SHORT).show()
        }
    }



    override fun onClick(v: View?) {
        when (v?.id){
            R.id.btnLogin -> {
                var username = txtUser.text.toString()
                var password = txtPwd.text.toString()


                if (username.equals("Hale") && password.equals(current_pwd)) {
                    val intent = Intent(this, Layout_admin::class.java)
                    intent.putExtra("username", username)
                    intent.putExtra("password", password)

                    getResult.launch(intent)

                }
                else
                    Toast.makeText(this, current_pwd, Toast.LENGTH_SHORT).show()


            }
            R.id.btnClose -> {
                finish();
            }
        }
    }
}