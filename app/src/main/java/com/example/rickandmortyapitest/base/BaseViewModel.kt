package com.example.rickandmortyapitest.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapitest.common.resource.Resource
import com.example.rickandmortyapitest.presentation.state.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    protected fun <T> subscribeTo(
        state: MutableLiveData<UIState<T>>,
        request: () -> Flow<Resource<T>>
    ) {
        viewModelScope.launch {
            request().collect {
                when (it) {
                    is Resource.Loading -> {
                        state.value = UIState.Loading()
                    }
                    is Resource.Error -> {
                        it.message?.let { error ->
                            state.value = UIState.Error(error)
                        }
                    }
                    is Resource.Success -> {
                        it.data?.let { data ->
                            state.value = UIState.Success(data)
                        }
                    }
                }
            }
        }
    }

    protected fun <T> subscribeToAsync(
        state: MutableLiveData<UIState<List<T>>>,
        list: ArrayList<T>,
        request: () -> Flow<Resource<List<T>>>
    ) {
        viewModelScope.launch {
            request().collect {
                when (it) {
                    is Resource.Loading -> {
                        state.value = UIState.Loading()
                    }
                    is Resource.Error -> {
                        it.message?.let { error ->
                            state.value = UIState.Error(error)

                        }
                    }
                    is Resource.Success -> {
                        it.data?.let { data ->
                            list.addAll(data)
                            state.value = UIState.Success(list)
                        }
                    }
                }
            }
        }
    }
}