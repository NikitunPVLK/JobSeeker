package com.example.jobseeker.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.jobseeker.databinding.FragmentSkillsBasedSearchBinding
import com.example.jobseeker.viewmodel.SkillBasedSearchViewModel
import com.example.jobseeker.viewmodel.VacancyViewModelFactory

/**
 * A simple [Fragment] subclass.
 * Use the [SkillsBasedSearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SkillsBasedSearchFragment : Fragment() {

    private val viewModel: SkillBasedSearchViewModel by activityViewModels {
        VacancyViewModelFactory()
    }

    private var _binding: FragmentSkillsBasedSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSkillsBasedSearchBinding.inflate(inflater, container, false)
        binding.searchButton.setOnClickListener {
            submitSearchParameters()
        }
        return binding.root
    }

    private fun submitSearchParameters() {
        viewModel.submitSkills(binding.skillsInput.text.toString())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}