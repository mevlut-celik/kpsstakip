package com.mevlutcelik.kpsstakip.data

import kotlinx.coroutines.flow.Flow

class ExamRepository(private val examDao: ExamDao) {
    val allExams: Flow<List<ExamEntity>> = examDao.getAllExams()
    val recentExams: Flow<List<ExamEntity>> = examDao.getRecentExams()

    suspend fun insertExam(exam: ExamEntity) {
        examDao.insertExam(exam)
    }

    suspend fun deleteExam(exam: ExamEntity) {
        examDao.deleteExam(exam)
    }

    suspend fun getExam(id: Int): ExamEntity? {
        return examDao.getExamById(id)
    }
}
