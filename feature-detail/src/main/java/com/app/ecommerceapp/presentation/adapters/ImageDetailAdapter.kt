package com.app.ecommerceapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.ecommerceapp.presentation.models.ImageItem
import com.app.feature_detail.databinding.ItemDetailImageBinding
import com.bumptech.glide.RequestManager

class ImageDetailAdapter(
    private val glideRequestManager: RequestManager,
    originalList: List<ImageItem>
) : RecyclerView.Adapter<ImageDetailAdapter.ImageDetailViewHolder>() {

    private val newList: List<ImageItem> =
        listOf(originalList.last()) + originalList + listOf(originalList.first())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageDetailViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemDetailImageBinding.inflate(inflater, parent, false)
        return ImageDetailViewHolder(glideRequestManager, binding)
    }

    override fun onBindViewHolder(holder: ImageDetailViewHolder, position: Int) {
        holder.setImage(newList[position].src)
    }

    override fun getItemCount(): Int = newList.size

    class ImageDetailViewHolder(
        private val glideRequestManager: RequestManager,
        private val binding: ItemDetailImageBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun setImage(src: String) {
            glideRequestManager
                .load(src)
                .fitCenter()
                .into(binding.ivDetailContainer)
        }
    }
}