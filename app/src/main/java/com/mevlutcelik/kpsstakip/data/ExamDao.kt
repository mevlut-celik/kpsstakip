package com.mevlutcelik.kpsstakip.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ExamDao {
    @Query("SELECT * FROM exams ORDER BY date DESC")
    fun getAllExams(): Flow<List<ExamEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExam(exam: ExamEntity)

    @Delete
    suspend fun deleteExam(exam: ExamEntity)

    @Query("SELECT * FROM exams WHERE id = :id")
    suspend fun getExamById(id: Int): ExamEntity?

    // For analysis: Get last 5 exams for quick summary
    @Query("SELECT * FROM exams ORDER BY date DESC LIMIT 5")
    fun getRecentExams(): Flow<List<ExamEntity>>
}
