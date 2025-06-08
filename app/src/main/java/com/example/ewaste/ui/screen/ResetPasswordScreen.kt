package com.example.ewaste.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ewaste.viewmodel.AuthViewModel
import kotlinx.coroutines.delay

@Composable
fun ResetPasswordScreen(navController: NavController, viewModel: AuthViewModel = androidx.hilt.navigation.compose.hiltViewModel()) {
    var newPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var redirecting by remember { mutableStateOf(false) }
    var passwordMismatch by remember { mutableStateOf(false) }

    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }

    // Efek samping untuk navigasi setelah reset password berhasil
    LaunchedEffect(viewModel.passwordResetSuccess) {
        if (viewModel.passwordResetSuccess) {
            redirecting = true
            delay(2000) // simulasi loading 2 detik
            navController.navigate("login") {
                popUpTo("reset") { inclusive = true }
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
            "Reset Password",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(32.dp))

//         Input Password Baru
        OutlinedTextField(
            value = newPassword,
            onValueChange = { newPassword = it },
            label = { Text("Password Baru") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        imageVector = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                        contentDescription = null
                    )
                }
            }
        )


        Spacer(modifier = Modifier.height(16.dp))

        // Input Konfirmasi Password
        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text("Konfirmasi Password") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        imageVector = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                        contentDescription = null
                    )
                }
            },
            isError = passwordMismatch // Menandai jika password tidak cocok
        )

        // Menampilkan pesan jika password tidak cocok
        if (passwordMismatch) {
            Text("Password dan konfirmasi password tidak cocok", color = MaterialTheme.colorScheme.error)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Tombol untuk reset password
        Button(
            onClick = {
                if (newPassword != confirmPassword) {
                    passwordMismatch = true // Menandai jika password tidak cocok
                } else {
                    passwordMismatch = false // Reset error message
                    // Menyertakan hanya password baru
                    viewModel.resetPassword(newPassword)
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Reset Password")
        }

        // Menampilkan pesan berhasil/gagal
        viewModel.registerMessage?.let {
            Spacer(modifier = Modifier.height(8.dp))
            Text(it)
        }

        if (redirecting) {
            Spacer(modifier = Modifier.height(16.dp))
            CircularProgressIndicator()
            Spacer(modifier = Modifier.height(8.dp))
            Text("Password berhasil direset. Mengarahkan ke login...")
        }
    }
}



//@Composable
//fun ResetPasswordScreen(navController: NavController, viewModel: AuthViewModel = androidx.hilt.navigation.compose.hiltViewModel()) {
//    var newPassword by remember { mutableStateOf("") }
//    var confirmPassword by remember { mutableStateOf("") }
//    var redirecting by remember { mutableStateOf(false) }
//    var passwordMismatch by remember { mutableStateOf(false) }
//
//    // Efek samping untuk navigasi setelah reset password berhasil
//    LaunchedEffect(viewModel.passwordResetSuccess) {
//        if (viewModel.passwordResetSuccess) {
//            redirecting = true
//            delay(2000) // simulasi loading 2 detik
//            navController.navigate("login") {
//                popUpTo("reset") { inclusive = true }
//            }
//        }
//    }
//
//    Column(modifier = Modifier.fillMaxSize().padding(24.dp), verticalArrangement = Arrangement.Center) {
//        Text("Reset Password", style = MaterialTheme.typography.headlineMedium)
//
//        // Input Password Baru
//        OutlinedTextField(value = newPassword, onValueChange = { newPassword = it }, label = { Text("Password Baru") })
//
//        // Input Konfirmasi Password
//        OutlinedTextField(
//            value = confirmPassword,
//            onValueChange = { confirmPassword = it },
//            label = { Text("Konfirmasi Password") },
//            isError = passwordMismatch // Menandai jika password tidak cocok
//        )
//
//        // Menampilkan pesan jika password tidak cocok
//        if (passwordMismatch) {
//            Text("Password dan konfirmasi password tidak cocok", color = MaterialTheme.colorScheme.error)
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        // Tombol untuk reset password
//        Button(onClick = {
//            if (newPassword != confirmPassword) {
//                passwordMismatch = true // Menandai jika password tidak cocok
//            } else {
//                passwordMismatch = false // Reset error message
//                // Menyertakan hanya password baru
//                viewModel.resetPassword(newPassword)
//            }
//        }) {
//            Text("Reset Password")
//        }
//
//        // Menampilkan pesan berhasil/gagal
//        viewModel.registerMessage?.let {
//            Spacer(modifier = Modifier.height(8.dp))
//            Text(it)
//        }
//
//        // Jika sedang mengarahkan ke halaman login
//        if (redirecting) {
//            Spacer(modifier = Modifier.height(16.dp))
//            CircularProgressIndicator()
//            Spacer(modifier = Modifier.height(8.dp))
//            Text("Password berhasil direset. Mengarahkan ke login...")
//        }
//    }
//}
//
//
//@Preview(showBackground = true)
//@Composable
//fun ResetPasswordScreenPreview() {
//    ResetPasswordScreen(navController = rememberNavController())
//}



//@Composable
//fun ResetPasswordScreen(navController: NavController, viewModel: AuthViewModel = androidx.hilt.navigation.compose.hiltViewModel()) {
//    var email by remember { mutableStateOf("") }
//    var otp by remember { mutableStateOf("") }
//    var newPassword by remember { mutableStateOf("") }
//
//    Column(modifier = Modifier.fillMaxSize().padding(24.dp), verticalArrangement = Arrangement.Center) {
//        Text("Reset Password", style = MaterialTheme.typography.headlineMedium)
//
//        // Input Email
//        OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
//
//        // Input OTP
//        OutlinedTextField(value = otp, onValueChange = { otp = it }, label = { Text("Kode OTP") })
//
//        // Input Password Baru
//        OutlinedTextField(value = newPassword, onValueChange = { newPassword = it }, label = { Text("Password Baru") })
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        // Tombol untuk reset password
//        Button(onClick = { viewModel.resetPassword(email, otp, newPassword) }) {
//            Text("Reset Password")
//        }
//
//        // Menampilkan pesan berhasil/gagal
//        viewModel.registerMessage?.let {
//            Spacer(modifier = Modifier.height(8.dp))
//            Text(it)
//        }
//
//    }
//}

//@Preview(showBackground = true)
//@Composable
//fun ResetPasswordScreenPreview() {
//    ResetPasswordScreen(navController = rememberNavController())
//}
