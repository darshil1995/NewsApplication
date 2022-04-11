package com.app.newsapplication.model

import java.io.Serializable

data class TypeAttributes(
    val author: Author,
    val body: Body,
    val categories: List<Category>,
    val commentsSectionId: String,
    val contextualHeadlines: List<ContextualHeadline>,
    val deck: String,
    val displayComments: Boolean,
    val flag: String,
    val flags: Flags,
    val headline: Headline,
    val imageAspects: String,
    val imageLarge: String,
    val imageSmall: String,
    val mediaCaptionUrl: Any,
    val mediaDuration: Any,
    val mediaId: Any,
    val mediaStreamType: Any,
    val sectionLabels: List<String>,
    val sectionList: List<String>,
    val show: String,
    val showSlug: String,
    val trending: Trending,
    val url: String,
    val urlSlug: String
)


