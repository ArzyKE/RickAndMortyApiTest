package com.example.rickandmortyapitest.presentation.ui.adapters

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.rickandmortyapitest.R
import com.example.rickandmortyapitest.common.extension.loadImage
import com.example.rickandmortyapitest.common.extension.setOnSingleClickListener
import com.example.rickandmortyapitest.common.extension.toFormatDate
import com.example.rickandmortyapitest.databinding.ItemCharacterBinding
import com.example.rickandmortyapitest.databinding.ItemEpisodeBinding
import com.example.rickandmortyapitest.databinding.ItemLocationsBinding
import com.example.rickandmortyapitest.domain.models.RickAndMorty

sealed class SearchRecyclerViewHolder<out V : ViewBinding>(binding: V) :
    RecyclerView.ViewHolder(binding.root) {

    class CharactersViewHolder(
        private val onItemClick: (id: Int, name: String) -> Unit,
        private val binding: ItemCharacterBinding
    ) : SearchRecyclerViewHolder<ItemCharacterBinding>(binding) {

        fun onBind(item: RickAndMorty.GeneralItem) = with(binding) {
            itemName.text = item.name
            itemImage.loadImage(item.image)
            itemStatus.text = item.status
            itemSpecies.text = item.species
            itemLocation.text = item.location?.name
            itemEpisode.text = item.origin?.name

            when (item.status) {

                "Alive" -> statusDot.setImageResource(R.drawable.ic_dot)

                "Dead" -> statusDot.setImageResource(R.drawable.ic_dot_red)

                "unknown" -> statusDot.setImageResource(R.drawable.ic_dot_gray)
            }
            itemView.setOnSingleClickListener {
                item.id?.let { id ->
                    item.name?.let { name ->
                        onItemClick(id, name)
                    }
                }
            }
        }
    }

    class LocationsViewHolder(
        private val onItemClick: (id: Int, name: String) -> Unit,
        private val binding: ItemLocationsBinding
    ) : SearchRecyclerViewHolder<ItemLocationsBinding>(binding) {

        fun onBind(item: RickAndMorty.GeneralItem) = with(binding) {
            itemName.text = item.name
            itemType.text = item.type
            imagePlanet.isVisible = item.type == "Planet"
            itemCreated.text = toFormatDate(item.created)
            itemDimension.text = item.dimension

            itemView.setOnSingleClickListener {
                item.id?.let { id ->
                    item.name?.let { name ->
                        onItemClick(id, name)
                    }
                }
            }
        }
    }

    class EpisodesViewHolder(
        private val onItemClick: (id: Int, name: String) -> Unit,
        private val binding: ItemEpisodeBinding
    ) : SearchRecyclerViewHolder<ItemEpisodeBinding>(binding) {

        fun onBind(item: RickAndMorty.GeneralItem) = with(binding) {
            itemName.text = item.name
            itemEpisode.text = item.episode
                ?.replace("S", "Season ")
                ?.replace("E", " Episode ")
            itemCreated.text = toFormatDate(item.created)
            itemAirDate.text = item.airDate

            itemView.setOnSingleClickListener {
                item.id?.let { id ->
                    item.name?.let { name ->
                        onItemClick(id, name)
                    }
                }
            }
        }
    }
}