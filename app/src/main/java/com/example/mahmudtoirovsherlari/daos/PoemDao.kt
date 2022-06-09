package com.example.mahmudtoirovsherlari.daos

import androidx.room.*
import com.example.mahmudtoirovsherlari.models.Poem

@Dao
interface PoemDao {

    @Query("Select * from poem")
    fun getAllPoems(): List<Poem>

    @Query("Select * from poem where id=:id ")
    fun getPoem(id: Long): Poem


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPoem(poem: Poem)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPoems(list: List<Poem?>?)

    @Delete
    fun deletePoem(poem: Poem)

}