package com.example.rickandmortyapitest.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.PagingDataAdapter
import androidx.viewbinding.ViewBinding
import com.example.rickandmortyapitest.base.BaseDiffUtilItemCallback
import com.example.rickandmortyapitest.base.BaseRecyclerViewHolder
import com.example.rickandmortyapitest.common.extension.setOnSingleClickListener
import com.example.rickandmortyapitest.common.extension.toFormatDate
import com.example.rickandmortyapitest.databinding.ItemLocationsBinding
import com.example.rickandmortyapitest.domain.models.RickAndMorty

class LocationAdapter(val onClick: (id: Int, name: String) -> Unit) :
    PagingDataAdapter<RickAndMorty.LocationsItem, BaseRecyclerViewHolder<ViewBinding, RickAndMorty.LocationsItem>>(
        BaseDiffUtilItemCallback()
    ) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseRecyclerViewHolder<ViewBinding, RickAndMorty.LocationsItem> {
        return LocationsViewHolder(
            ItemLocationsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: BaseRecyclerViewHolder<ViewBinding, RickAndMorty.LocationsItem>,
        position: Int
    ) {
        getItem(position)?.let { holder.onBind(it) }
    }

    inner class LocationsViewHolder(
        binding: ItemLocationsBinding
    ) : BaseRecyclerViewHolder<ItemLocationsBinding, RickAndMorty.LocationsItem>(binding) {

        override fun onBind(item: RickAndMorty.LocationsItem) = with(binding) {
            itemName.text = item.name
            itemType.text = item.type
            imagePlanet.isVisible = item.type == "Planet"
            itemCreated.text = toFormatDate(item.created)
            itemDimension.text = item.dimension

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