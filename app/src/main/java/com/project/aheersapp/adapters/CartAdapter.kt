package com.project.aheersapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.aheersapp.databinding.CartItemsBinding
import com.project.aheersapp.util.CartItems
import com.project.aheersapp.util.FormatUtil
import com.project.aheersapp.util.RecyclerViewInterface

class CartAdapter(
    private val items: List<CartItems>,
    private val recyclerInterface: RecyclerViewInterface
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>()
{


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        return CartViewHolder(
            CartItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bindItems(items[position])
        holder.itemView.setOnClickListener {
            recyclerInterface.onItemClick(position)
        }
    }


    class CartViewHolder(private val binding: CartItemsBinding) : RecyclerView.ViewHolder(binding.root) {
    @SuppressLint("SetTextI18n")
    fun bindItems(item: CartItems){
        binding.ciItem.setImageResource(item.image)
        binding.ItemName.text = item.name
        binding.itemQuantity.text = "Quantity: ${item.quantity}"
        binding.itemPrice.text = "R ${FormatUtil.roundOffDecimal(item.price)}"
    }
}
}