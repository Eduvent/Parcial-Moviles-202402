package com.example.upc_pre_202402_cc238_ea_ventura_chancafe_eduardo_renato_u202212645.database

import androidx.room.TypeConverter
import com.example.upc_pre_202402_cc238_ea_ventura_chancafe_eduardo_renato_u202212645.models.Source
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromSource(source: Source?): String? {
        return Gson().toJson(source)
    }

    @TypeConverter
    fun toSource(sourceString: String?): Source? {
        val type = object : TypeToken<Source>() {}.type
        return Gson().fromJson(sourceString, type)
    }
}
