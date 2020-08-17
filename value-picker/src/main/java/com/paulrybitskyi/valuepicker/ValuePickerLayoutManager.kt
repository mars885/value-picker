package com.paulrybitskyi.valuepicker

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.paulrybitskyi.valuepicker.model.Orientation
import com.paulrybitskyi.valuepicker.model.toRvOrientation
import kotlin.math.abs


private const val ALPHA_FACTOR = 0.5f


internal class ValuePickerLayoutManager(
    context: Context,
    private val orientation: Orientation
) : LinearLayoutManager(context, orientation.toRvOrientation(), false) {


    var onViewSelectedListener: ((View) -> Unit)? = null


    override fun onLayoutChildren(
        recycler: RecyclerView.Recycler,
        state: RecyclerView.State
    ) {
        super.onLayoutChildren(recycler, state)

        updateChildrenAlpha()
    }


    private fun updateChildrenAlpha() {
        val recyclerViewCenter = calculateRecyclerViewCenter().toFloat()

        for(i in 0 until childCount) {
            val child = (getChildAt(i) ?: continue)
            val childCenter = calculateChildCenter(child)
            val rvDistanceFrmChildCenter = abs(recyclerViewCenter - childCenter)
            val alpha = calculateChildAlpha(rvDistanceFrmChildCenter)

            child.alpha = alpha
        }
    }


    private fun calculateRecyclerViewCenter(): Int {
        return when(orientation) {
            Orientation.VERTICAL -> (height / 2)
            Orientation.HORIZONTAL -> (width / 2)
        }
    }


    private fun calculateChildCenter(child: View): Int {
        return when(orientation) {
            Orientation.VERTICAL -> ((child.height / 2) + child.top)
            Orientation.HORIZONTAL -> ((child.width / 2) + child.left)
        }
    }


    private fun calculateChildAlpha(rvDistanceFrmChildCenter: Float): Float {
        return (1 - (rvDistanceFrmChildCenter / (orientation.dimension * ALPHA_FACTOR)))
    }


    private val Orientation.dimension: Int
        get() = when(this) {
            Orientation.VERTICAL -> height
            Orientation.HORIZONTAL -> width
        }


    override fun scrollVerticallyBy(
        dy: Int,
        recycler: RecyclerView.Recycler,
        state: RecyclerView.State
    ): Int {
        return super.scrollVerticallyBy(dy, recycler, state)
            .also { updateChildrenAlpha() }
    }


    override fun scrollHorizontallyBy(
        dx: Int,
        recycler: RecyclerView.Recycler,
        state: RecyclerView.State
    ): Int {
        return super.scrollHorizontallyBy(dx, recycler, state)
            .also { updateChildrenAlpha() }
    }


    override fun onScrollStateChanged(state: Int) {
        super.onScrollStateChanged(state)

        if(state == RecyclerView.SCROLL_STATE_IDLE) {
            val recyclerViewCenter = calculateRecyclerViewCenter()
            var minDistance = orientation.dimension
            var selectedChild: View? = null

            for(i in 0 until childCount) {
                val child = (getChildAt(i) ?: continue)
                val childCenter = calculateChildCenter(child)
                val childDistanceFromRvCenter = abs(childCenter - recyclerViewCenter)

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