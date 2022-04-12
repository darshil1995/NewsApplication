package com.app.newsapplication.db

import androidx.room.TypeConverter
import com.app.newsapplication.model.Images
import com.app.newsapplication.model.TypeAttributes
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

//Class for Converting objects using Gson for storing Database.
class Converters {

    @TypeConverter
    fun toImage(url: String): Images {
        val type = object : TypeToken<Images>() {}.type
        return Gson().fromJson(url, type)
    }

    @TypeConverter
    fun toString(images: Images): String {
        val type = object : TypeToken<Images>() {}.type
        return Gson().toJson(images, type)
    }

    @TypeConverter
    fun toTypeAttributes(str: String): TypeAttributes {
        val type = object : TypeToken<TypeAttributes>() {}.type
        return Gson().fromJson(str, type)
    }

    @TypeConverter
    fun toTypeAttributesJson(typeAttributes: TypeAttributes): String {
        val type = object : TypeToken<TypeAttributes>() {}.type
        return Gson().toJson(typeAttributes, type)
    }
}