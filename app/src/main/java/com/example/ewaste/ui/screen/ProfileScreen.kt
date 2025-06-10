package com.example.ewaste.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ewaste.R
import com.example.ewaste.ui.theme.PrimaryGreen
import com.example.ewaste.viewmodel.AuthViewModel
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: AuthViewModel = hiltViewModel()
) {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }

    // Load user data from repository
    LaunchedEffect(Unit) {
        username = viewModel.repository.getUserName() ?: ""
        email = viewModel.repository.getUserEmail() ?: ""
    }

    Column(modifier = Modifier.fillMaxSize().padding(24.dp)) {

        // Header dengan back button
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = PrimaryGreen
                )
            }
            Text(
                "Profile",
                style = MaterialTheme.typography.headlineMedium,
                color = PrimaryGreen
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Header section with profile image
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Surface(
                modifier = Modifier.size(100.dp),
                shape = CircleShape,
                color = Color.Gray
            ) {
                // Placeholder untuk foto profil
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = username.take(1).uppercase(),
                        style = MaterialTheme.typography.headlineLarge,
                        color = Color.White
                    )
                }
            }
        }

        TextButton(
            onClick = { /* Handle Change Picture */ },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Change Picture", color = PrimaryGreen)
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Profile Fields
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            enabled = false // Email tidak bisa diubah
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = phone,
            onValueChange = { phone = it },
            label = { Text("Phone Number") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = address,
            onValueChange = { address = it },
            label = { Text("Address") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Update button
        Button(
            onClick = {
                viewModel.updateProfile(username, address, "", null)
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen)
        ) {
            Text("Update Profile")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Change password button
        Button(
            onClick = {
                navController.navigate("change-password")
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
        ) {
            Text("Change Password")
        }

        // Show update message
        viewModel.registerMessage?.let {
            Spacer(modifier = Modifier.height(16.dp))
            Text(it, color = if (it.contains("success")) Color.Green else Color.Red)
        }
    }
}