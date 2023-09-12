package com.example.jobseeker.adapter

import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.jobseeker.R
import com.example.jobseeker.databinding.VacancyListItemBinding
import com.example.jobseeker.model.Vacancy

class VacancyListAdapter(
    private val onItemClicked: (Vacancy) -> Unit, private val onSaveButtonClicked: (Vacancy) -> Unit
) : ListAdapter<Vacancy, VacancyListAdapter.VacancyViewHolder>(DiffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VacancyViewHolder {
        return VacancyViewHolder(
            VacancyListItemBinding.inflate(
                LayoutInflater.from(parent.context)
            ),
            onSaveButtonClicked
        )
    }

    override fun onBindViewHolder(holder: VacancyViewHolder, position: Int) {
        val current = getItem(position)
        holder.itemView.setOnClickListener {
            onItemClicked(current)
        }
        holder.bind(current)
    }

    class VacancyViewHolder(
        private var binding: VacancyListItemBinding,
        private val onSaveButtonClicked: (Vacancy) -> Unit
    ) : ViewHolder(binding.root) {
        fun bind(vacancy: Vacancy) {
            binding.apply {
                vacancyTitle.text = vacancy.title
                if (vacancy.salary.isNotEmpty()) {
                    vacancySalary.text = vacancy.salary
                } else {
                    vacancySalary.visibility = View.GONE
                }
                vacancyCompany.text = vacancy.company
                vacancyLocation.text = vacancy.location
                vacancyShortDescription.text = Html.fromHtml(vacancy.description)
                setupSaveButton(vacancy)
                vacancySaveButton.setOnClickListener {
                    onSaveButtonClicked(vacancy)
                    setupSaveButton(vacancy)
                }
            }
        }

        private fun setupSaveButton(vacancy: Vacancy) {
            if (vacancy.isSaved) {
                binding.vacancySaveButton.text = "Delete"
            } else {
                binding.vacancySaveButton.text = "Save"
            }
        }
    }

    companion object {
        private val DiffCallBack = object : DiffUtil.ItemCallback<Vacancy>() {
            override fun areItemsTheSame(oldItem: Vacancy, newItem: Vacancy): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Vacancy, newItem: Vacancy): Boolean {
                return oldItem.url == newItem.url
            }
        }
    }

}