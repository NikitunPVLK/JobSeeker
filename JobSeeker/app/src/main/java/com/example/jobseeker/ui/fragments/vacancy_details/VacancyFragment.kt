package com.example.jobseeker.ui.fragments.vacancy_details

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.jobseeker.databinding.FragmentVacancyBinding
import com.example.jobseeker.domain.models.Vacancy
import com.example.jobseeker.ui.fragments.common.BaseFragment
import com.example.jobseeker.ui.viewmodels.saved_vacancies.VacancyViewModel

class VacancyFragment : BaseFragment() {
    private val navigationArgs: VacancyFragmentArgs by navArgs()

    private lateinit var vacancyViewModel: VacancyViewModel

    private lateinit var vacancy: Vacancy

    private var _binding: FragmentVacancyBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentVacancyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vacancy = navigationArgs.vacancy

        vacancyViewModel = ViewModelProvider(
            requireActivity(),
            viewModelFactory
        )[VacancyViewModel::class.java]

        binding.apply {
            vacancyTitle.text = vacancy.title
            if (vacancy.salary.isNotEmpty()) {
                vacancySalary.text = vacancy.salary
            } else {
                vacancySalary.visibility = View.GONE
            }
            vacancyCompany.text = vacancy.company
            vacancyLocation.text = vacancy.location
            vacancyDescription.text = Html.fromHtml(vacancy.description)
            vacancyUrl.text = buildUrlText(vacancy.url)
            vacancySaveButton.setOnClickListener {
                vacancyViewModel.changeVacancySavedState(vacancy)
                setupSaveButtonText()
            }
            setupSaveButtonText()
        }
    }

    private fun setupSaveButtonText() {
        if (vacancy.isSaved) {
            binding.vacancySaveButton.text = "Delete"
        } else {
            binding.vacancySaveButton.text = "Save"
        }
    }

    private fun buildUrlText(url: String): String {
        return "Source: $url"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}