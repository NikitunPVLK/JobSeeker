package com.example.jobseeker.ui.fragments

import android.graphics.drawable.AnimatedVectorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewAnimationUtils
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.annotation.DrawableRes
import androidx.core.animation.doOnEnd
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.jobseeker.R
import com.example.jobseeker.databinding.FragmentSearchByParametersBinding
import com.example.jobseeker.ui.viewmodels.SearchViewModel
import com.example.jobseeker.ui.viewmodels.ViewModelFactory
import kotlin.math.hypot

class SearchByParametersFragment : Fragment() {

    private val searchViewModel: SearchViewModel by activityViewModels {
        ViewModelFactory()
    }

    private var _binding: FragmentSearchByParametersBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchByParametersBinding.inflate(inflater, container, false)
        setupCategoryAutoComplete()
        setupExperienceSpinner()
        setupLocationAutoComplete()
        setupExtendedSearchButton()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchParametersFragment = this
    }

    private fun setupCategoryAutoComplete() {
        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_dropdown_item_1line,
            resources.getStringArray(R.array.category_array)
        )
        val categoryInput = binding.categoryInput
        categoryInput.setAdapter(adapter)
    }

    private fun setupExperienceSpinner() {
        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_dropdown_item_1line,
            resources.getStringArray(R.array.experience_array)
        )
        val experienceSpinner = binding.experienceSpinner
        experienceSpinner.adapter = adapter
    }

    private fun setupLocationAutoComplete() {
        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_dropdown_item_1line,
            resources.getStringArray(R.array.location_array)
        )
        val locationInput = binding.locationInput
        locationInput.setAdapter(adapter)
    }
    private fun setupExtendedSearchButton() {
        with(binding.extendedSearchButton) {
            isSaveEnabled = false
            isCheckable = true
            isToggleCheckedStateOnClick = true
            setOnClickListener {
                val extendedSearchHideRevealFunc : () -> Unit
                val icon: AnimatedVectorDrawable
                if (isChecked) {
                    icon = getAnimatedVectorDrawable(R.drawable.animated_ic_open_extended_search)
                    extendedSearchHideRevealFunc = { revealExtendedSearch() }
                } else {
                    icon = getAnimatedVectorDrawable(R.drawable.animated_ic_close_extended_search)
                    extendedSearchHideRevealFunc = { hideExtendedSearch() }
                }
                this.icon = icon
                icon.start()
                extendedSearchHideRevealFunc()
            }
        }
    }

    private fun revealExtendedSearch() {
        val extendedSearchCardView = binding.extendedSearchCardView
        extendedSearchCardView.isVisible = true
        ViewAnimationUtils.createCircularReveal(extendedSearchCardView,
            extendedSearchCardView.width / 2,
            0,
            0f,
            hypot(extendedSearchCardView.width.toFloat(), extendedSearchCardView.height.toFloat())
        )
            .setDuration(400)
            .start()
    }

    private fun hideExtendedSearch() {
        val extendedSearchCardView = binding.extendedSearchCardView
        val animator = ViewAnimationUtils.createCircularReveal(extendedSearchCardView,
            extendedSearchCardView.width / 2,
            0,
            hypot(extendedSearchCardView.width.toFloat(), extendedSearchCardView.height.toFloat()),
            0f
        )
        animator.duration = 400
        animator.doOnEnd {
            extendedSearchCardView.isVisible = false
        }
        animator.start()
    }

    private fun getAnimatedVectorDrawable(@DrawableRes id: Int): AnimatedVectorDrawable {
        return ResourcesCompat.getDrawable(
            resources,
            id,
            requireContext().theme
        ) as AnimatedVectorDrawable
    }

    fun submitSearchParameters() {
        searchViewModel.searchVacanciesByParameters(
            binding.keyWordsInput.text.toString(),
            binding.categoryInput.text.toString(),
            binding.experienceSpinner.selectedItem.toString(),
            binding.locationInput.text.toString()
        )
        val action =
            SearchByParametersFragmentDirections
                .actionSearchParametersFragmentToVacanciesListFragment(
                    Destination.PARAMETERS_SEARCH
                )
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}