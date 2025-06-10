package com.example.ewaste.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ewaste.ui.theme.PrimaryGreen
import com.example.ewaste.ui.theme.SecondaryGreen
import com.example.ewaste.viewmodel.AuthViewModel
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    authViewModel: AuthViewModel = hiltViewModel()
) {
    val userName = authViewModel.repository.getUserName() ?: "User"

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

        // Header dengan nama user dari API
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = "Selamat Datang,",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Gray
                )
                Text(
                    text = userName,
                    style = MaterialTheme.typography.titleLarge,
                    color = PrimaryGreen
                )
            }

            IconButton(onClick = { navController.navigate("profile") }) {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = "Profile",
                    tint = PrimaryGreen
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Kecamatan Jagakarsa",
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Card aksi utama
        Card(
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            colors = CardDefaults.cardColors(containerColor = PrimaryGreen)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            ) {
                Text(
                    "Tukarkan sampah elektronikmu sekarang",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White
                )
                Text(
                    "Dapatkan poin dan kontribusi untuk lingkungan",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.White.copy(alpha = 0.8f)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Menu navigation
        Column(modifier = Modifier.fillMaxWidth()) {
            Button(
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                onClick = { navController.navigate("kategori") },
                colors = ButtonDefaults.buttonColors(
                    containerColor = PrimaryGreen,
                    contentColor = Color.White
                )
            ) {
                Text("Kategori Sampah")
            }

            Button(
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                onClick = { navController.navigate("jenis") },
                colors = ButtonDefaults.buttonColors(
                    containerColor = PrimaryGreen,
                    contentColor = Color.White
                )
            ) {
                Text("Jenis Sampah")
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // Logout button
        Button(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            onClick = {
                authViewModel.logout()
                navController.navigate("splash") {
                    popUpTo("home") { inclusive = true }
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = SecondaryGreen,
                contentColor = Color.White
            )
        ) {
            Text("Log Out")
        }
    }
}