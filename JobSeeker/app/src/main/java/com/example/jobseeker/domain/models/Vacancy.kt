package com.example.jobseeker.domain.models

import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vacancy")
data class Vacancy(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "url")
    val url: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "company")
    val company: String,
    @ColumnInfo(name = "salary")
    val salary: String,
    @ColumnInfo(name = "location")
    val location: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "is_saved")
    var isSaved: Boolean = false
) : Parcelable {

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        with(dest) {
            writeInt(id)
            writeString(url)
            writeString(title)
            writeString(company)
            writeString(salary)
            writeString(location)
            writeString(description)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                writeBoolean(isSaved)
            }
        }
    }

    companion object CREATOR : Creator<Vacancy> {
        override fun createFromParcel(source: Parcel?): Vacancy {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                Vacancy(
                    source?.readInt()!!,
                    source.readString()!!,
                    source.readString()!!,
                    source.readString()!!,
                    source.readString()!!,
                    source.readString()!!,
                    source.readString()!!,
                    source.readBoolean()
                )
            } else {
                TODO("VERSION.SDK_INT < Q")
            }
        }

        override fun newArray(size: Int): Array<Vacancy?> {
            return arrayOfNulls(size)
        }
    }
}