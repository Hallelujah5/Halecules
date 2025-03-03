package com.example.week6


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.EditText
import androidx.fragment.app.Fragment

class BottomFragment :Fragment {
    constructor()
    lateinit var txtFullname : EditText


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.bottom_fragment, container, false)
        txtFullname = view.findViewById(R.id.txtFullname)
        return view
    }


    fun showText(firstName: String, lastName: String) {
        txtFullname.setText(firstName + " " + lastName)
        }
    }

