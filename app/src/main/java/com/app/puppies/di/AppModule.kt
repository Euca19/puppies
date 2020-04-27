package com.app.puppies.di

import com.app.puppies.data.remote.PuppiesApi
import com.app.puppies.BuildConfig
import com.app.puppies.data.repository.BreedRepositoryImp
import com.app.puppies.domain.repository.BreedRepository
import com.app.puppies.domain.usecase.GetBreedImageUseCase
import com.app.puppies.domain.usecase.GetBreedUseCase
import com.app.puppies.presentation.viewmodels.BreedImageViewModel
import com.app.puppies.presentation.viewmodels.BreedListViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**
 * Created by Eucaris Guerrero on 23-04-20.
 */

/**
 * dependency injection
 * */
val AppModule = module {

    single { createService(get()) }

    single { createRetrofit(get(), BuildConfig.API_URL,get(),get()) }

    single { createOkHttpClient() }

    single { createGsonConverterFactory() }

    single { createRxJava2CallAdapterFactory() }

    single { createGetBreedListUseCase(get()) }

    single { createGetBreedImageUseCase(get()) }

    single { createBreedRepository(get()) }


    viewModel {
        BreedListViewModel(
            get()
        )
    }

    viewModel {
        BreedImageViewModel(
            get(),
            getProperty("breedId")
        )
    }

}


fun createGetBreedListUseCase(
    breedRepository: BreedRepository
): GetBreedUseCase {
    return GetBreedUseCase(
        breedRepository
    )
}

fun createGetBreedImageUseCase(
    breedRepository: BreedRepository
): GetBreedImageUseCase {
    return GetBreedImageUseCase(
        breedRepository
    )
}

fun createOkHttpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
    return OkHttpClient.Builder()

        .connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor).build()
}

fun createService(retrofit: Retrofit): PuppiesApi {
    return retrofit.create(PuppiesApi::class.java)
}

fun createRetrofit(okHttpClient: OkHttpClient, url: String, gsonConverterFactory: GsonConverterFactory, rxJava2CallAdapterFactory: RxJava2CallAdapterFactory): Retrofit {
    return Retrofit.Builder()
        .baseUrl(url)
        .addCallAdapterFactory(rxJava2CallAdapterFactory)
        .client(okHttpClient)
        .addConverterFactory(gsonConverterFactory).build()
}

fun createBreedRepository(puppiesApi: PuppiesApi) : BreedRepository{
    return BreedRepositoryImp(puppiesApi)
}


fun createGsonConverterFactory(): GsonConverterFactory {
    return GsonConverterFactory.create()
}
fun createRxJava2CallAdapterFactory(): RxJava2CallAdapterFactory {
    return RxJava2CallAdapterFactory.create()
}



