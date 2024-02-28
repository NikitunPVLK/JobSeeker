package com.example.jobseeker.domain.di

import com.example.jobseeker.common.application.JobSeekerApplication
import com.example.jobseeker.domain.usecase.db.VacancyDao
import com.example.jobseeker.domain.usecase.network.IVacanciesNetworkRepository
import com.example.jobseeker.data.network.services.VacanciesNetworkService
import com.example.jobseeker.domain.usecase.network.FetchVacanciesByParametersUseCase
import com.example.jobseeker.domain.usecase.network.FetchVacanciesBySkillsUseCase
import com.example.jobseeker.domain.common.ParametersHandler
import com.example.jobseeker.domain.usecase.db.DeleteVacancyUseCase
import com.example.jobseeker.domain.usecase.db.GetSavedVacanciesUseCase
import com.example.jobseeker.domain.usecase.db.SaveVacancyUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule(private val application: JobSeekerApplication) {

    @Provides
    fun parametersHandler(): ParametersHandler = ParametersHandler()

    @Provides
    @ApplicationScope
    fun vacancyDao(): VacancyDao = application.database.vacancyDao()

    @Provides
    @ApplicationScope
    fun vacanciesNetworkRepository(): IVacanciesNetworkRepository =
        VacanciesNetworkService.getVacancyNetworkRepository()

    @Provides
    fun getSavedVacanciesUseCase(vacancyDao: VacancyDao): GetSavedVacanciesUseCase =
        GetSavedVacanciesUseCase(vacancyDao)

    @Provides
    fun saveVacancyUseCase(vacancyDao: VacancyDao): SaveVacancyUseCase =
        SaveVacancyUseCase(vacancyDao)

    @Provides
    fun deleteVacancyUseCase(vacancyDao: VacancyDao): DeleteVacancyUseCase =
        DeleteVacancyUseCase(vacancyDao)

    @Provides
    fun fetchVacanciesByParametersUseCase(
        parametersHandler: ParametersHandler,
        vacanciesNetworkRepository: IVacanciesNetworkRepository
    ): FetchVacanciesByParametersUseCase =
        FetchVacanciesByParametersUseCase(
            vacanciesNetworkRepository,
            parametersHandler
        )

    @Provides
    fun fetchVacanciesBySkillsUseCase(
        vacanciesNetworkRepository: IVacanciesNetworkRepository
    ): FetchVacanciesBySkillsUseCase =
        FetchVacanciesBySkillsUseCase(vacanciesNetworkRepository)
}