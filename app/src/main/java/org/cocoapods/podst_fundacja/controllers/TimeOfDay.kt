package org.cocoapods.podst_fundacja.controllers

import org.cocoapods.podst_fundacja.R
import java.util.*

class TimeOfDay {

    // static methd used for return resources with bacgrounds dependent of day time

    companion object {
        val resources = listOf<Int>(R.drawable.sunrise1, R.drawable.sunrise2, R.drawable.sunrise3,
                R.drawable.day1, R.drawable.day2, R.drawable.day3, R.drawable.sunset1,
                R.drawable.sunset2, R.drawable.sunset3, R.drawable.night1, R.drawable.night2,
                R.drawable.night3)

        fun checkTimeOfDay(screen : Int) : Int{
            val localTime = Calendar.getInstance()
            val hour = localTime.get(Calendar.HOUR_OF_DAY)

            when (hour) {
                in 6..9 -> return resources[(screen-1)]
                in 10..17 -> return resources[(screen-1) + 3]
                in 18..21 -> return resources[(screen-1) + 6]
                else -> return resources[(screen-1) + 9]
            }
        }
    }
}