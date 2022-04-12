package com.app.newsapplication.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(
    tableName = "newsresponseitems"
)
data class NewsResponseItem(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "pid")
    @NonNull
    val pid: Long? = null,
    val id: Int?,
    val active: Boolean?,
    val description: String?,
    val embedTypes: String?,
    val images: Images?,
    val language: String?,
    val publishedAt: Long?,
    val readablePublishedAt: String?,
    val readableUpdatedAt: String?,
    val source: String?,
    val sourceId: String?,
    val title: String?,
    val type: String?,
    val typeAttributes: TypeAttributes?,
    val updatedAt: Long?,
    val version: String?
) : Serializable