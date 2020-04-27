package com.app.puppies.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.app.puppies.R
import com.app.puppies.databinding.ItemBreedImageBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlin.properties.Delegates


/**
 * Created by Eucaris Guerrero on 25-04-20.
 */
class BreedImageAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>()  {

     var breedImage: List<String> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val breedItemImageBinding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context), R.layout.item_breed_image, parent, false
        )
        return BreedImageViewModel(breedItemImageBinding)
    }

    override fun getItemCount(): Int {
       return breedImage.size
    }

    private fun getItem(position: Int): String {
        return breedImage[position]
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as BreedImageViewModel).onBind(getItem(position))
    }

    inner class BreedImageViewModel(private val viewDataBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {

        fun onBind(
            breedImage: String
        )
        {
            (viewDataBinding as ItemBreedImageBinding).url = breedImage
            viewDataBinding.puppiesImage.loadImage(breedImage)

        }
    }

    fun ImageView.loadImage(uri: String?) {
        val options = RequestOptions()
            .placeholder(R.drawable.ic_dog)
            .centerCrop()
            .error(R.mipmap.ic_launcher_round)
        Glide.with(this.context)
            .setDefaultRequestOptions(options)
            .load(uri)
            .into(this)
    }
}