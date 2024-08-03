package com.lavender.readmore

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lavender.readmore.screens.bookdata.BookDataScreen
import com.lavender.readmore.screens.booklist.BookListScreen
import com.lavender.readmore.ui.theme.ReadMoreTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * Main activity and entry point for the application.
 * This class sets up the theme and navigation routes.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val tag = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(tag, "onCreate: $savedInstanceState")

        setContent {
            ReadMoreTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ReadMoreNavHost()
                }
            }
        }
    }
}

@Composable
fun ReadMoreNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: String = "book-list"
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable("book-list") {
            BookListScreen(
                onBookData = { uuid: String -> navController.navigate("book-data/$uuid") }
            )
        }

        composable("book-data/{uuid}") {
            it.arguments?.getString("uuid")?.let { uuid ->
                BookDataScreen(
                    uuid = uuid,
                    onBack = navController::popBackStack
                )
            }
        }
    }
}
