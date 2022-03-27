package com.app.ecommerceapp.presentation.screens

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.ecommerceapp.constants.ResourceConstants.COLOR_DARK_BLUE
import com.app.ecommerceapp.extensions.changeNavigationBarColor
import com.app.ecommerceapp.extensions.hide
import com.app.ecommerceapp.extensions.show
import com.app.ecommerceapp.extensions.toSeparatedNumber
import com.app.ecommerceapp.helpers.UiState
import com.app.ecommerceapp.presentation.adapters.CartItemAdapter
import com.app.ecommerceapp.presentation.viewmodel.CartViewModel
import com.app.feature_cart.databinding.FragmentCartBinding
import com.bumptech.glide.RequestManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject
import com.app.core.R as CoreR

private const val TAG = "CartFragment"

@AndroidEntryPoint
class CartFragment : Fragment() {

    private var _binding: FragmentCartBinding? = null
    private val binding get() = requireNotNull(_binding)

    private val cartViewModel: CartViewModel by viewModels()

    @Inject
    lateinit var glideRequestManager: RequestManager

    private val cartAdapter by lazy(LazyThreadSafetyMode.NONE) {
        CartItemAdapter(glideRequestManager)
    }

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

                            val cartContent = state.content
                            tvTotal.text = getString(
                                CoreR.string.price_format,
                                cartContent.total.toSeparatedNumber()
                            )
                            tvDelivery.text = cartContent.delivery
                            cartAdapter.submitList(cartContent.cart)
                        }
                        is UiState.Error -> {
                            progressbarCart.root.hide()
                            cartLayout.hide()

                            tvErrorCart.root.apply {
                                this.show()
                                text = getString(CoreR.string.network_error)
                            }

                            Log.d(TAG, "Received error: ${state.error}")
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