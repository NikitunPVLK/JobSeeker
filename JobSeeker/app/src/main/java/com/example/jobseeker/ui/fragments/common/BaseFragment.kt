package com.example.jobseeker.ui.fragments.common

import android.content.Context
import androidx.fragment.app.Fragment
import com.example.jobseeker.common.application.JobSeekerApplication
import com.example.jobseeker.domain.di.UseCaseModule
import com.example.jobseeker.ui.di.DaggerFragmentsComponent
import com.example.jobseeker.ui.di.FragmentsComponent
import com.example.jobseeker.ui.viewmodels.common.ViewModelFactory
import javax.inject.Inject

open class BaseFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    protected lateinit var injector: FragmentsComponent

    override fun onAttach(context: Context) {
        super.onAttach(context)
        injector = DaggerFragmentsComponent.builder()
            .useCaseModule(UseCaseModule((requireActivity().application) as JobSeekerApplication))
            .build()

        injector.inject(this)
    }
}