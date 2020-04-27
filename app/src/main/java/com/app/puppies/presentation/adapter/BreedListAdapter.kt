package com.app.puppies.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.app.puppies.R
import com.app.puppies.databinding.ItemBeedBinding
import kotlin.properties.Delegates


/**
 * Created by Eucaris Guerrero on 23-04-20.
 */
class BreedListAdapter(val adapterOnClick: (String, Int) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var breed: List<String> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val breedItemBinding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context), R.layout.item_beed, parent, false
        )
        return BreedListViewModel(breedItemBinding)
    }

    override fun getItemCount(): Int {
        return breed.size
    }

    private fun getItem(position: Int): String {
        return breed[position]
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as BreedListViewModel).onBind(getItem(position),adapterOnClick)
    }


    inner class BreedListViewModel(private val viewDataBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {

        fun onBind(
            breed: String,
            adapterOnClick: (String, Int) -> Unit
            )
        {
            (viewDataBinding as ItemBeedBinding).breed = breed
            viewDataBinding.puppiesBreedName.text = breed
            viewDataBinding.setClickListener {
                adapterOnClick(breed,position);
            }

        }
    }
}