package com.example.data.local.dao

import androidx.room.*
import com.example.data.local.entity.ContactEntity
import com.example.domain.model.ContactCategory
import kotlinx.coroutines.flow.Flow

@Dao
internal interface ContactDao {

    @Query("SELECT * FROM contacts ORDER BY firstName ASC")
    fun getAllContacts(): Flow<List<ContactEntity>>

    @Query("SELECT * FROM contacts WHERE category = :category ORDER BY firstName ASC")
    fun getContactsByCategory(category: ContactCategory): Flow<List<ContactEntity>>

    @Query(
        """
        SELECT * FROM contacts 
        WHERE firstName LIKE '%' || :query || '%' 
           OR lastName LIKE '%' || :query || '%' 
        ORDER BY firstName ASC
    """
    )
    fun searchContacts(query: String): Flow<List<ContactEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContact(contact: ContactEntity)

    @Delete
    suspend fun deleteContact(contact: ContactEntity)

    @Query("SELECT * FROM contacts WHERE id = :id LIMIT 1")
    suspend fun getContactById(id: Int): ContactEntity?
}
