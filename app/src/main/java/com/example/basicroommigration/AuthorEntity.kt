package com.example.basicroommigration

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AuthorEntity(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val name: String
)
