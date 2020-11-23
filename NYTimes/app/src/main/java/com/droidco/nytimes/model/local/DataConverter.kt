package com.droidco.nytimes.model.local

import androidx.room.TypeConverter
import com.droidco.nytimes.model.data.Multimedia
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import timber.log.Timber


class DataConverter {
    @TypeConverter
    fun fromMultimediaList(multimedia: ArrayList<Multimedia?>?): String? {
        if (multimedia == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<ArrayList<Multimedia?>?>() {}.type
        return gson.toJson(multimedia, type)
    }

    @TypeConverter
    fun toMultimediaList(multimediaString: String?): ArrayList<Multimedia>? {
        if (multimediaString == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<ArrayList<Multimedia?>?>() {}.type
        return gson.fromJson<ArrayList<Multimedia>>(multimediaString, type)
    }
}