package com.project.aheersapp.ui.cart

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.aheersapp.adapters.CartAdapter
import com.project.aheersapp.databinding.FragmentCartBinding
import com.project.aheersapp.util.Cart
import com.project.aheersapp.util.FormatUtil
import com.project.aheersapp.util.RecyclerViewInterface

class CartFragment : Fragment(), RecyclerViewInterface {

    private lateinit var viewModel: CartViewModel
    private lateinit var binding : FragmentCartBinding
    private var totalPrice = 0.0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCartBinding.inflate(layoutInflater)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[CartViewModel::class.java]

        Cart.cartItems.observe(viewLifecycleOwner) { cartItems ->

            if (cartItems != null)
                if (cartItems.isEmpty()) {
                    binding.emptyList.visibility = View.VISIBLE
                    binding.container.visibility = View.GONE
                } else {
                    binding.container.visibility = View.VISIBLE
                    binding.emptyList.visibility = View.GONE

                    binding.rcvCart.layoutManager = LinearLayoutManager(context)
                    binding.rcvCart.setHasFixedSize(false)
                    binding.rcvCart.isNestedScrollingEnabled = false
                    binding.rcvCart.adapter = CartAdapter(cartItems, this)

                    //set the price
                    for (item in cartItems)
                        totalPrice += item.price
                    binding.txtPrice.text = "R ${FormatUtil.roundOffDecimal(totalPrice)}"

                }
        }



    }

    override fun onItemClick(pos: Int) {
        Cart.cartList.elementAt(pos).apply {
            val direction = CartFragmentDirections.actionNavCartToNavChange(
                    this.image,
                    this.name,
                    this.quantity,
                    this.price.toString()
                )
            findNavController().navigate(direction)
        }

    }

}