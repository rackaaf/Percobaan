package com.example.ewaste.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ewaste.viewmodel.AuthViewModel

@Composable
fun ChangePasswordScreen(navController: NavController, viewModel: AuthViewModel = androidx.hilt.navigation.compose.hiltViewModel()) {
    var oldPass by remember { mutableStateOf("") }
    var newPass by remember { mutableStateOf("") }
    var confirmPass by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(24.dp)) {
        Text("Ubah Password", style = MaterialTheme.typography.headlineMedium)
        OutlinedTextField(value = oldPass, onValueChange = { oldPass = it }, label = { Text("Password Lama") })
        OutlinedTextField(value = newPass, onValueChange = { newPass = it }, label = { Text("Password Baru") })
        OutlinedTextField(value = confirmPass, onValueChange = { confirmPass = it }, label = { Text("Konfirmasi Password Baru") })

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            viewModel.updatePassword(oldPass, newPass, confirmPass)
        }) {
            Text("Simpan")
        }

        viewModel.registerMessage?.let {
            Spacer(modifier = Modifier.height(8.dp))
            Text(it)
        }
    }
}
