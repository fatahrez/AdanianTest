package com.fatahapps.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fatahapps.data.local.models.kweaModels.KweaItemLocal

@Dao
interface KweasDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertKweas(kweas: List<KweaItemLocal>)

    @Query("DELETE FROM kweaitemlocal")
    suspend fun deleteKweas()

    @Query("SELECT * FROM kweaitemlocal")
    suspend fun getAllKweas(): List<KweaItemLocal>

    @Query("SELECT * FROM kweaitemlocal WHERE id = :id")
    suspend fun getIndividualKwea(id: Int): KweaItemLocal
}