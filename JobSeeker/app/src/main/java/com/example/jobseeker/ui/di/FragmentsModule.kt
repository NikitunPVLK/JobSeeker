package com.example.jobseeker.ui.di

import com.example.jobseeker.domain.di.ApplicationScope
import com.example.jobseeker.domain.usecase.db.DeleteVacancyUseCase
import com.example.jobseeker.domain.usecase.db.GetSavedVacanciesUseCase
import com.example.jobseeker.domain.usecase.network.FetchVacanciesByParametersUseCase
import com.example.jobseeker.domain.usecase.network.FetchVacanciesBySkillsUseCase
import com.example.jobseeker.domain.usecase.db.SaveVacancyUseCase
import com.example.jobseeker.ui.viewmodels.common.ViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Provider

@Module
class FragmentsModule {

    @Provides
    @ApplicationScope
    fun viewModelFactory(
        getSavedVacanciesUseCaseProvider: Provider<GetSavedVacanciesUseCase>,
        saveVacancyUseCaseProvider: Provider<SaveVacancyUseCase>,
        deleteVacancyUseCaseProvider: Provider<DeleteVacancyUseCase>,
        fetchVacanciesByParametersUseCaseProvider: Provider<FetchVacanciesByParametersUseCase>,
        fetchVacanciesBySkillsUseCaseProvider: Provider<FetchVacanciesBySkillsUseCase>
    ): ViewModelFactory = ViewModelFactory(
        getSavedVacanciesUseCaseProvider,
        saveVacancyUseCaseProvider,
        deleteVacancyUseCaseProvider,
        fetchVacanciesByParametersUseCaseProvider,
        fetchVacanciesBySkillsUseCaseProvider
    )
}