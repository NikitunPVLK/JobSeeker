package com.example.jobseeker.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jobseeker.JobSeekerApplication
import com.example.jobseeker.adapter.VacancyListAdapter
import com.example.jobseeker.databinding.FragmentDetailedVacancyBinding
import com.example.jobseeker.databinding.FragmentSavedVacanciesBinding
import com.example.jobseeker.model.SavedVacanciesViewModel
import com.example.jobseeker.model.SavedVacanciesViewModelFactory

class SavedVacanciesFragment : Fragment() {
    private var _binding: FragmentSavedVacanciesBinding? = null
    private val binding get() = _binding!!

    private val savedVacanciesViewModel: SavedVacanciesViewModel by activityViewModels {
        SavedVacanciesViewModelFactory(
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
                            it.description
                        )
                findNavController().navigate(action)
            },
            {
                savedVacanciesViewModel.deleteVacancy(it)
            }
        )
        binding.vacancyList.adapter = adapter
        savedVacanciesViewModel.vacancies.observe(viewLifecycleOwner) { vacancies ->
            vacancies.let {
                adapter.submitList(it)
            }
        }

        binding.vacancyList.layoutManager = LinearLayoutManager(context)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}