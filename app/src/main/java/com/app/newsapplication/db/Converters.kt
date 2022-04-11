package com.app.newsapplication.db

import androidx.room.TypeConverter
import com.app.newsapplication.model.Images
import com.app.newsapplication.model.TypeAttributes
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    @TypeConverter
    fun imagesToUrl(images: Images): String {
        return images.square_140
    }

    @TypeConverter
    fun urlToImages(url: String): Images {
        return Images(url)
    }

    @TypeConverter
    fun toTypeAttributes(userInfo: String): TypeAttributes {
        val type = object : TypeToken<TypeAttributes>() {}.type
        return Gson().fromJson(userInfo, type)
    }

    @TypeConverter
    fun toTypeAttributesJson(typeAttributes: TypeAttributes): String {
        val type = object : TypeToken<TypeAttributes>() {}.type
        return Gson().toJson(typeAttributes, type)
    }
}