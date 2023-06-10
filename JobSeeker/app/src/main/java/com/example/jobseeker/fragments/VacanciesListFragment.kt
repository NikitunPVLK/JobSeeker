package com.example.jobseeker.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jobseeker.JobSeekerApplication
import com.example.jobseeker.adapter.VacancyListAdapter
import com.example.jobseeker.databinding.FragmentVacanciesListBinding
import com.example.jobseeker.model.*
import kotlinx.coroutines.launch

class VacanciesListFragment : Fragment() {
    private val vacancyViewModel: VacancyViewModel by activityViewModels {
        VacancyViewModelFactory()
    }
    private val savedVacancyViewModel: SavedVacancyViewModel by activityViewModels {
        VacancyViewModelFactory(
            (activity?.application as JobSeekerApplication).database.vacancyDao()
        )
    }

    private var _binding: FragmentVacanciesListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentVacanciesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = VacancyListAdapter(
            {
                val action =
                    VacanciesListFragmentDirections
                        .actionVacanciesListFragmentToDetailedVacancyFragment(
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
                if (it.isSaved) {
                    it.isSaved = false
                    savedVacancyViewModel.deleteVacancy(it)
                } else {
                    it.isSaved = true
                    val result = savedVacancyViewModel.addVacancyAsync(it)
                    lifecycleScope.launch {
                        if (!result.await()) {
                            Toast.makeText(requireContext(), "This vacancy already saved", Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        )
        binding.vacancyList.adapter = adapter
        vacancyViewModel.vacancies.observe(viewLifecycleOwner) { vacancies ->
            vacancies.let {
                adapter.submitList(it)
            }
        }
        binding.vacancyList.layoutManager = LinearLayoutManager(context)
        setupTextViews()
    }

    private fun setupTextViews() {
        with(binding) {
            keyWordsTextView.text = vacancyViewModel.keyWords
            categoryTextView.text = vacancyViewModel.category
            experienceTextView.text = vacancyViewModel.experience
            locationTextView.text = vacancyViewModel.location
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}