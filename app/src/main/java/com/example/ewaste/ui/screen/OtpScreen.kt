package com.example.ewaste.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ewaste.viewmodel.AuthViewModel
import kotlinx.coroutines.delay

@Composable
fun OtpScreen(
    navController: NavController,
    purpose: String,
    viewModel: AuthViewModel = androidx.hilt.navigation.compose.hiltViewModel()
) {
    var code by remember { mutableStateOf("") }
    var redirecting by remember { mutableStateOf(false) }

    // Efek samping untuk redirect setelah verifikasi berhasil
    LaunchedEffect(viewModel.otpVerified) {
        if (viewModel.otpVerified || viewModel.otpVerifiedForForgot) {
            redirecting = true
            delay(2000) // simulasi loading 2 detik

            // Logika navigasi berdasarkan tujuan (register atau forgot)
            when (purpose) {
                "register" -> {
                    navController.navigate("login") {
                        popUpTo("register") { inclusive = true } // Menghapus halaman register dari backstack
                    }
                }
                "forgot" -> {
                    navController.navigate("reset") {
                        popUpTo("otp/forgot") { inclusive = true } // Menghapus halaman OTP lupa password
                    }
                }
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
        // Judul
        Text(
            "Verifikasi OTP",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Input Kode OTP
        OutlinedTextField(
            value = code,
            onValueChange = { code = it },
            label = { Text("Kode OTP") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Tombol Verifikasi
        Button(
            onClick = {
                viewModel.otpVerified = false
                redirecting = false
                viewModel.verifyOtp(code)  // Cukup mengirimkan kode OTP
            },
            enabled = !redirecting,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Verifikasi")
        }

        if (redirecting) {
            Spacer(modifier = Modifier.height(16.dp))
            CircularProgressIndicator()
            Spacer(modifier = Modifier.height(8.dp))
            Text("Verifikasi berhasil. Mengarahkan...")
        }

        // Tombol Navigasi Kembali ke Halaman Login
        Spacer(modifier = Modifier.height(16.dp))
        TextButton(onClick = { navController.popBackStack() }) {
            Text("Kembali ke Login", color = MaterialTheme.colorScheme.primary)
        }
    }
}



//@Composable
//fun OtpScreen(
//    navController: NavController,
//    purpose: String,
//    viewModel: AuthViewModel = androidx.hilt.navigation.compose.hiltViewModel()
//) {
//    var email by remember { mutableStateOf("") }
//    var code by remember { mutableStateOf("") }
//    var redirecting by remember { mutableStateOf(false) }
//
//    // Efek samping untuk redirect setelah verifikasi berhasil
//    LaunchedEffect(viewModel.otpVerified) {
//        if (viewModel.otpVerified || viewModel.otpVerifiedForForgot) {
//            redirecting = true
//            delay(2000) // simulasi loading 2 detik
//
//            // Logika navigasi berdasarkan tujuan (register atau forgot)
//            when (purpose) {
//                "register" -> {
//                    navController.navigate("login") {
//                        popUpTo("register") { inclusive = true } // Menghapus halaman register dari backstack
//                    }
//                }
//                "forgot" -> {
//                    navController.navigate("reset") {
//                        popUpTo("otp/forgot") { inclusive = true } // Menghapus halaman OTP lupa password
//                    }
//                }
//            }
//        }
//    }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(24.dp),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        Text(
//            "Verifikasi OTP",
//            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
//            color = MaterialTheme.colorScheme.primary
//        )
//
//        Spacer(modifier = Modifier.height(32.dp))
//
//        // Input Email
//        OutlinedTextField(
//            value = email,
//            onValueChange = { email = it },
//            label = { Text("Email") },
//            modifier = Modifier.fillMaxWidth()
//        )
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        // Input OTP Code
//        OutlinedTextField(
//            value = code,
//            onValueChange = { code = it },
//            label = { Text("Kode OTP") },
//            modifier = Modifier.fillMaxWidth()
//        )
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        // Tombol Verifikasi
//        Button(
//            onClick = {
//                viewModel.otpVerified = false
//                redirecting = false
//                viewModel.verifyOtp(email, code)
//            },
//            enabled = !redirecting,
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            Text("Verifikasi")
//        }
//
//        if (redirecting) {
//            Spacer(modifier = Modifier.height(16.dp))
//            CircularProgressIndicator()
//            Spacer(modifier = Modifier.height(8.dp))
//            Text("Verifikasi berhasil. Mengarahkan...")
//        }
//    }
//}



//@Composable
//fun OtpScreen(
//    navController: NavController,
//    purpose: String,
//    viewModel: AuthViewModel = androidx.hilt.navigation.compose.hiltViewModel()
//) {
//    var email by remember { mutableStateOf("") }
//    var code by remember { mutableStateOf("") }
//    var redirecting by remember { mutableStateOf(false) }
//
//    // Efek samping untuk redirect setelah verifikasi berhasil
//    LaunchedEffect(viewModel.otpVerified) {
//        if (viewModel.otpVerified || viewModel.otpVerifiedForForgot) {
//            redirecting = true
//            delay(2000) // simulasi loading 2 detik
//
//            // Logika navigasi berdasarkan tujuan (register atau forgot)
//            when (purpose) {
//                "register" -> {
//                    // Setelah OTP berhasil diverifikasi untuk registrasi, navigasi ke halaman login
//                    navController.navigate("login") {
//                        popUpTo("register") { inclusive = true } // Menghapus halaman register dari backstack
//                    }
//                }
//                "forgot" -> {
//                    // Setelah OTP berhasil diverifikasi untuk lupa password, navigasi ke halaman reset_password
//                    navController.navigate("reset") {
//                        popUpTo("otp/forgot") { inclusive = true } // Menghapus halaman OTP lupa password
//                    }
//                }
//            }
//        }
//    }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(24.dp),
//        verticalArrangement = Arrangement.Center
//    ) {
//        Text("Verifikasi OTP", style = MaterialTheme.typography.headlineMedium)
//        OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
//        OutlinedTextField(value = code, onValueChange = { code = it }, label = { Text("Kode OTP") })
//        Spacer(modifier = Modifier.height(16.dp))
//        Button(
//            onClick = {
//                viewModel.otpVerified = false
//                redirecting = false
//                viewModel.verifyOtp(email, code)
//            },
//            enabled = !redirecting
//        ) {
//            Text("Verifikasi")
//        }
//
//        viewModel.registerMessage?.let {
//            Spacer(modifier = Modifier.height(8.dp))
//            Text(it)
//        }
//
//        if (redirecting) {
//            Spacer(modifier = Modifier.height(16.dp))
//            CircularProgressIndicator()
//            Spacer(modifier = Modifier.height(8.dp))
//            Text("Verifikasi berhasil. Mengarahkan...")
//        }
//    }
//}


//@Composable
//fun OtpScreen(
//    navController: NavController,
//    purpose: String,
//    viewModel: AuthViewModel = androidx.hilt.navigation.compose.hiltViewModel()
//) {
//    var email by remember { mutableStateOf("") }
//    var code by remember { mutableStateOf("") }
//
//    // Navigasi sesuai tujuan
//    LaunchedEffect(viewModel.otpVerified) {
//        if (viewModel.otpVerified) {
//            when (purpose) {
//                "register" -> {
//                    navController.navigate("login") {
//                        popUpTo("register") { inclusive = true }
//                    }
//                }
//                "forgot" -> {
//                    navController.navigate("login") {
//                        popUpTo("otp/forgot") { inclusive = true }
//                    }
//                }
//            }
//        }
//    }
//
//    Column(modifier = Modifier.fillMaxSize().padding(24.dp), verticalArrangement = Arrangement.Center) {
//        Text("Verifikasi OTP", style = MaterialTheme.typography.headlineMedium)
//        OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
//        OutlinedTextField(value = code, onValueChange = { code = it }, label = { Text("Kode OTP") })
//        Spacer(modifier = Modifier.height(16.dp))
//        Button(onClick = {
//            viewModel.otpVerified = false
//            viewModel.verifyOtp(email, code)
//        }) {
//            Text("Verifikasi")
//        }
//        viewModel.registerMessage?.let {
//            Spacer(modifier = Modifier.height(8.dp))
//            Text(it)
//        }
//    }
//}


//@Composable
//fun OtpScreen(
//    navController: NavController,
//    viewModel: AuthViewModel = androidx.hilt.navigation.compose.hiltViewModel()
//) {
//    var email by remember { mutableStateOf("") }
//    var code by remember { mutableStateOf("") }
//
//    // Navigasi ke login setelah verifikasi berhasil
//    LaunchedEffect(viewModel.otpVerified) {
//        if (viewModel.otpVerified) {
//            delay(2000)
//            navController.navigate("login") {
//                popUpTo("register") { inclusive = true }
//            }
//        }
//    }
//
//    Column(modifier = Modifier.fillMaxSize().padding(24.dp), verticalArrangement = Arrangement.Center) {
//        Text("Verifikasi OTP", style = MaterialTheme.typography.headlineMedium)
//        OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
//        OutlinedTextField(value = code, onValueChange = { code = it }, label = { Text("Kode OTP") })
//        Spacer(modifier = Modifier.height(16.dp))
//        Button(onClick = {
//            viewModel.otpVerified = false // reset dulu
//            viewModel.verifyOtp(email, code)
//        }) {
//            Text("Verifikasi")
//        }
//        viewModel.registerMessage?.let {
//            Spacer(modifier = Modifier.height(8.dp))
//            Text(it)
//        }
//    }
//}
