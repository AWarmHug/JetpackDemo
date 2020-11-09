package com.bingo.jetpackdemo.ui.core.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bingo.jetpackdemo.R
import com.bingo.jetpackdemo.base.DataBindingFragment
import com.bingo.jetpackdemo.databinding.FavoritesFragmentBinding
import com.bingo.jetpackdemo.databinding.HomeFragmentBinding

class FavoritesFragment() : DataBindingFragment() {

    private lateinit var binding: FavoritesFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = binding(inflater, R.layout.favorites_fragment, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
        }
    }


    companion object {
        fun newInstance(): FavoritesFragment {
            return FavoritesFragment()
        }
    }

}