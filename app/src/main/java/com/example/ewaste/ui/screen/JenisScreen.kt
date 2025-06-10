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
fun JenisScreen(
    navController: NavController,
    viewModel: KategoriViewModel = hiltViewModel()
) {
    val jenisList by viewModel.jenisList.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    // Alternative: load all jenis if accessed directly
    LaunchedEffect(Unit) {
        if (jenisList.isEmpty()) {
            viewModel.loadAllJenis()
        }
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
                "Jenis Sampah",
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
                    Text("Memuat data jenis sampah...")
                }
            }
        } else if (jenisList.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("Tidak ada jenis sampah tersedia")
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize()
            ) {
                items(jenisList) { jenis ->
                    JenisItem(
                        jenis = jenis.namaJenis,
                        onClick = {
                            // Handle jenis click if needed (maybe show details)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun JenisItem(jenis: String, onClick: () -> Unit) {
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
                text = jenis,
                color = Color.White,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}