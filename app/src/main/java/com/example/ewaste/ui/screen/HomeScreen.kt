package com.example.ewaste.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ewaste.R
import com.example.ewaste.ui.theme.PrimaryGreen
import com.example.ewaste.ui.theme.SecondaryGreen

@Composable
fun HomeScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

        // Bagian Header dengan Salam
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Selamat Datang, Wooo",
                style = MaterialTheme.typography.titleLarge,
                color = PrimaryGreen
            )
            // Tombol untuk menuju halaman profile
            IconButton(onClick = { navController.navigate("profile") }) {
                Icon(imageVector = Icons.Filled.Person, contentDescription = "Profile", tint = PrimaryGreen)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Sub-heading: Kecamatan Jagakarsa
        Text(
            text = "Kecamatan Jagakarsa",
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Tombol Aksi: Tukarkan sampah plastik
        Card(
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            colors = CardDefaults.cardColors(containerColor = PrimaryGreen)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text("Tukarkan sampah plastikmu sekarang", style = MaterialTheme.typography.bodyMedium, color = Color.White)
                }
                // Ikon daur ulang atau sampah
                Icon(painter = painterResource(id = R.drawable.sampahelektronik), contentDescription = "Recycle", tint = Color.White)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Menu Kategori: Button menuju halaman kategori dan jenis
        Column(modifier = Modifier.fillMaxWidth()) {
            // Tombol untuk menuju halaman Kategori
            Button(
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                onClick = { navController.navigate("kategori") },
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen, contentColor = Color.White)
            ) {
                Text("Kategori Sampah")
            }

            // Tombol untuk menuju halaman Jenis Sampah
            Button(
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                onClick = { navController.navigate("jenis") },
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen, contentColor = Color.White)
            ) {
                Text("Jenis Sampah")
            }


        }

        Spacer(modifier = Modifier.weight(1f))

        // Tombol Log Out
        Button(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            onClick = { /* Handle Log Out */ },
            colors = ButtonDefaults.buttonColors(containerColor = SecondaryGreen, contentColor = Color.White)
        ) {
            Text("Log Out")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController())
}



//package com.example.ewaste.ui.screen
//
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.*
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavController
//import androidx.navigation.compose.rememberNavController
//import com.example.ewaste.ui.theme.PrimaryGreen
//import com.example.ewaste.ui.theme.SecondaryGreen
//
//@Composable
//fun HomeScreen(navController: NavController) {
//    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
//        Text(
//            text = "Welcome to eWaste!",
//            style = MaterialTheme.typography.headlineMedium,
//            color = PrimaryGreen,
//            modifier = Modifier.padding(bottom = 32.dp)
//        )
//
//        Button(
//            modifier = Modifier.fillMaxWidth().padding(8.dp),
//            onClick = { navController.navigate("profile") },
//            colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen, contentColor = Color.White)
//        ) {
//            Text("My Profile")
//        }
//
//        Button(
//            modifier = Modifier.fillMaxWidth().padding(8.dp),
//            onClick = { navController.navigate("kategori") },
//            colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen, contentColor = Color.White)
//        ) {
//            Text("Kategori Sampah")
//        }
//
//        Button(
//            modifier = Modifier.fillMaxWidth().padding(8.dp),
//            onClick = { navController.navigate("jenis") },
//            colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen, contentColor = Color.White)
//        ) {
//            Text("Jenis Sampah")
//        }
//
//        Spacer(modifier = Modifier.weight(1f))
//
//        Button(
//            modifier = Modifier.fillMaxWidth().padding(8.dp),
//            onClick = { /* Handle Log Out */ },
//            colors = ButtonDefaults.buttonColors(containerColor = SecondaryGreen, contentColor = Color.White)
//        ) {
//            Text("Log Out")
//        }
//    }
//}
//
//
//@Preview(showBackground = true)
//@Composable
//fun HomeScreenPreview() {
//    HomeScreen(navController = rememberNavController())
//}
