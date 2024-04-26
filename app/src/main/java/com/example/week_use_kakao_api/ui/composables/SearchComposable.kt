package com.example.week_use_kakao_api.ui.composables


import android.widget.AdapterView.OnItemLongClickListener
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.week_use_kakao_api.ui.theme.Week_use_kakao_apiTheme
import com.example.week_use_kakao_api.viewmodel.MainViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.week_use_kakao_api.data.model.MemberResponse
import com.example.week_use_kakao_api.viewmodel.MainViewModelFactory

@Composable
fun SearchComposable(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel,
) {
    LazyColumn(modifier = modifier) {
        items(viewModel.documents) { document ->
            SearchColumnItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                document = document
            ) { checked ->
                if(checked) viewModel.addBookmark(document)
                else viewModel.removeBookmark(document)
            }
        }
    }
}

@Composable
fun SearchColumnItem(
    modifier: Modifier = Modifier,
    document: MemberResponse,
    onToggleClickListener: (Boolean) -> Unit,
) {
    var checked by remember { mutableStateOf(false) }

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

        Spacer(modifier = Modifier.weight(1f))

        Switch(
            checked = checked,
            onCheckedChange = {
                checked = !checked
                onToggleClickListener(checked)
            }
        )

        Spacer(modifier = Modifier.size(12.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun ColumnItemPreview() {
    Week_use_kakao_apiTheme {
        // ColumnItem(modifier = Modifier.fillMaxWidth())
    }
}
