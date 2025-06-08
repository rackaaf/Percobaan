package com.example.ewaste.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.graphics.Color
import com.example.ewaste.ui.theme.PrimaryGreen

@Composable
fun SplashScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("E-Waste Management", style = MaterialTheme.typography.headlineLarge, color = PrimaryGreen)


        Button(
            onClick = { navController.navigate("login") },
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen, contentColor = Color.White),
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        ) {
            Text("Login")
        }

        Button(
            onClick = { navController.navigate("register") },
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen, contentColor = Color.White),
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        ) {
            Text("Register")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    SplashScreen(navController = rememberNavController())
}
