package com.app.puppies.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.app.puppies.R
import com.app.puppies.databinding.FragmentBreedImageDetailBinding
import com.app.puppies.presentation.GridItemDecoration
import com.app.puppies.presentation.adapter.BreedImageAdapter
import com.app.puppies.presentation.viewmodels.BreedImageViewModel
import org.koin.android.ext.android.getKoin
import org.koin.android.viewmodel.ext.android.viewModel


/**
 * Created by Eucaris Guerrero on 25-04-20.
 */
class BreedImageDetailFragment: Fragment()  {

    private val args : BreedImageDetailFragmentArgs by navArgs()
    private var breedImageAdapter : BreedImageAdapter? = null
    private val breedImageViewModel : BreedImageViewModel by viewModel()
    private lateinit var fragmentBreedImageBinding : FragmentBreedImageDetailBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getKoin().setProperty("breedId",args.breedId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        fragmentBreedImageBinding = DataBindingUtil.inflate( inflater,
            R.layout.fragment_breed_image_detail, container, false)

        fragmentBreedImageBinding.lifecycleOwner=this

        fragmentBreedImageBinding.toolbar.setNavigationOnClickListener { view ->
            view.findNavController().navigateUp()
        }


        return fragmentBreedImageBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        init()
        initObservers()
    }

    private fun initObservers(){
        breedImageViewModel.urlList.observe(viewLifecycleOwner, Observer {
            breedImageAdapter?.breedImage = it
        })

        breedImageViewModel.loadingVisibility.observe(viewLifecycleOwner, Observer {
            fragmentBreedImageBinding.loadingVisibility.visibility = it

        })

    }

    fun init (){
        breedImageViewModel.getBreedImage()
        breedImageAdapter = BreedImageAdapter()
        fragmentBreedImageBinding.breedImageRecyclerView.layoutManager = GridLayoutManager(activity,2)
        fragmentBreedImageBinding.breedImageRecyclerView.addItemDecoration(GridItemDecoration(0, 2))
        fragmentBreedImageBinding.breedImageRecyclerView.adapter = breedImageAdapter


    }

}