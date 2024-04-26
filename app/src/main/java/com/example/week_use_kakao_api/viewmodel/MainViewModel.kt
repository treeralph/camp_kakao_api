package com.example.week_use_kakao_api.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.week_use_kakao_api.data.model.MemberResponse
import com.example.week_use_kakao_api.data.model.SearchResponse
import com.example.week_use_kakao_api.data.repository.SearchRepository
import com.example.week_use_kakao_api.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

class MainViewModel(
    private val searchRepository: SearchRepository
): ViewModel() {
    companion object {
        const val TAG = "MainViewModel"
    }

    private val _documents = mutableStateListOf<MemberResponse>()
    val documents: List<MemberResponse> = _documents

    private val _bookmarks = mutableStateListOf<MemberResponse>()
    val bookmarks: List<MemberResponse> = _bookmarks

    init { onSearch("kotlin") }

    private fun onSearch(query: String) {
        thread(start = true) {
            viewModelScope.launch(Dispatchers.IO) {
                val result = searchRepository.getSearchImage(query)
                Log.i(TAG, "onSearch: $result")
                _documents.addAll(result.documents)
            }
        }
    }

    fun addBookmark(memberResponse: MemberResponse) {
        _bookmarks.add(memberResponse)
        Log.e("TAG", "addBookmark: ${bookmarks.size}")
    }
    fun removeBookmark(memberResponse: MemberResponse) = _bookmarks.remove(memberResponse)
}

class MainViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        return MainViewModel(
            SearchRepository(RetrofitClient.search)
        ) as T
    }
}