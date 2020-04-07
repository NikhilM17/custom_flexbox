package com.example.custom_flexbox

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.custom_flexbox.databinding.FragmentListBinding
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent


class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ListAdapter(context)

        val layoutManager = FlexboxLayoutManager(context)
        layoutManager.flexDirection = FlexDirection.ROW
        layoutManager.justifyContent = JustifyContent.FLEX_START

//        LinearLayoutManager(context, VERTICAL, false)

        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter

        adapter.setItems(getItems())

    }

    private fun getItems(): MutableList<BaseModel> {
        val list: MutableList<BaseModel> = mutableListOf()
        val multiplier = 4
        for (i in 1..20) {
            when {
                i % multiplier == 0 -> list.add(BaseModel("Interest $i", false))
                i % multiplier == 1 -> list.add(BaseModel("Too Interested $i", false))
                i % multiplier == 2 -> list.add(BaseModel("Interesting $i", false))
                i % multiplier == 3 -> list.add(BaseModel("Have Been $i", false))
            }
        }
        return list
    }
}