package com.example.ewaste.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ewaste.data.local.dao.*
import com.example.ewaste.data.local.entity.*

@Database(entities = [KategoriEntity::class, JenisEntity::class], version = 1)
abstract class EWasteDatabase : RoomDatabase() {
    abstract fun kategoriDao(): KategoriDao
    abstract fun jenisDao(): JenisDao
}
