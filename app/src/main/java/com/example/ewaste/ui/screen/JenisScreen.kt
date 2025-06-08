package com.example.ewaste.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ewaste.ui.theme.PrimaryGreen

@Composable
fun JenisScreen(navController: NavController) {
    // Data dummy jenis sampah
    val jenisList = listOf(
        "Botol plastik", "Kantong plastik", "Sisa makanan", "Daun-daunan", "Sayuran busuk",
        "Kotoran hewan", "Baterai bekas", "Lampu bekas", "Cat bekas", "Pembangun", "Popok bekas"
    )

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Jenis Sampah", style = MaterialTheme.typography.headlineMedium, color = PrimaryGreen)

        Spacer(modifier = Modifier.height(16.dp))

        // Menggunakan LazyVerticalGrid dengan 2 kotak per baris
        LazyVerticalGrid(
            columns = GridCells.Fixed(2), // Menampilkan 2 kolom per baris
            modifier = Modifier.fillMaxSize()
        ) {
            items(jenisList) { jenis ->
                JenisItem(
                    jenis = jenis,
                    onClick = {
                        // Navigasi jika diperlukan
                    }
                )
            }
        }
    }
}

@Composable
fun JenisItem(jenis: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(16.dp)  // Menambah padding antar item grid
            .height(150.dp)  // Tinggi tetap untuk konsistensi
            .fillMaxWidth()  // Menyesuaikan lebar dengan grid
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),  // Sudut lebih bulat
        colors = CardDefaults.cardColors(containerColor = PrimaryGreen),
    ) {
        Box(
            contentAlignment = Alignment.Center, // Menjaga teks tetap terpusat
            modifier = Modifier.fillMaxSize() // Membuat kotak memenuhi ruang
        ) {
            Text(
                text = jenis,
                color = Color.White,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun JenisScreenPreview() {
    JenisScreen(navController = rememberNavController())
}
