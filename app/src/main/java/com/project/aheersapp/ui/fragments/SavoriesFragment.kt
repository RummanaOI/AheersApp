package com.project.aheersapp.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.project.aheersapp.adapters.SavoriesAdapter
import com.project.aheersapp.databinding.FragmentSavoriesBinding
import com.project.aheersapp.ui.info.InfoActivity
import com.project.aheersapp.util.RecyclerViewInterface
import com.project.aheersapp.util.TestLists

class SavoriesFragment : Fragment(), RecyclerViewInterface {
    private lateinit var binding: FragmentSavoriesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSavoriesBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rcView = binding.rcvSavories

        rcView.layoutManager = GridLayoutManager(context, 2)
        rcView.setHasFixedSize(false)
        rcView.adapter = SavoriesAdapter(TestLists.cakes, this)
    }

    override fun onItemClick(pos: Int) {
        //navigate to next one
        val intent = Intent(context, InfoActivity::class.java)

        intent.putExtra("IMG", TestLists.cakes.elementAt(pos).image)
        intent.putExtra("NAME", TestLists.cakes.elementAt(pos).name)
        intent.putExtra("PRICE", TestLists.cakes.elementAt(pos).price)
        intent.putExtra("QUANTITY", 1)

        startActivity(intent)
    }
}