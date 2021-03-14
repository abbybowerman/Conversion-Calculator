package com.appdev.conversioncalculator

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import kotlin.math.round


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //get preferred theme from shared preferences
        val sh = getSharedPreferences("MySharedPref", MODE_PRIVATE)
        //if no value is there, it will set the value to default
        when(sh.getInt("mode", 2)){
            0 -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                delegate.applyDayNight()
            }
            1 -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                delegate.applyDayNight()
            }
            2 -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                delegate.applyDayNight()
            }
        }

        //getting spinners, input fields, buttons and instance from Conversions class
        val c = Conversions()
        val changeUnitsButton = findViewById<Button>(R.id.changeUnitsButton)
        val calculateButton = findViewById<Button>(R.id.calculateButton)
        val modeLabel = findViewById<TextView>(R.id.modeLabel)

        val input = findViewById<TextView>(R.id.input)
        val output = findViewById<TextView>(R.id.output)

        val fromSpinner: Spinner = findViewById(R.id.fromUnits)
        val toSpinner: Spinner = findViewById(R.id.toUnits)



        //add units to spinners
        //default is length units
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

        //goes to the change units screen when the button is pressed
        changeUnitsButton.setOnClickListener {
            val intent = Intent(this, ChangeUnits::class.java)
            startActivityForResult(intent, 1)
        }

        //does calculations when the calculate button is pressed
        calculateButton.setOnClickListener {
            val start: Double
            var answer: Double = 0.0
            //if user didn't put anything in the input box, the default value will be 0
            if(input.text.isBlank()){
                input.text = "0.0"
                start = 0.0
            }else{
                //otherwise it will take the input
                start = input.text.toString().toDouble()
            }

            //for length, volume and mass it will check to make sure a negative value
            //wasn't entered
            if(modeLabel.text == "Length"){
                if(start < 0){
                    Toast.makeText(
                        applicationContext,
                        "can't have negative value",
                        Toast.LENGTH_SHORT
                    ).show()
                }else {
                    answer = c.calculateLength(
                        start, fromSpinner.selectedItem.toString(),
                        toSpinner.selectedItem.toString()
                    )
                }
            }else if(modeLabel.text == "Volume"){
                if(start < 0){
                    Toast.makeText(
                        applicationContext,
                        "can't have negative value",
                        Toast.LENGTH_SHORT
                    ).show()
                }else {
                    answer = c.calculateVolume(
                        start, fromSpinner.selectedItem.toString(),
                        toSpinner.selectedItem.toString()
                    )
                }
            }else if(modeLabel.text == "Mass"){
                if(start < 0){
                    Toast.makeText(
                        applicationContext,
                        "can't have negative value",
                        Toast.LENGTH_SHORT
                    ).show()
                }else {
                    answer = c.calculateMass(
                        start, fromSpinner.selectedItem.toString(),
                        toSpinner.selectedItem.toString()
                    )
                }
            }else if(modeLabel.text == "Temperature"){
                answer = c.calculateTemperature(
                    start, fromSpinner.selectedItem.toString(),
                    toSpinner.selectedItem.toString()
                )
            }

            //rounds the answer to 4 decimal places and displays value in output text view
            answer = answer.round(4)
            output.text = answer.toString()
        }
    }

    /**
     * This function creates the settings button at the top of the screen
     * */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    /**
     * This function takes the user to the settings screen when the settings button
     * is pressed.
     * */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.settings -> {
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
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

    /**
     * This function checks which value was selected the the change units screen
     * and displays the appropriate units in the spinner
     * */
    @SuppressLint("SetTextI18n")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 1){
            //gets the spinners and text view
            val fromSpinner: Spinner = findViewById(R.id.fromUnits)
            val toSpinner: Spinner = findViewById(R.id.toUnits)
            val modeLabel = findViewById<TextView>(R.id.modeLabel)

            //sets the default mode to length if it can't find any
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