package com.example.jobseeker.viewmodel

class ParametersHandler {
    fun getFormattedExperience(experience: String): String {
        return when (experience) {
            "No experience" -> ""
            "1 year" -> "ONE_YEAR"
            "3 years" -> "THREE_YEARS"
            "5 years" -> "FIVE_YEARS"
            else -> ""
        }
    }

    fun getFormattedLocation(location: String): String {
        return when (location) {
            "Kyiv" -> "Київ"
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
            "Relocation" -> "RELOCATION"
            else -> location
        }
    }
}