package com.mevlutcelik.kpsstakip.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.mevlutcelik.kpsstakip.data.ExamEntity
import com.mevlutcelik.kpsstakip.data.ExamRepository
import kotlinx.coroutines.launch

class ExamViewModel(private val repository: ExamRepository) : ViewModel() {

    fun addExam(exam: ExamEntity) {
        viewModelScope.launch {
            repository.insertExam(exam)
        }
    }

    fun deleteExam(exam: ExamEntity) {
        viewModelScope.launch {
            repository.deleteExam(exam)
        }
    }

    class Factory(private val repository: ExamRepository) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ExamViewModel::class.java)) {
                return ExamViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
