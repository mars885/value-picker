package com.paulrybitskyi.valuepicker.scrollhelpers

internal class InfiniteScrollHelper(override var realItemCount: Int = 0) : ScrollHelper {


    override val adapterItemCount: Int
        get() = Integer.MAX_VALUE


    override fun calculateInitialPosition(position: Int): Int {
        val halfAdapterItemCount = (adapterItemCount / 2)
        val initialPosition = ((halfAdapterItemCount / realItemCount) * realItemCount)

        return (initialPosition + position)
    }


    override fun calculateCurrentPosition(position: Int): Int {
        return (position % realItemCount)
    }


}