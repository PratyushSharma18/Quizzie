package com.pratyushvkp.quizzie.utils

object ColorPicker {
    val colors = arrayOf(
        "#DA3535" ,
        "#FF03DAC5",
        "#89D638",
        "#FF018786",
        "#56ACF8",
        "#DDAA9A",
        "#FFBB86FC",
        "#00897B"

    )
    var currentColorIndex = 0;

    fun getColor():String{
        currentColorIndex = (currentColorIndex + 1)% colors.size
        return colors[currentColorIndex]
    }
}