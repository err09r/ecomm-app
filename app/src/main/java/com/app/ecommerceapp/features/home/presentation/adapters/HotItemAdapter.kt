package com.app.ecommerceapp.features.home.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.ecommerceapp.core.common.show
import com.app.ecommerceapp.databinding.ItemHotSalesBinding
import com.app.ecommerceapp.features.home.domain.models.HotItem
import com.bumptech.glide.Glide

class HotItemAdapter(private val listener: (HotItem) -> Unit) :
    ListAdapter<HotItem, HotItemAdapter.HotItemViewHolder>(Differ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHotSalesBinding.inflate(inflater, parent, false)
        return HotItemViewHolder(binding) { pos ->
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
                if (hotItem.isNew) {
                    tvNewLabel.show()
                }
                if (hotItem.isBuy) {
                    btnBuy.show()
                }

                // Code below was implemented due to API image problems (text on images, bad image etc.)
                if (hotItem.id == 1) {
                    tvTitle.text = hotItem.title
                    tvSubtitle.text = hotItem.subtitle
                }
                if (hotItem.id == 3) {
                    tvSubtitle.text = hotItem.subtitle
                }

            }
            setImage(hotItem.src)
        }

        private fun setImage(src: String) {
            Glide
                .with(binding.root.context)
                .load(src)
                .centerCrop()
                .into(binding.ivPicture)
        }
    }
}