package com.example.rickandmortyapitest.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.viewbinding.ViewBinding
import com.example.rickandmortyapitest.R
import com.example.rickandmortyapitest.base.BaseDiffUtilItemCallback
import com.example.rickandmortyapitest.base.BaseRecyclerViewHolder
import com.example.rickandmortyapitest.common.extension.loadImage
import com.example.rickandmortyapitest.common.extension.setOnSingleClickListener
import com.example.rickandmortyapitest.databinding.ItemCharacterBinding
import com.example.rickandmortyapitest.domain.models.RickAndMorty


class CharacterAdapter(val onClick: (id: Int, name: String) -> Unit) :
    PagingDataAdapter<RickAndMorty.CharactersItem, BaseRecyclerViewHolder<ViewBinding, RickAndMorty.CharactersItem>>(
        BaseDiffUtilItemCallback()
    ) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseRecyclerViewHolder<ViewBinding, RickAndMorty.CharactersItem> {
        return CharacterViewHolder(
            ItemCharacterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: BaseRecyclerViewHolder<ViewBinding, RickAndMorty.CharactersItem>,
        position: Int
    ) {
        getItem(position)?.let { holder.onBind(it) }
    }

    inner class CharacterViewHolder(
        binding: ItemCharacterBinding
    ) : BaseRecyclerViewHolder<ItemCharacterBinding, RickAndMorty.CharactersItem>(binding) {

        override fun onBind(item: RickAndMorty.CharactersItem) = with(binding) {
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
                getItem(absoluteAdapterPosition)?.apply {
                    this.id?.let { id ->
                        this.name?.let { name ->
                            onClick(id, name)
                        }
                    }
                }
            }
        }
    }
}