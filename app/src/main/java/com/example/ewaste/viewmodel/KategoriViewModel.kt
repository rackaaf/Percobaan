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

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun loadKategori() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                _kategoriList.value = repo.fetchKategori()
            } catch (e: Exception) {
                // Handle error if needed
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun loadJenis(kategoriId: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                _jenisList.value = repo.fetchJenis(kategoriId)
            } catch (e: Exception) {
                // Handle error if needed
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun loadAllJenis() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                _jenisList.value = repo.fetchAllJenis()
            } catch (e: Exception) {
                // Handle error if needed
            } finally {
                _isLoading.value = false
            }
        }
    }
}