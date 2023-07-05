package com.pratyushvkp.quizzie.utils

import com.pratyushvkp.quizzie.R

object IconPicker {
    val icons = arrayOf(
        R.drawable.icon1,
        R.drawable.icon2,
        R.drawable.icon3,
        R.drawable.icon4,
        R.drawable.icon5,
        R.drawable.icon6,
        R.drawable.icon7,
        R.drawable.icon8
    )
    var currentIcon = 0;

    fun getIcon():Int{
        currentIcon = (currentIcon + 1)% icons.size
        return icons[currentIcon]
    }
}