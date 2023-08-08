package com.example.jobseeker.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.jobseeker.model.Vacancy
import kotlinx.coroutines.flow.Flow

@Dao
interface VacancyDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(vacancy: Vacancy)

    @Delete
    suspend fun delete(vacancy: Vacancy)

    @Query("DELETE FROM vacancy WHERE url=:url")
    suspend fun deleteByUrl(url: String)

    @Query("SELECT * FROM vacancy WHERE url=:url")
    fun getVacancyByUrl(url: String): Vacancy?

    @Query("SELECT * FROM vacancy")
    fun getVacancies(): LiveData<List<Vacancy>>
}