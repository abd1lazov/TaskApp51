package com.example.taskapp51.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.activity.result.ActivityResultLauncher
import com.example.taskapp51.R

class Preferences(context: Context)  {
    private val sharedPreference: SharedPreferences = context.getSharedPreferences("preferences", Context.MODE_PRIVATE)

    fun setBoardingShowed(isShow: Boolean){
        return sharedPreference.edit().putBoolean("board", isShow).apply()

    }

    fun isBoardingShowed(): Boolean{
        return sharedPreference.getBoolean("board",false)
    }

    fun setImageProfile(uri: String?){
        return sharedPreference.edit().putString("image", uri).apply()
    }

    fun getImageProfile(): String?{
        return sharedPreference.getString("image", R.mipmap.ic_launcher.toString())
    }

}
