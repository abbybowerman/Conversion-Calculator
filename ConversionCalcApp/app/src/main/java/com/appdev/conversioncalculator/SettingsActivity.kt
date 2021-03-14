package com.appdev.conversioncalculator

import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate


class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        //gets shared preferences to be edited
        val sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE)
        val myEdit = sharedPreferences.edit()

        val saveButton = findViewById<Button>(R.id.saveSettings)
        val group = findViewById<RadioGroup>(R.id.themeGroup)

        //has the system default button checked by default
        group.check(R.id.defaultButton)

        saveButton.setOnClickListener {
            //checks which radio button was selected and applies the appropriate theme
            when (group.checkedRadioButtonId){
                R.id.lightButton -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    delegate.applyDayNight()
                    myEdit.putInt("mode", 0)
                }
                R.id.darkButton -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    delegate.applyDayNight()
                    myEdit.putInt("mode", 1)
                }
                R.id.defaultButton -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                    delegate.applyDayNight()
                    myEdit.putInt("mode", 2)
                }
            }

            //puts the changed preference in shared preferences
            myEdit.apply()
            //closes the settings screen to return to the main screen
            finish()
        }
    }
}