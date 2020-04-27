package com.app.puppies.presentation.viewmodels

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.app.puppies.MyApplication
import com.app.puppies.R
import com.app.puppies.domain.usecase.GetBreedImageUseCase
import com.app.puppies.utils.NetworkConnection

/**
 * Created by Eucaris Guerrero on 25-04-20.
 */
class BreedImageViewModel constructor(
    private val getBreedImageUseCase: GetBreedImageUseCase,
    private val breedId: String
): BaseViewModel() {

    val messageData = MutableLiveData<String>()
    val urlList = MutableLiveData<List<String>>()
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()

    fun getBreedImage() {
        loadingVisibility.value = View.VISIBLE
        if (NetworkConnection().isNetworkOnline()) {
            getBreedImageUseCase.BreedImage(breedId)
            getBreedImageUseCase.execute(
                onSuccess = {
                    loadingVisibility.value = View.GONE
                    urlList.value = it.message

                },
                onError = {
                    it.printStackTrace()
                    messageData.value = MyApplication.applicationContext().getString(R.string.no_internet)
                    loadingVisibility.value = View.GONE


                })
        }else{
            messageData.value = MyApplication.applicationContext().getString(R.string.no_internet)
            loadingVisibility.value = View.GONE
        }
    }

}