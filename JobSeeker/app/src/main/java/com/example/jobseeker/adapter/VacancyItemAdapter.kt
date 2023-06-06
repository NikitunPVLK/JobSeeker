package com.example.jobseeker.adapter

import android.content.Context
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.jobseeker.R
import com.example.jobseeker.model.Vacancy

class VacancyItemAdapter(
    private val context: Context,
    private val vacancies: List<Vacancy>
) : Adapter<VacancyItemAdapter.VacancyItemViewHolder>() {

    private lateinit var parent: ViewGroup

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VacancyItemViewHolder {
        this.parent = parent
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.vacancy_list_item, parent, false)

        return VacancyItemViewHolder(adapterLayout)
    }

    override fun getItemCount() = vacancies.size

    override fun onBindViewHolder(holder: VacancyItemViewHolder, position: Int) {
        val item = vacancies[position]
        with(holder) {
            titleTextView.text = item.title
            salaryTextView.text = item.salary
            companyTextView.text = item.company
            locationTextView.text = item.location
            shortDescriptionTextView.text = Html.fromHtml(item.description)
            itemView.setOnClickListener {
                openDetailedVacancy(item)
            }
        }
    }

    private fun openDetailedVacancy(item: Vacancy) {
        with(item) {
            parent.findNavController()
                .navigate(
                    R.id.action_vacanciesListFragment_to_detailedVacancyFragment,
                    bundleOf(
                        Pair("title", title),
                        Pair("salary", salary),
                        Pair("company", company),
                        Pair("location", location),
                        Pair("description", description)
                    )
                )
        }
    }

    class VacancyItemViewHolder(view: View) : ViewHolder(view) {
        val titleTextView: TextView = view.findViewById(R.id.vacancy_title)
        val salaryTextView: TextView = view.findViewById(R.id.vacancy_salary)
        val companyTextView: TextView = view.findViewById(R.id.vacancy_company)
        val locationTextView: TextView = view.findViewById(R.id.vacancy_location)
        val shortDescriptionTextView: TextView = view.findViewById(R.id.vacancy_short_description)
    }

}