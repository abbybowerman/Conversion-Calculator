package com.appdev.conversioncalculator

class Conversions {
    fun calculateLength(to: Double, fromUnit: String, toUnit: String): Double{
        if(fromUnit == toUnit) return to

        return 0.0
    }

    fun calculateVolume(to: Double, fromUnit: String, toUnit: String): Double{
        if(fromUnit == toUnit) return to

        return 0.0
    }

    fun calculateMass(to: Double, fromUnit: String, toUnit: String): Double{
        if(fromUnit == toUnit) return to

        if(fromUnit == "Pounds"){
            return 0.453592 * to
        }else if(fromUnit == "Kilograms"){
            return 2.20462262185 * to
        }

        return 0.0
    }

    fun calculateTemperature(to: Double, fromUnit: String, toUnit: String): Double{
        if(fromUnit == toUnit) return to

        if(fromUnit == "Fahrenheit"){
            if(toUnit == "Celsius"){
                return ((to - 32) * (5.0/9.0))
            }else if(toUnit == "Kelvin"){
                return ((to - 32) * (5.0/9.0) + 273.15)
            }
        }else if(fromUnit == "Celsius"){
            if(toUnit == "Fahrenheit"){
                return (to * (9.0/5.0)) + 32
            }else if(toUnit == "Kelvin"){
                return to + 273.15
            }
        }else if(fromUnit == "Kelvin"){
            if(toUnit == "Fahrenheit"){
                return ((to - 273.15) * (9.0/5.0)) + 32
            }else if(toUnit == "Celsius"){
                return to - 273.15
            }
        }
        return 0.0
    }
}