package com.bellalogica.yosderelojes.core.domain.repository

import com.bellalogica.yosderelojes.core.model.local.dao.LevelsDao
import com.bellalogica.yosderelojes.core.model.remote.IKnowWatchesAPI
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class IKnowWatchesRepositoryImplTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var levelDao: LevelsDao
    private lateinit var remoteAPI: IKnowWatchesAPI
    private lateinit var repository: IKnowWatchesRepositoryImpl

    @BeforeEach
    fun setUp() {
        mockWebServer = MockWebServer()
        remoteAPI = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(mockWebServer.url("/")).build().create()
        levelDao = FakeLevelDao()
        repository = IKnowWatchesRepositoryImpl(levelDao, remoteAPI)
    }

    @AfterEach
    fun tearDown() {
    }

    @Test
    fun `getNumOfLevels correcto`() = runTest {
        mockWebServer.enqueue(
            MockResponse().setResponseCode(200).setBody(jsonNumLevelsCorrecto.trimIndent())
        )
        val result = repository.getLevelsToLoadFromApi()
        assertTrue(result.isSuccess)
        assertEquals(2, result.getOrNull())
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `getNumOfLevels api failure`() = runTest {
        mockWebServer.enqueue(
            MockResponse().setResponseCode(404)
        )
        val result = repository.getLevelsToLoadFromApi()
        assertTrue(result.isSuccess)
        assertEquals(1, result.getOrNull()) // debe devolver el numLevel de la BD
    }

    @Test
    fun getLastLevelPlayed() {
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getLevel() = runTest {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(jsonLevel.trimIndent())
        )
        val result = repository.getLevel(1)
        assertTrue(result.isSuccess)
        assertEquals(6, result.getOrNull()?.size)
    }


    companion object {
        private const val jsonNumLevelsCorrecto = """
            {
                "numberOfLevels": 2
            }
        """
        private const val jsonLevel = """[
   {
      "questionType":"FourPictures",
      "questionText":"Cuál de estos relojes  se conoce como \"Big Pilot\"?",
      "leadingImage":"",
      "answers":[
         {
            "content":"http://192.168.1.12:8080/watches/casiof91.jpg",
            "isCorrectAnswer":false
         },
         {
            "content":"http://192.168.1.12:8080/watches/lacoultre.jpg",
            "isCorrectAnswer":false
         },
         {
            "content":"http://192.168.1.12:8080/watches/bigpilot.jpg",
            "isCorrectAnswer":true
         },
         {
            "content":"http://192.168.1.12:8080/watches/invicta.jpg",
            "isCorrectAnswer":false
         }
      ]
   },
   {
      "questionType":"FourPictures",
      "questionText":"Cual fue el primer reloj digital?",
      "leadingImage":"",
      "answers":[
         {
            "content":"http://192.168.1.12:8080/watches/hamiltopulsar.jpg",
            "isCorrectAnswer":true
         },
         {
            "content":"http://192.168.1.12:8080/watches/casiof91w.jpg",
            "isCorrectAnswer":false
         },
         {
            "content":"http://192.168.1.12:8080/watches/timex.jpg",
            "isCorrectAnswer":false
         },
         {
            "content":"http://192.168.1.12:8080/watches/swatchold.jpg",
            "isCorrectAnswer":false
         }
      ]
   },
   {
      "questionType":"FourPictures",
      "questionText":"Cuál de estas marcas no fabrica relojes suizos?",
      "leadingImage":"",
      "answers":[
         {
            "content":"http://192.168.1.12:8080/watches/certinalogo.jpg",
            "isCorrectAnswer":true
         },
         {
            "content":"http://192.168.1.12:8080/watches/junkerslogo.jpg",
            "isCorrectAnswer":false
         },
         {
            "content":"http://192.168.1.12:8080/watches/lecoultrelogo.jpg",
            "isCorrectAnswer":false
         },
         {
            "content":"http://192.168.1.12:8080/watches/tissotlogo.jpg",
            "isCorrectAnswer":false
         }
      ]
   },
   {
      "questionType":"FourTexts",
      "questionText":"El movimiento de este reloj se califica como ?",
      "leadingImage":"http://192.168.1.12:8080/watches/casiof91.jpg",
      "answers":[
         {
            "content":"despacito",
            "isCorrectAnswer":false
         },
         {
            "content":"cuarzo",
            "isCorrectAnswer":true
         },
         {
            "content":"mecanico",
            "isCorrectAnswer":false
         },
         {
            "content":"digital",
            "isCorrectAnswer":false
         }
      ]
   },
   {
      "questionType":"FourTexts",
      "questionText":"Un reloj de (5 ATM - 5 BAR - 50M) de resistencia al agua, no se daña si lo uso para:",
      "leadingImage":"http://192.168.1.12:8080/watches/timexwr50m.jpg",
      "answers":[
         {
            "content":"nadar",
            "isCorrectAnswer":false
         },
         {
            "content":"Lavarme las manos",
            "isCorrectAnswer":true
         },
         {
            "content":"bucear",
            "isCorrectAnswer":false
         },
         {
            "content":"no aguanta salpicaduras",
            "isCorrectAnswer":false
         }
      ]
   },
   {
      "questionType":"FourTexts",
      "questionText":"A mayor cantidad de rubíes o joyas en el reloj, tendremos",
      "leadingImage":"http://192.168.1.12:8080/watches/rubies.jpg",
      "answers":[
         {
            "content":"Mayor precisión",
            "isCorrectAnswer":false
         },
         {
            "content":"Mayor valor y precio",
            "isCorrectAnswer":false
         },
         {
            "content":"Mayor durabilidad",
            "isCorrectAnswer":true
         },
         {
            "content":"Mayor frecuencia",
            "isCorrectAnswer":false
         }
      ]
   }
]
"""

    }
}