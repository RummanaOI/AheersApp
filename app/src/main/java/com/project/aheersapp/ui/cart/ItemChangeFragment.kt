package com.project.aheersapp.ui.cart

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.project.aheersapp.databinding.FragmentItemChangeBinding
import com.project.aheersapp.util.Cart
import com.project.aheersapp.util.CartItems
import com.project.aheersapp.util.FormatUtil

class ItemChangeFragment : Fragment() {

    private lateinit var viewModel: ItemChangeViewModel
    private lateinit var binding: FragmentItemChangeBinding
    //reading the arguments passed by the previous [cart] frag
    private val args : ItemChangeFragmentArgs by navArgs()
    private var quantity : Int = 1
    private var individualPrice : Double = 0.00
    private var totalPrice : Double = 0.00
    private var name : String? = ""
    private var img : Int? = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentItemChangeBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[ItemChangeViewModel::class.java]
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        img =  args.argImg
        name = args.argName
        totalPrice = args.argPrice.toDouble()
        quantity = args.argQuantity
        individualPrice = totalPrice / quantity

        binding.apply {
            //init all the values/fields
            imgItem.setImageResource(img!!)
            txtQuantity.text = "$quantity X"
            txtQuantitySmall.text = "$quantity"
            txtDescription.text = "$name @ R ${FormatUtil.roundOffDecimal(individualPrice)}"
            txtTotalPrice.text = "R ${FormatUtil.roundOffDecimal(totalPrice)}"

        }
//button click events
        onClick(binding.btnUpdate)
        onClick(binding.btnRemove)
        onClick(binding.imgAdd)
        onClick(binding.imgRemove)

    }

    @SuppressLint("SetTextI18n")
    private fun onClick(v : View){
        //way to navigate back to previous frag [cartFrag]
        val direction = ItemChangeFragmentDirections.actionNavChangeToNavCart()
        v.setOnClickListener{
            when (v.id) {
                binding.btnUpdate.id -> {
                    Cart.updateItem(CartItems(img!!, name!!, totalPrice, quantity))
                    Toast.makeText(context, "Item has been updated", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(direction)
                }

                binding.btnRemove.id -> {
                    Cart.removeItem(img!!)
                    Toast.makeText(context, "Item has been removed from cart", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(direction)
                }

                binding.imgAdd.id -> {
                    quantity++
                    totalPrice = individualPrice * quantity
                    binding.txtQuantitySmall.text = quantity.toString()
                    binding.txtTotalPrice.text = "R ${FormatUtil.roundOffDecimal(totalPrice)}"
                }

                binding.imgRemove.id -> {
                    quantity--
                    if (quantity < 1){
                        quantity = 1
                    }
                    totalPrice = individualPrice * quantity
                    binding.txtQuantitySmall.text = quantity.toString()
                    binding.txtTotalPrice.text = "R ${FormatUtil.roundOffDecimal(totalPrice)}"
                }
            }
        }

    }
}