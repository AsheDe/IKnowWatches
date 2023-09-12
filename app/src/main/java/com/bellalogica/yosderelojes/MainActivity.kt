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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bellalogica.yosderelojes.core.ui.Routes
import com.bellalogica.yosderelojes.game.model.Answers
import com.bellalogica.yosderelojes.game.model.ImageWrapper
import com.bellalogica.yosderelojes.game.model.Question
import com.bellalogica.yosderelojes.game.ui.FourStringsQuestion
import com.bellalogica.yosderelojes.game.ui.GameScreen
import com.bellalogica.yosderelojes.game.ui.GameState
import com.bellalogica.yosderelojes.game.ui.GameViewModel
import com.bellalogica.yosderelojes.game.ui.UserState
import com.bellalogica.yosderelojes.start.ui.StartScreen
import com.bellalogica.yosderelojes.start.ui.StartScreenEvents
import com.bellalogica.yosderelojes.start.ui.StartScreenViewModel
import com.bellalogica.yosderelojes.ui.theme.YoSéDeRelojesTheme
import kotlinx.coroutines.delay

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
                            val startViewModel = viewModel<StartScreenViewModel>()
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
                                        }

                                        is StartScreenEvents.ShowMessage -> {
                                            Toast.makeText(
                                                this@MainActivity,
                                                it.message.asString(this@MainActivity),
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }

                                        else -> {}
                                    }
                                }
                            }

                            LaunchedEffect(key1 = true) {
                                delay(5000L)
                                startViewModel.getGameStatus()
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

                            //val gameViewModel = viewModel<GameViewModel>()
                            //gameViewModel.gameLevel.value

                            val listOfQuestion = remember {
                            mutableStateOf(
                            listOf(
                                Question.FourPicturesQuestion(
                                    questionText = "¿Cuál es el nombre de este reloj?", answers = listOf(
                                        Answers.ImageAnswer(ImageWrapper.ResourcesImage(R.mipmap.vacheron), true),
                                        Answers.ImageAnswer(ImageWrapper.ResourcesImage(R.mipmap.vacheron), false),
                                        Answers.ImageAnswer(ImageWrapper.ResourcesImage(R.mipmap.vacheron), false),
                                        Answers.ImageAnswer(ImageWrapper.ResourcesImage(R.mipmap.vacheron), false)
                                    )
                                ),

                                Question.FourTextsQuestion(
                                    questionText = "¿Cuál es el nombre de este reloj?",
                                    leadingImage = ImageWrapper.ResourcesImage(R.mipmap.vacheron),
                                    answers = listOf(
                                        Answers.TextAnswer("answ 1", true),
                                        Answers.TextAnswer("answ 2", false),
                                        Answers.TextAnswer("answ 3", false),
                                        Answers.TextAnswer("answ 4", false)
                                    )
                                )
                            ).shuffled())}

                            GameScreen(gameState = GameState(
                                listOfLevelQuestions = listOfQuestion.value, userState = UserState()
                            ), onGameEvent = {
                                listOfQuestion.value = listOfQuestion.value.drop(1)
                            } )

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


