package com.bellalogica.yosderelojes

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bellalogica.yosderelojes.game.model.Answers
import com.bellalogica.yosderelojes.game.model.ImageWrapper
import com.bellalogica.yosderelojes.game.model.Question
import com.bellalogica.yosderelojes.game.ui.Four_Pictures_Question
import com.bellalogica.yosderelojes.game.ui.Four_Strings_Question
import com.bellalogica.yosderelojes.start.ui.StartScreen
import com.bellalogica.yosderelojes.start.ui.StartScreenEvents
import com.bellalogica.yosderelojes.start.ui.StartScreenInfo
import com.bellalogica.yosderelojes.start.ui.StartScreenState
import com.bellalogica.yosderelojes.start.ui.StartScreenViewModel
import com.bellalogica.yosderelojes.ui.theme.YoSéDeRelojesTheme
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YoSéDeRelojesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController, startDestination = "start") {
                        composable(route = "start",
                            exitTransition = {
                                shrinkHorizontally(
                                    animationSpec = tween(500)
                                )
                            }
                        ) {
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
                                            navController.navigate("game")
                                        }
                                        is StartScreenEvents.ShowMessage -> {
                                            Toast.makeText(this@MainActivity, it.message.asString(this@MainActivity), Toast.LENGTH_SHORT).show()
                                        }
                                        else ->{}
                                    }
                                }
                            }

                            LaunchedEffect(key1 = true ) {
                                delay(5000L)
                                startViewModel.getGameStatus()
                            }
                        }

                        composable(route = "game",
                            exitTransition = {
                                shrinkHorizontally(
                                    animationSpec = tween(500)
                                )
                            },
                            enterTransition = {
                                slideInHorizontally(
                                    animationSpec = tween(500)
                                )
                            }
                        ) {
                           /* Four_Pictures_Question(question = Question.FourPicturesQuestion("question",
                                listOf(
                                    Answers.ImageAnswer(ImageWrapper.ResourcesImage(R.mipmap.vacheron)),
                                    Answers.ImageAnswer(ImageWrapper.ResourcesImage(R.mipmap.vacheron)),
                                    Answers.ImageAnswer(ImageWrapper.ResourcesImage(R.mipmap.vacheron)),
                                    Answers.ImageAnswer(ImageWrapper.ResourcesImage(R.mipmap.vacheron)),
                                )),
                                event = {})*/

                            Four_Strings_Question(
                                question = Question.FourTextsQuestion(
                                    questionText = "¿Cuál es el nombre de este reloj?",
                                    leadingImage = ImageWrapper.ResourcesImage(R.mipmap.vacheron),
                                    answers = listOf(
                                        Answers.StringAnswer("answ 1", true),
                                        Answers.StringAnswer("answ 2", false),
                                        Answers.StringAnswer("answ 3", false),
                                        Answers.StringAnswer("answ 4", false)
                                    )
                                ),
                                event = {}
                            )

                        }
                        composable(route = "my_scores",
                            exitTransition = {
                                slideOutVertically(
                                    animationSpec = tween(500)
                                )
                            }
                        ) {


                        }
                    }
                }
            }
        }
    }

}


