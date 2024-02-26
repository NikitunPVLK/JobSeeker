package com.example.jobseeker.ui.fragments.search_by_parameters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.jobseeker.databinding.FragmentSearchByParametersResultBinding
import com.example.jobseeker.ui.fragments.common.BaseSearchResultFragment
import com.example.jobseeker.ui.viewmodels.common.ViewModelFactory
import com.example.jobseeker.ui.viewmodels.search_by_parameters.SearchByParametersViewModel

class BaseSearchByParametersResultFragment : BaseSearchResultFragment() {

    override val searchViewModel: SearchByParametersViewModel by activityViewModels {
        ViewModelFactory()
    }

    private var _binding: FragmentSearchByParametersResultBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchByParametersResultBinding.inflate(inflater, container, false)
        vacancyList = binding.vacancyList
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            val searchParameters = searchViewModel.searchParameters

            keyWordsTextView.text = searchParameters.keyWords
            categoryTextView.text = searchParameters.category
            experienceTextView.text = searchParameters.experience
            locationTextView.text = searchParameters.location
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}