package com.mevlutcelik.kpsstakip

import android.app.Application
import com.mevlutcelik.kpsstakip.data.AppDatabase
import com.mevlutcelik.kpsstakip.data.ExamRepository
import com.mevlutcelik.kpsstakip.data.PreferencesManager

class KpssApplication : Application() {
    // Manual dependency injection for simplicity
    val database by lazy { AppDatabase.getDatabase(this) }
    val repository by lazy { ExamRepository(database.examDao()) }
    val preferencesManager by lazy { PreferencesManager(this) }
}
