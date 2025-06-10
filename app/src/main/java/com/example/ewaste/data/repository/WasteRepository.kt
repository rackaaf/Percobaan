package com.example.ewaste.data.repository

import com.example.ewaste.data.local.dao.KategoriDao
import com.example.ewaste.data.local.dao.JenisDao
import com.example.ewaste.data.local.entity.KategoriEntity
import com.example.ewaste.data.local.entity.JenisEntity
import com.example.ewaste.data.remote.ApiService
import javax.inject.Inject

class WasteRepository @Inject constructor(
    private val api: ApiService,
    private val kategoriDao: KategoriDao,
    private val jenisDao: JenisDao
) {
    // Fetch kategori dari API dan cache ke Room
    suspend fun fetchKategori(): List<KategoriEntity> {
        return try {
            val response = api.getKategori()
            if (response.isSuccessful) {
                val apiData = response.body()?.map {
                    KategoriEntity(it.id, it.nama)
                } ?: emptyList()

                // Save to local database
                kategoriDao.deleteAll()
                kategoriDao.insertAll(apiData)
                return apiData
            } else {
                // Fallback to local data if API fails
                kategoriDao.getAll()
            }
        } catch (e: Exception) {
            // Fallback to local data if network error
            kategoriDao.getAll()
        }
    }

    // Fetch jenis dari API dan cache ke Room
    suspend fun fetchJenis(kategoriId: Int): List<JenisEntity> {
        return try {
            val response = api.getJenisByCategory(kategoriId)
            if (response.isSuccessful) {
                val apiData = response.body()?.map {
                    JenisEntity(it.id, it.namaJenis, it.kategoriId)
                } ?: emptyList()

                // Save to local database (replace existing for this category)
                jenisDao.insertAll(apiData)
                return apiData
            } else {
                // Fallback to local data if API fails
                jenisDao.getByKategori(kategoriId)
            }
        } catch (e: Exception) {
            // Fallback to local data if network error
            jenisDao.getByKategori(kategoriId)
        }
    }

    // Get all jenis dari API
    suspend fun fetchAllJenis(): List<JenisEntity> {
        return try {
            val response = api.getJenis()
            if (response.isSuccessful) {
                val apiData = response.body()?.map {
                    JenisEntity(it.id, it.namaJenis, it.kategoriId)
                } ?: emptyList()

                // Save to local database
                jenisDao.deleteAll()
                jenisDao.insertAll(apiData)
                return apiData
            } else {
                // Fallback to local data if API fails
                jenisDao.getAll()
            }
        } catch (e: Exception) {
            // Fallback to local data if network error
            jenisDao.getAll()
        }
    }
}