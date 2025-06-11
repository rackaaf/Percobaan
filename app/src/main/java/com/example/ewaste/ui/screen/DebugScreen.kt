//package com.example.ewaste.ui.screen
//
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.platform.LocalContext
//import android.content.Context
//
//@Composable
//fun DebugProfileData() {
//    val context = LocalContext.current
//    val prefs = context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)
//
//    Column(modifier = Modifier.padding(16.dp)) {
//        Text("Debug Profile Data:")
//        Text("Photo URL: ${prefs.getString("user_photo_url", "NULL")}")
//        Text("Name: ${prefs.getString("user_name", "NULL")}")
//        Text("Email: ${prefs.getString("user_email", "NULL")}")
//    }
//}