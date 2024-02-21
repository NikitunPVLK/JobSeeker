package com.example.jobseeker.ui.fragments.common

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jobseeker.common.application.JobSeekerApplication
import com.example.jobseeker.domain.Vacancy
//import com.example.jobseeker.ui.fragments.VacanciesListFragmentDirections
import com.example.jobseeker.ui.fragments.adapters.VacancyListAdapter
import com.example.jobseeker.ui.viewmodels.ViewModelFactory
import com.example.jobseeker.ui.viewmodels.saved_vacancies.VacancyViewModel

open class BaseVacanciesListFragment : Fragment() {

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

    private fun onListItemClicked(vacancy: Vacancy) {
//        val action =
//            VacanciesListFragmentDirections
//                .actionVacanciesListFragmentToDetailedVacancyFragment(
//                    vacancy.title,
//                    vacancy.salary,
//                    vacancy.company,
//                    vacancy.location,
//                    vacancy.description,
//                    vacancy.url
//                )
//        findNavController().navigate(action)
    }

    private fun onSaveButtonClicked(vacancy: Vacancy) {
        vacancyViewModel.changeSavedState(vacancy)
    }
}