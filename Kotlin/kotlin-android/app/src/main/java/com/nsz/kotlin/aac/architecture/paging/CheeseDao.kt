package com.nsz.kotlin.aac.architecture.paging

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CheeseDao {

    @Query("SELECT * FROM Cheese ORDER BY id DESC")
    fun allCheesesByName(): DataSource.Factory<Int, Cheese>

    @Insert
    fun insert(cheese: Cheese)

    @Delete
    fun delete(cheese: Cheese)

}