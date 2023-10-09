package com.example.jobseeker.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jobseeker.data.database.VacancyDao
import com.example.jobseeker.domain.Vacancy
import kotlinx.coroutines.*


class VacancyViewModel(private val vacancyDao: VacancyDao) : ViewModel() {

    val vacancies: LiveData<List<Vacancy>> = vacancyDao.getVacancies()

    private fun addVacancy(vacancy: Vacancy): Deferred<Boolean> {
        return viewModelScope.async {
            val existingVacancy = withContext(Dispatchers.IO) {
                vacancyDao.getVacancyByUrl(vacancy.url)
            }
            if (existingVacancy == null) {
                return@async withContext(Dispatchers.IO) {
                    vacancyDao.insert(vacancy)
                    return@withContext true
                }
            } else {
                return@async false
            }
        }
    }

    fun deleteVacancy(vacancy: Vacancy) {
        viewModelScope.launch {
            vacancyDao.deleteByUrl(vacancy.url)
        }
    }

    fun saveVacancy(vacancy: Vacancy){
        if (vacancy.isSaved) {
            vacancy.isSaved = false
            deleteVacancy(vacancy)
        } else {
            vacancy.isSaved = true
            addVacancy(vacancy)
        }
    }
}

