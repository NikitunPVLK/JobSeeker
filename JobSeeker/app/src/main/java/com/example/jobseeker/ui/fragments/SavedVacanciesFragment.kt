package com.example.jobseeker.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jobseeker.ui.application.JobSeekerApplication
import com.example.jobseeker.ui.adapters.VacancyListAdapter
import com.example.jobseeker.databinding.FragmentSavedVacanciesBinding
import com.example.jobseeker.ui.viewmodels.VacancyViewModel
import com.example.jobseeker.ui.viewmodels.ViewModelFactory

class SavedVacanciesFragment : Fragment() {
    private var _binding: FragmentSavedVacanciesBinding? = null
    private val binding get() = _binding!!

    private val vacancyViewModel: VacancyViewModel by activityViewModels {
        ViewModelFactory(
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
                    vacancyViewModel.deleteVacancy(it)
                }
            }
        )
        binding.vacancyList.adapter = adapter
        vacancyViewModel.vacancies.observe(viewLifecycleOwner) { vacancies ->
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