package com.example.ewaste.data.repository

import com.example.ewaste.data.local.dao.KategoriDao
import com.example.ewaste.data.local.dao.JenisDao
import com.example.ewaste.data.local.entity.KategoriEntity
import com.example.ewaste.data.local.entity.JenisEntity
import com.example.ewaste.data.remote.ApiService
import javax.inject.Inject

class WasteRepository @Inject constructor(
    private val api: ApiService,      // Untuk mengambil data dari API
    private val kategoriDao: KategoriDao,  // DAO untuk kategori di Room
    private val jenisDao: JenisDao     // DAO untuk jenis di Room
) {
    // Fungsi untuk mengambil kategori dari API dan menyimpannya di Room jika baru
    suspend fun fetchKategori(): List<KategoriEntity> {
        val response = api.getKategori()  // Mengambil data kategori dari API

        if (response.isSuccessful) {
            val list = response.body()?.map {
                // Memetakan response API ke dalam entity KategoriEntity
                KategoriEntity(it.id, it.nama)
            } ?: emptyList()

            // Menyimpan kategori yang baru didapatkan ke dalam Room
            kategoriDao.insertAll(list)
        }

        // Mengambil data kategori dari Room, jika sudah ada
        return kategoriDao.getAll()  // Mengambil semua kategori yang ada di Room
    }

    // Fungsi untuk mengambil jenis sampah berdasarkan kategoriId dari API dan menyimpannya di Room jika baru
    suspend fun fetchJenis(kategoriId: Int): List<JenisEntity> {
        val response = api.getJenis()  // Mengambil data jenis dari API

        if (response.isSuccessful) {
            val list = response.body()?.map {
                // Memetakan response API ke dalam entity JenisEntity
                JenisEntity(it.id, it.namaJenis, it.kategoriId)
            } ?: emptyList()

            // Menyimpan jenis yang baru didapatkan ke dalam Room
            jenisDao.insertAll(list)
        }

        // Mengambil data jenis berdasarkan kategoriId dari Room
        return jenisDao.getByKategori(kategoriId)  // Mengambil jenis berdasarkan kategoriId dari Room
    }
}
