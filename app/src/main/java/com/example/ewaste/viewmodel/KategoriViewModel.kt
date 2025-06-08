package com.example.ewaste.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ewaste.data.local.entity.*
import com.example.ewaste.data.repository.WasteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class KategoriViewModel @Inject constructor(
    private val repo: WasteRepository
) : ViewModel() {

    private val _kategoriList = MutableStateFlow<List<KategoriEntity>>(emptyList())
    val kategoriList: StateFlow<List<KategoriEntity>> = _kategoriList

    private val _jenisList = MutableStateFlow<List<JenisEntity>>(emptyList())
    val jenisList: StateFlow<List<JenisEntity>> = _jenisList

    fun loadKategori() {
        viewModelScope.launch {
            _kategoriList.value = repo.fetchKategori()
        }
    }

    fun loadJenis(kategoriId: Int) {
        viewModelScope.launch {
            _jenisList.value = repo.fetchJenis(kategoriId)
        }
    }
}
