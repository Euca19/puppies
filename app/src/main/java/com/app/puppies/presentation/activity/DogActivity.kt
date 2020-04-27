package com.app.puppies.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import com.app.puppies.R
import com.app.puppies.databinding.ActivityDogBinding

class DogActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView<ActivityDogBinding>(this,
            R.layout.activity_dog
        )
    }

}
