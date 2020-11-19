package com.bingo.jetpackdemo.ui.core.tree

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupWindow
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bingo.jetpackdemo.R
import com.bingo.jetpackdemo.databinding.TreeProjectPopupBinding
import com.chebada.utils.ktx.dp

class TreeProjectPopup(fragment: Fragment) : PopupWindow(fragment.context) {

    private val projectViewModel: TreeProjectViewModel by fragment.viewModels()
    val model = ViewModelProvider(fragment).get(TreeProjectViewModel::class.java)


    init {
        isFocusable = true
        isOutsideTouchable = true
        setBackgroundDrawable(
            ContextCompat.getDrawable(
                fragment.requireContext(),
                R.color.colorPrimary
            )
        )
        width = ViewGroup.LayoutParams.MATCH_PARENT
        height = ViewGroup.LayoutParams.WRAP_CONTENT
        animationStyle = R.style.AppTheme_Animation_Dialog
        elevation = 4.dp.toFloat()
        val binding: TreeProjectPopupBinding = DataBindingUtil.inflate(
            LayoutInflater.from(fragment.context),
            R.layout.tree_project_popup,
            null,
            false
        )
        binding.loadingLayout.start()
        projectViewModel.projectTree()
            .observe(fragment.viewLifecycleOwner, Observer {
                it.forEach { treeItem ->
                    val textView = LayoutInflater.from(fragment.context)
                        .inflate(
                            R.layout.tree_project_item_view,
                            binding.flexbox,
                            false
                        ) as TextView
                    textView.text = treeItem.name
                    textView.setOnClickListener { view ->
                        model.projectList(0, treeItem.id.toString())
                    }
                    binding.flexbox.addView(textView)
                }
                if (binding.flexbox.childCount != 0) {
                    binding.flexbox.getChildAt(0).isSelected = true
                }

            })
        binding.loadingLayout.dismiss()


        contentView = binding.root


    }


}