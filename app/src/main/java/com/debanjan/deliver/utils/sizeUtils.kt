package com.debanjan.deliver.utils

import android.content.Context
import android.util.TypedValue


fun dpToPx(dp: Float, context: Context): Int {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics()).toInt()
}