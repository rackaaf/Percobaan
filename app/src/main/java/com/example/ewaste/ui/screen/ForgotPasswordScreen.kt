package com.example.ewaste.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ewaste.viewmodel.AuthViewModel
import kotlinx.coroutines.delay

@Composable
fun ForgotPasswordScreen(navController: NavController, viewModel: AuthViewModel = androidx.hilt.navigation.compose.hiltViewModel()) {
    var email by remember { mutableStateOf("") }
    var redirecting by remember { mutableStateOf(false) }

    // Efek samping untuk navigasi dengan delay
    LaunchedEffect(redirecting) {
        if (redirecting) {
            delay(2000) // simulasi loading 2 detik
            navController.navigate("otp/forgot") // Navigasi setelah delay
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
            "Lupa Password",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Input Email
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Tombol Kirim OTP
        Button(
            onClick = {
                viewModel.forgotPassword(email)
                redirecting = true // Menandakan bahwa kita sedang dalam proses mengarahkan user ke halaman OTP
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Kirim OTP")
        }

        // Menampilkan pesan jika ada
        viewModel.registerMessage?.let {
            Spacer(modifier = Modifier.height(8.dp))
            Text(it)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Tombol navigasi ke login screen
        TextButton(onClick = { navController.navigate("login") }) {
            Text("Back to Login", color = MaterialTheme.colorScheme.primary)
        }
    }
}

//@Composable
//fun ForgotPasswordScreen(navController: NavController, viewModel: AuthViewModel = androidx.hilt.navigation.compose.hiltViewModel()) {
//    var email by remember { mutableStateOf("") }
//    var redirecting by remember { mutableStateOf(false) }
//
//    // Efek samping untuk navigasi dengan delay
//    LaunchedEffect(redirecting) {
//        if (redirecting) {
//            delay(2000) // simulasi loading 2 detik
//            navController.navigate("otp/forgot") // Navigasi setelah delay
//        }
//    }
//
//    Column(modifier = Modifier.fillMaxSize().padding(24.dp), verticalArrangement = Arrangement.Center) {
//        Text("Lupa Password", style = MaterialTheme.typography.headlineMedium)
//
//        // Input Email
//        OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        // Tombol Kirim OTP
//        Button(onClick = {
//            viewModel.forgotPassword(email)
//            redirecting = true // Menandakan bahwa kita sedang dalam proses mengarahkan user ke halaman OTP
//        }) {
//            Text("Kirim OTP")
//        }
//
//        // Menampilkan pesan jika ada
//        viewModel.registerMessage?.let {
//            Spacer(modifier = Modifier.height(8.dp))
//            Text(it)
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        // Tombol navigasi ke login screen
//        TextButton(onClick = { navController.navigate("login") }) {
//            Text("Back to Login", color = MaterialTheme.colorScheme.primary)
//        }
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun ForgotPasswordScreenPreview() {
//    ForgotPasswordScreen(navController = rememberNavController())
//}
