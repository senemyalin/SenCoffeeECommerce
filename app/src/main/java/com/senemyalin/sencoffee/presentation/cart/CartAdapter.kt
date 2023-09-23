package com.senemyalin.sencoffee.presentation.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.senemyalin.sencoffee.common.loadImage
import com.senemyalin.sencoffee.data.dto.Product
import com.senemyalin.sencoffee.databinding.ItemFavouriteBinding

class CartAdapter(
    private val onProductClick: (Int) -> Unit,
    private val onDeleteClick: (Int) -> Unit
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {
    private val productList = mutableListOf<Product>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder =
        CartViewHolder(
            ItemFavouriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) =
        holder.bind(productList[position])

    inner class CartViewHolder(
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
                    product.id.let(onDeleteClick)
                }
                root.setOnClickListener {
                    product.id.let(onProductClick)
                }
            }
    }


    fun updateList(list: List<Product>) {
        productList.clear()
        productList.addAll(list)
        notifyDataSetChanged()
    }
}