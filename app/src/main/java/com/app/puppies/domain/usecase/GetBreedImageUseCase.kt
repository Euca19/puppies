package com.app.puppies.domain.usecase

import com.android.artgallery.domain.usecase.base.SingleUseCase
import com.app.puppies.domain.model.BreedImage
import com.app.puppies.domain.repository.BreedRepository
import io.reactivex.Single

/**
 * Created by Eucaris Guerrero on 25-04-20.
 */
class GetBreedImageUseCase (
    private val breedRepository: BreedRepository
) : SingleUseCase<BreedImage>() {

    private lateinit var mbreedImage: String

    fun BreedImage(breedImage: String) {
        mbreedImage = breedImage
    }

    override fun buildUseCaseSingle(): Single<BreedImage> {
        return breedRepository.getBredImage(mbreedImage)

    }
}




