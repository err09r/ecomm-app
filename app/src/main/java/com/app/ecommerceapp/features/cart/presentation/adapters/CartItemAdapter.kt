package com.app.ecommerceapp.features.cart.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.ecommerceapp.R
import com.app.ecommerceapp.core.common.toStringWithFractionalPart
import com.app.ecommerceapp.databinding.ItemCartBinding
import com.app.ecommerceapp.features.cart.domain.models.CartItem
import com.bumptech.glide.Glide

class CartItemAdapter : ListAdapter<CartItem, CartItemAdapter.CartItemViewHolder>(Differ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCartBinding.inflate(inflater, parent, false)
        return CartItemViewHolder(binding)
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

    class CartItemViewHolder(private val binding: ItemCartBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(cartItem: CartItem) {
            with(binding) {
                tvItemPrice.text = binding.root.context.getString(
                    R.string.price_format,
                    cartItem.price.toStringWithFractionalPart()
                )
                tvItemTitle.text = cartItem.title
                counterView.counterField.text = "2"
            }
            setImage(cartItem.src)
        }

        private fun setImage(src: String) {
            Glide
                .with(binding.root.context)
                .load(src)
                .centerCrop()
                .into(binding.ivCartItem)
        }
    }
}