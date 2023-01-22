package com.example.taskapp51.ui.utils

import android.content.Context
import android.content.SharedPreferences

class Preferences(context: Context)  {
    private val sharedPreference: SharedPreferences = context.getSharedPreferences("preferences", Context.MODE_PRIVATE)

    fun setBoardingShowed(isShow: Boolean){
        return sharedPreference.edit().putBoolean("board", isShow).apply()

    }

    fun isBoardingShowed(): Boolean{
        return sharedPreference.getBoolean("board",false)
    }
}
