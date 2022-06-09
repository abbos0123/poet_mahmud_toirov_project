package com.example.mahmudtoirovsherlari.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mahmudtoirovsherlari.daos.AudioPoemDao
import com.example.mahmudtoirovsherlari.daos.BookDao
import com.example.mahmudtoirovsherlari.daos.PoemDao
import com.example.mahmudtoirovsherlari.models.AudioPoem
import com.example.mahmudtoirovsherlari.models.Book
import com.example.mahmudtoirovsherlari.models.Poem

@Database(
    entities = [Poem::class, AudioPoem::class, Book::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun poemDao(): PoemDao
    abstract fun bookDao(): BookDao
    abstract fun audioPoemDao(): AudioPoemDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            val tempInstance = INSTANCE

            if (tempInstance != null)
                return tempInstance

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database2"
                )
                    .allowMainThreadQueries()
                    .build()

                INSTANCE = instance
                return instance
            }
        }
    }
}