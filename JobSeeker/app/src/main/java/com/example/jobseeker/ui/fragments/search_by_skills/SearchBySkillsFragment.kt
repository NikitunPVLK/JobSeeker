package com.example.jobseeker.ui.fragments.search_by_skills

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
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.jobseeker.R
import com.example.jobseeker.databinding.FragmentSearchBySkillsBinding
import com.example.jobseeker.ui.fragments.common.BaseFragment
import com.example.jobseeker.ui.viewmodels.search_by_skills.SearchBySkillsViewModel
import com.google.android.material.button.MaterialButton

class SearchBySkillsFragment : BaseFragment() {

    private val skills = mutableListOf<String>()

    private lateinit var searchViewModel: SearchBySkillsViewModel

    private var _binding: FragmentSearchBySkillsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        searchViewModel = ViewModelProvider(
            requireActivity(),
            viewModelFactory
        )[SearchBySkillsViewModel::class.java]

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
            val columnWeight = 1f
            val rowWeight = 1f
            params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, columnWeight)
            params.rowSpec = GridLayout.spec(GridLayout.UNDEFINED, rowWeight)
            params.marginEnd = resources
                .getDimension(R.dimen.skills_grid_margin_end)
                .toInt()
            binding.grid.addView(skillButton, params)
            if (skills.size % resources.getInteger(R.integer.search_by_skills_max_row_size) == 1) {
                runSearchButtonAnimation(
                    resources.getDimension(R.dimen.search_by_skills_search_button_offset_down)
                )
            }
            ObjectAnimator
                .ofFloat(skillButton, "alpha", 1.0f)
                .setDuration(resources.getInteger(R.integer.skill_animation_duration).toLong())
                .start()

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
                duration = resources.getInteger(R.integer.skill_animation_duration).toLong()
                doOnEnd {
                    skills.remove(clickedSkillButton.text)
                    binding.grid.removeView(clickedSkillButton)
                    if (skills.size % resources.getInteger(R.integer.search_by_skills_max_row_size) == 0) {
                        runSearchButtonAnimation(resources.getDimension(R.dimen.search_by_skills_search_button_offset_up))
                    }
                }
                start()
            }
        }
        skillButton.alpha = 0f
        return skillButton
    }

    private fun runSearchButtonAnimation(offset: Float) {
        val buttonPosition = binding.searchButton.translationY
        ObjectAnimator.ofFloat(binding.searchButton, "translationY", buttonPosition + offset)
            .apply {
                duration = resources
                    .getInteger(R.integer.search_by_skills_find_button_animation_duration)
                    .toLong()
                interpolator = FastOutSlowInInterpolator()
                start()
            }
    }

    private fun submitSearchParameters() {
        searchViewModel.searchVacanciesBySkills(skills)
        val action =
            SearchBySkillsFragmentDirections
                .actionSearchBySkillsFragmentToSearchBySkillsResultFragment()
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}