package com.example.jobseeker.ui.fragments

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.GridLayout
import androidx.core.animation.doOnEnd
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import androidx.navigation.fragment.findNavController
import com.example.jobseeker.R
import com.example.jobseeker.databinding.FragmentSearchBySkillsBinding
import com.example.jobseeker.ui.viewmodels.SearchViewModel
import com.example.jobseeker.ui.viewmodels.ViewModelFactory
import com.google.android.material.button.MaterialButton

class SearchBySkillsFragment : Fragment() {

    private val skills = mutableListOf<String>()

    private val searchViewModel: SearchViewModel by activityViewModels {
        ViewModelFactory()
    }

    private var _binding: FragmentSearchBySkillsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBySkillsBinding.inflate(inflater, container, false)
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
            }
            false
        }

        return binding.root
    }

    private fun addSkill(): Boolean {
        val skill = binding.skillsInput.text.toString().trim()
        if (skill.isNotEmpty()) {
            skills.add(skill)

            val skillButton = createSkillButton(skill)

            val params = GridLayout.LayoutParams()
            params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
            params.rowSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
            params.marginEnd = 20
            binding.grid.addView(skillButton, params)
            if (skills.size % 3 == 1) {
                runButtonAnimation(135f)
            }
            ObjectAnimator.ofFloat(skillButton, "alpha", 1.0f).setDuration(300).start()

            binding.skillsInput.text.clear()

            return true
        }
        return false
    }

    private fun createSkillButton(buttonText: String): MaterialButton {
        val skillButton = MaterialButton(requireContext())
        skillButton.text = buttonText
        skillButton.icon = ResourcesCompat.getDrawable(
            resources,
            R.drawable.ic_delete_skill,
            requireContext().theme
        )
        skillButton.iconGravity = MaterialButton.ICON_GRAVITY_END
        skillButton.iconTint = ResourcesCompat.getColorStateList(
            resources,
            R.color.skill_button_text_color,
            requireContext().theme
        )
        skillButton.setBackgroundColor(
            ResourcesCompat.getColor(
                resources,
                R.color.white,
                requireContext().theme
            )
        )
        skillButton.setTextColor(
            ResourcesCompat.getColor(
                resources,
                R.color.skill_button_text_color,
                requireContext().theme
            )
        )
        skillButton.setOnClickListener {
            val clickedSkillButton = (it as Button)
            ObjectAnimator.ofFloat(skillButton, "alpha", 0.0f).apply {
                duration = 150
                doOnEnd {
                    skills.remove(clickedSkillButton.text)
                    binding.grid.removeView(clickedSkillButton)
                    if (skills.size % 3 == 0) {
                        runButtonAnimation(-135f)
                    }
                }
                start()
            }
        }
        skillButton.alpha = 0f
        return skillButton
    }

    private fun runButtonAnimation(offset: Float) {
        val buttonPosition = binding.searchButton.translationY
        ObjectAnimator.ofFloat(binding.searchButton, "translationY", buttonPosition + offset)
            .apply {
                duration = 200
                interpolator = FastOutSlowInInterpolator()
                start()
            }
    }

    private fun submitSearchParameters() {
        searchViewModel.searchVacanciesBySkills(skills)
        val action =
            SearchBySkillsFragmentDirections
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