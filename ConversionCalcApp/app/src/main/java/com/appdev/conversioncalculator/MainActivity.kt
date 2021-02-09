package com.appdev.conversioncalculator

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.round


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val c = Conversions()
        val changeUnitsButton = findViewById<Button>(R.id.changeUnitsButton)
        val calculateButton = findViewById<Button>(R.id.calculateButton)
        val modeLabel = findViewById<TextView>(R.id.modeLabel)

        val input = findViewById<TextView>(R.id.input)
        val output = findViewById<TextView>(R.id.output)

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

        calculateButton.setOnClickListener {
            val start: Double
            var answer: Double = 0.0
            if(input.text.isBlank()){
                input.text = "0.0"
                start = 0.0
            }else{
                start = input.text.toString().toDouble()
            }

            if(modeLabel.text == "Length"){
                if(start < 0){
                    Toast.makeText(applicationContext,"can't have negative value",Toast.LENGTH_SHORT).show()
                }else {
                    answer = c.calculateLength(start, fromSpinner.selectedItem.toString(),
                            toSpinner.selectedItem.toString())
                }
            }else if(modeLabel.text == "Volume"){
                if(start < 0){
                    Toast.makeText(applicationContext,"can't have negative value",Toast.LENGTH_SHORT).show()
                }else {
                    answer = c.calculateVolume(start, fromSpinner.selectedItem.toString(),
                            toSpinner.selectedItem.toString())
                }
            }else if(modeLabel.text == "Mass"){
                if(start < 0){
                    Toast.makeText(applicationContext,"can't have negative value",Toast.LENGTH_SHORT).show()
                }else {
                    answer = c.calculateMass(start, fromSpinner.selectedItem.toString(),
                            toSpinner.selectedItem.toString())
                }
            }else if(modeLabel.text == "Temperature"){
                answer = c.calculateTemperature(start, fromSpinner.selectedItem.toString(),
                        toSpinner.selectedItem.toString())
            }

            answer = answer.round(4)
            output.text = answer.toString()
        }
    }

    /**
     * This function was taken directly from this website on 2/8/2021:
     * https://discuss.kotlinlang.org/t/how-do-you-round-a-number-to-n-decimal-places/8843
     * */
    private fun Double.round(decimals: Int): Double {
        var multiplier = 1.0
        repeat(decimals) { multiplier *= 10 }
        return round(this * multiplier) / multiplier
    }

    @SuppressLint("SetTextI18n")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 1){
            val fromSpinner: Spinner = findViewById(R.id.fromUnits)
            val toSpinner: Spinner = findViewById(R.id.toUnits)
            val modeLabel = findViewById<TextView>(R.id.modeLabel)
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
                modeLabel.text = "Length"
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
                modeLabel.text = "Volume"
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
                modeLabel.text = "Mass"
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
                modeLabel.text = "Temperature"
                toSpinner.setSelection(1)
            }
        }

    }
}