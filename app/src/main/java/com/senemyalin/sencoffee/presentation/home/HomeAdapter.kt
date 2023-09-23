package com.senemyalin.sencoffee.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.senemyalin.sencoffee.common.loadImage
import com.senemyalin.sencoffee.data.dto.Product
import com.senemyalin.sencoffee.databinding.ItemHomeBinding


class HomeAdapter(
    private val onProductClick: (Int) -> Unit,
    private val onAddClick: (Int) -> Unit
) : ListAdapter<Product, HomeAdapter.HomeViewHolder>(HomeDiffCallBack()) {

    private val productList = mutableListOf<Product>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder =
        HomeViewHolder(
            ItemHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) =
        holder.bind(productList[position])

    inner class HomeViewHolder(
        private val binding: ItemHomeBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) =
            with(binding) {
                textViewName.text = product.title
                textViewDescription.text = product.description
                imageView.loadImage(product.imageOne)

                if(product.saleState){
                    textViewPrice.text = "${product.salePrice} ₺ \n Old Price: ${product.price}"
                }else{
                    textViewPrice.text = "${product.price} ₺"
                }

                btnAdd.setOnClickListener {
                    product.id.let(onAddClick)
                }
                root.setOnClickListener {
                    product.id.let(onProductClick)
                }
            }
    }


    fun updateList(list: List<Product>) {
        productList.clear()
        productList.addAll(list)
        notifyItemRangeChanged(0, list.size)
    }

    override fun getItemCount() = productList.size

    class HomeDiffCallBack : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }
    }

}