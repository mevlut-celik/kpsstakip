package com.mevlutcelik.kpsstakip.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exams")
data class ExamEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val date: Long, // Timestamp
    val name: String, // e.g., "TG-1", "Yediiklim 3"
    // Genel Yetenek (60 questions)
    val turkceDogru: Int = 0,      // Max 30
    val turkceYanlis: Int = 0,
    val matDogru: Int = 0,          // Max 30
    val matYanlis: Int = 0,
    // Genel Kültür (60 questions)
    val tarihDogru: Int = 0,        // Max 27
    val tarihYanlis: Int = 0,
    val cografyaDogru: Int = 0,     // Max 18
    val cografyaYanlis: Int = 0,
    val vatandaslikDogru: Int = 0,  // Max 9
    val vatandaslikYanlis: Int = 0,
    val guncelDogru: Int = 0,       // Max 6 (Güncel Bilgi)
    val guncelYanlis: Int = 0,
    val totalNet: Float = 0f,
    // Time tracking (in seconds)
    val turkceTime: Int = 0,
    val matTime: Int = 0,
    val tarihTime: Int = 0,
    val cografyaTime: Int = 0,
    val vatandaslikTime: Int = 0,
    val guncelTime: Int = 0,
    val totalExamTime: Int = 0  // Total time spent on exam
) {
    fun calculateTotalNet(): Float {
        val turkce = turkceDogru - (turkceYanlis / 4f)
        val mat = matDogru - (matYanlis / 4f)
        val tarih = tarihDogru - (tarihYanlis / 4f)
        val cografya = cografyaDogru - (cografyaYanlis / 4f)
        val vatandaslik = vatandaslikDogru - (vatandaslikYanlis / 4f)
        val guncel = guncelDogru - (guncelYanlis / 4f)
        return turkce + mat + tarih + cografya + vatandaslik + guncel
    }
    
    fun getTurkceNet(): Float = turkceDogru - (turkceYanlis / 4f)
    fun getMatNet(): Float = matDogru - (matYanlis / 4f)
    fun getTarihNet(): Float = tarihDogru - (tarihYanlis / 4f)
    fun getCografyaNet(): Float = cografyaDogru - (cografyaYanlis / 4f)
    fun getVatandaslikNet(): Float = vatandaslikDogru - (vatandaslikYanlis / 4f)
    fun getGuncelNet(): Float = guncelDogru - (guncelYanlis / 4f)
    
    // Time percentage calculations
    fun getTurkceTimePercent(): Float = if (totalExamTime > 0) (turkceTime * 100f / totalExamTime) else 0f
    fun getMatTimePercent(): Float = if (totalExamTime > 0) (matTime * 100f / totalExamTime) else 0f
    fun getTarihTimePercent(): Float = if (totalExamTime > 0) (tarihTime * 100f / totalExamTime) else 0f
    fun getCografyaTimePercent(): Float = if (totalExamTime > 0) (cografyaTime * 100f / totalExamTime) else 0f
    fun getVatandaslikTimePercent(): Float = if (totalExamTime > 0) (vatandaslikTime * 100f / totalExamTime) else 0f
    fun getGuncelTimePercent(): Float = if (totalExamTime > 0) (guncelTime * 100f / totalExamTime) else 0f
}
