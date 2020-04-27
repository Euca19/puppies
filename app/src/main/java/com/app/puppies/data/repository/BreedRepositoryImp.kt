package com.app.puppies.data.repository

import com.app.puppies.data.remote.PuppiesApi
import com.app.puppies.domain.model.Breed
import com.app.puppies.domain.model.BreedImage
import com.app.puppies.domain.repository.BreedRepository
import io.reactivex.Single

/**
 * Created by Eucaris Guerrero on 24-04-20.
 */
class BreedRepositoryImp(private val puppiesApi: PuppiesApi) : BreedRepository {

    override fun getBreedList(): Single<Breed> {
        return puppiesApi.getBreedList()
    }

    override fun getBredImage( breedImage : String): Single<BreedImage> {
        return  puppiesApi.getPuppiesBreedsImages(breedImage)

    }
}