package com.leguia.rvezy.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.leguia.rvezy.R
import com.leguia.rvezy.databinding.FragmentCatDetailBinding

class CatDetailFragment : Fragment() {

    companion object {
        fun newInstance() = CatDetailFragment()
    }

    private lateinit var viewModel: CatViewModel
    private lateinit var binding: FragmentCatDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCatDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(CatViewModel::class.java)
        viewModel.catSelected.observe(viewLifecycleOwner, Observer { cat ->
            Glide.with(binding.catImage.context)
                .load(cat.url)
                .fitCenter()
                .into(binding.catImage);
        })
    }
}