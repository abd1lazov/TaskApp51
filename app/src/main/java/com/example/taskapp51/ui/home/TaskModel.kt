package com.example.taskapp51.ui.home

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TaskModel(

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,

    val title: String? = null,
    val desc: String? = null
)
