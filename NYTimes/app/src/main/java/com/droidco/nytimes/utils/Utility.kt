package com.droidco.nytimes.utils

object Utility {


    fun isStringValid(text: String?): Boolean {

        if (text == null) {
            return false
        }

        val trimmedStringToValidate: String = text.trim()
        return (trimmedStringToValidate.isNotEmpty()
                && !trimmedStringToValidate.equals("null", ignoreCase = true)
                && !trimmedStringToValidate.equals("None", ignoreCase = true))
    }
}