package com.example.myapplication.view.listings

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemStoreItemBinding
import com.example.myapplication.domain.model.StoreItem

class ItemListingsAdapter(
    diffCallback: DiffUtil.ItemCallback<StoreItem> = object :
        DiffUtil.ItemCallback<StoreItem>() {
        override fun areItemsTheSame(oldItem: StoreItem, newItem: StoreItem): Boolean {
            return newItem.name == oldItem.name
                    && newItem.weight == oldItem.weight
                    && newItem.bagColor == oldItem.bagColor
        }

        override fun areContentsTheSame(oldItem: StoreItem, newItem: StoreItem): Boolean {
            return true
        }
    }
): ListAdapter<StoreItem, ItemListingsAdapter.GenericViewHolder>(diffCallback) {

    interface ActionCallback {
        fun onItemClicked(view: View, name: String)
    }

    var callback: ActionCallback? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder {
        return ItemViewHolder(
            ItemStoreItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ).also { binding ->
                binding.root.setOnClickListener {
                    callback?.onItemClicked(
                        binding.itemBadge,
                        binding.root.tag as String
                    )
                }
            }
        )
    }

    override fun onBindViewHolder(holder: GenericViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    abstract class GenericViewHolder(binding: ItemStoreItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        abstract fun bind(item: StoreItem)
    }

    inner class ItemViewHolder(private val binding: ItemStoreItemBinding) :
        GenericViewHolder(binding) {
        override fun bind(item: StoreItem) {
            binding.itemName.text = item.name
            binding.itemWeight.text = item.weight
            binding.itemBadge.setBackgroundColor(Color.parseColor(item.bagColor))

            val transitionName = "$adapterPosition:${item.bagColor}"
            ViewCompat.setTransitionName(binding.itemBadge, transitionName)
            binding.root.tag = transitionName
        }
    }
}