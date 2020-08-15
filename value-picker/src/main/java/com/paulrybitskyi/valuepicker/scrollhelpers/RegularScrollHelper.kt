package com.paulrybitskyi.valuepicker.scrollhelpers

internal class RegularScrollHelper(override var realItemCount: Int = 0) : ScrollHelper {


    override val adapterItemCount: Int
        get() = realItemCount


    override fun calculateInitialPosition(position: Int): Int {
        return position
    }


    override fun calculateCurrentPosition(position: Int): Int {
        return position
    }


}