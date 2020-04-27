package com.app.puppies.presentation.views

import android.os.Bundle
import androidx.navigation.NavDirections
import com.app.puppies.R

/**
 * Created by Eucaris Guerrero on 25-04-20.
 */
class HomeViewPagerFragmentDirections private constructor() {
    private data class ActionFragmentToBreedImageDetailFragment(
        val BreedId: String
    ) : NavDirections {
        override fun getActionId(): Int = R.id.action_fragment_breed_image_detail

        override fun getArguments(): Bundle {
            val result = Bundle()
            result.putString("breedId", this.BreedId)
            return result
        }
    }

    companion object {
        fun actionViewPagerFragmentToPlantDetailFragment(BreedId: String): NavDirections =
            ActionFragmentToBreedImageDetailFragment(BreedId)
    }
}