package com.example.rickandmortyapitest.common.extension

import android.content.Context
import android.graphics.Color
import android.view.MenuItem
import android.widget.SearchView
import com.example.rickandmortyapitest.R


fun SearchView.submitSearch(getFilterByName: (query: String?) -> Unit) {
    this.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            getFilterByName(query)
            return false
        }

        override fun onQueryTextChange(query: String?): Boolean {
            return false
        }
    })
}

fun SearchView.setTools(context: Context?) {
    this.queryHint = context?.getString(R.string.searching)
    this.setBackgroundColor(Color.parseColor("#0C0D0E"))
}

fun MenuItem.setOnActionExpandListener(searchView: SearchView, hideKeyboard: () -> Unit) {
    this.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
        override fun onMenuItemActionExpand(p0: MenuItem?): Boolean {
            searchView.isIconified = false
            searchView.onActionViewExpanded()
            return true
        }

        override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
            searchView.setQuery("", true)
            hideKeyboard()
            return true
        }
    })
}