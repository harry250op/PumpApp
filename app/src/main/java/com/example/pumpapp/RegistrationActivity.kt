package com.example.pumpapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

    private val TAG="registrationActivity"


class RegistrationActivity: AppCompatActivity()  {
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_getting_data_user)
        var editName: EditText? =findViewById(R.id.editTextName)
        var editAge: EditText? =findViewById(R.id.editTextAge)
        var editWeight: EditText? =findViewById(R.id.editTextNumberWeight)
        var editHeight: EditText? =findViewById(R.id.editTextNumberHeight)
        var button:Button=findViewById<Button>(R.id.button1)



        button.setOnClickListener {}

    }





    }

