package com.appdev.conversioncalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner

class ChangeUnits : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_units)

        val spinner = findViewById<Spinner>(R.id.types)
        val saveButton = findViewById<Button>(R.id.saveButton)

        //Adds the array of measurements to the spinner
        ArrayAdapter.createFromResource(
            this,
            R.array.measurement_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            //Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            //Apply the adapter to the spinner
            spinner.adapter = adapter
        }

        saveButton.setOnClickListener {
            val intent = Intent()
            //passes the selected type of measurement back to the main screen
            intent.putExtra("MODE", spinner.selectedItemPosition.toString())
            setResult(1, intent)
            //closes units activity
            finish()
        }
    }
}