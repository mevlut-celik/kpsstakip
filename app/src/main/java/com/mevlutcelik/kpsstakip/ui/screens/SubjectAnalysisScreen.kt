package com.mevlutcelik.kpsstakip.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
// Divider not available
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mevlutcelik.kpsstakip.viewmodel.AnalysisViewModel

data class SubjectStats(
    val name: String,
    val maxQuestions: Int,
    val averageNet: Float,
    val averageCorrect: Float,
    val averageWrong: Float,
    val averageTimePercent: Float,
    val color: Color
)

@Composable
fun SubjectAnalysisScreen(viewModel: AnalysisViewModel) {
    val exams by viewModel.allExams.collectAsState()
    
    val subjectStats = if (exams.isNotEmpty()) {
        listOf(
            SubjectStats(
                name = "Türkçe",
                maxQuestions = 30,
                averageNet = exams.map { it.getTurkceNet() }.average().toFloat(),
                averageCorrect = exams.map { it.turkceDogru }.average().toFloat(),
                averageWrong = exams.map { it.turkceYanlis }.average().toFloat(),
                averageTimePercent = exams.map { it.getTurkceTimePercent() }.average().toFloat(),
                color = Color(0xFF6750A4)
            ),
            SubjectStats(
                name = "Matematik",
                maxQuestions = 30,
                averageNet = exams.map { it.getMatNet() }.average().toFloat(),
                averageCorrect = exams.map { it.matDogru }.average().toFloat(),
                averageWrong = exams.map { it.matYanlis }.average().toFloat(),
                averageTimePercent = exams.map { it.getMatTimePercent() }.average().toFloat(),
                color = Color(0xFF625B71)
            ),
            SubjectStats(
                name = "Tarih",
                maxQuestions = 27,
                averageNet = exams.map { it.getTarihNet() }.average().toFloat(),
                averageCorrect = exams.map { it.tarihDogru }.average().toFloat(),
                averageWrong = exams.map { it.tarihYanlis }.average().toFloat(),
                averageTimePercent = exams.map { it.getTarihTimePercent() }.average().toFloat(),
                color = Color(0xFF7D5260)
            ),
            SubjectStats(
                name = "Coğrafya",
                maxQuestions = 18,
                averageNet = exams.map { it.getCografyaNet() }.average().toFloat(),
                averageCorrect = exams.map { it.cografyaDogru }.average().toFloat(),
                averageWrong = exams.map { it.cografyaYanlis }.average().toFloat(),
                averageTimePercent = exams.map { it.getCografyaTimePercent() }.average().toFloat(),
                color = Color(0xFF4CAF50)
            ),
            SubjectStats(
                name = "Vatandaşlık",
                maxQuestions = 9,
                averageNet = exams.map { it.getVatandaslikNet() }.average().toFloat(),
                averageCorrect = exams.map { it.vatandaslikDogru }.average().toFloat(),
                averageWrong = exams.map { it.vatandaslikYanlis }.average().toFloat(),
                averageTimePercent = exams.map { it.getVatandaslikTimePercent() }.average().toFloat(),
                color = Color(0xFF2196F3)
            ),
            SubjectStats(
                name = "Güncel Bilgi",
                maxQuestions = 6,
                averageNet = exams.map { it.getGuncelNet() }.average().toFloat(),
                averageCorrect = exams.map { it.guncelDogru }.average().toFloat(),
                averageWrong = exams.map { it.guncelYanlis }.average().toFloat(),
                averageTimePercent = exams.map { it.getGuncelTimePercent() }.average().toFloat(),
                color = Color(0xFFFF9800)
            )
        )
    } else {
        emptyList()
    }

    LazyColumn(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Text(
                text = "Ders Bazlı Analiz",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "${exams.size} deneme üzerinden ortalamalar",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
            )
        }
        
        if (subjectStats.isEmpty()) {
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant
                    )
                ) {
                    Text(
                        text = "Henüz deneme eklenmemiş. Deneme ekleyerek ders bazlı analizinizi görebilirsiniz!",
                        modifier = Modifier.padding(24.dp),
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        } else {
            items(subjectStats.size) { index ->
                SubjectCard(subjectStats[index])
            }
        }
    }
}

@Composable
fun SubjectCard(stats: SubjectStats) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = stats.name,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Max ${stats.maxQuestions} soru",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
                    )
                }
                
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(
                            Brush.linearGradient(
                                colors = listOf(stats.color, stats.color.copy(alpha = 0.7f))
                            )
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = String.format("%.1f", stats.averageNet),
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Text(
                            text = "Net",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.White.copy(alpha = 0.9f)
                        )
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Progress bar
            val progress = (stats.averageNet / stats.maxQuestions).coerceIn(0f, 1f)
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Başarı Oranı",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = "${(progress * 100).toInt()}%",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                        color = stats.color
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                LinearProgressIndicator(
                    progress = progress,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                        .clip(RoundedCornerShape(4.dp)),
                    color = stats.color,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant
                )
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            // Divider removed
            Spacer(modifier = Modifier.height(16.dp))
            
            // Stats row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                StatItem("Ort. Doğru", String.format("%.1f", stats.averageCorrect), Color(0xFF4CAF50))
                StatItem("Ort. Yanlış", String.format("%.1f", stats.averageWrong), Color(0xFFEF5350))
                StatItem("Ort. Net", String.format("%.1f", stats.averageNet), stats.color)
            }
            
            // Time percentage (if available)
            if (stats.averageTimePercent > 0) {
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "⏱️ Ortalama Süre: ",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
                    )
                    Text(
                        text = "${String.format("%.1f", stats.averageTimePercent)}%",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                        color = stats.color
                    )
                }
            }
        }
    }
}

@Composable
fun StatItem(label: String, value: String, color: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = value,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = color
        )
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
        )
    }
}
