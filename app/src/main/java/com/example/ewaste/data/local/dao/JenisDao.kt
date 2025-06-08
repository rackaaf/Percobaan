package com.example.ewaste.data.local.dao

import androidx.room.*
import com.example.ewaste.data.local.entity.JenisEntity

@Dao
interface JenisDao {
    @Query("SELECT * FROM jenis WHERE kategoriId = :kategoriId")
    suspend fun getByKategori(kategoriId: Int): List<JenisEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(data: List<JenisEntity>)

}
