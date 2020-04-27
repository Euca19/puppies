package com.app.puppies

import com.app.puppies.data.remote.PuppiesApi
import com.app.puppies.domain.model.BreedImage
import com.app.puppies.domain.repository.BreedRepository
import com.app.puppies.domain.usecase.GetBreedImageUseCase
import com.nhaarman.mockitokotlin2.doNothing
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.Mockito.`when`


/**
 * Created by Eucaris Guerrero on 26-04-20.
 */
class GetBreedListImageUseCaseTest: KoinTest {

    val useCase : GetBreedImageUseCase by inject()
    val apiService: PuppiesApi by inject()
    val breedRepository : BreedRepository by inject()

    @Before
    fun setUp(){

        startKoin {
            modules(
                module {
                    single { mock<PuppiesApi>() }
                    single {
                        GetBreedImageUseCase(
                            get()
                        )
                    }
                    single { mock<BreedRepository>() }

                }
            )
        }
    }

    @After
    fun tearDown(){
        stopKoin()
    }


    @Test
    fun `get all BreedImage `() {
        val breedImageList = listOf(
            "https://images.dog.ceo/breeds/greyhound-italian/n02091032_10079.jpg",
            "https://images.dog.ceo/breeds/greyhound-italian/n02091032_102.jpg",
            "https://images.dog.ceo/breeds/greyhound-italian/n02091032_1030.jpg",
            "https://images.dog.ceo/breeds/greyhound-italian/n02091032_10300.jpg",
            "https://images.dog.ceo/breeds/greyhound-italian/n02091032_10314.jpg",
            "https://images.dog.ceo/breeds/greyhound-italian/n02091032_1034.jpg",
            "https://images.dog.ceo/breeds/greyhound-italian/n02091032_10352.jpg",
            "https://images.dog.ceo/breeds/greyhound-italian/n02091032_1041.jpg"

        )

        val breedImageMock: BreedImage = BreedImage(breedImageList,"success")



        whenever(apiService.getPuppiesBreedsImages("greyhound")).thenReturn(Single.just(breedImageMock))
        whenever(breedRepository.getBredImage("greyhound")).thenReturn(Single.just(breedImageMock))

        useCase.BreedImage("greyhound")

        assert(useCase.buildUseCaseSingle().blockingGet().status=="success")
        assert(useCase.buildUseCaseSingle().blockingGet().message.contains("https://images.dog.ceo/breeds/greyhound-italian/n02091032_10079.jpg"))



    }

}