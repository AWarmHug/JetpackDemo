package com.bingo.jetpackdemo.ui.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;

import com.bingo.jetpackdemo.R;
import com.google.android.material.appbar.AppBarLayout;


/**
 * 作者：warm
 * 时间：2018-08-11 11:17
 * 描述：
 */
public class ToolbarBehavior extends CoordinatorLayout.Behavior<Toolbar> {

    private Context mContext;

    public ToolbarBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, Toolbar child, View dependency) {
        return dependency instanceof AppBarLayout;
    }


    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, Toolbar child, View dependency) {
        if (dependency instanceof AppBarLayout) {
            float alpha = Math.abs(dependency.getTop() * 1.0f) / (dependency.getMeasuredHeight() - child.getMeasuredHeight());

            if (alpha > 1) {
                alpha = 1;
            }

            if (child.getBackground() != null) {
                int a = (int) (alpha * 255);
                child.getBackground().mutate().setAlpha(a);
            }
            float red = 255-( 255- 68) * alpha ;
            float green =255- (255 - 68) * alpha ;
            float blue =255- (255 - 68) * alpha;

            int color = Color.argb(255, (int) red, (int) green, (int) blue);

            child.setTitleTextColor(color);
            child.getMenu().getItem(0).getIcon().setTint(color);
        }
        return super.onDependentViewChanged(parent, child, dependency);
    }

}
