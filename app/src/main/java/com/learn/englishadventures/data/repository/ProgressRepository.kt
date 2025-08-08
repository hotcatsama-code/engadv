package com.learn.englishadventures.data.repository

import com.learn.englishadventures.data.database.AppDatabase
import com.learn.englishadventures.data.database.ProgressEntity
import kotlinx.coroutines.flow.Flow

class ProgressRepository(private val db: AppDatabase) {
    fun observe(gameType: String) = db.progressDao().observeProgress(gameType)

    suspend fun update(gameType: String, lastCompletedLevel: Int) {
        db.progressDao().upsert(ProgressEntity(gameType, lastCompletedLevel))
    }
}
