package com.project.aheersapp.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.project.aheersapp.adapters.MealsAdapter
import com.project.aheersapp.databinding.FragmentMealsBinding
import com.project.aheersapp.ui.info.InfoActivity
import com.project.aheersapp.util.RecyclerViewInterface
import com.project.aheersapp.util.TestLists

class MealsFragment : Fragment(), RecyclerViewInterface {
    private lateinit var binding: FragmentMealsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMealsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rcView = binding.rcvMeals

        rcView.layoutManager = GridLayoutManager(context, 2)
        rcView.setHasFixedSize(false)
        rcView.adapter = MealsAdapter(TestLists.foods, this)
    }

    override fun onItemClick(pos: Int) {
        //navigate to next one
        val intent = Intent(context, InfoActivity::class.java)

        intent.putExtra("IMG", TestLists.foods.elementAt(pos).image)
        intent.putExtra("NAME", TestLists.foods.elementAt(pos).name)
        intent.putExtra("PRICE", TestLists.foods.elementAt(pos).price)
        intent.putExtra("QUANTITY", 1)

        startActivity(intent)
    }
}