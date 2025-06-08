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
fun KategoriScreen(navController: NavController) {
    // Data dummy kategori sampah
    val kategoriList = listOf(
        "B3", "Organik", "Residue", "Plastik", "Logam", "Kaca", "Elektronik", "Kemasan", "Kompos"
    )

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Kategori Sampah", style = MaterialTheme.typography.headlineMedium, color = PrimaryGreen)

        Spacer(modifier = Modifier.height(16.dp))

        // Menggunakan LazyVerticalGrid dengan 2 kotak per baris
        LazyVerticalGrid(
            columns = GridCells.Fixed(2), // Menampilkan 2 kolom per baris
            modifier = Modifier.fillMaxSize()
        ) {
            items(kategoriList) { kategori ->
                KategoriItem(
                    kategori = kategori,
                    onClick = {
                        // Navigasi ke halaman jenis
                        navController.navigate("jenis/$kategori")
                    }
                )
            }
        }
    }
}

@Composable
fun KategoriItem(kategori: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)  // Padding antar item
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
                text = kategori,
                color = Color.White,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun KategoriScreenPreview() {
    KategoriScreen(navController = rememberNavController())
}
