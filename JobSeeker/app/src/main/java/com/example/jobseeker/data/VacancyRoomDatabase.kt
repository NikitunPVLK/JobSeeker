package com.example.jobseeker.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.jobseeker.model.Vacancy

@Database(entities = [Vacancy::class], version = 2, exportSchema = false)
abstract class VacancyRoomDatabase : RoomDatabase() {

    abstract fun vacancyDao(): VacancyDao

    companion object {
        @Volatile
        private var INSTANCE: VacancyRoomDatabase? = null

        fun getDatabase(context: Context): VacancyRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    VacancyRoomDatabase::class.java,
                    "vacancy_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}

