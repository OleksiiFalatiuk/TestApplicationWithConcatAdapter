package com.example.testapplicationwithconcatadapter.ui.model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.testapplicationwithconcatadapter.databinding.ItemFeaturedBinding
import timber.log.Timber

class FeaturedItemAdapter(
    private val listener: (String) -> Unit
): ListAdapter<Featured, FeaturedItemAdapter.FeaturedItemViewHolder>(FeaturedItemDiffCallback()){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeaturedItemViewHolder {
        val itemBinding = ItemFeaturedBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return FeaturedItemViewHolder(itemBinding,listener)
    }

    override fun onBindViewHolder(holder: FeaturedItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class FeaturedItemViewHolder(
        private val binding: ItemFeaturedBinding,
        private val listener: (String) -> Unit
    ) : RecyclerView.ViewHolder(binding.root){

        lateinit var item: Featured

        init {
            itemView.setOnClickListener {
                Timber.d("item with position within adapter selected $bindingAdapterPosition")
                Timber.d("item with position within all adapters selected $absoluteAdapterPosition")
                listener(item.id)
            }
        }

        fun bind(item: Featured){
            this.item = item

            binding.image.load(item.drawId){
                crossfade(true)
            }
            binding.title.text = item.title
            binding.description.text = item.description
        }

    }

    class FeaturedItemDiffCallback : DiffUtil.ItemCallback<Featured>() {
        override fun areItemsTheSame(oldItem: Featured, newItem: Featured): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Featured, newItem: Featured): Boolean {
            return oldItem == newItem
        }
    }


}




