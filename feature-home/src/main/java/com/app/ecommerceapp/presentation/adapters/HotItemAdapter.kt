package com.app.ecommerceapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.ecommerceapp.domain.models.HotItem
import com.app.ecommerceapp.extensions.show
import com.app.feature_home.databinding.ItemHotSalesBinding
import com.bumptech.glide.RequestManager

class HotItemAdapter(
    private val glideRequestManager: RequestManager,
    private val listener: (HotItem) -> Unit
) : ListAdapter<HotItem, HotItemAdapter.HotItemViewHolder>(Differ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHotSalesBinding.inflate(inflater, parent, false)
        return HotItemViewHolder(glideRequestManager, binding) { pos ->
            getItem(pos)?.let { listener(it) }
        }
    }

    override fun onBindViewHolder(holder: HotItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private object Differ : DiffUtil.ItemCallback<HotItem>() {
        override fun areItemsTheSame(oldItem: HotItem, newItem: HotItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: HotItem, newItem: HotItem): Boolean {
            return oldItem == newItem
        }
    }

    class HotItemViewHolder(
        private val glideRequestManager: RequestManager,
        private val binding: ItemHotSalesBinding,
        clickListener: (Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                clickListener(adapterPosition)
            }
        }

        fun bind(hotItem: HotItem) {

            with(binding) {
                tvTitle.text = hotItem.title
                tvSubtitle.text = hotItem.subtitle

                if (hotItem.isNew) {
                    tvNewLabel.show()
                }
                if (hotItem.isBuy) {
                    btnBuy.show()
                }

                glideRequestManager
                    .load(hotItem.src)
                    .centerCrop()
                    .into(ivPicture)
            }
        }
    }
}