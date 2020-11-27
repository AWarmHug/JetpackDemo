package com.bingo.jetpackdemo.ui.core.mine

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bingo.jetpackdemo.R
import com.bingo.jetpackdemo.base.DataBindingFragment
import com.bingo.jetpackdemo.data.remote.Category
import com.bingo.jetpackdemo.databinding.GirlFragmentBinding
import com.bingo.jetpackdemo.databinding.MineFragmentBinding

class MineFragment : DataBindingFragment() {

    companion object {
        fun newInstance(): MineFragment {
            return MineFragment()
        }
    }

    private lateinit var binding: MineFragmentBinding

    private val viewModel: MineViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = binding(inflater, R.layout.mine_fragment, container)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}