package com.example.rickandmortyapitest.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.viewbinding.ViewBinding
import com.example.rickandmortyapitest.base.BaseDiffUtilItemCallback
import com.example.rickandmortyapitest.base.BaseRecyclerViewHolder
import com.example.rickandmortyapitest.common.extension.setOnSingleClickListener
import com.example.rickandmortyapitest.common.extension.toFormatDate
import com.example.rickandmortyapitest.databinding.ItemEpisodeBinding
import com.example.rickandmortyapitest.domain.models.RickAndMorty

class EpisodeAdapter(val onClick: (id: Int, name: String) -> Unit) :
    PagingDataAdapter<RickAndMorty.EpisodesItem, BaseRecyclerViewHolder<ViewBinding, RickAndMorty.EpisodesItem>>(
        BaseDiffUtilItemCallback()
    ) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseRecyclerViewHolder<ViewBinding, RickAndMorty.EpisodesItem> {
        return EpisodesViewHolder(
            ItemEpisodeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: BaseRecyclerViewHolder<ViewBinding, RickAndMorty.EpisodesItem>,
        position: Int
    ) {
        getItem(position)?.let { holder.onBind(it) }
    }

    inner class EpisodesViewHolder(
        binding: ItemEpisodeBinding
    ) : BaseRecyclerViewHolder<ItemEpisodeBinding, RickAndMorty.EpisodesItem>(binding) {

        override fun onBind(item: RickAndMorty.EpisodesItem) = with(binding) {
            itemName.text = item.name
            itemEpisode.text = item.episode
                ?.replace("S", "Season ")
                ?.replace("E", " Episode ")
            itemCreated.text = toFormatDate(item.created)
            itemAirDate.text = item.airDate

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