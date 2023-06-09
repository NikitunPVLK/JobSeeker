package com.example.jobseeker.fragments

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.jobseeker.databinding.FragmentDetailedVacancyBinding

class DetailedVacancyFragment : Fragment() {
    private val navigationArgs: DetailedVacancyFragmentArgs by navArgs()

    private var _binding: FragmentDetailedVacancyBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailedVacancyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            with(navigationArgs) {
                vacancyTitle.text = title
                if (salary.isNotEmpty()) {
                    vacancySalary.text = salary
                }
                else {
                    vacancySalary.visibility = View.GONE
                }
                vacancyCompany.text = company
                vacancyLocation.text = location
                vacancyDescription.text = Html.fromHtml(description)
                vacancyUrl.text = buildUrlText(url)
            }
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