package com.project.aheersapp.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.project.aheersapp.adapters.DrinksAdapter
import com.project.aheersapp.databinding.FragmentDrinksBinding
import com.project.aheersapp.ui.info.InfoActivity
import com.project.aheersapp.util.RecyclerViewInterface
import com.project.aheersapp.util.TestLists

class DrinksFragment : Fragment(), RecyclerViewInterface{
    private lateinit var binding: FragmentDrinksBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDrinksBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rcView = binding.rcvDrinks

        rcView.layoutManager = GridLayoutManager(context, 2)
        rcView.setHasFixedSize(false)
        rcView.adapter = DrinksAdapter(TestLists.drinks, this)
    }

    override fun onItemClick(pos: Int) {
        //navigate to next one
        val intent = Intent(context, InfoActivity::class.java)

        intent.putExtra("IMG", TestLists.drinks.elementAt(pos).image)
        intent.putExtra("NAME", TestLists.drinks.elementAt(pos).name)
        intent.putExtra("PRICE", TestLists.drinks.elementAt(pos).price)
        intent.putExtra("QUANTITY", 1)

        startActivity(intent)
    }
}