package com.app.ecommerceapp.presentation.adapters

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.ecommerceapp.domain.models.BestSellerItem
import com.app.ecommerceapp.extensions.toSeparatedNumber
import com.app.feature_home.R
import com.app.feature_home.databinding.ItemBestSellerBinding
import com.bumptech.glide.RequestManager
import javax.inject.Inject
import com.app.core.R as CoreR

class BestSellerItemAdapter @Inject constructor(
    private val glideRequestManager: RequestManager,
    private val listener: (Int) -> Unit
) : ListAdapter<BestSellerItem, BestSellerItemAdapter.BestSellerItemViewHolder>(Differ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BestSellerItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBestSellerBinding.inflate(inflater, parent, false)
        return BestSellerItemViewHolder(glideRequestManager, binding) { pos ->
            listener(pos)
        }
    }

    override fun onBindViewHolder(holder: BestSellerItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private object Differ : DiffUtil.ItemCallback<BestSellerItem>() {
        override fun areItemsTheSame(oldItem: BestSellerItem, newItem: BestSellerItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: BestSellerItem, newItem: BestSellerItem): Boolean {
            return oldItem == newItem
        }
    }

    class BestSellerItemViewHolder(
        private val glideRequestManager: RequestManager,
        private val binding: ItemBestSellerBinding,
        clickListener: (Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                clickListener(adapterPosition)
            }
        }

        fun bind(bestSeller: BestSellerItem) {

            with(binding) {
                if (bestSeller.isFavorites) {
                    ibFav.setImageResource(R.drawable.ic_fav2_filled)
                }
                tvDetail.text = bestSeller.title
                tvFinalPrice.apply {
                    text = resources.getString(
                        CoreR.string.price_format,
                        bestSeller.finalPrice.toSeparatedNumber()
                    )
                }
                tvFullPrice.apply {
                    text = resources.getString(
                        CoreR.string.price_format,
                        bestSeller.fullPrice.toSeparatedNumber()
                    )
                    paintFlags = this.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                }

                glideRequestManager
                    .load(bestSeller.src)
                    .centerInside()
                    .into(ivHomeContainer)
            }
        }
    }
}