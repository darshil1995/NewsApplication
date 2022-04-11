package com.app.newsapplication.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(
    tableName = "newsresponseitems"
)
data class NewsResponseItem(
    @PrimaryKey(autoGenerate = true)
    var pid: Int? = null,
    val active: Boolean?,
    val description: String?,
    val embedTypes: String?,
    val id: Int?,
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
) : Serializable {
    override fun hashCode(): Int {
        var result = pid.hashCode()
        if (description.isNullOrEmpty()) {
            result = 31 * result + description.hashCode()
        }
        if (embedTypes.isNullOrEmpty()) {
            result = 31 * result + embedTypes.hashCode()
        }
        if (language.isNullOrEmpty()) {
            result = 31 * result + language.hashCode()
        }
        if (readablePublishedAt.isNullOrEmpty()) {
            result = 31 * result + readablePublishedAt.hashCode()
        }
        if (readableUpdatedAt.isNullOrEmpty()) {
            result = 31 * result + readableUpdatedAt.hashCode()
        }
        if (source.isNullOrEmpty()) {
            result = 31 * result + source.hashCode()
        }
        if (sourceId.isNullOrEmpty()) {
            result = 31 * result + sourceId.hashCode()
        }
        if (title.isNullOrEmpty()) {
            result = 31 * result + title.hashCode()
        }
        if (type.isNullOrEmpty()) {
            result = 31 * result + type.hashCode()
        }
        if (version.isNullOrEmpty()) {
            result = 31 * result + version.hashCode()
        }

        return result
    }

}