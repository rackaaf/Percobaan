package com.example.ewaste.ui.screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ewaste.R
import com.example.ewaste.viewmodel.AuthViewModel

@Composable
fun ProfileScreen(navController: NavController, viewModel: AuthViewModel = androidx.hilt.navigation.compose.hiltViewModel()) {
    var username by remember { mutableStateOf("yANCHU") }
    var email by remember { mutableStateOf("yanchuli@gmail.com") }
    var phone by remember { mutableStateOf("+14897889999") }
    var password by remember { mutableStateOf("avFTbyVVCd") }
    var profileImage: Painter = painterResource(id = R.drawable.sample) // Replace with actual image or resource

    Column(modifier = Modifier.fillMaxSize().padding(24.dp)) {

        // Header section with profile image and "Change Picture"
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Surface(
                modifier = Modifier.size(100.dp),
                shape = CircleShape,
                color = Color.Gray
            ) {
                Image(painter = profileImage, contentDescription = "Profile Picture", modifier = Modifier.fillMaxSize())
            }
        }

        TextButton(onClick = { /* Handle Change Picture */ }) {
            Text("Change Picture", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.primary)
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Profile Fields
        OutlinedTextField(
            value = username, onValueChange = { username = it },
            label = { Text("Username") }, modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = email, onValueChange = { email = it },
            label = { Text("Email Id") }, modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = phone, onValueChange = { phone = it },
            label = { Text("Phone Number") }, modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password, onValueChange = { password = it },
            label = { Text("Password") }, modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Update button
        Button(
            onClick = {
                // Handle save/update profile logic
                viewModel.updateProfile(username, email, phone, password)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Update")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Change password button
        Button(
            onClick = {
                // Handle change password logic
                navController.navigate("change_password")
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
        ) {
            Text("Change Password")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(navController = rememberNavController())
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
//
//@Composable
//fun ProfileScreen(navController: NavController, viewModel: AuthViewModel = androidx.hilt.navigation.compose.hiltViewModel()) {
//    var name by remember { mutableStateOf("") }
//    var address by remember { mutableStateOf("") }
//    var birthDate by remember { mutableStateOf("") }
//    var photoUrl by remember { mutableStateOf("") }
//
//    Column(modifier = Modifier.fillMaxSize().padding(24.dp)) {
//        Text("Profil Pengguna", style = MaterialTheme.typography.headlineMedium)
//        Spacer(modifier = Modifier.height(16.dp))
//        OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Nama") })
//        OutlinedTextField(value = address, onValueChange = { address = it }, label = { Text("Alamat") })
//        OutlinedTextField(value = birthDate, onValueChange = { birthDate = it }, label = { Text("Tanggal Lahir (YYYY-MM-DD)") })
//        OutlinedTextField(value = photoUrl, onValueChange = { photoUrl = it }, label = { Text("Foto URL (opsional)") })
//
//        Spacer(modifier = Modifier.height(16.dp))
//        Button(onClick = {
//            viewModel.updateProfile(name, address, birthDate, photoUrl.ifBlank { null })
//        }) {
//            Text("Simpan Perubahan")
//        }
//
//        viewModel.registerMessage?.let {
//            Spacer(modifier = Modifier.height(8.dp))
//            Text(it)
//        }
//    }
//}
