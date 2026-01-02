package com.mevlutcelik.kpsstakip.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
// Divider not available
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.mevlutcelik.kpsstakip.data.ExamEntity
import com.mevlutcelik.kpsstakip.viewmodel.ExamViewModel

@Composable
fun AddExamScreen(viewModel: ExamViewModel, onExamAdded: () -> Unit) {
    var name by remember { mutableStateOf("") }
    var showSuccess by remember { mutableStateOf(false) }
    
    // States for inputs
    var turkceD by remember { mutableStateOf("") }
    var turkceY by remember { mutableStateOf("") }
    var matD by remember { mutableStateOf("") }
    var matY by remember { mutableStateOf("") }
    var tarihD by remember { mutableStateOf("") }
    var tarihY by remember { mutableStateOf("") }
    var cogD by remember { mutableStateOf("") }
    var cogY by remember { mutableStateOf("") }
    var vatD by remember { mutableStateOf("") }
    var vatY by remember { mutableStateOf("") }
    var guncelD by remember { mutableStateOf("") }
    var guncelY by remember { mutableStateOf("") }

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(scrollState)
    ) {
        Text(
            text = "Yeni Deneme Ekle",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Deneme Adı (Örn: TG-1)") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Genel Yetenek Section
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Genel Yetenek (60 Soru)",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier.padding(bottom = 12.dp)
                )
                
                LessonInputRow("Türkçe", 30, turkceD, { turkceD = it }, turkceY, { turkceY = it })
                LessonInputRow("Matematik", 30, matD, { matD = it }, matY, { matY = it })
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Genel Kültür Section
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer
            )
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Genel Kültür (60 Soru)",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    modifier = Modifier.padding(bottom = 12.dp)
                )
                
                LessonInputRow("Tarih", 27, tarihD, { tarihD = it }, tarihY, { tarihY = it })
                LessonInputRow("Coğrafya", 18, cogD, { cogD = it }, cogY, { cogY = it })
                LessonInputRow("Vatandaşlık", 9, vatD, { vatD = it }, vatY, { vatY = it })
                LessonInputRow("Güncel Bilgi", 6, guncelD, { guncelD = it }, guncelY, { guncelY = it })
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        AnimatedVisibility(
            visible = showSuccess,
            enter = fadeIn() + expandVertically(),
            exit = fadeOut() + shrinkVertically()
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer
                )
            ) {
                Text(
                    text = "✓ Deneme başarıyla kaydedildi!",
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onTertiaryContainer
                )
            }
        }

        Button(
            onClick = {
                val exam = ExamEntity(
                    date = System.currentTimeMillis(),
                    name = name.ifBlank { "Deneme" },
                    turkceDogru = turkceD.toIntOrNull() ?: 0,
                    turkceYanlis = turkceY.toIntOrNull() ?: 0,
                    matDogru = matD.toIntOrNull() ?: 0,
                    matYanlis = matY.toIntOrNull() ?: 0,
                    tarihDogru = tarihD.toIntOrNull() ?: 0,
                    tarihYanlis = tarihY.toIntOrNull() ?: 0,
                    cografyaDogru = cogD.toIntOrNull() ?: 0,
                    cografyaYanlis = cogY.toIntOrNull() ?: 0,
                    vatandaslikDogru = vatD.toIntOrNull() ?: 0,
                    vatandaslikYanlis = vatY.toIntOrNull() ?: 0,
                    guncelDogru = guncelD.toIntOrNull() ?: 0,
                    guncelYanlis = guncelY.toIntOrNull() ?: 0
                )
                val finalExam = exam.copy(totalNet = exam.calculateTotalNet())
                
                viewModel.addExam(finalExam)
                showSuccess = true
                
                // Reset form after a delay
                name = ""
                turkceD = ""; turkceY = ""
                matD = ""; matY = ""
                tarihD = ""; tarihY = ""
                cogD = ""; cogY = ""
                vatD = ""; vatY = ""
                guncelD = ""; guncelY = ""
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Text(
                text = "Kaydet",
                modifier = Modifier.padding(8.dp),
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@Composable
fun LessonInputRow(
    label: String,
    maxQuestions: Int,
    dVal: String, onDChange: (String) -> Unit,
    yVal: String, onYChange: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = label,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = "Max: $maxQuestions soru",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
            )
        }
        OutlinedTextField(
            value = dVal,
            onValueChange = onDChange,
            label = { Text("D") },
            modifier = Modifier.width(80.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true
        )
        Spacer(modifier = Modifier.width(8.dp))
        OutlinedTextField(
            value = yVal,
            onValueChange = onYChange,
            label = { Text("Y") },
            modifier = Modifier.width(80.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true
        )
    }
}
