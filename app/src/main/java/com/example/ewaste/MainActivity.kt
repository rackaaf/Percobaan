package com.example.ewaste

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.ewaste.ui.navigation.EWasteApp
import com.example.ewaste.ui.theme.EWasteTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EWasteTheme {
                EWasteApp()
            }
        }
    }
}
