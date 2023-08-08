package com.example.jobseeker.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.GridLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jobseeker.application.JobSeekerApplication
import com.example.jobseeker.adapter.VacancyListAdapter
import com.example.jobseeker.databinding.FragmentVacanciesListBinding
import com.example.jobseeker.viewmodel.SavedVacancyViewModel
import com.example.jobseeker.viewmodel.SearchViewModel
import com.example.jobseeker.viewmodel.VacancyViewModelFactory
import kotlinx.coroutines.launch

class VacanciesListFragment : Fragment() {
    private val searchViewModel: SearchViewModel by activityViewModels {
        VacancyViewModelFactory()
    }

    private val savedVacancyViewModel: SavedVacancyViewModel by activityViewModels {
        VacancyViewModelFactory(
            (activity?.application as JobSeekerApplication).database.vacancyDao()
        )
    }

    private var _binding: FragmentVacanciesListBinding? = null
    private val binding get() = _binding!!

    private val navigationArgs: VacanciesListFragmentArgs by navArgs()

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
                            Toast.makeText(
                                requireContext(),
                                "This vacancy already saved",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }
            }
        )
        setupViews(adapter)

        setupTextViews()
    }

    private fun setupViews(adapter: VacancyListAdapter) {
        binding.vacancyList.adapter = adapter

        if (navigationArgs.destination == Destination.PARAMETERS_SEARCH) {
            with(binding) {
                keyWordsLabel.visibility = View.VISIBLE
                keyWordsTextView.text = searchViewModel.keyWords
                keyWordsCardView.visibility = View.VISIBLE

                categoryLabel.visibility = View.VISIBLE
                categoryCardView.visibility = View.VISIBLE
                categoryTextView.text = searchViewModel.category

                experienceLabel.visibility = View.VISIBLE
                experienceTextView.text = searchViewModel.experience
                experienceCardView.visibility = View.VISIBLE

                locationTextView.text = searchViewModel.location
                locationLabel.visibility = View.VISIBLE
                locationCardView.visibility = View.VISIBLE
            }

            searchViewModel.vacancies.observe(viewLifecycleOwner) { vacancies ->
                vacancies.let {
                    adapter.submitList(it)
                }
            }

        } else if (navigationArgs.destination == Destination.SKILLS_SEARCH) {

            binding.skillsGrid.visibility = View.VISIBLE
            for (skill in searchViewModel.skills) {
                val skillButton = Button(this@VacanciesListFragment.requireContext())
                skillButton.text = skill
                val params = GridLayout.LayoutParams()
                params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
                params.rowSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
                binding.skillsGrid.addView(skillButton, params)
            }

            searchViewModel.vacancies.observe(viewLifecycleOwner) {
                it.let {
                    adapter.submitList(it)
                }
            }
        }

        binding.vacancyList.layoutManager = LinearLayoutManager(context)
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