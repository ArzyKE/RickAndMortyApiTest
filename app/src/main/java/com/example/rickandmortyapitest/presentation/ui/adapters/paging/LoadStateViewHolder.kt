package com.example.rickandmortyapitest.presentation.ui.adapters.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyapitest.common.extension.setOnSingleClickListener
import com.example.rickandmortyapitest.databinding.ItemLoadFooterBinding

class LoadStateViewHolder(
    private val binding: ItemLoadFooterBinding,
    retry: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.btnRetry.setOnSingleClickListener {
            retry.invoke()
        }
    }

    fun onBind(loadState: LoadState) {
        binding.progressBar.isVisible = loadState is LoadState.Loading
        binding.btnRetry.isVisible = loadState is LoadState.Error
        binding.errorMsg.isVisible = loadState is LoadState.Error
    }

    companion object {
        fun create(parent: ViewGroup, retry: () -> Unit): LoadStateViewHolder {
            return LoadStateViewHolder(
                ItemLoadFooterBinding.inflate(
                    LayoutInflater
                        .from(parent.context), parent,
                    false
                ), retry
            )

        }
    }
}