package com.app.puppies.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.app.puppies.R
import com.app.puppies.databinding.FragmentContainerBinding
import com.app.puppies.presentation.adapter.BreedListAdapter
import com.app.puppies.presentation.viewmodels.BreedListViewModel
import com.app.puppies.presentation.views.HomeViewPagerFragmentDirections
import com.google.android.material.snackbar.Snackbar
import org.koin.android.viewmodel.ext.android.viewModel


/**
 * Created by Eucaris Guerrero on 23-04-20.
 */
class BreedListFragment: Fragment(), (String, Int)-> Unit {
    private val breedListViewModel: BreedListViewModel by viewModel()
    private var breedListAdapter: BreedListAdapter? = null
    private lateinit var  breedListBinding: FragmentContainerBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(this) {
            requireActivity().finish()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        breedListBinding = DataBindingUtil.inflate( inflater,
            R.layout.fragment_container, container, false)

        breedListBinding.lifecycleOwner=this

        return breedListBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        init()
        initObservers()
    }

    private fun init(){
        breedListViewModel.getBreedList()
        breedListAdapter = BreedListAdapter(this)
        breedListBinding.breedListViewLayout.breedRecyclerView.adapter = breedListAdapter

    }

    private fun initObservers(){
        breedListViewModel.breedData.observe(viewLifecycleOwner, Observer {
            if(it.size != null) {
                breedListAdapter?.breed = it
            }
        })

        breedListViewModel.messageData.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                Snackbar.make(breedListBinding.root, it, Snackbar.LENGTH_SHORT).show()
            }
        })

        breedListViewModel.loadingVisibility.observe(viewLifecycleOwner, Observer {
           state(it)
        })


        breedListBinding.emptyViewLayout.refreshButton.setOnClickListener{
            state(View.VISIBLE)
            breedListViewModel.getBreedList()

        }

    }

    override fun invoke(breed: String, action: Int) {
        val direction = HomeViewPagerFragmentDirections.actionViewPagerFragmentToPlantDetailFragment(breed)
        findNavController().navigate(direction)

    }

    fun state(state : Int) {

        when (state){
            View.VISIBLE->{//cargando
                breedListBinding.emptyViewLayout.loadingVisibility.visibility = View.VISIBLE
                breedListBinding.emptyViewLayout.emptyRefreshContainer.visibility = View.GONE
                breedListBinding.breedListViewLayout.rootLayout.visibility = View.GONE
            }

            View.GONE->{ //Termino de cargar
                breedListBinding.emptyViewLayout.loadingVisibility.visibility = View.GONE
               if (breedListAdapter!!.itemCount>0){
                   breedListBinding.emptyViewLayout.emptyRefreshContainer.visibility = View.GONE
                   breedListBinding.breedListViewLayout.rootLayout.visibility = View.VISIBLE
               }else{
                   breedListBinding.emptyViewLayout.emptyRefreshContainer.visibility = View.VISIBLE
                   breedListBinding.breedListViewLayout.rootLayout.visibility = View.GONE
               }

            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        breedListViewModel.clearStatus()
    }
}