package com.learn.englishadventures.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "progress")
data class ProgressEntity(
    @PrimaryKey val gameType: String,
    val lastCompletedLevel: Int
)
