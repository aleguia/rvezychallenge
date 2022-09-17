package com.leguia.rvezy.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.leguia.rvezy.R
import com.leguia.rvezy.databinding.FragmentCatListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CatListFragment : Fragment() {

    companion object {
        fun newInstance() = CatListFragment()
    }

    private lateinit var viewModel: CatViewModel
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
        catAdapter.onCatClicked = { cat ->
            viewModel.catSelected.value = cat
            Navigation.findNavController(requireView()).navigate(R.id.list_to_detail)
        }

        binding.catsRecycler.apply {
            adapter = catAdapter
            layoutManager = GridLayoutManager(view.context, 2)

        }

        viewModel = ViewModelProvider(requireActivity()).get(CatViewModel::class.java)

        viewModel.catList.observe(viewLifecycleOwner, Observer { pagingData ->

            lifecycleScope.launch {
                catAdapter.submitData(pagingData)
            }


        })




    }



}