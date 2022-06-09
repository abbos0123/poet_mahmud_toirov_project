package com.example.mahmudtoirovsherlari.daos

import androidx.room.*
import com.example.mahmudtoirovsherlari.models.AudioPoem
import com.example.mahmudtoirovsherlari.models.Poem

@Dao
interface AudioPoemDao {

    @Query("Select * from audiopoem")
    fun getAllPoems(): List<AudioPoem>

    @Query("Select * from audiopoem where id=:id ")
    fun getPoem(id: Long): AudioPoem


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPoem(audioPoem: AudioPoem)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPoems(list: List<AudioPoem?>?)

    @Delete
    fun deletePoem(audioPoem: AudioPoem)

}