package com.mevlutcelik.kpsstakip

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.mevlutcelik.kpsstakip.ui.KpssTakipApp
import com.mevlutcelik.kpsstakip.ui.theme.KPSSTakipTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val app = application as KpssApplication
        
        setContent {
            val isDarkMode by app.preferencesManager.isDarkMode.collectAsState(initial = false)
            
            KPSSTakipTheme(darkTheme = isDarkMode) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    KpssTakipApp()
                }
            }
        }
    }
}
