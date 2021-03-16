package com.appdev.conversioncalculator

class Conversions {
    fun calculateLength(to: Double, fromUnit: String, toUnit: String): Double{
        //If the selected units are the same, return the user input
        if(fromUnit == toUnit) return to

        //Current units of length are inches, feet, centimeters, meters, yards, millimeters
        if(fromUnit == "Inches"){
            if(toUnit == "Feet"){
                return to / 12.0
            }else if(toUnit == "Centimeters"){
                return to * 2.54
            }else if(toUnit == "Meters"){
                return to / 39.37
            }else if(toUnit == "Yards"){
                return to / 36.0
            }else if(toUnit == "Millimeters"){
                return to * 25.4
            }
        }else if(fromUnit == "Feet"){
            if(toUnit == "Inches"){
                return to * 12
            }else if(toUnit == "Centimeters"){
                return to * 30.48
            }else if(toUnit == "Meters"){
                return to / 3.281
            }else if(toUnit == "Yards"){
                return to / 3.0
            }else if(toUnit == "Millimeters"){
                return to * 304.8
            }
        }else if(fromUnit == "Centimeters"){
            if(toUnit == "Inches"){
                return to / 2.54
            }else if(toUnit == "Feet"){
                return to / 30.48
            }else if(toUnit == "Meters"){
                return to * 0.01
            }else if(toUnit == "Yards"){
                return to / 91.44
            }else if(toUnit == "Millimeters"){
                return to * 10
            }
        }else if(fromUnit == "Meters"){
            if(toUnit == "Inches"){
                return to * 39.37
            }else if(toUnit == "Feet"){
                return to * 3.281
            }else if(toUnit == "Centimeters"){
                return to * 100
            }else if(toUnit == "Yards"){
                return to * 1.09361
            }else if(toUnit == "Millimeters"){
                return to * 1000
            }
        }else if(fromUnit == "Yards"){
            if(toUnit == "Inches"){
                return to * 36
            }else if(toUnit == "Feet"){
                return to * 3
            }else if(toUnit == "Centimeters"){
                return to * 91.44
            }else if(toUnit == "Meters"){
                return to / 1.09361
            }else if(toUnit == "Millimeters"){
                return to * 914.4
            }
        }else if(fromUnit == "Millimeters"){
            if(toUnit == "Inches"){
                return to / 25.4
            }else if(toUnit == "Feet"){
                return to / 304.8
            }else if(toUnit == "Centimeters"){
                return to * 0.1
            }else if(toUnit == "Meters"){
                return to * 0.001
            }else if(toUnit == "Yards"){
                return to / 914.4
            }
        }

        return 0.0
    }

    fun calculateVolume(to: Double, fromUnit: String, toUnit: String): Double{
        //If the selected units are the same, return the user input
        if(fromUnit == toUnit) return to

        //Current units of volume are liters, gallons, quarts, pints, cups and ounces
        if(fromUnit == "Liters"){
            if(toUnit == "Gallons"){
                return to / 3.78541
            }else if(toUnit == "Quarts"){
                return to * 1.05669
            }else if(toUnit == "Pints"){
                return to * 2.113
            }else if(toUnit == "Cups"){
                return to * 4.167
            }else if(toUnit == "Ounces"){
                return to * 33.184
            }
        }else if(fromUnit == "Gallons"){
            if(toUnit == "Liters"){
                return to * 3.78541
            }else if(toUnit == "Quarts"){
                return to * 4
            }else if(toUnit == "Pints"){
                return to * 8
            }else if(toUnit == "Cups"){
                return to * 16
            }else if(toUnit == "Ounces"){
                return to * 128
            }
        }else if(fromUnit == "Quarts"){
            if(toUnit == "Liters"){
                return to * 0.946353
            }else if(toUnit == "Gallons"){
                return to * 0.25
            }else if(toUnit == "Pints"){
                return to * 2
            }else if(toUnit == "Cups"){
                return to * 4
            }else if(toUnit == "Ounces"){
                return to * 32
            }
        }else if(fromUnit == "Pints"){
            if(toUnit == "Liters"){
                return to * 0.473176
            }else if(toUnit == "Gallons"){
                return to * 0.125
            }else if(toUnit == "Quarts"){
                return to * 0.5
            }else if(toUnit == "Cups"){
                return to * 2
            }else if(toUnit == "Ounces"){
                return to * 16
            }
        }else if(fromUnit == "Cups"){
            if(toUnit == "Liters"){
                return to * 0.236588
            }else if(toUnit == "Gallons"){
                return to / 16.0
            }else if(toUnit == "Quarts"){
                return to / 4.0
            }else if(toUnit == "Pints"){
                return to * 0.5
            }else if(toUnit == "Ounces"){
                return to * 8
            }
        }else if(fromUnit == "Ounces"){
            if(toUnit == "Liters"){
                return to / 33.184
            }else if(toUnit == "Gallons"){
                return to / 128.0
            }else if(toUnit == "Quarts"){
                return to / 32.0
            }else if(toUnit == "Pints"){
                return to / 16.0
            }else if(toUnit == "Cups"){
                return to * 0.125
            }
        }

        return 0.0
    }

    fun calculateMass(to: Double, fromUnit: String, toUnit: String): Double{
        //If the selected units are the same, return the user input
        if(fromUnit == toUnit) return to

        //Current units of mass are pounds and kilograms
        if(fromUnit == "Pounds"){
            return 0.453592 * to
        }else if(fromUnit == "Kilograms"){
            return 2.20462262185 * to
        }

        return 0.0
    }

    fun calculateTemperature(to: Double, fromUnit: String, toUnit: String): Double{
        //If the selected units are the same, return the user input
        if(fromUnit == toUnit) return to

        //Current temperature units are Fahrenheit, Celsius and Kelvin
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