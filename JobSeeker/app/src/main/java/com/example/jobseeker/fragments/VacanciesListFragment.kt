package com.example.jobseeker.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.jobseeker.adapter.VacancyItemAdapter
import com.example.jobseeker.databinding.FragmentVacanciesListBinding
import com.example.jobseeker.model.SearchParametersViewModel

class VacanciesListFragment : Fragment() {
    private val searchViewModel: SearchParametersViewModel by activityViewModels()

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
        searchViewModel.vacancies.observe(viewLifecycleOwner) { vacancies ->
            val vacanciesRecyclerView = binding.vacancyList
            vacanciesRecyclerView.adapter = VacancyItemAdapter(requireContext(), vacancies)
            vacanciesRecyclerView.setHasFixedSize(true)
        }
        setupTextViews()
    }

    private fun setupTextViews() {
        with(binding) {
            keyWordsTextView.text = searchViewModel.keyWords
            categoryTextView.text = searchViewModel.category
            experienceTextView.text = searchViewModel.experience
            locationTextView.text = searchViewModel.location
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}