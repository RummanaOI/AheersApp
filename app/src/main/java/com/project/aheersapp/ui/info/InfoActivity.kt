package com.project.aheersapp.ui.info

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import com.project.aheersapp.databinding.ActivityInfoBinding
import com.project.aheersapp.util.*

class InfoActivity : AppCompatActivity() {

    private lateinit var binding : ActivityInfoBinding
    private var quantity : Int = 1
    private var individualPrice : Double = 0.00
    private var totalPrice : Double = 0.00
    private var name : String? = ""
    private var img : Int? = 0
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.txtQuantity.text = quantity.toString()

        img = intent.extras?.getInt("IMG")
        name = intent.extras?.getString("NAME")
        val price = intent.extras?.getString("PRICE")
        quantity = intent.extras?.getInt("QUANTITY") ?: 1
        individualPrice = price!!.toDouble()
        totalPrice = individualPrice

        binding.apply {
            itemIMG.setBackgroundResource(img!!)
            txtName.text = name
            txtPrice.text = "R ${FormatUtil.roundOffDecimal(individualPrice)}"
            txtPriceSmall.text = "R ${FormatUtil.roundOffDecimal(totalPrice)}"
            binding.txtQuantity.text = quantity.toString()

            imgAdd.setOnClickListener{
                quantity++
                totalPrice = individualPrice * quantity
                txtQuantity.text = quantity.toString()
                txtPriceSmall.text = "R ${FormatUtil.roundOffDecimal(totalPrice)}"
            }

            imgRemove.setOnClickListener{
                quantity--
                if (quantity < 1){
                    quantity = 1
                }
                totalPrice = individualPrice * quantity
                txtQuantity.text = quantity.toString()
                txtPriceSmall.text = "R ${FormatUtil.roundOffDecimal(totalPrice)}"
            }
        }
        onClick(binding.btnComplete)

    }

    private fun onClick(v :View){
        v.setOnClickListener{
            when(v){
                is Button -> {
                        Cart.addItem(CartItems(img!!, name!!, totalPrice, quantity))
                        for (output in Cart.cartList){
                            Log.i("OUTPUT", "PRICE ${output.price} QUANTITY : ${output.quantity}")
                        }
                        finish()
                }
            }
        }

    }

}