package com.example.basicroommigration

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BookEntity(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val title: String,
    @ColumnInfo(name = "sub_title", defaultValue = "") val subTitle: String,
    val description: String
)
