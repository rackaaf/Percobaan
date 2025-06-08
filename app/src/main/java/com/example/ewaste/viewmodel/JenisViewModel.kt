package com.example.ewaste.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ewaste.data.local.entity.JenisEntity
import com.example.ewaste.data.repository.WasteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


//
//@HiltViewModel
//class JenisViewModel @Inject constructor(
//    private val wasteRepository: WasteRepository
//) : ViewModel() {
//
//    var jenisList = mutableStateOf<List<JenisEntity>>(emptyList())

//    fun loadJenis() {
//        viewModelScope.launch {
//            jenisList.value = wasteRepository.getAllJenis() // Memuat jenis sampah
//        }
//    }
//}
