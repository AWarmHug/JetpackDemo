package com.bingo.jetpackdemo.ui.core.girl

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

class GirlFragment : DataBindingFragment() {

    companion object {
        fun newInstance(): GirlFragment {
            return GirlFragment()
        }
    }

    private lateinit var binding: GirlFragmentBinding

    private val viewModel: GirlViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = binding(inflater, R.layout.girl_fragment, container)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.data(Category.GIRL.api,"girl",0).observe(viewLifecycleOwner, {

        })
    }

}