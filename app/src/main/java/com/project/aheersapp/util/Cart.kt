package com.project.aheersapp.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class Cart {
    companion object {
        val cartList: MutableList<CartItems> = mutableListOf()
        private val _cartItems = MutableLiveData<List<CartItems>?>()
        val cartItems: LiveData<List<CartItems>?> = _cartItems

        fun addItem(element: CartItems) {
            for (items in cartList)
                if (items.image == element.image) {
                    val found = cartList.filter { item -> item.image == element.image }
                    found[0].quantity += element.quantity
                    found[0].price += element.price
                    return
                }
            cartList.add(element)
            _cartItems.value = cartList
        }

        fun updateItem(element : CartItems){
            val found = cartList.filter { item -> item.image == element.image }
            found[0].quantity = element.quantity
            found[0].price = element.price
        }

        //TODO: Implement
        //Finds the item in the cart with the same image ID then removes it
        fun removeItem(element: Int) {
            cartList.remove(cartList.first { x -> x.image == element })
        }
    }

    init {
        _cartItems.value = null
    }

}

data class CartItems(var image: Int, var name: String, var price: Double, var quantity: Int)