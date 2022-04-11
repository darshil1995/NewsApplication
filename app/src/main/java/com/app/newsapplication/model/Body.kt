package com.app.newsapplication.model

data class Body(
    val containsAudio: Boolean,
    val containsPhotogallery: Boolean,
    val containsVideo: Boolean,
    val formatVersion: Int
)