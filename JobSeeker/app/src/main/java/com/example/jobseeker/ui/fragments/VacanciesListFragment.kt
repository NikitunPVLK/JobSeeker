package com.example.jobseeker.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.GridLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jobseeker.ui.application.JobSeekerApplication
import com.example.jobseeker.ui.adapters.VacancyListAdapter
import com.example.jobseeker.databinding.FragmentVacanciesListBinding
import com.example.jobseeker.ui.viewmodels.VacancyViewModel
import com.example.jobseeker.ui.viewmodels.SearchViewModel
import com.example.jobseeker.ui.viewmodels.ViewModelFactory

class VacanciesListFragment : Fragment() {
    private val searchViewModel: SearchViewModel by activityViewModels {
        ViewModelFactory()
    }

    private val vacancyViewModel: VacancyViewModel by activityViewModels {
        ViewModelFactory(
            (activity?.application as JobSeekerApplication).database.vacancyDao()
        )
    }

    private val navigationArgs: VacanciesListFragmentArgs by navArgs()

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
        val adapter = createListAdapter()
        setupViews(adapter)
    }

    private fun createListAdapter(): VacancyListAdapter {
        return VacancyListAdapter(
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
                vacancyViewModel.saveVacancy(it)
            }
        )
    }

    private fun setupViews(adapter: VacancyListAdapter) {
        binding.vacancyList.adapter = adapter

        when (navigationArgs.destination) {
            Destination.PARAMETERS_SEARCH -> setupParametersViews()
            Destination.SKILLS_SEARCH -> setupSkillsViews()
        }

        searchViewModel.vacancies.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        binding.vacancyList.layoutManager = LinearLayoutManager(context)
    }

    private fun setupParametersViews() {
        with(binding) {
            val searchParameters = searchViewModel.searchParameters

            keyWordsCardView.visibility = View.VISIBLE
            keyWordsLabel.visibility = View.VISIBLE
            keyWordsTextView.text = searchParameters.keyWords

            categoryCardView.visibility = View.VISIBLE
            categoryLabel.visibility = View.VISIBLE
            categoryTextView.text = searchParameters.category

            experienceCardView.visibility = View.VISIBLE
            experienceLabel.visibility = View.VISIBLE
            experienceTextView.text = searchParameters.experience

            locationCardView.visibility = View.VISIBLE
            locationLabel.visibility = View.VISIBLE
            locationTextView.text = searchParameters.location
        }
    }

    private fun setupSkillsViews() {
        binding.skillsGrid.visibility = View.VISIBLE
        for (skill in searchViewModel.skills) {
            val skillButton = Button(requireContext())
            skillButton.text = skill
            val params = GridLayout.LayoutParams()
            params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
            params.rowSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
            binding.skillsGrid.addView(skillButton, params)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}