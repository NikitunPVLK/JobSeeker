package com.example.jobseeker.ui.fragments.common

import android.os.Bundle
import android.view.View
import com.example.jobseeker.ui.viewmodels.common.ISearchViewModel

abstract class BaseSearchResultFragment: BaseVacanciesListFragment() {

    protected abstract val searchViewModel: ISearchViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchViewModel.vacancies.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }
}