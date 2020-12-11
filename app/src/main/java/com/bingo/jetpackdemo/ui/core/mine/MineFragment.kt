package com.bingo.jetpackdemo.ui.core.mine

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.fragment.app.viewModels
import com.bingo.jetpackdemo.R
import com.bingo.jetpackdemo.base.DataBindingFragment
import com.bingo.jetpackdemo.databinding.MineFragmentBinding
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.android.FlutterView
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.dart.DartExecutor


class MineFragment : DataBindingFragment() {

    companion object {
        fun newInstance(): MineFragment {
            return MineFragment()
        }
    }

    private lateinit var binding: MineFragmentBinding

    private val viewModel: MineViewModel by viewModels()

    private val flutterEngine by lazy {
        val flutterEngine = FlutterEngine(requireContext())
        flutterEngine.dartExecutor.executeDartEntrypoint(
            DartExecutor.DartEntrypoint.createDefault()
        )
        flutterEngine.navigationChannel.setInitialRoute("route1")
        return@lazy flutterEngine
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = binding(inflater, R.layout.mine_fragment, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btn.setOnClickListener {

            // 关键代码，将Flutter页面显示到FlutterView中
            binding.rootView.attachToFlutterEngine(flutterEngine)

//            startActivity(
//                FlutterActivity
//                    .withNewEngine()
//                    .initialRoute("route1")
//                    .build(requireContext()))
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        flutterEngine.lifecycleChannel.appIsResumed();
    }

    override fun onPause() {
        super.onPause()
        flutterEngine.lifecycleChannel.appIsInactive();
    }

    override fun onStop() {
        super.onStop()
        flutterEngine.lifecycleChannel.appIsPaused();
    }

}