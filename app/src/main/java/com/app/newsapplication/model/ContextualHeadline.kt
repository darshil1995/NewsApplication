package com.app.newsapplication.model

data class ContextualHeadline(
    val contextId: String,
    val contextualLineupSlug: String,
    val headline: String,
    val headlineType: String,
    val pubQueueId: String
)