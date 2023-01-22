package com.example.taskapp51.ui.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.activity.result.ActivityResultLauncher

class Preferences(context: Context)  {
    private val sharedPreference: SharedPreferences = context.getSharedPreferences("preferences", Context.MODE_PRIVATE)

    fun setBoardingShowed(isShow: Boolean){
        return sharedPreference.edit().putBoolean("board", isShow).apply()

    }

    fun isBoardingShowed(): Boolean{
        return sharedPreference.getBoolean("board",false)
    }

    fun isImageUsers(): String? {
        return sharedPreference.getString("imageUsers", null)
    }

    fun saveUsernameProfile(name: String?) {
        sharedPreference.edit().putString("username", name).apply()
    }

    fun saveImageUsers(uri: ActivityResultLauncher<String>) {
        sharedPreference.edit().putString("imageUsers", uri.toString()).apply()
    }
}
