package com.example.jobseeker.fragments

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.jobseeker.databinding.FragmentDetailedVacancyBinding

class DetailedVacancyFragment : Fragment() {

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
        arguments?.let {
            binding.vacancyTitle.text = it.getString("title")
            binding.vacancySalary.text = it.getString("salary")
            binding.vacancyCompany.text = it.getString("company")
            binding.vacancyLocation.text = it.getString("location")
            binding.vacancyDescription.text = Html.fromHtml(it.getString("description"))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}