package com.mevlutcelik.kpsstakip.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
// Divider not available
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mevlutcelik.kpsstakip.viewmodel.AnalysisViewModel

@Composable
fun AnalysisScreen(viewModel: AnalysisViewModel) {
    val exams by viewModel.allExams.collectAsState()

    LazyColumn(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text(
                text = "Gelişim Analizi",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
        }
        
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Net Gelişim Grafiği (Son 10 Deneme)",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    
                    if (exams.isNotEmpty()) {
                        LineChart(
                            data = exams.take(10).reversed().map { it.totalNet },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(220.dp)
                        )
                    } else {
                        Text(
                            text = "Henüz deneme eklenmemiş. Deneme ekleyerek gelişiminizi takip edin!",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
                            modifier = Modifier.padding(vertical = 32.dp)
                        )
                    }
                }
            }
        }
        
        item {
            // Divider removed
        }
        
        item {
            Text(
                text = "Tüm Denemeler",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        }
        
        items(exams) { exam ->
            ExamItem(exam)
        }
    }
}

@Composable
fun LineChart(data: List<Float>, modifier: Modifier = Modifier) {
    if (data.isEmpty()) return

    val maxVal = (data.maxOrNull() ?: 100f) + 10f
    val primaryColor = MaterialTheme.colorScheme.primary
    val secondaryColor = MaterialTheme.colorScheme.secondary
    
    Canvas(modifier = modifier) {
        val width = size.width
        val height = size.height
        val xStep = width / (data.size - 1).coerceAtLeast(1)
        val yStep = height / maxVal

        val path = Path()
        
        data.forEachIndexed { index, value ->
            val x = index * xStep
            val y = height - (value * yStep)
            
            if (index == 0) {
                path.moveTo(x, y)
            } else {
                path.lineTo(x, y)
            }
        }
        
        // Draw gradient fill under the line
        val fillPath = Path().apply {
            addPath(path)
            lineTo(width, height)
            lineTo(0f, height)
            close()
        }
        
        drawPath(
            path = fillPath,
            brush = Brush.verticalGradient(
                colors = listOf(
                    primaryColor.copy(alpha = 0.3f),
                    primaryColor.copy(alpha = 0.0f)
                )
            )
        )
        
        // Draw the line
        drawPath(
            path = path,
            brush = Brush.horizontalGradient(
                colors = listOf(primaryColor, secondaryColor)
            ),
            style = Stroke(width = 6f)
        )
        
        // Draw points
        data.forEachIndexed { index, value ->
            val x = index * xStep
            val y = height - (value * yStep)
            
            drawCircle(
                color = primaryColor,
                center = Offset(x, y),
                radius = 10f
            )
            drawCircle(
                color = androidx.compose.ui.graphics.Color.White,
                center = Offset(x, y),
                radius = 6f
            )
        }
    }
}
