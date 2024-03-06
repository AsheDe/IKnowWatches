package com.bellalogica.yosderelojes

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bellalogica.yosderelojes.core.ui.Routes
import com.bellalogica.yosderelojes.game.ui.GameEvents
import com.bellalogica.yosderelojes.game.ui.GameScreen
import com.bellalogica.yosderelojes.game.ui.GameViewModel
import com.bellalogica.yosderelojes.start.ui.StartScreen
import com.bellalogica.yosderelojes.start.ui.StartScreenEvents
import com.bellalogica.yosderelojes.start.ui.StartScreenViewModel
import com.bellalogica.yosderelojes.ui.theme.YoSéDeRelojesTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YoSéDeRelojesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController, startDestination = Routes.START) {
                        composable(route = Routes.START, exitTransition = {
                            shrinkHorizontally(
                                animationSpec = tween(500)
                            )
                        }) {
                            val startViewModel = koinViewModel<StartScreenViewModel>()
                            StartScreen(
                                startScreenState = startViewModel.startState.value.startScreenInfo,
                                onEvent = startViewModel::onEnterGameClicked
                            )
                            LaunchedEffect(key1 = true) {
                                startViewModel.uiEvent.collect {
                                    when (it) {
                                        is StartScreenEvents.GoToGame -> {
                                            // pasar el game status
                                            navController.navigate(Routes.GAME)
                                            Toast.makeText(
                                                this@MainActivity,
                                                "Iniciando nivel ${ it.gameStatus.levelPlaying.toString() }",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }

                                        is StartScreenEvents.ShowMessage -> {
                                            Toast.makeText(
                                                this@MainActivity,
                                                it.message.asString(this@MainActivity),
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }

                                        StartScreenEvents.EnterGameClicked -> TODO()
                                    }
                                }
                            }

                            LaunchedEffect(key1 = true) {
                                startViewModel.getGameStarterStatus()
                            }
                        }

                        composable(route = Routes.GAME, exitTransition = {
                            shrinkHorizontally(
                                animationSpec = tween(500)
                            )
                        }, enterTransition = {
                            slideInHorizontally(
                                animationSpec = tween(500)
                            )
                        }) {

                            val gameViewModel = koinViewModel<GameViewModel>()
                            GameScreen(
                                gameState = gameViewModel.gameState.value
                            ) {
                                gameViewModel.onGameEvent(it)
                            }
                            LaunchedEffect(key1 = true ) {
                                gameViewModel.getGameLevelToStart()
                            }
                            LaunchedEffect(true) {
                                gameViewModel.uiEvent.collect {
                                    when (it) {
                                        GameEvents.OnExitGameScreen -> {
                                            navController.navigateUp()
                                        }

                                        is GameEvents.GameLevelLoaded -> {}
                                        GameEvents.OnGameEnded -> {}
                                        GameEvents.OnLevelEnded -> {}
                                        GameEvents.OnOpenScoreScreen -> {}
                                        is GameEvents.OnQuestionAnswered -> {}
                                        is GameEvents.OnQuestionTimeExpired -> {}
                                    }
                                }
                            }

                        }
                        composable(route = Routes.GAME_SCORE, exitTransition = {
                            slideOutVertically(
                                animationSpec = tween(500)
                            )
                        }) {


                        }
                    }
                }
            }
        }
    }

}


