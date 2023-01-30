package com.example.taskapp51

import android.app.Application
import androidx.room.Room
import com.example.taskapp51.data.local.room.DataBase

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            this, DataBase::class.java,
            "database"
        )
            .allowMainThreadQueries()
            .build()
    }

    companion object {
        lateinit var database: DataBase
    }
}