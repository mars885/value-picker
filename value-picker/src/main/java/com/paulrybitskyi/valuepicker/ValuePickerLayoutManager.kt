package com.paulrybitskyi.valuepicker

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.abs


private const val ALPHA_FACTOR = 0.5f


internal class ValuePickerLayoutManager(
    context: Context,
    orientation: Int,
    reverseLayout: Boolean
) : LinearLayoutManager(context, orientation, reverseLayout) {


    var onViewSelectedListener: ((View) -> Unit)? = null


    override fun onLayoutChildren(
        recycler: RecyclerView.Recycler,
        state: RecyclerView.State
    ) {
        super.onLayoutChildren(recycler, state)

        updateChildrenAlpha()
    }


    private fun updateChildrenAlpha() {
        val recyclerViewCenterY = calculateRecyclerViewCenterY().toFloat()

        for(i in 0 until childCount) {
            val child = (getChildAt(i) ?: continue)
            val childCenterY = calculateChildCenterY(child)
            val rvDistanceFrmChildCenter = abs(recyclerViewCenterY - childCenterY)
            val alpha = (1 - (rvDistanceFrmChildCenter / (height * ALPHA_FACTOR)))

            child.alpha = alpha
        }
    }


    private fun calculateRecyclerViewCenterY(): Int {
        return (height / 2)
    }


    private fun calculateChildCenterY(child: View): Int {
        return ((child.height / 2) + child.top)
    }


    override fun scrollVerticallyBy(
        dy: Int,
        recycler: RecyclerView.Recycler,
        state: RecyclerView.State
    ): Int {
        return if(orientation == VERTICAL) {
            super.scrollVerticallyBy(dy, recycler, state)
                .also { updateChildrenAlpha() }
        } else {
            return 0
        }
    }


    override fun onScrollStateChanged(state: Int) {
        super.onScrollStateChanged(state)

        if(state == RecyclerView.SCROLL_STATE_IDLE) {
            val recyclerViewCenterY = calculateRecyclerViewCenterY()
            var minDistance = height
            var selectedChild: View? = null

            for(i in 0 until childCount) {
                val child = (getChildAt(i) ?: continue)
                val childCenterY = calculateChildCenterY(child)
                val childDistanceFromRvCenter = abs(childCenterY - recyclerViewCenterY)

                if(childDistanceFromRvCenter < minDistance) {
                    minDistance = childDistanceFromRvCenter
                    selectedChild = child
                }
            }

            selectedChild?.let { onViewSelectedListener?.invoke(it) }
        }
    }


    override fun smoothScrollToPosition(
        recyclerView: RecyclerView,
        state: RecyclerView.State,
        position: Int
    ) {
        ValuePickerSmoothScroller(recyclerView.context)
            .apply { targetPosition = position }
            .also { startSmoothScroll(it) }
    }


}