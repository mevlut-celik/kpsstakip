package com.mevlutcelik.kpsstakip.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.mevlutcelik.kpsstakip.data.ExamEntity
import com.mevlutcelik.kpsstakip.data.ExamRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class SubjectTime(
    val name: String,
    var startTime: Long = 0,
    var totalTime: Int = 0,
    var isCompleted: Boolean = false
)

class TimerViewModel(private val repository: ExamRepository) : ViewModel() {
    
    private val _timeRemaining = MutableStateFlow(7800) // 130 minutes in seconds
    val timeRemaining: StateFlow<Int> = _timeRemaining.asStateFlow()
    
    private val _isRunning = MutableStateFlow(false)
    val isRunning: StateFlow<Boolean> = _isRunning.asStateFlow()
    
    private val _currentSubjectIndex = MutableStateFlow(0)
    val currentSubjectIndex: StateFlow<Int> = _currentSubjectIndex.asStateFlow()
    
    private val _subjects = MutableStateFlow(listOf(
        SubjectTime("Türkçe"),
        SubjectTime("Matematik"),
        SubjectTime("Tarih"),
        SubjectTime("Coğrafya"),
        SubjectTime("Vatandaşlık"),
        SubjectTime("Güncel Bilgi")
    ))
    val subjects: StateFlow<List<SubjectTime>> = _subjects.asStateFlow()
    
    private var timerJob: Job? = null
    private var examStartTime: Long = 0
    
    fun startTimer() {
        if (_isRunning.value) return
        
        _isRunning.value = true
        if (examStartTime == 0L) {
            examStartTime = System.currentTimeMillis()
            // Start first subject
            _subjects.value = _subjects.value.toMutableList().also {
                it[0].startTime = System.currentTimeMillis()
            }
        }
        
        timerJob = viewModelScope.launch {
            while (_timeRemaining.value > 0 && _isRunning.value) {
                delay(1000)
                _timeRemaining.value -= 1
            }
        }
    }
    
    fun pauseTimer() {
        _isRunning.value = false
        timerJob?.cancel()
    }
    
    fun resetTimer() {
        pauseTimer()
        _timeRemaining.value = 7800
        _currentSubjectIndex.value = 0
        examStartTime = 0
        _subjects.value = listOf(
            SubjectTime("Türkçe"),
            SubjectTime("Matematik"),
            SubjectTime("Tarih"),
            SubjectTime("Coğrafya"),
            SubjectTime("Vatandaşlık"),
            SubjectTime("Güncel Bilgi")
        )
    }
    
    fun completeSubject(index: Int) {
        val currentTime = System.currentTimeMillis()
        _subjects.value = _subjects.value.toMutableList().also { list ->
            if (!list[index].isCompleted) {
                val subject = list[index]
                subject.totalTime = ((currentTime - subject.startTime) / 1000).toInt()
                subject.isCompleted = true
                
                // Start next uncompleted subject
                val nextIndex = list.indexOfFirst { !it.isCompleted }
                if (nextIndex != -1) {
                    list[nextIndex].startTime = currentTime
                    _currentSubjectIndex.value = nextIndex
                }
            }
        }
    }
    
    fun uncompleteSubject(index: Int) {
        _subjects.value = _subjects.value.toMutableList().also { list ->
            list[index].isCompleted = false
            list[index].totalTime = 0
            list[index].startTime = 0
        }
    }
    
    fun getCurrentSubjectTime(): Int {
        val currentIndex = _currentSubjectIndex.value
        val subject = _subjects.value.getOrNull(currentIndex) ?: return 0
        if (subject.startTime == 0L || subject.isCompleted) return 0
        return ((System.currentTimeMillis() - subject.startTime) / 1000).toInt()
    }
    
    fun saveExamWithTimeData(exam: ExamEntity, onSaved: () -> Unit) {
        val totalTime = 7800 - _timeRemaining.value
        val subjectsList = _subjects.value
        
        val examWithTime = exam.copy(
            turkceTime = subjectsList[0].totalTime,
            matTime = subjectsList[1].totalTime,
            tarihTime = subjectsList[2].totalTime,
            cografyaTime = subjectsList[3].totalTime,
            vatandaslikTime = subjectsList[4].totalTime,
            guncelTime = subjectsList[5].totalTime,
            totalExamTime = totalTime
        )
        
        viewModelScope.launch {
            repository.insertExam(examWithTime)
            onSaved()
        }
    }
    
    class Factory(private val repository: ExamRepository) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(TimerViewModel::class.java)) {
                return TimerViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
