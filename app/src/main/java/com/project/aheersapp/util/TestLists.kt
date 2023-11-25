package com.project.aheersapp.util

import com.project.aheersapp.R
//also think about SQL lite
object TestLists {
    val foods = listOf(
    ShopItems(R.drawable.pies, "Pies", FormatUtil.roundOffDecimal(29.50)),
    ShopItems(R.drawable.samoosa, "Samoosa", FormatUtil.roundOffDecimal(6.00)),
    ShopItems(R.drawable.chicken_pops, "Chicken Pops portion", FormatUtil.roundOffDecimal(18.00)),
    ShopItems(R.drawable.pops_chips, "Chicken Pops & Chips", FormatUtil.roundOffDecimal(28.00)),
    ShopItems(R.drawable.strips_chips, "Chicken Strips & Chips", FormatUtil.roundOffDecimal(28.00)),
    ShopItems(R.drawable.mini_sausagerolls, "Mini Chicken Sausage Rolls", FormatUtil.roundOffDecimal(6.00)),
    ShopItems(R.drawable.sausage_rolls, "Sausage Rolls",FormatUtil.roundOffDecimal(29.50))
    )

    val cakes = listOf(
        ShopItems(R.drawable.cake_slice, "Chocolate", FormatUtil.roundOffDecimal(30.00)),
        ShopItems(R.drawable.snowball, "Snowballs", FormatUtil.roundOffDecimal(11.00)),

    )

    val drinks = listOf(
        ShopItems(R.drawable.cappuccino,"Cappuccino",FormatUtil.roundOffDecimal(30.00)),
        ShopItems(R.drawable.hot_chocolate,"Hot Chocolate",FormatUtil.roundOffDecimal(35.00)),
        ShopItems(R.drawable.mocha_chino,"Mocha Chino",FormatUtil.roundOffDecimal(35.00)),
        ShopItems(R.drawable.americano,"Americano",FormatUtil.roundOffDecimal(30.00)),
        ShopItems(R.drawable.latte,"Latte",FormatUtil.roundOffDecimal(30.00)),
        ShopItems(R.drawable.decaf_coffee,"Decaf",FormatUtil.roundOffDecimal(30.00)),
    )
}