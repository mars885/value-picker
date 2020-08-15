package com.paulrybitskyi.valuepicker.scrollhelpers

internal interface ScrollHelper {

    val adapterItemCount: Int

    var realItemCount: Int


    fun calculateInitialPosition(position: Int): Int

    fun calculateCurrentPosition(position: Int): Int

}