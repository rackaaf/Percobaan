package com.example.ewaste.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ewaste.ui.theme.PrimaryGreen
import com.example.ewaste.viewmodel.AuthViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController,
    authViewModel: AuthViewModel = hiltViewModel()
) {
    // Auto-login check
    LaunchedEffect(Unit) {
        delay(2000) // Show splash for 2 seconds
        if (authViewModel.repository.isLoggedIn()) {
            navController.navigate("home") {
                popUpTo("splash") { inclusive = true }
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "E-Waste Management",
            style = MaterialTheme.typography.headlineLarge,
            color = PrimaryGreen
        )

        Spacer(modifier = Modifier.height(48.dp))

        Button(
            onClick = { navController.navigate("login") },
            colors = ButtonDefaults.buttonColors(
                containerColor = PrimaryGreen,
                contentColor = Color.White
            ),
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        ) {
            Text("Login")
        }

        Button(
            onClick = { navController.navigate("register") },
            colors = ButtonDefaults.buttonColors(
                containerColor = PrimaryGreen,
                contentColor = Color.White
            ),
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        ) {
            Text("Register")
        }
    }
}