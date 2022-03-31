package com.fatahapps.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.fatahapps.data.local.models.kweaModels.KweaItemLocal

@Database(
    entities = [KweaItemLocal::class],
    version = 4
)
@TypeConverters(Converters::class)
abstract class EcobbaDatabase: RoomDatabase() {

    abstract val dao: KweasDao
}