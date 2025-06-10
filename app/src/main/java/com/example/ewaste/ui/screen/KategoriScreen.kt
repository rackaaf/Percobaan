package com.example.ewaste.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ewaste.ui.theme.PrimaryGreen
import com.example.ewaste.viewmodel.KategoriViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.collectAsState

@Composable
fun KategoriScreen(
    navController: NavController,
    viewModel: KategoriViewModel = hiltViewModel()
) {
    val kategoriList by viewModel.kategoriList.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    // Load data saat screen pertama kali dibuka
    LaunchedEffect(Unit) {
        viewModel.loadKategori()
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

        // Header dengan back button
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = PrimaryGreen
                )
            }
            Text(
                "Kategori Sampah",
                style = MaterialTheme.typography.headlineMedium,
                color = PrimaryGreen
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    CircularProgressIndicator(color = PrimaryGreen)
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Memuat kategori sampah...")
                }
            }
        } else if (kategoriList.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("Tidak ada kategori tersedia")
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize()
            ) {
                items(kategoriList) { kategori ->
                    KategoriItem(
                        kategori = kategori.namaKategori,
                        onClick = {
                            // Load jenis for this category and navigate
                            viewModel.loadJenis(kategori.id)
                            navController.navigate("jenis")
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun KategoriItem(kategori: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .height(150.dp)
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = PrimaryGreen),
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = kategori,
                color = Color.White,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}