package com.example.jobseeker.application

import android.app.Application
import com.example.jobseeker.data.VacancyRoomDatabase

class JobSeekerApplication : Application() {
    val database: VacancyRoomDatabase by lazy { VacancyRoomDatabase.getDatabase(this) }
}