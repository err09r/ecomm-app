@file:Suppress("MoveVariableDeclarationIntoWhen")

package com.app.ecommerceapp.presentation.screens

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IntRange
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.app.ecommerceapp.constants.ResourceConstants.COLOR_DARK_BLUE
import com.app.ecommerceapp.constants.ResourceConstants.COLOR_ORANGE
import com.app.ecommerceapp.extensions.changeTextColor
import com.app.ecommerceapp.extensions.hide
import com.app.ecommerceapp.extensions.show
import com.app.ecommerceapp.helpers.UiState
import com.app.ecommerceapp.presentation.adapters.BestSellerItemAdapter
import com.app.ecommerceapp.presentation.adapters.HotItemAdapter
import com.app.ecommerceapp.presentation.viewmodel.HomeViewModel
import com.app.feature_home.databinding.FragmentHomeBinding
import com.bumptech.glide.RequestManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject
import com.app.core.R as CoreR
import com.app.navigation.R as NavR

private const val TAG = "HomeFragment"

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = requireNotNull(_binding)

    private val homeViewModel: HomeViewModel by viewModels()

    @Inject
    lateinit var glideRequestManager: RequestManager

    private val hotItemAdapter by lazy(LazyThreadSafetyMode.NONE) {
        HotItemAdapter(glideRequestManager) { clickedId ->
            Log.d(TAG, "HotItem clicked: $clickedId")
            findNavController().navigate(NavR.id.action_homeFragment_to_detailFragment)
        }
    }

    private val bestSellerItemAdapter by lazy(LazyThreadSafetyMode.NONE) {
        BestSellerItemAdapter(glideRequestManager) { clickedId ->
            Log.d(TAG, "BestSeller clicked: $clickedId")
            findNavController().navigate(NavR.id.action_homeFragment_to_detailFragment)
        }
    }

    @IntRange(from = 0, to = LAST_CATEGORY_INDEX)
    private var prevCheckedIndex = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        setUpToolbar()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners()

        binding.viewpagerHotSales.apply {
            adapter = hotItemAdapter
        }

        binding.recyclerviewBestSellers.apply {
            layoutManager = GridLayoutManager(requireContext(), SPAN_COUNT)
            adapter = bestSellerItemAdapter
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            homeViewModel.uiState.collectLatest { state ->
                with(binding) {

                    when (state) {
                        is UiState.Loading -> {
                            homeLayout.hide()
                            progressbarHome.root.show()
                        }
                        is UiState.Success -> {
                            homeLayout.show()
                            progressbarHome.root.hide()

                            val content = state.content
                            bestSellerItemAdapter.submitList(content.bestSellers)
                            hotItemAdapter.submitList(content.hotItems)
                        }
                        is UiState.Error -> {
                            homeLayout.hide()
                            progressbarHome.root.hide()

                            tvErrorHome.root.apply {
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

    private fun initListeners() {
        binding.radiogroupHome.setOnCheckedChangeListener { radioGroup, buttonId ->

            val buttonIndex = radioGroup.indexOfChild(view?.findViewById(buttonId))
            when (buttonIndex) {
                CATEGORY_PHONES_INDEX -> {
                    changePreviousTextColor(index = prevCheckedIndex)
                    binding.tvHomePhones.changeTextColor(color = COLOR_ORANGE)
                    prevCheckedIndex = CATEGORY_PHONES_INDEX
                }
                CATEGORY_COMPUTER_INDEX -> {
                    changePreviousTextColor(index = prevCheckedIndex)
                    binding.tvHomeComputer.changeTextColor(color = COLOR_ORANGE)
                    prevCheckedIndex = CATEGORY_COMPUTER_INDEX
                }
                CATEGORY_HEALTH_INDEX -> {
                    changePreviousTextColor(index = prevCheckedIndex)
                    binding.tvHomeHealth.changeTextColor(color = COLOR_ORANGE)
                    prevCheckedIndex = CATEGORY_HEALTH_INDEX
                }
                CATEGORY_BOOKS_INDEX -> {
                    changePreviousTextColor(index = prevCheckedIndex)
                    binding.tvHomeBooks.changeTextColor(color = COLOR_ORANGE)
                    prevCheckedIndex = CATEGORY_BOOKS_INDEX
                }
            }
        }
    }

    private fun changePreviousTextColor(index: Int) {
        when (index) {
            CATEGORY_PHONES_INDEX -> {
                binding.tvHomePhones.changeTextColor(COLOR_DARK_BLUE)
            }
            CATEGORY_COMPUTER_INDEX -> {
                binding.tvHomeComputer.changeTextColor(COLOR_DARK_BLUE)
            }
            CATEGORY_HEALTH_INDEX -> {
                binding.tvHomeHealth.changeTextColor(COLOR_DARK_BLUE)
            }
            CATEGORY_BOOKS_INDEX -> {
                binding.tvHomeBooks.changeTextColor(COLOR_DARK_BLUE)
            }
        }
    }

    private fun setUpToolbar() {
        val toolbar = binding.appbarMain.toolbarMain
        (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)
    }

    private companion object {
        private const val CATEGORY_PHONES_INDEX = 0
        private const val CATEGORY_COMPUTER_INDEX = 1
        private const val CATEGORY_HEALTH_INDEX = 2
        private const val CATEGORY_BOOKS_INDEX = 3
        private const val LAST_CATEGORY_INDEX = 3L
        private const val SPAN_COUNT = 2
    }
}