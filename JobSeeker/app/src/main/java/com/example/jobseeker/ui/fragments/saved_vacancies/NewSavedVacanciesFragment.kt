package com.example.jobseeker.ui.fragments.saved_vacancies

import android.os.Bundle
import android.view.View
import com.example.jobseeker.ui.fragments.common.BaseVacanciesListFragment

class NewSavedVacanciesFragment(): BaseVacanciesListFragment() {

//    private var _binding: FragmentNewSavedVacanciesBinding? = null
//    private val binding
//        get() = _binding!!
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        _binding = FragmentNewSavedVacanciesBinding.inflate(inflater, container, false)
//        return binding.root
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vacancyViewModel.vacancies.observe(viewLifecycleOwner) { vacancies ->
            if (vacancies.isEmpty()) {
//                binding.emptyListTextView.visibility = View.VISIBLE
            } else {
                vacancies.let {
                    adapter.submitList(it)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
//        _binding = null
    }
}