package com.example.week_use_kakao_api.data.repository

import com.example.week_use_kakao_api.data.remote.SearchRemoteDataSource

class SearchRepository(
    private val dataSource: SearchRemoteDataSource
) {
    suspend fun getSearchImage(
        query: String,
        sort: String = "accuracy",
        page: Int = 1,
        size: Int = 80
    ) = dataSource.getSearchImage(query, sort, page, size)

}