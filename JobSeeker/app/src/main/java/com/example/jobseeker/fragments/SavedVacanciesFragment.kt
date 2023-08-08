package com.example.jobseeker.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jobseeker.application.JobSeekerApplication
import com.example.jobseeker.adapter.VacancyListAdapter
import com.example.jobseeker.databinding.FragmentSavedVacanciesBinding
import com.example.jobseeker.viewmodel.SavedVacancyViewModel
import com.example.jobseeker.viewmodel.VacancyViewModelFactory

class SavedVacanciesFragment : Fragment() {
    private var _binding: FragmentSavedVacanciesBinding? = null
    private val binding get() = _binding!!

    private val savedVacancyViewModel: SavedVacancyViewModel by activityViewModels {
        VacancyViewModelFactory(
            (activity?.application as JobSeekerApplication).database.vacancyDao()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSavedVacanciesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = VacancyListAdapter(
            {
                val action =
                    SavedVacanciesFragmentDirections
                        .actionSavedVacanciesFragmentToDetailedVacancyFragment(
                            it.title,
                            it.salary,
                            it.company,
                            it.location,
                            it.description,
                            it.url
                        )
                findNavController().navigate(action)
            },
            {
                if(it.isSaved) {
                    it.isSaved = false
                    savedVacancyViewModel.deleteVacancy(it)
                }
            }
        )
        binding.vacancyList.adapter = adapter
        savedVacancyViewModel.vacancies.observe(viewLifecycleOwner) { vacancies ->
            if (vacancies.isEmpty()) {
                binding.emptyListTextView.visibility = View.VISIBLE
            } else {
                vacancies.let {
                    adapter.submitList(it)
                }
            }
        }

        binding.vacancyList.layoutManager = LinearLayoutManager(context)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}