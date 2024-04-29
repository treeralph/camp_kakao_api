package com.example.week_use_kakao_api

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.week_use_kakao_api.ui.composables.BookmarkComposable
import com.example.week_use_kakao_api.ui.composables.SearchComposable
import com.example.week_use_kakao_api.ui.theme.Week_use_kakao_apiTheme
import com.example.week_use_kakao_api.viewmodel.MainViewModel
import com.example.week_use_kakao_api.viewmodel.MainViewModelFactory
import kotlin.concurrent.thread

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels { MainViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        thread(start = true) {
            viewModel.onSearch("kotlin")
        }

        setContent {
            Week_use_kakao_apiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainComposable(viewModel)
                }
            }
        }
    }
}

@Composable
fun MainComposable(
    viewModel: MainViewModel
) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) {
        Box(modifier = Modifier.padding(it)) {
            Navigation(
                navController = navController,
                viewModel = viewModel
            )
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {

    val items = listOf("Search", "Bookmark")
    val icons = listOf(Icons.Filled.Search, Icons.Filled.Bookmark)
    var selectedItem by remember { mutableStateOf(0) }
    var currentRoute by remember { mutableStateOf(items[0]) }

    items.forEachIndexed { index, navigationItem ->
        if (navigationItem == currentRoute) {
            selectedItem = index
        }
    }

    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                alwaysShowLabel = true,
                icon = { Icon(icons[index], contentDescription = "") },
                label = { Text(item) },
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    currentRoute = item
                    navController.navigate(item) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Composable
fun Navigation(
    navController: NavHostController,
    viewModel: MainViewModel
) {
    NavHost(navController, startDestination = "Search") {
        composable("Search") {
            SearchComposable(
                modifier = Modifier.fillMaxSize(),
                viewModel = viewModel
            )
        }
        composable("Bookmark") {
            BookmarkComposable(
                modifier = Modifier.fillMaxSize(),
                viewModel = viewModel
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Week_use_kakao_apiTheme {

    }
}