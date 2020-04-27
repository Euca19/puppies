package com.app.puppies.domain.usecase

import com.android.artgallery.domain.usecase.base.SingleUseCase
import com.app.puppies.domain.model.Breed
import com.app.puppies.domain.repository.BreedRepository
import io.reactivex.Single

/**
 * Created by Eucaris Guerrero on 23-04-20.
 */
class GetBreedUseCase constructor(
    private val breedRepository: BreedRepository
) : SingleUseCase<Breed>() {

    override fun buildUseCaseSingle(): Single<Breed> {
        return breedRepository.getBreedList()
    }
}