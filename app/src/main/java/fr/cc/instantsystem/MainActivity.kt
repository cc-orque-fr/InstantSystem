package fr.cc.instantsystem

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import fr.cc.instantsystem.ui.theme.InstantSystemTheme
import fr.cc.instantsystem.ui.theme.compose.initLoadingScreen
import fr.cc.instantsystem.viewmodels.LatestNewsUiState
import fr.cc.instantsystem.viewmodels.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InstantSystemTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    val value by mainViewModel.uiState.collectAsState()

                    when(value ){
                        is LatestNewsUiState.LoadingCatgories -> {
                            initLoadingScreen()
                        }
                        is LatestNewsUiState.SuccessCategory -> {

                        }
                        else -> {}
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    InstantSystemTheme {
        Greeting("Android")
    }
}