package com.example.pumpapp

import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

    private const val TAG="registrationActivity"


class RegistrationActivity: AppCompatActivity()  {
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val databaseUser=baseContext.openOrCreateDatabase("user_info", MODE_PRIVATE, null)
        setContentView(R.layout.activity_getting_data_user)
        var editName: EditText? =findViewById(R.id.editTextName)
        val editAge: EditText? =findViewById(R.id.editTextAge)
        val editWeight: EditText? =findViewById(R.id.editTextNumberWeight)
        val editHeight: EditText? =findViewById(R.id.editTextNumberHeight)
        val button:Button=findViewById<Button>(R.id.button1)
        val checkBoxMen=findViewById<CheckBox>(R.id.checkBoxMale)
        var checkBoxWomen=findViewById<CheckBox>(R.id.checkBoxFemale)
        Log.d(TAG,"OnCreate")





        button.setOnClickListener {
            var sex=""
            val user = ContentValues()
            Log.d(TAG, editName.toString())

            if(checkBoxMen.isChecked)
            {
                sex="man"
            }else sex="woman"
            var tesk=editName?.text.toString()


            user.put("name", editName?.text.toString())
            user.put("age", (editAge?.text.toString()).toInt())
            user.put("sex",sex)
            user.put("weight", (editWeight?.text.toString()).toInt())
            user.put("height", (editHeight?.text.toString()).toInt())
            databaseUser.insert(
                "user",
                null,
                user
            )
            databaseUser.close()


            Log.d(TAG, "The data has been added to database")
            val text="IT WORKS"
            val data=Intent()
            data.data=Uri.parse(editName?.text.toString())
            setResult(RESULT_OK,data)
            finish()

        }

    }





    }

