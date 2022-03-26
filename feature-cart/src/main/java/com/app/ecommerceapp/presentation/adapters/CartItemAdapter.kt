package com.app.ecommerceapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.ecommerceapp.domain.models.CartItem
import com.app.ecommerceapp.extensions.toStringWithFractionalPart
import com.app.feature_cart.databinding.ItemCartBinding
import com.bumptech.glide.RequestManager
import com.app.core.R as CoreR

class CartItemAdapter(
    private var glideRequestManager: RequestManager
) : ListAdapter<CartItem, CartItemAdapter.CartItemViewHolder>(Differ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCartBinding.inflate(inflater, parent, false)
        return CartItemViewHolder(glideRequestManager, binding)
    }

    override fun onBindViewHolder(holder: CartItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private object Differ : DiffUtil.ItemCallback<CartItem>() {
        override fun areItemsTheSame(oldItem: CartItem, newItem: CartItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CartItem, newItem: CartItem): Boolean {
            return oldItem == newItem
        }
    }

    class CartItemViewHolder(
        private val glideRequestManager: RequestManager,
        private val binding: ItemCartBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(cartItem: CartItem) {

            with(binding) {
                tvItemPrice.text = binding.root.context.getString(
                    CoreR.string.price_format,
                    cartItem.price.toStringWithFractionalPart()
                )
                tvItemTitle.text = cartItem.title
                counterView.counterField.text = ITEM_COUNT

                glideRequestManager
                    .load(cartItem.src)
                    .centerCrop()
                    .into(ivCartItem)
            }
        }
    }

    private companion object {
        private const val ITEM_COUNT = "2"
    }
}