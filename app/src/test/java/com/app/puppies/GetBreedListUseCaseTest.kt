package com.app.puppies

import com.app.puppies.data.remote.PuppiesApi
import com.app.puppies.domain.model.Breed
import com.app.puppies.domain.repository.BreedRepository
import com.app.puppies.domain.usecase.GetBreedUseCase
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


/**
 * Created by Eucaris Guerrero on 26-04-20.
 */
class GetBreedListUseCaseTest : KoinTest {

    val useCase : GetBreedUseCase by inject()
    val apiService: PuppiesApi by inject()
    val breedRepository : BreedRepository by inject()

    @Before
    fun setUp(){

        startKoin {
            modules(
                module {
                    single { mock<PuppiesApi>() }
                    single {
                        GetBreedUseCase(
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
    fun `get all Breed `() {
        val hashMap : HashMap<String, List<String>> = HashMap<String, List<String>> ()
        val breedList = listOf("shepherd", "norwegian")

        hashMap.put("affenpinscher",breedList)
        hashMap.put("african",breedList)
        hashMap.put("australian",breedList)

        val breed: Breed = Breed(hashMap,"success" )


        whenever(apiService.getBreedList()).thenReturn(Single.just(breed))
        whenever(breedRepository.getBreedList()).thenReturn(Single.just(breed))

        assert(useCase.buildUseCaseSingle().blockingGet().status=="success")
        assert(useCase.buildUseCaseSingle().blockingGet().message.contains("affenpinscher"))

    }

}