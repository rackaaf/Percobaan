package com.example.ewaste.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ewaste.viewmodel.AuthViewModel
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: AuthViewModel = hiltViewModel()
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    // Navigasi saat login berhasil
    LaunchedEffect(viewModel.loginSuccess) {
        if (viewModel.loginSuccess) {
            navController.navigate("home") {
                popUpTo("login") { inclusive = true }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "Login",
            style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
            color = Color(0xFF00695C)
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Field input Email
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Field input Password
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        imageVector = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                        contentDescription = null
                    )
                }
            },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done)
        )

        // Tautan forgot password
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            TextButton(onClick = { navController.navigate("forgot") }) {
                Text("Lupa Password?")
            }
        }

        // Tombol Login
        Button(
            onClick = {
                viewModel.resetStates()
                viewModel.login(email, password)
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00695C)),
            enabled = !viewModel.isLoading
        ) {
            if (viewModel.isLoading) {
                CircularProgressIndicator(color = Color.White, modifier = Modifier.size(20.dp))
            } else {
                Text("Log In", color = Color.White)
            }
        }

        if (viewModel.loginSuccess) {
            Text(
                "Login Berhasil!",
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        // Menampilkan pesan kesalahan jika ada
        viewModel.errorMessage?.let {
            Spacer(modifier = Modifier.height(8.dp))
            Text(it, color = MaterialTheme.colorScheme.error)
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Tautan registrasi
        TextButton(onClick = { navController.navigate("register") }) {
            Text("Belum punya akun? Daftar disini")
        }
    }
}