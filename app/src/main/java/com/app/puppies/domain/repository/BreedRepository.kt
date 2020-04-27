package com.app.puppies.domain.repository

import com.app.puppies.domain.model.Breed
import com.app.puppies.domain.model.BreedImage
import io.reactivex.Single

/**
 * Created by Eucaris Guerrero on 23-04-20.
 */
interface BreedRepository {

    fun getBreedList(): Single<Breed>

    fun getBredImage(breedImage : String): Single<BreedImage>
}