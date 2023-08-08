package com.example.jobseeker.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.jobseeker.R
import com.example.jobseeker.databinding.FragmentSearchParametersBinding
import com.example.jobseeker.viewmodel.SearchViewModel
import com.example.jobseeker.viewmodel.VacancyViewModelFactory

class SearchParametersFragment : Fragment() {

    private val searchViewModel: SearchViewModel by activityViewModels {
        VacancyViewModelFactory()
    }

    private var _binding: FragmentSearchParametersBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchParametersBinding.inflate(inflater, container, false)
        setupCategoryAutoComplete()
        setupExperienceSpinner()
        setupLocationAutoComplete()
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

    fun submitSearchParameters() {
        searchViewModel.searchVacanciesByParameters(
            binding.keyWordsInput.text.toString(),
            binding.categoryInput.text.toString(),
            binding.experienceSpinner.selectedItem.toString(),
            binding.locationInput.text.toString()
        )
        val action =
            SearchParametersFragmentDirections
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