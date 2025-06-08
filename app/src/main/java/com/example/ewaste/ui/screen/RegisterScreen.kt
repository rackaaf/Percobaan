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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ewaste.viewmodel.AuthViewModel
import kotlinx.coroutines.delay


@Composable
fun RegisterScreen(
    navController: NavController,
    viewModel: AuthViewModel = androidx.hilt.navigation.compose.hiltViewModel()
) {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    // Navigasi ke halaman OTP
    LaunchedEffect(viewModel.otpSent) {
        if (viewModel.otpSent) {
            delay(2000)
            navController.navigate("otp/register")
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Judul
        Text(
            "Register",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Field input Username
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Field input Email
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Field input Phone
        OutlinedTextField(
            value = phone,
            onValueChange = { phone = it },
            label = { Text("No. Telepon") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Field input Password
//        OutlinedTextField(
//            value = password,
//            onValueChange = { password = it },
//            label = { Text("Password") },
//            modifier = Modifier.fillMaxWidth(),
//            visualTransformation = PasswordVisualTransformation() // Menyembunyikan password
//        )

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
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { /* Handle login */ }
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Tombol Register
        Button(
            onClick = {
                viewModel.otpSent = false // reset dulu
                viewModel.register(username, email, phone, password)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Daftar")
        }

        viewModel.registerMessage?.let {
            Spacer(modifier = Modifier.height(8.dp))
            Text(it)
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Tautan Login
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            TextButton(onClick = { navController.popBackStack() }) {
                Text("Sudah punya akun? Login")
            }
        }
    }
}



//package com.example.ewaste.ui.screen
//
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavController
//import com.example.ewaste.viewmodel.AuthViewModel
//import kotlinx.coroutines.delay
//
//@Composable
//fun RegisterScreen(
//    navController: NavController,
//    viewModel: AuthViewModel = androidx.hilt.navigation.compose.hiltViewModel()
//) {
//    var username by remember { mutableStateOf("") }
//    var email by remember { mutableStateOf("") }
//    var phone by remember { mutableStateOf("") }
//    var password by remember { mutableStateOf("") }
//
//    // Navigasi ke halaman OTP
//    LaunchedEffect(viewModel.otpSent) {
//        if (viewModel.otpSent) {
//            delay(2000)
//            navController.navigate("otp/register")
//        }
//    }
//
//    Column(modifier = Modifier.fillMaxSize().padding(24.dp), verticalArrangement = Arrangement.Center) {
//        Text("Daftar Akun", style = MaterialTheme.typography.headlineMedium)
//        OutlinedTextField(value = username, onValueChange = { username = it }, label = { Text("Username") })
//        OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
//        OutlinedTextField(value = phone, onValueChange = { phone = it }, label = { Text("No. Telepon") })
//        OutlinedTextField(value = password, onValueChange = { password = it }, label = { Text("Password") })
//
//        Spacer(modifier = Modifier.height(16.dp))
//        Button(onClick = {
//            viewModel.otpSent = false // reset dulu
//            viewModel.register(username, email, phone, password)
//        }) {
//            Text("Register")
//        }
//
//        viewModel.registerMessage?.let {
//            Spacer(modifier = Modifier.height(8.dp))
//            Text(it)
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//        TextButton(onClick = { navController.popBackStack() }) {
//            Text("Sudah punya akun? Login")
//        }
//    }
//}

