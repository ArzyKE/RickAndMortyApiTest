package com.example.rickandmortyapitest.presentation.ui.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.rickandmortyapitest.R
import com.example.rickandmortyapitest.common.extension.setOnSingleClickListener
import com.example.rickandmortyapitest.databinding.FragmentDialogBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FilterDialogFragment : DialogFragment() {

    private lateinit var binding: FragmentDialogBinding
    private val args by navArgs<FilterDialogFragmentArgs>()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = FragmentDialogBinding.inflate(LayoutInflater.from(context))

        val dialog = AlertDialog.Builder(activity)
            .setView(binding.root)
            .create()
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        initialize()
        setupListeners()
        return dialog
    }

    private fun initialize() {
        when (args.type) {
            getString(R.string.characters_filter_type) -> {
                applyCharactersFilter()
            }
            getString(R.string.locations_filter_type) -> {
                applyLocationsFilter()
            }
            getString(R.string.episodes_filter_type) -> {
                applyEpisodesFilter()
            }
            else -> {}
        }
    }

    private fun setupListeners() = with(binding) {
        binding.applyButton.setOnSingleClickListener {
            when (args.type) {
                getString(R.string.characters_filter_type) -> {
                    val selectedStatus = binding.statusSpinner.selectedItem.toString()
                    val selectedGender = binding.genderSpinner.selectedItem.toString()

                    val action = FilterDialogFragmentDirections
                        .actionFilterDialogFragmentToCharactersFragment(
                            checkOnNothing(selectedStatus),
                            checkOnNothing(selectedGender)
                        )
                    findNavController().navigate(action)
                }
                getString(R.string.locations_filter_type) -> {
                    val textType = etType.text.toString()
                    val textDimension = etDimension.text.toString()
                    val action = FilterDialogFragmentDirections
                        .actionFilterDialogFragmentToLocationsFragment(textType, textDimension)
                    findNavController().navigate(action)
                }
                getString(R.string.episodes_filter_type) -> {
                    val textEpisode = etEpisode.text.toString()

                    val action = FilterDialogFragmentDirections
                        .actionFilterDialogFragmentToEpisodesFragment(textEpisode)
                    findNavController().navigate(action)
                }
            }
        }
    }

    private fun checkOnNothing(item: String): String {
        return if (item == "Nothing") {
            ""
        } else {
            item.lowercase()
        }
    }

    private fun applyCharactersFilter() = with(binding) {
        context?.let {
            etDimension.isVisible = false
            etEpisode.isVisible = false
            etType.isVisible = false
            statusSpinner.isVisible = true
            genderSpinner.isVisible = true
            ArrayAdapter.createFromResource(
                it,
                R.array.status_list,
                android.R.layout.simple_spinner_item
            ).also { adapter ->

                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.statusSpinner.adapter = adapter
            }
            ArrayAdapter.createFromResource(
                it,
                R.array.gender_list,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.genderSpinner.adapter = adapter
            }
        }
    }

    private fun applyLocationsFilter() = with(binding) {
        etDimension.isVisible = true
        etType.isVisible = true
        etEpisode.isVisible = false
        statusSpinner.isVisible = false
        genderSpinner.isVisible = false
    }

    private fun applyEpisodesFilter() = with(binding) {
        etDimension.isVisible = false
        etType.isVisible = false
        etEpisode.isVisible = true
        statusSpinner.isVisible = false
        genderSpinner.isVisible = false
    }
}