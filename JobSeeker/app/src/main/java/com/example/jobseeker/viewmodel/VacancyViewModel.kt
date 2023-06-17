package com.example.jobseeker.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jobseeker.network.VacanciesApi
import kotlinx.coroutines.launch

class VacancyViewModel : ViewModel() {
    private var _keyWords: String = ""
    val keyWords: String get() = _keyWords
    private var _category: String = ""
    val category: String get() = _category
    private var _experience: String = ""
    val experience: String get() = _experience
    private var _location: String = ""
    val location: String get() = _location

    private var _vacancies = MutableLiveData<List<Vacancy>>()
    val vacancies: LiveData<List<Vacancy>>
        get() = _vacancies

    fun submitSearchParameters(
        keyWords: String,
        category: String,
        experience: String,
        location: String
    ) {
        _keyWords = keyWords
        _category = category
        _experience = experience
        _location = location
        getVacancies()
    }

    private fun getVacancies() {
        viewModelScope.launch {
            try {
                _vacancies.value = VacanciesApi.retrofitService.getVacancies(
                    keyWords,
                    category,
                    getFormattedExperience(),
                    getRegion(),
                    getFormattedLocation()
                )
                Log.i("SearchViewModel", vacancies.toString())
            } catch (e: Exception) {
                println(e.message)
            }
        }
    }

    private fun getFormattedExperience(): String {
        return when (experience) {
            "No experience" -> ""
            "1 year" -> "ONE_YEAR"
            "3 years" -> "THREE_YEARS"
            "5 years" -> "FIVE_YEARS"
            else -> ""
        }
    }

    private fun getRegion(): String {
        if (location != "Remote" && location != "Relocation") {
            return "ukr"
        }
        return ""
    }

    private fun getFormattedLocation(): String {
        return when (location) {
            "Kyiv" -> "KYIV"
            "Lviv" -> "LVIV"
            "Dnipro" -> "DNIPRO"
            "Odesa" -> "ODESA"
            "Vinnytsya" -> "VINNYTSYA"
            "Kharkiv" -> "KHARKIV"
            "Ivano-Frankivsk" -> "IVANO_FRANKIVSK"
            "Ternopil" -> "TERNOPIL"
            "Chernivtsi" -> "CHERNIVTSI"
            "Lutsk" -> "LUTSK"
            "Cherkasy" -> "CHERKASY"
            "Poltava" -> "POLTAVA"
            "Uzhhorod" -> "UZHHOROD"
            "Rivne" -> "RIVNE"
            "Sumy" -> "SUMY"
            "Zhytomyr" -> "ZHYTOMYR"
            "Zaporizhzhia" -> "ZAPORIZHZHIA"
            "Khmelnytskyi" -> "KHMELNYTSKYI"
            "Kremenchuk" -> "KREMENCHUK"
            "Mykolaiv" -> "MYKOLAIV"
            "Chernihiv" -> "CHERNIHIV"
            "Kropyvnytskyi" -> "KROPYVNYTSKYI"
            "Kherson" -> "KHERSON"
            "Bila Cerkva" -> "BILA_CERKVA"
            "Donetsk" -> "DONETSK"
            "Kryvyi Rig" -> "KRYVYI_RIG"
            "Lugansk" -> "LUGANSK"
            "Mariupol" -> "MARIUPOL"
            "Drogobych" -> "DROGOBYCH"
            "Remote" -> "REMOTE"
            "Relocation" -> "RELOCATIO"
            else -> location
        }
    }
}