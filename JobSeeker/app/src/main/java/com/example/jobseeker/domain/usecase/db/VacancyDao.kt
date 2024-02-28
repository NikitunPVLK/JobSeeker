package com.example.jobseeker.domain.usecase.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.jobseeker.domain.di.ApplicationScope
import com.example.jobseeker.domain.models.Vacancy

@Dao
@ApplicationScope
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