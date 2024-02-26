package com.example.jobseeker.ui.fragments.common

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jobseeker.common.application.JobSeekerApplication
import com.example.jobseeker.domain.Vacancy
//import com.example.jobseeker.ui.fragments.VacanciesListFragmentDirections
import com.example.jobseeker.ui.fragments.adapters.VacancyListAdapter
import com.example.jobseeker.ui.viewmodels.common.ViewModelFactory
import com.example.jobseeker.ui.viewmodels.saved_vacancies.VacancyViewModel

abstract class BaseVacanciesListFragment : Fragment() {

    protected val vacancyViewModel: VacancyViewModel by activityViewModels {
        ViewModelFactory((activity?.application as JobSeekerApplication).database.vacancyDao())
    }

    protected val adapter = createListAdapter()

    protected lateinit var vacancyList: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vacancyList.adapter = adapter
        vacancyList.layoutManager = LinearLayoutManager(context)
    }

    private fun createListAdapter(): VacancyListAdapter {
        return VacancyListAdapter(
            ::onListItemClicked,
            ::onSaveButtonClicked
        )
    }

    abstract fun onListItemClicked(vacancy: Vacancy)

    private fun onSaveButtonClicked(vacancy: Vacancy) {
        vacancyViewModel.changeSavedState(vacancy)
    }
}