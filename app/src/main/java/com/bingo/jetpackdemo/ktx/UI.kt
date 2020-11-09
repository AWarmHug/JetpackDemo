package com.chebada.utils.ktx

import android.content.Context
import android.content.res.Resources
import android.util.TypedValue

val Int.dp
    get() = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            this.toFloat(),
            Resources.getSystem().displayMetrics
    ).toInt()
val Int.sp
    get() = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP,
            this.toFloat(),
            Resources.getSystem().displayMetrics
    ).toInt()

val Context.widthPixels
get() = resources.displayMetrics.widthPixels

val Context.heightPixels
    get() = resources.displayMetrics.heightPixels
