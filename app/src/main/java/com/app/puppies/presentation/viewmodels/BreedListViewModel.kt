package com.app.puppies.presentation.viewmodels

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.app.puppies.MyApplication
import com.app.puppies.R
import com.app.puppies.domain.usecase.GetBreedUseCase
import com.app.puppies.utils.NetworkConnection

/**
 * Created by Eucaris Guerrero on 23-04-20.
 */
class BreedListViewModel constructor(
    private val getBreedUseCase: GetBreedUseCase

): BaseViewModel() {

    val messageData = MutableLiveData<String>()
    val breedData = MutableLiveData<List<String>>()
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()

    fun getBreedList(){
        loadingVisibility.value = View.VISIBLE
        if (NetworkConnection().isNetworkOnline()) {
            getBreedUseCase.execute(
                onSuccess = {
                    setFirsData(it.message)

                },
                onError = {
                    it.printStackTrace()
                    messageData.value = MyApplication.applicationContext().getString(R.string.no_internet)
                    loadingVisibility.value = View.GONE

                })
        }else{
            messageData.value =MyApplication.applicationContext().getString(R.string.no_internet)
            loadingVisibility.value = View.GONE

        }
    }

    private fun setFirsData(hashMap: HashMap<String, List<String>>){
        val firstDataItems: ArrayList<String> = arrayListOf()
        for (entry in hashMap.entries){
            entry.key.let {
                if(entry.value.isNotEmpty()){
                    for (type in entry.value){
                        firstDataItems.add(it.plus("/").plus(type))
                    }
                } else {
                    firstDataItems.add(it)
                }
            }
        }

        breedData.value = firstDataItems.sortedBy { it }
        loadingVisibility.value = View.GONE

    }

    fun clearStatus(){
        messageData.value=null
        loadingVisibility.value = null
    }
}