package com.example.jobseeker.ui.application

import android.app.Application
import com.example.jobseeker.data.database.VacancyRoomDatabase

class JobSeekerApplication : Application() {
    val database: VacancyRoomDatabase by lazy { VacancyRoomDatabase.getDatabase(this) }
}