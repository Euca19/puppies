package com.app.puppies.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.BindingAdapter
import com.app.puppies.databinding.ActivityDogBinding
import androidx.databinding.DataBindingUtil.setContentView
import androidx.lifecycle.MutableLiveData
import com.app.puppies.R

class DogActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView<ActivityDogBinding>(this,
            R.layout.activity_dog
        )
    }

}
