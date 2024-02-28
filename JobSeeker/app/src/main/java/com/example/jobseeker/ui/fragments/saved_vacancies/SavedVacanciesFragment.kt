package com.example.jobseeker.ui.fragments.saved_vacancies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.jobseeker.databinding.FragmentSavedVacanciesBinding
import com.example.jobseeker.domain.models.Vacancy
import com.example.jobseeker.ui.fragments.common.BaseVacanciesListFragment

class SavedVacanciesFragment : BaseVacanciesListFragment() {

    private var _binding: FragmentSavedVacanciesBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSavedVacanciesBinding.inflate(inflater, container, false)
        vacancyList = binding.vacancyList
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vacancyViewModel.vacancies.observe(viewLifecycleOwner) { vacancies ->
            if (vacancies.isEmpty()) {
                binding.emptyListTextView.visibility = View.VISIBLE
                binding.vacancyList.visibility = View.INVISIBLE
            } else {
                binding.emptyListTextView.visibility = View.INVISIBLE
                binding.vacancyList.visibility = View.VISIBLE
                vacancies.let {
                    adapter.submitList(it)
                }
            }
        }
    }

    override fun onListItemClicked(vacancy: Vacancy) {
        val action =
            SavedVacanciesFragmentDirections
                .actionSavedVacanciesFragmentToDetailedVacancyFragment(vacancy)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}