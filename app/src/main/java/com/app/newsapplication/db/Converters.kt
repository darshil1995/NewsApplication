package com.app.newsapplication.db

import androidx.room.TypeConverter
import com.app.newsapplication.model.Author
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
    fun urlToImages(url:String) : Images {
        return Images(url)
    }

//    @TypeConverter
//    fun TypeAttributesToName(typeAttributes: TypeAttributes) :String{
//        return typeAttributes.author.name
//    }

    @TypeConverter
    fun TypeAttributesToGSON(typeAttributes: TypeAttributes) :String{
        val gson=Gson()
        return gson.toJson(typeAttributes)
    }

    @TypeConverter
    fun GsonTOTypeAttributes(str: String) :TypeAttributes{
        val listType = object : TypeToken<TypeAttributes>() {}.type
        return Gson().fromJson(str, listType)
    }
}