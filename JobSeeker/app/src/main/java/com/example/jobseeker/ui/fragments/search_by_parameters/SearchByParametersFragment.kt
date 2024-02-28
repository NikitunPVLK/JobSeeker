package com.example.jobseeker.ui.fragments.search_by_parameters

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
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.jobseeker.R
import com.example.jobseeker.databinding.FragmentSearchByParametersBinding
import com.example.jobseeker.domain.models.SearchParameters
import com.example.jobseeker.ui.fragments.common.BaseFragment
import com.example.jobseeker.ui.viewmodels.search_by_parameters.SearchByParametersViewModel
import kotlin.math.hypot

class SearchByParametersFragment : BaseFragment() {

    private lateinit var searchViewModel: SearchByParametersViewModel

    private var _binding: FragmentSearchByParametersBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchByParametersBinding.inflate(inflater, container, false)

        searchViewModel = ViewModelProvider(
            requireActivity(),
            viewModelFactory
        )[SearchByParametersViewModel::class.java]

        setupCategoryAutoComplete()
        setupExperienceSpinner()
        setupLocationAutoComplete()
        setupExtendedSearchButton()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchButton.setOnClickListener {
            submitSearchParameters()
        }
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
                val extendedSearchHideRevealFunc: () -> Unit
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
        ViewAnimationUtils.createCircularReveal(
            extendedSearchCardView,
            extendedSearchCardView.width / 2,
            0,
            0f,
            hypot(extendedSearchCardView.width.toFloat(), extendedSearchCardView.height.toFloat())
        )
            .setDuration(
                resources.getInteger(R.integer.reveal_extended_search_animation_duration).toLong()
            )
            .start()
    }

    private fun hideExtendedSearch() {
        val extendedSearchCardView = binding.extendedSearchCardView
        ViewAnimationUtils.createCircularReveal(
            extendedSearchCardView,
            extendedSearchCardView.width / 2,
            0,
            hypot(extendedSearchCardView.width.toFloat(), extendedSearchCardView.height.toFloat()),
            0f
        ).apply {
            duration = resources
                .getInteger(R.integer.hide_extended_search_animation_duration)
                .toLong()
            doOnEnd { extendedSearchCardView.isVisible = false }
            start()
        }
    }

    private fun getAnimatedVectorDrawable(@DrawableRes id: Int): AnimatedVectorDrawable {
        return ResourcesCompat.getDrawable(
            resources,
            id,
            requireContext().theme
        ) as AnimatedVectorDrawable
    }

    private fun submitSearchParameters() {
        val searchParameters = SearchParameters(
            binding.keyWordsInput.text.toString(),
            binding.categoryInput.text.toString(),
            binding.experienceSpinner.selectedItem.toString(),
            binding.locationInput.text.toString()
        )
        searchViewModel.searchVacanciesByParameters(searchParameters)
        val action =
            SearchByParametersFragmentDirections
                .actionSearchByParametersFragmentToSearchByParametersResultFragment()
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}