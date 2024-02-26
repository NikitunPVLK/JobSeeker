package com.example.jobseeker.ui.di

import com.example.jobseeker.common.application.JobSeekerApplication
import com.example.jobseeker.domain.di.ApplicationScope
import com.example.jobseeker.domain.usecase.db.VacancyDao
import com.example.jobseeker.domain.usecase.db.DeleteVacancyUseCase
import com.example.jobseeker.domain.usecase.network.FetchVacanciesByParametersUseCase
import com.example.jobseeker.domain.usecase.network.FetchVacanciesBySkillsUseCase
import com.example.jobseeker.domain.usecase.db.SaveVacancyUseCase
import com.example.jobseeker.ui.viewmodels.common.ViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class FragmentsModule {

    @Provides
    @ApplicationScope
    fun viewModelFactory(
        saveVacancyUseCase: SaveVacancyUseCase,
        deleteVacancyUseCase: DeleteVacancyUseCase,
        fetchVacanciesByParametersUseCase: FetchVacanciesByParametersUseCase,
        fetchVacanciesBySkillsUseCase: FetchVacanciesBySkillsUseCase,
        vacancyDao: VacancyDao
    ): ViewModelFactory = ViewModelFactory(
        saveVacancyUseCase,
        deleteVacancyUseCase,
        fetchVacanciesByParametersUseCase,
        fetchVacanciesBySkillsUseCase,
        vacancyDao
    )
}