package com.example.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.local.dao.UserDao
import com.example.data.local.dao.ContactDao
import com.example.data.local.entity.UserEntity
import com.example.data.local.entity.ContactEntity

@Database(
    entities = [UserEntity::class, ContactEntity::class],
    version = 1
)
internal abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun contactDao(): ContactDao
}
