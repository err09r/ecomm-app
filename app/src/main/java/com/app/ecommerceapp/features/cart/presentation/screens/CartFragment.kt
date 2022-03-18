package com.app.ecommerceapp.features.cart.presentation.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.ecommerceapp.R
import com.app.ecommerceapp.core.common.Constants.COLOR_DARK_BLUE
import com.app.ecommerceapp.core.common.Constants.ERROR_MSG
import com.app.ecommerceapp.core.common.UiState
import com.app.ecommerceapp.core.common.changeNavigationBarColor
import com.app.ecommerceapp.core.common.hide
import com.app.ecommerceapp.core.common.show
import com.app.ecommerceapp.core.common.setError
import com.app.ecommerceapp.core.common.toSeparatedNumber
import com.app.ecommerceapp.databinding.FragmentCartBinding
import com.app.ecommerceapp.features.cart.presentation.adapters.CartItemAdapter
import com.app.ecommerceapp.features.cart.presentation.viewmodel.CartViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class CartFragment : Fragment() {

    private var _binding: FragmentCartBinding? = null
    private val binding get() = requireNotNull(_binding)

    private val cartViewModel: CartViewModel by viewModels()

    private val cartAdapter = CartItemAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerviewCart.apply {
            adapter = cartAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        binding.appbarCart.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            cartViewModel.uiState.collectLatest { state ->
                with(binding) {

                    when (state) {
                        is UiState.Loading -> {
                            progressbarCart.root.show()
                            cartLayout.hide()
                        }
                        is UiState.Success -> {
                            requireActivity().changeNavigationBarColor(color = COLOR_DARK_BLUE)
                            progressbarCart.root.hide()
                            cartLayout.show()

                            val cartContent = state.content.first()
                            tvTotal.text = getString(
                                R.string.cart_price_format,
                                cartContent.total.toSeparatedNumber()
                            )
                            tvDelivery.text = cartContent.delivery
                            cartAdapter.submitList(cartContent.cart)
                        }
                        is UiState.Error -> {
                            progressbarCart.root.hide()
                            cartLayout.hide()
                            tvErrorCart.root.setError(message = ERROR_MSG)
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}