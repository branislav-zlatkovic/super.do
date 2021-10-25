package com.example.myapplication.view.common

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class UnifiedItemDecoration(context: Context, dimenRes: Int) : RecyclerView.ItemDecoration() {

    private val margin: Int = context.resources.getDimensionPixelSize(dimenRes)

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.left = margin
        outRect.right = margin
        outRect.bottom = margin
        outRect.top = margin
    }
}