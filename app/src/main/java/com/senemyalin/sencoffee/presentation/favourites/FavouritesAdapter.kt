package com.senemyalin.sencoffee.presentation.favourites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.senemyalin.sencoffee.common.loadImage
import com.senemyalin.sencoffee.data.dto.Product
import com.senemyalin.sencoffee.databinding.ItemFavouriteBinding

class FavouritesAdapter(
    private val onProductClick: (Int) -> Unit,
    private val onDeleteClick: (Product) -> Unit
) : ListAdapter<Product, FavouritesAdapter.FavouritesViewHolder>(FavouritesDiffCallBack()) {

    private val favouritesList = mutableListOf<Product>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouritesViewHolder =
        FavouritesViewHolder(
            ItemFavouriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: FavouritesViewHolder, position: Int) =
        holder.bind(favouritesList[position])

    inner class FavouritesViewHolder(
        private val binding: ItemFavouriteBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) =
            with(binding) {
                txtProductTitle.text = product.title
                txtProductDesc.text = product.description
                ivProduct.loadImage(product.imageOne)

                if (product.saleState) {
                    txtPrice.text = "${product.salePrice} ₺ \n Old Price: ${product.price}"
                } else {
                    txtPrice.text = "${product.price} ₺"
                }

                ivDelete.setOnClickListener {
                    product.let(onDeleteClick)
                }
                root.setOnClickListener {
                    product.id.let(onProductClick)
                }
            }
    }


    fun updateList(list: List<Product>) {
        favouritesList.clear()
        favouritesList.addAll(list)
        notifyItemRangeChanged(0, list.size)
    }

    override fun getItemCount() = favouritesList.size

    class FavouritesDiffCallBack : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }
    }
}