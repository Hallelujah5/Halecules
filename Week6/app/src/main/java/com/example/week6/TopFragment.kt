package com.example.week6

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment

class TopFragment :Fragment {
    constructor()

    lateinit var txtFirstName : EditText
    lateinit var txtLastName : EditText
    lateinit var btnOk : Button
    lateinit var mainActivity: Main_Fragment_Activity


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        return super.onCreateView(inflater, container, savedInstanceState)
        val view: View = inflater.inflate(R.layout.top_fragment, container, false)
        txtFirstName = view.findViewById(R.id.txtFirstName)
        txtLastName = view.findViewById(R.id.txtLastName)
        btnOk = view.findViewById(R.id.btnOk)

        btnOk.setOnClickListener(View.OnClickListener {
            val firstName = txtFirstName.getText().toString();
            val lastName = txtLastName.getText().toString();
            mainActivity.showText(firstName, lastName)
        })
        return view

    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is Main_Fragment_Activity){
            mainActivity = context
        }
    }
}