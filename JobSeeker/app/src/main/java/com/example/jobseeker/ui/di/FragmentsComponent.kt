package com.example.jobseeker.ui.di

import com.example.jobseeker.domain.di.ApplicationScope
import com.example.jobseeker.domain.di.UseCaseModule
import com.example.jobseeker.ui.fragments.common.BaseFragment
import com.example.jobseeker.ui.fragments.search_by_parameters.SearchByParametersFragment
import dagger.Component

@Component(modules = [FragmentsModule::class, UseCaseModule::class])
@ApplicationScope
interface FragmentsComponent {

    fun inject(baseFragment: BaseFragment)

    fun inject(searchByParametersFragment: SearchByParametersFragment)
}