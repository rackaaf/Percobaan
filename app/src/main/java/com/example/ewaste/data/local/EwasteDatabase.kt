package com.example.ewaste.data.local

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import com.example.ewaste.data.local.dao.JenisDao
import com.example.ewaste.data.local.dao.KategoriDao
import com.example.ewaste.data.local.entity.JenisEntity
import com.example.ewaste.data.local.entity.KategoriEntity

@Database(
    entities = [KategoriEntity::class, JenisEntity::class],
    version = 1,
    exportSchema = false
)
abstract class EWasteDatabase : RoomDatabase() {
    abstract fun kategoriDao(): KategoriDao
    abstract fun jenisDao(): JenisDao
}