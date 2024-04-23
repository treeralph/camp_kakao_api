package com.example.week_use_kakao_api.data.model

import com.google.gson.annotations.SerializedName


data class SearchResponse(
    @SerializedName("documents") val documents: List<MemberResponse>,
    @SerializedName("meta") val meta: Meta,
)

data class MemberResponse(
    @SerializedName("collection") val collection: String,
    @SerializedName("datetime") val datetime: String,
    @SerializedName("display_sitename") val display_sitename: String,
    @SerializedName("doc_url") val doc_url: String,
    @SerializedName("image_url") val image_url: String,
    @SerializedName("thumbnail_url") val thumbnail_url: String,
    @SerializedName("height") val height: Int,
    @SerializedName("width") val width: Int,
)

data class Meta(
    @SerializedName("is_end") val is_end: Boolean,
    @SerializedName("pageable_count") val pageable_count: Int,
    @SerializedName("total_count") val total_count: Int
)

/**
 *
 *  Response Format:
 *         {
 *             "collection": "news",
 *             "datetime": "2015-12-11T21:13:30.000+09:00",
 *             "display_sitename": "텐아시아",
 *             "doc_url": "http://v.media.daum.net/v/20151211211330856",
 *             "height": 640,
 *             "image_url": "http://t1.daumcdn.net/news/201512/11/10asia/20151211211330191kqtg.jpg",
 *             "thumbnail_url": "https://search3.kakaocdn.net/argon/130x130_85_c/Ks9THy0WTjD",
 *             "width": 540
 *         },
 *
 *         "is_end": false,
 *         "pageable_count": 3975,
 *         "total_count": 1133670
 * */