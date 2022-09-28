package com.example.rickandmortyapitest.common.extension

import android.view.View
import android.widget.SearchView
import com.example.rickandmortyapitest.common.singlelistener.OnSingleClickListener


fun SearchView.setOnSingleClickListener(l: View.OnFocusChangeListener) {
    setOnQueryTextFocusChangeListener(OnSingleClickListener(l))
}

fun SearchView.setOnSingleClickListener(l: (View) -> Unit) {
    setOnQueryTextFocusChangeListener(OnSingleClickListener(l))
}

fun View.setOnSingleClickListener(l: (View) -> Unit) {
    setOnClickListener(OnSingleClickListener(l))
}