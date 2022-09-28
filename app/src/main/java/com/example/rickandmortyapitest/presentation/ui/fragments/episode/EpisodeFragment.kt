package com.example.rickandmortyapitest.presentation.ui.fragments.episode

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
import com.example.rickandmortyapitest.databinding.FragmentEpisodeBinding
import com.example.rickandmortyapitest.presentation.ui.adapters.EpisodeAdapter
import com.example.rickandmortyapitest.presentation.ui.adapters.paging.LoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EpisodesFragment : BaseFragment<FragmentEpisodeBinding, EpisodesViewModel>(
    R.layout.fragment_episode
) {

    override val binding by viewBinding(FragmentEpisodeBinding::bind)
    override val viewModel by viewModels<EpisodesViewModel>()
    private val adapter: EpisodeAdapter = EpisodeAdapter(this::onClickToDetail)
    private val args by navArgs<EpisodesFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun initialize() {
        setupRecycler()
    }

    private fun setupRecycler() = with(binding) {
        episodesRecycler.layoutManager = LinearLayoutManager(requireContext())
        episodesRecycler.adapter = adapter.withLoadStateFooter(LoadStateAdapter {
            adapter.retry()
        })
    }

    override fun setupViews() {
        setupProgressBar()
    }

    private fun setupProgressBar() = with(binding) {
        adapter.addLoadStateListener {
            if (view != null) {
                episodesLoading.isVisible = it.refresh is LoadState.Loading
                episodesRecycler.isVisible = it.refresh !is LoadState.Error
                container.containerNotFound.isVisible =
                    it.refresh is LoadState.Error && it.append is LoadState.NotLoading
            }
        }
    }

    override fun setupRequests() {
        if (args.episode == "") {
            viewModel.getEpisodes("")
        } else {
            viewModel.getEpisodesWithFilter("", args.episode)
        }
    }

    override fun setupObserves() {
        if (args.episode == "") {
            viewModel.episodesSate.observe(viewLifecycleOwner, {
                lifecycleScope.launch {
                    adapter.submitData(it)
                }
            })
        } else {
            viewModel.episodesFilterSate.observe(viewLifecycleOwner, {
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

        if (args.episode == "") {
            searchView.submitSearch { viewModel.getEpisodes(it.toString()) }
        } else {
            searchView.submitSearch {
                viewModel.getEpisodesWithFilter(
                    it.toString(),
                    args.episode
                )
            }
        }

        searchItem.setOnActionExpandListener(searchView) { hideKeyboard() }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.filter) {
            findNavController().navigate(
                EpisodesFragmentDirections
                    .actionEpisodesFragmentToFilterDialogFragment(
                        getString(R.string.episodes_filter_type)
                    )
            )
        }
        return super.onOptionsItemSelected(item)
    }

    private fun onClickToDetail(id: Int, name: String) {
        val action = EpisodesFragmentDirections
            .actionEpisodesFragmentToEpisodeDetailFragment(id, name)
        findNavController().navigate(action)
    }
}