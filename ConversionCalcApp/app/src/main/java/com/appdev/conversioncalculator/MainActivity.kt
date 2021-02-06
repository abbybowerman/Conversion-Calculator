package com.appdev.conversioncalculator

import android.R.attr
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity


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
            startActivityForResult(intent, 1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 1){
            val fromSpinner: Spinner = findViewById(R.id.fromUnits)
            val toSpinner: Spinner = findViewById(R.id.toUnits)
            val m: String = data?.getStringExtra("MODE") ?: "0"
            if(m == "0"){
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
                toSpinner.setSelection(4)
            }else if(m == "1"){
                ArrayAdapter.createFromResource(
                        this,
                        R.array.volume_array,
                        android.R.layout.simple_spinner_item
                ).also { adapter ->
                    // Specify the layout to use when the list of choices appears
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    // Apply the adapter to the spinner
                    fromSpinner.adapter = adapter
                    toSpinner.adapter = adapter
                }
                toSpinner.setSelection(1)
            }else if(m == "2"){
                ArrayAdapter.createFromResource(
                        this,
                        R.array.mass_array,
                        android.R.layout.simple_spinner_item
                ).also { adapter ->
                    // Specify the layout to use when the list of choices appears
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    // Apply the adapter to the spinner
                    fromSpinner.adapter = adapter
                    toSpinner.adapter = adapter
                }
                toSpinner.setSelection(1)
            } else if(m == "3"){
                ArrayAdapter.createFromResource(
                        this,
                        R.array.temperature_array,
                        android.R.layout.simple_spinner_item
                ).also { adapter ->
                    // Specify the layout to use when the list of choices appears
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    // Apply the adapter to the spinner
                    fromSpinner.adapter = adapter
                    toSpinner.adapter = adapter
                }
                toSpinner.setSelection(1)
            }
        }

    }
}