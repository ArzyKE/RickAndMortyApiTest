package com.example.rickandmortyapitest.presentation.ui.fragments.character.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickandmortyapitest.R
import com.example.rickandmortyapitest.base.BaseFragment
import com.example.rickandmortyapitest.common.extension.loadImage
import com.example.rickandmortyapitest.databinding.FragmentCharacterDetailBinding
import com.example.rickandmortyapitest.presentation.state.UIState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailFragment :
    BaseFragment<FragmentCharacterDetailBinding, CharacterDetailViewModel>(
        R.layout.fragment_character_detail
    ) {

    override val binding by viewBinding(FragmentCharacterDetailBinding::bind)
    override val viewModel by viewModels<CharacterDetailViewModel>()
    private val args by navArgs<CharacterDetailFragmentArgs>()

    override fun setupRequests() {
        viewModel.getCharacterById(args.id)
    }

    override fun setupObserves() = with(binding) {
        viewModel.characterDetailState.observe(viewLifecycleOwner, {
            characterDetailLoading.isVisible = it is UIState.Loading
            when (it) {
                is UIState.Loading -> {
                }
                is UIState.Error -> {
                }
                is UIState.Success -> {
                    it.data.apply {
                        detailName.text = name
                        detailImage.loadImage(image)
                        detailStatus.text = status
                        detailSpecies.text = species
                        detailLocation.text = location?.name
                        detailEpisode.text = origin?.name

                        when (status) {
                            "Alive" -> statusDot.setImageResource(R.drawable.ic_dot)

                            "Dead" -> statusDot.setImageResource(R.drawable.ic_dot_red)

                            "unknown" -> statusDot.setImageResource(R.drawable.ic_dot_gray)
                        }
                    }
                }
            }
        })
    }
}