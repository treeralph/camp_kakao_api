package com.example.week_use_kakao_api.data.model

data class MemberResponseUIState(
    val collection: String,
    val datetime: String,
    val display_sitename: String,
    val doc_url: String,
    val image_url: String,
    val thumbnail_url: String,
    val height: Int,
    val width: Int,
    val bookmarked: Boolean,
)