package com.appdev.conversioncalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val changeUnitsButton = findViewById<Button>(R.id.changeUnitsButton)

        val fromSpinner: Spinner = findViewById(R.id.fromUnits)
        val toSpinner: Spinner = findViewById(R.id.toUnits)

        ArrayAdapter.createFromResource(
                this,
                R.array.length_array,
                android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            fromSpinner.adapter = adapter
            toSpinner.adapter = adapter
        }

        //set the default to value to centimeters
        toSpinner.setSelection(4)

        changeUnitsButton.setOnClickListener {
            val intent = Intent(this, ChangeUnits::class.java)
            startActivity(intent)
        }
    }
}