package com.example.jobseeker.fragments

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.GridLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.jobseeker.databinding.FragmentSkillsBasedSearchBinding
import com.example.jobseeker.viewmodel.SearchViewModel
import com.example.jobseeker.viewmodel.VacancyViewModelFactory

class SkillsBasedSearchFragment : Fragment() {

    private val skills = mutableListOf<String>()

    private val searchViewModel: SearchViewModel by activityViewModels {
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

        binding.addWordButton.setOnClickListener {
            addSkill()
        }

        val skillsInput = binding.skillsInput
        skillsInput.setOnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN) {
                addSkill()
                true
            } else {
                false
            }
        }
        return binding.root
    }

    private fun addSkill() {

        val skill = binding.skillsInput.text.toString().trim()
        if (skill.isNotEmpty()) {
            skills.add(skill)

            val skillButton = Button(this@SkillsBasedSearchFragment.requireContext())
            skillButton.text = skill
            skillButton.setOnClickListener {
                val clickedSkillButton = (it as Button)
                binding.grid.removeView(clickedSkillButton)
                skills.remove(clickedSkillButton.text)
            }

            val params = GridLayout.LayoutParams()
            params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
            params.rowSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
            binding.grid.addView(skillButton, params)

            binding.skillsInput.text.clear()
        }

    }

    private fun submitSearchParameters() {
        searchViewModel.searchVacanciesBySkills(skills)
        val action =
            SkillsBasedSearchFragmentDirections
                .actionSkillsBasedSearchFragmentToVacanciesListFragment(
                    Destination.SKILLS_SEARCH
                )
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}