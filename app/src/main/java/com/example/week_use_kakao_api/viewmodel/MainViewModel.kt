package com.example.week_use_kakao_api.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.week_use_kakao_api.data.model.MemberResponse
import com.example.week_use_kakao_api.data.model.MemberResponseUIState
import com.example.week_use_kakao_api.data.model.SearchResponse
import com.example.week_use_kakao_api.data.repository.SearchRepository
import com.example.week_use_kakao_api.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainViewModel(
    private val searchRepository: SearchRepository
): ViewModel() {

    private val _documents = mutableStateListOf<MemberResponseUIState>()
    val documents: List<MemberResponseUIState> = _documents

    fun onSearch(query: String) = viewModelScope.launch(Dispatchers.IO) {
        runCatching {
            val result = searchRepository.getSearchImage(query)
            result.documents.forEach { document ->
                Log.i("TAG", "onSearch: $document")
                _documents.add(document.toUIState())
            }
        }.onFailure {
            Log.e("TAG", "onSearch: $it", )
        }

    }

    fun onBookmarkChanged(index: Int, bookmarked: Boolean) {
        _documents[index] = _documents[index].copy(bookmarked = bookmarked)
    }
}

class MainViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        return MainViewModel(
            SearchRepository(RetrofitClient.search)
        ) as T
    }
}