package com.example.myalbum.ui.home

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myalbum.databinding.FragmentHomeBinding
import com.example.myalbum.model.response.Album
import com.example.myalbum.utils.Utils.showErrorDialog
import dagger.android.support.DaggerFragment
import javax.inject.Inject

/*
* Home Fragment that host the UI
* */
class HomeFragment: DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding
    private var albumListListAdapter: AlbumsListAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)
        binding = FragmentHomeBinding.inflate(inflater, container, false).apply {
            viewModel = viewModel
            lifecycleOwner = viewLifecycleOwner
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.init()
        setUpViews()
        observeViewModel()
    }

    /**
     * Method for setting up views
     */
    private fun setUpViews() {
        albumListListAdapter = AlbumsListAdapter(viewModel.albumsList)
        albumListListAdapter?.onItemClick = {}

        binding.rvAlbums.isNestedScrollingEnabled = true
        binding.rvAlbums.layoutManager = LinearLayoutManager(context)
        binding.rvAlbums.adapter = albumListListAdapter
    }

    /**
     * Method for observing the view model live data
     */
    private fun observeViewModel() {

        viewModel.errorLiveData.observe(viewLifecycleOwner, Observer { errMessage ->
            showErrorDialog(requireContext(), null)
        })

        viewModel.albumsDBLiveData.observe(viewLifecycleOwner, Observer { albums ->
            var dbAlbumList = viewModel.getAlbumsFromDbObj(albums)
            albumListListAdapter?.updateAlbumsList(dbAlbumList)
            if(albums.isNullOrEmpty()) {
                binding.rvAlbums.visibility = View.GONE
                binding.errorTv.visibility = View.VISIBLE
            } else {
                binding.rvAlbums.visibility = View.VISIBLE
                binding.errorTv.visibility = View.GONE
            }
        })
    }

    companion object {
        fun newInstance(): HomeFragment{
            return HomeFragment()
        }
    }
}
