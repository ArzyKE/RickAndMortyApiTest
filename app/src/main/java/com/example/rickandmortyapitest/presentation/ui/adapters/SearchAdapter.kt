package com.example.rickandmortyapitest.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.viewbinding.ViewBinding
import com.example.rickandmortyapitest.R
import com.example.rickandmortyapitest.base.BaseDiffUtilItemCallback
import com.example.rickandmortyapitest.databinding.ItemCharacterBinding
import com.example.rickandmortyapitest.databinding.ItemEpisodeBinding
import com.example.rickandmortyapitest.databinding.ItemLocationsBinding
import com.example.rickandmortyapitest.domain.models.RickAndMorty

class SearchAdapter(
    private val onItemCharacterClick: (id: Int, name: String) -> Unit,
    private val onItemLocationClick: (id: Int, name: String) -> Unit,
    private val onItemEpisodeClick: (id: Int, name: String) -> Unit
) :
    ListAdapter<RickAndMorty.GeneralItem, SearchRecyclerViewHolder<ViewBinding>>(
        BaseDiffUtilItemCallback()
    ) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchRecyclerViewHolder<ViewBinding> {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            R.layout.item_character -> SearchRecyclerViewHolder.CharactersViewHolder(
                onItemCharacterClick,
                ItemCharacterBinding.inflate(
                    inflater,
                    parent,
                    false
                )
            )
            R.layout.item_locations -> SearchRecyclerViewHolder.LocationsViewHolder(
                onItemLocationClick,
                ItemLocationsBinding.inflate(
                    inflater,
                    parent,
                    false
                )
            )
            R.layout.item_episode -> SearchRecyclerViewHolder.EpisodesViewHolder(
                onItemEpisodeClick,
                ItemEpisodeBinding.inflate(
                    inflater,
                    parent,
                    false
                )
            )
            else -> throw IllegalAccessException("Invalid viewType provided")
        }
    }

    override fun onBindViewHolder(
        holder: SearchRecyclerViewHolder<ViewBinding>,
        position: Int
    ) {
        when (getItemViewType(position)) {
            R.layout.item_character -> (
                    holder as SearchRecyclerViewHolder.CharactersViewHolder
                    ).onBind(getItem(position))
            R.layout.item_locations -> (
                    holder as SearchRecyclerViewHolder.LocationsViewHolder
                    ).onBind(getItem(position))
            R.layout.item_episode -> (
                    holder as SearchRecyclerViewHolder.EpisodesViewHolder
                    ).onBind(getItem(position))
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            getItem(position).status?.isNotEmpty() == true -> {
                R.layout.item_character
            }
            getItem(position).airDate?.isNotEmpty() == true -> {
                R.layout.item_episode
            }
            else -> {
                R.layout.item_locations
            }
        }
    }
}