package com.app.ecommerceapp.presentation.screens

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.Px
import androidx.annotation.StyleRes
import androidx.core.widget.TextViewCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.app.ecommerceapp.constants.ResourceConstants.COLOR_WHITE_BACKGROUND
import com.app.ecommerceapp.domain.models.DetailContent
import com.app.ecommerceapp.extensions.changeNavigationBarColor
import com.app.ecommerceapp.extensions.hide
import com.app.ecommerceapp.extensions.show
import com.app.ecommerceapp.extensions.toImageItem
import com.app.ecommerceapp.extensions.toSeparatedNumberWithFractionalPart
import com.app.ecommerceapp.helpers.UiState
import com.app.ecommerceapp.presentation.adapters.ImageDetailAdapter
import com.app.ecommerceapp.presentation.viewmodel.DetailViewModel
import com.app.feature_detail.R
import com.app.feature_detail.databinding.FragmentDetailBinding
import com.bumptech.glide.RequestManager
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject
import kotlin.math.abs
import com.app.navigation.R as NavR
import com.app.core.R as CoreR

private const val TAG = "DetailFragment"

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = requireNotNull(_binding)

    private val detailViewModel: DetailViewModel by viewModels()

    @Inject
    lateinit var glideRequestManager: RequestManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners()
        setTabTextStyle(binding.tabLayout.getTabAt(0), STYLE_BOLD)

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            detailViewModel.uiState.collectLatest { state ->
                with(binding) {

                    when (state) {
                        is UiState.Loading -> {
                            detailLayout.hide()
                            progressbarDetail.root.show()
                        }
                        is UiState.Success -> {
                            detailLayout.show()
                            progressbarDetail.root.hide()
                            requireActivity().changeNavigationBarColor(color = COLOR_WHITE_BACKGROUND)

                            val item = state.content
                            initViews(item)
                            setUpViewPager(item)
                        }
                        is UiState.Error -> {
                            progressbarDetail.root.hide()
                            detailLayout.hide()

                            tvErrorDetail.root.apply {
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

    private fun initViews(item: DetailContent) {
        with(binding) {

            btnDetailAdd.text =
                getString(
                    R.string.btn_detail_add,
                    item.price.toSeparatedNumberWithFractionalPart()
                )
            tvItemDetail.text = item.title
            ratingBar.rating = item.rating
            tvCpu.text = item.processor
            tvCamera.text = item.camera
            tvRam.text = item.ram
            tvSd.text = item.sd

            if (item.isFavorites) {
                ibFavDetail.setImageResource(R.drawable.ic_fav3_filled)
            }

            if (item.colors.isNotEmpty()) {
                rbColor1.background.setTint(Color.parseColor(item.colors[0]))
                rbColor2.background.setTint(Color.parseColor(item.colors[1]))
            }

            if (item.capacity.isNotEmpty()) {
                rbCapacity1.text = item.capacity[0]
                rbCapacity2.text = item.capacity[1]
            }
        }
    }

    private fun initListeners() {
        binding.appbarDetail.btnBack.setOnClickListener {
            findNavController().navigate(NavR.id.action_detailFragment_to_homeFragment)
        }

        binding.appbarDetail.btnCart.setOnClickListener {
            findNavController().navigate(NavR.id.action_detailFragment_to_cartFragment)
        }

        binding.btnDetailAdd.setOnClickListener {
            findNavController().navigate(NavR.id.action_detailFragment_to_cartFragment)
        }

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                setTabTextStyle(tab, STYLE_BOLD)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                setTabTextStyle(tab, STYLE_NORMAL)
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    private fun setTabTextStyle(tab: TabLayout.Tab?, @StyleRes styleRes: Int) {
        if (tab != null) {
            val views = arrayListOf<View>()
            tab.view.findViewsWithText(views, tab.text, View.FIND_VIEWS_WITH_TEXT)
            views.forEach { view ->
                if (view is TextView) {
                    TextViewCompat.setTextAppearance(view, styleRes)
                }
            }
        }
    }

    private fun setUpViewPager(item: DetailContent) {
        binding.viewpagerImages.apply {
            val imageList = item.images.map { it.toImageItem() }
            val newListSize = imageList.size + 2

            adapter = ImageDetailAdapter(glideRequestManager, imageList)
            offscreenPageLimit = 3
            clipToPadding = false
            clipChildren = false
            overScrollMode = RecyclerView.OVER_SCROLL_NEVER
            setCurrentItem(1, false)

            val pageTransformer = ViewPager2.PageTransformer { page, position ->
                page.scaleY = (0.85f + (1 - abs(position)) * 0.15f)
            }

            val compositePageTransformer = CompositePageTransformer().apply {
                addTransformer(MarginPageTransformer(PAGE_MARGIN))
                addTransformer(pageTransformer)
            }
            setPageTransformer(compositePageTransformer)

            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageScrollStateChanged(state: Int) {
                    super.onPageScrollStateChanged(state)

                    if (state != ViewPager2.SCROLL_STATE_IDLE) {
                        return
                    }

                    when (currentItem) {
                        0 -> setCurrentItem(newListSize - 2, false)
                        newListSize - 1 -> setCurrentItem(1, false)
                    }
                }
            })
        }
    }

    private companion object {
        @StyleRes
        private val STYLE_BOLD = R.style.TabTextBoldStyle

        @StyleRes
        private val STYLE_NORMAL = R.style.TabTextStyle

        @Px
        private const val PAGE_MARGIN = 30
    }
}
