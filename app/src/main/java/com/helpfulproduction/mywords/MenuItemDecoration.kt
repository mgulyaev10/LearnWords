package com.helpfulproduction.mywords

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.helpfulproduction.mywords.menu.MenuAdapter


class MenuItemDecoration(context: Context, orientation: Int): DividerItemDecoration(context, orientation) {
    private val divider: Drawable? = ContextCompat.getDrawable(context, R.drawable.divider_menu)

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left: Int = parent.paddingLeft
        val right: Int = parent.width - parent.paddingRight
        val childCount: Int = parent.childCount
        for (i in 0 until childCount) {
            val child: View = parent.getChildAt(i)
            val hasDivider = (parent.adapter as? MenuAdapter)?.isLastInBlock(i)
            if (hasDivider == false) {
                return
            }
            val params = child.layoutParams as RecyclerView.LayoutParams
            val top: Int = child.bottom + params.bottomMargin
            val bottom = top + divider!!.intrinsicHeight
            divider.setBounds(left, top, right, bottom)
            divider.draw(c)
        }
    }
}