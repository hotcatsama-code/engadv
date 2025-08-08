package com.learn.englishadventures.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ProgressDao {
    @Query("SELECT * FROM progress WHERE gameType = :gameType LIMIT 1")
    fun observeProgress(gameType: String): Flow<ProgressEntity?>

    @Query("SELECT * FROM progress WHERE gameType = :gameType LIMIT 1")
    suspend fun getProgress(gameType: String): ProgressEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(entity: ProgressEntity)
}
