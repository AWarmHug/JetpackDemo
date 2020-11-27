package com.bingo.jetpackdemo.ui.core.girl

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.bingo.jetpackdemo.R
import com.bingo.jetpackdemo.base.DataBindingFragment
import com.bingo.jetpackdemo.data.entity.Article
import com.bingo.jetpackdemo.data.remote.Category
import com.bingo.jetpackdemo.databinding.GirlFragmentBinding
import com.bingo.jetpackdemo.databinding.GirlItemAdapterItemBinding
import com.bingo.jetpackdemo.databinding.GirlItemFragmentItemBinding
import com.bingo.jetpackdemo.databinding.HomeItemAdapterItemBinding
import com.bingo.jetpackdemo.ui.widget.recyclerview.decoration.LineItemDecoration
import com.chebada.utils.ktx.dp

class GirlFragment : DataBindingFragment() {

    companion object {
        fun newInstance(): GirlFragment {
            return GirlFragment()
        }
    }

    private lateinit var binding: GirlFragmentBinding
    private val viewModel: GirlViewModel by viewModels()

    private val girlListAdapter = GirlListAdapter()
    private val girlPagerAdapter: GirlPagerAdapter by lazy {
        GirlPagerAdapter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = binding(inflater, R.layout.girl_fragment, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            list.adapter = girlListAdapter
            list.addItemDecoration(
                LineItemDecoration(
                    context,
                    RecyclerView.HORIZONTAL,
                    10.dp,
                    ContextCompat.getColor(requireContext(), R.color.transparent)
                )
            )
            list.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            pager.adapter = girlPagerAdapter
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.data(Category.GIRL.api, "Girl", 0).observe(viewLifecycleOwner, {
            girlListAdapter.list.clear()
            girlListAdapter.list.addAll(it)
            girlListAdapter.notifyDataSetChanged()

            girlPagerAdapter.list.addAll(it)
            girlPagerAdapter.notifyDataSetChanged()
        })
    }
}

private class GirlListAdapter : RecyclerView.Adapter<GirlListAdapter.VH>() {

    val list = mutableListOf<Article>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding: GirlItemAdapterItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.girl_item_adapter_item,
            parent,
            false
        )
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.binding.article = list[position]
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class VH(val binding: GirlItemAdapterItemBinding) : RecyclerView.ViewHolder(binding.root)
}

private class GirlPagerAdapter(girlFragment: GirlFragment) : FragmentStateAdapter(girlFragment) {
    val list = mutableListOf<Article>()

    override fun getItemCount(): Int {
        return list.size
    }

    override fun createFragment(position: Int): Fragment {
        return GirlItemFragment.newInstance(list[position])
    }

}

class GirlItemFragment : DataBindingFragment() {

    companion object {
        fun newInstance(article: Article): GirlItemFragment {
            val girlFragment = GirlItemFragment()
            val bundle = Bundle()
            bundle.putParcelable("article", article)
            girlFragment.arguments = bundle
            return girlFragment
        }
    }

    private lateinit var binding: GirlItemFragmentItemBinding

    private val article: Article? by lazy {
        return@lazy arguments?.getParcelable("article")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = binding(inflater, R.layout.girl_item_fragment_item, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.article = article
    }

}