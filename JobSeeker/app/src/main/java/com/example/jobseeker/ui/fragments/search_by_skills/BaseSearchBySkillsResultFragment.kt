package com.example.jobseeker.ui.fragments.search_by_skills

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.GridLayout
import androidx.fragment.app.activityViewModels
import com.example.jobseeker.databinding.FragmentSearchBySkillsResultBinding
import com.example.jobseeker.ui.fragments.common.BaseSearchResultFragment
import com.example.jobseeker.ui.viewmodels.ViewModelFactory
import com.example.jobseeker.ui.viewmodels.search_by_skills.SearchBySkillsViewModel

class BaseSearchBySkillsResultFragment : BaseSearchResultFragment() {

    override val searchViewModel: SearchBySkillsViewModel by activityViewModels {
        ViewModelFactory()
    }

    private var _binding: FragmentSearchBySkillsResultBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBySkillsResultBinding.inflate(inflater, container, false)
        vacancyList = binding.vacancyList
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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