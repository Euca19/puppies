package com.app.puppies.data.remote

import com.app.puppies.domain.model.Breed
import com.app.puppies.domain.model.BreedImage
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

/**
 * Created by Eucaris Guerrero on 23-04-20.
 */
interface PuppiesApi {
    @GET("breeds/list/all")
    fun getBreedList(): Single<Breed>

    @GET("breed/{name}/images")
    fun getPuppiesBreedsImages(@Path(value = "name", encoded = true) platformID: String): Single<BreedImage>
}