package com.example.rickandmortyapitest.presentation.ui.fragments.location

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickandmortyapitest.R
import com.example.rickandmortyapitest.base.BaseFragment
import com.example.rickandmortyapitest.common.extension.hideKeyboard
import com.example.rickandmortyapitest.common.extension.setOnActionExpandListener
import com.example.rickandmortyapitest.common.extension.setTools
import com.example.rickandmortyapitest.common.extension.submitSearch
import com.example.rickandmortyapitest.databinding.FragmentLocationBinding
import com.example.rickandmortyapitest.presentation.ui.adapters.LocationAdapter
import com.example.rickandmortyapitest.presentation.ui.adapters.paging.LoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LocationsFragment :
    BaseFragment<FragmentLocationBinding, LocationsViewModel>(R.layout.fragment_location) {

    override val binding by viewBinding(FragmentLocationBinding::bind)
    override  val viewModel by viewModels<LocationsViewModel>()
    private val adapter: LocationAdapter = LocationAdapter(this::onClickToDetail)
    private val args by navArgs<LocationsFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun initialize() {
        setupRecycler()
    }

    private fun setupRecycler() = with(binding) {
        locationsRecycler.layoutManager = LinearLayoutManager(requireContext())
        locationsRecycler.adapter = adapter.withLoadStateFooter(LoadStateAdapter {
            adapter.retry()
        })
    }

    override fun setupViews() {
        setupProgressBar()
    }

    private fun setupProgressBar() = with(binding) {
        adapter.addLoadStateListener {
            if (view != null) {
                locationsLoading.isVisible = it.refresh is LoadState.Loading
                locationsRecycler.isVisible = it.refresh !is LoadState.Error
                container.containerNotFound.isVisible =
                    it.refresh is LoadState.Error && it.append is LoadState.NotLoading
            }
        }
    }

    override fun setupRequests() {
        if (args.type == "" && args.dimension == "") {
            viewModel.getLocations("")
        } else {
            viewModel.getLocationsWithFilter("", args.type, args.dimension)
        }
    }

    override fun setupObserves() {
        if (args.type == "" && args.dimension == "") {
            viewModel.locationsState.observe(viewLifecycleOwner, {
                lifecycleScope.launch {
                    adapter.submitData(it)
                }
            })
        } else {
            viewModel.locationsFilterSate.observe(viewLifecycleOwner, {
                lifecycleScope.launch {
                    adapter.submitData(it)
                }
            })
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.toolbar_menu, menu)
        val searchItem = menu.findItem(R.id.search)
        val searchView = searchItem.actionView as SearchView

        searchView.setTools(context)

        if (args.type == "" && args.dimension == "") {
            searchView.submitSearch { viewModel.getLocations(it.toString()) }
        } else {
            searchView.submitSearch {
                viewModel.getLocationsWithFilter(
                    it.toString(),
                    args.type,
                    args.dimension
                )
            }
        }

        searchItem.setOnActionExpandListener(searchView) { hideKeyboard() }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.filter) {
            findNavController().navigate(
                LocationsFragmentDirections
                    .actionLocationsFragmentToFilterDialogFragment(
                        getString(R.string.locations_filter_type)
                    )
            )
        }
        return super.onOptionsItemSelected(item)
    }

    private fun onClickToDetail(id: Int, name: String) {
        val action = LocationsFragmentDirections
            .actionLocationsFragmentToLocationDetailFragment(id, name)
        findNavController().navigate(action)
    }
}