package com.example.week_use_kakao_api.ui.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.week_use_kakao_api.data.model.MemberResponse
import com.example.week_use_kakao_api.viewmodel.MainViewModel
import com.example.week_use_kakao_api.viewmodel.MainViewModelFactory

@Composable
fun BookmarkComposable(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel,
) {
    LazyColumn(modifier = modifier) {
        items(viewModel.bookmarks) { bookmark ->
            BookmarkColumnItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                document = bookmark
            )
        }
    }
}

@Composable
fun BookmarkColumnItem(
    document: MemberResponse,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            modifier = Modifier.size(120.dp),
            model = document.image_url,
            contentScale = ContentScale.Crop,
            contentDescription = "",
        )
        Text(
            modifier = Modifier.padding(start = 12.dp),
            text = document.display_sitename
        )
    }
}