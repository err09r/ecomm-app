package com.app.ecommerceapp.features.home.presentation.screens

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.app.ecommerceapp.R
import com.app.ecommerceapp.core.common.Constants.COLOR_DARK_BLUE
import com.app.ecommerceapp.core.common.Constants.COLOR_ORANGE
import com.app.ecommerceapp.core.common.Constants.ERROR_MSG
import com.app.ecommerceapp.core.common.UiState
import com.app.ecommerceapp.core.common.changeTextColor
import com.app.ecommerceapp.core.common.hide
import com.app.ecommerceapp.core.common.setError
import com.app.ecommerceapp.core.common.show
import com.app.ecommerceapp.databinding.FragmentHomeBinding
import com.app.ecommerceapp.features.home.presentation.adapters.BestSellerItemAdapter
import com.app.ecommerceapp.features.home.presentation.adapters.HotItemAdapter
import com.app.ecommerceapp.features.home.presentation.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

private const val TAG = "HomeFragment"

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = requireNotNull(_binding)

    private val homeViewModel: HomeViewModel by viewModels()

    private val hotItemAdapter = HotItemAdapter { clickedId ->
        Log.d(TAG, "HotItem clicked: $clickedId")
        findNavController().navigate(R.id.action_mainFragment_to_detailFragment)
    }

    private val bestSellerItemAdapter = BestSellerItemAdapter { clickedId ->
        Log.d(TAG, "BestSeller clicked: $clickedId")
        findNavController().navigate(R.id.action_mainFragment_to_detailFragment)
    }

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
            layoutManager = GridLayoutManager(requireContext(), 2)
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

                            val content = state.content.first()
                            bestSellerItemAdapter.submitList(content.bestSellers)
                            hotItemAdapter.submitList(content.hotItems)
                        }
                        is UiState.Error -> {
                            homeLayout.hide()
                            progressbarHome.root.hide()
                            tvErrorHome.root.setError(message = ERROR_MSG)
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
                    changePreviousTextColor(prevCheckedIndex)
                    binding.tvHomePhones.changeTextColor(COLOR_ORANGE)
                    prevCheckedIndex = CATEGORY_PHONES_INDEX
                }
                CATEGORY_COMPUTER_INDEX -> {
                    changePreviousTextColor(prevCheckedIndex)
                    binding.tvHomeComputer.changeTextColor(COLOR_ORANGE)
                    prevCheckedIndex = CATEGORY_COMPUTER_INDEX
                }
                CATEGORY_HEALTH_INDEX -> {
                    changePreviousTextColor(prevCheckedIndex)
                    binding.tvHomeHealth.changeTextColor(COLOR_ORANGE)
                    prevCheckedIndex = CATEGORY_HEALTH_INDEX
                }
                CATEGORY_BOOKS_INDEX -> {
                    changePreviousTextColor(prevCheckedIndex)
                    binding.tvHomeBooks.changeTextColor(COLOR_ORANGE)
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
    }
}