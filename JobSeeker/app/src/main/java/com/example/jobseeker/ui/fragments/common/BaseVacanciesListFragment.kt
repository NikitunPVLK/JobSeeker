package com.example.jobseeker.ui.fragments.common

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jobseeker.domain.models.Vacancy
import com.example.jobseeker.ui.fragments.adapters.VacancyListAdapter
import com.example.jobseeker.ui.viewmodels.saved_vacancies.VacancyViewModel

abstract class BaseVacanciesListFragment : BaseFragment() {

    protected lateinit var vacancyViewModel: VacancyViewModel

    protected val adapter = createListAdapter()

    protected lateinit var vacancyList: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vacancyViewModel = ViewModelProvider(
            requireActivity(),
            viewModelFactory
        )[VacancyViewModel::class.java]

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
        vacancyViewModel.changeVacancySavedState(vacancy)
    }
}