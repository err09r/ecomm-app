package com.app.ecommerceapp.presentation.screens

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.annotation.LayoutRes
import com.app.feature_home.R
import com.app.feature_home.databinding.FragmentFilterBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.app.core.R as CoreR


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
        initViews()
    }

    private fun initViews() {
        with(binding) {

            spinnerBrand.adapter = ArrayAdapter.createFromResource(
                requireContext(),
                R.array.brands,
                SPINNER_ITEM
            ).apply {
                setDropDownViewResource(SPINNER_ITEM)
            }

            spinnerPrice.adapter = ArrayAdapter.createFromResource(
                requireContext(),
                R.array.prices,
                SPINNER_ITEM
            ).apply {
                setDropDownViewResource(SPINNER_ITEM)
            }

            spinnerSize.adapter = ArrayAdapter.createFromResource(
                requireContext(),
                R.array.sizes,
                SPINNER_ITEM
            ).apply {
                setDropDownViewResource(SPINNER_ITEM)
            }

            btnFilterClose.setOnClickListener {
                dialog?.dismiss()
            }

            btnFilterDone.setOnClickListener {
                dialog?.dismiss()
            }
        }
    }

    private companion object {
        private const val DIM_VALUE = 0.0f

        @LayoutRes
        private val SPINNER_ITEM = CoreR.layout.item_spinner
    }
}
