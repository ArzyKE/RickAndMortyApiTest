package com.example.rickandmortyapitest.presentation.ui.fragments.episode.detail

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickandmortyapitest.R
import com.example.rickandmortyapitest.base.BaseFragment
import com.example.rickandmortyapitest.common.extension.toFormatDate
import com.example.rickandmortyapitest.databinding.FragmentEpisodeDetailBinding
import com.example.rickandmortyapitest.presentation.state.UIState
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class EpisodeDetailFragment :
    BaseFragment<FragmentEpisodeDetailBinding, EpisodeDetailViewModel>(
        R.layout.fragment_episode_detail
    ) {

    override val binding by viewBinding(FragmentEpisodeDetailBinding::bind)
    override val viewModel by viewModels<EpisodeDetailViewModel>()
    private val args by navArgs<EpisodeDetailFragmentArgs>()

    override fun setupRequests() {
        viewModel.getEpisodeById(args.id)
    }

    override fun setupObserves() = with(binding) {
        viewModel.episodeDetailState.observe(viewLifecycleOwner, {
            when (it) {
                is UIState.Loading -> {
                }
                is UIState.Error -> {
                }
                is UIState.Success -> {
                    it.data.apply {
                        detailName.text = name
                        detailAirDate.text = airDate
                        detailCreated.text = toFormatDate(created)
                        detailEpisode.text = episode
                            ?.replace("S", "Season ")
                            ?.replace("E", " Episode ")
                    }
                }
            }
        })
    }
}