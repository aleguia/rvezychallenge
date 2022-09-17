package com.leguia.rvezy.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.leguia.rvezy.R
import com.leguia.rvezy.databinding.FragmentCatListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CatListFragment : Fragment() {

    companion object {
        fun newInstance() = CatListFragment()
    }

    private lateinit var viewModel: CatListViewModel
    private lateinit var catAdapter: CatListAdapter
    private lateinit var binding: FragmentCatListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCatListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        catAdapter = CatListAdapter()

        binding.catsRecycler.apply {
            adapter = catAdapter
            layoutManager = GridLayoutManager(view.context, 2)
        }

        viewModel = ViewModelProvider(this).get(CatListViewModel::class.java)

        viewModel.catList.observe(viewLifecycleOwner, Observer {
            catAdapter.setItems(it)
        })
    }


}