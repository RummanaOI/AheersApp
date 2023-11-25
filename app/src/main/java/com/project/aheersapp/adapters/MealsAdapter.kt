package com.project.aheersapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.aheersapp.databinding.ShopItemsBinding
import com.project.aheersapp.util.RecyclerViewInterface
import com.project.aheersapp.util.ShopItems

class MealsAdapter(
    private var items: List<ShopItems>,
    private var recyclerInterface: RecyclerViewInterface
) :
    RecyclerView.Adapter<MealsAdapter.MealViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        return MealViewHolder(
            ShopItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        holder.bindItems(items[position])
        holder.itemView.setOnClickListener {
            recyclerInterface.onItemClick(position)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class MealViewHolder(private val binding: ShopItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bindItems(item: ShopItems) {
            binding.imageView.setImageResource(item.image)
            binding.mealName.text = item.name
            binding.price.text = "R ${item.price}"
        }
    }
}