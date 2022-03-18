package com.app.ecommerceapp.features.home.presentation.screens

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.app.ecommerceapp.R
import com.app.ecommerceapp.databinding.FragmentFilterBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class FilterFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentFilterBinding? = null
    private val binding get() = requireNotNull(_binding)

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.setOnShowListener {
            (view?.parent as ViewGroup).background =
                ColorDrawable(Color.TRANSPARENT)
        }
        return dialog
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFilterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireDialog().window?.attributes?.dimAmount = DIM_VALUE

        binding.spinnerBrand.adapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.brands,
            R.layout.item_spinner
        ).apply {
            setDropDownViewResource(R.layout.item_spinner)
        }

        binding.spinnerPrice.adapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.prices,
            R.layout.item_spinner
        ).apply {
            setDropDownViewResource(R.layout.item_spinner)
        }

        binding.spinnerSize.adapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.sizes,
            R.layout.item_spinner
        ).apply {
            setDropDownViewResource(R.layout.item_spinner)
        }

        binding.btnFilterClose.setOnClickListener {
            dialog?.dismiss()
        }

        binding.btnFilterDone.setOnClickListener {
            dialog?.dismiss()
        }
    }

    private companion object {
        private const val DIM_VALUE = 0.0f
    }
}
