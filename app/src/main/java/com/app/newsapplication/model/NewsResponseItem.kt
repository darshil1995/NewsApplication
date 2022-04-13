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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as NewsResponseItem

        if (pid != other.pid) return false
        if (id != other.id) return false
        if (active != other.active) return false
        if (description != other.description) return false
        if (embedTypes != other.embedTypes) return false
        if (images != other.images) return false
        if (language != other.language) return false
        if (publishedAt != other.publishedAt) return false
        if (readablePublishedAt != other.readablePublishedAt) return false
        if (readableUpdatedAt != other.readableUpdatedAt) return false
        if (source != other.source) return false
        if (sourceId != other.sourceId) return false
        if (title != other.title) return false
        if (type != other.type) return false
        if (typeAttributes != other.typeAttributes) return false
        if (updatedAt != other.updatedAt) return false
        if (version != other.version) return false

        return true
    }
}