package com.paulrybitskyi.valuepicker.scrollerhelpers

internal interface ScrollerHelper {

    val adapterItemCount: Int

    var dataSetItemCount: Int


    fun calculateAdapterPosition(dataSetPosition: Int): Int

    fun calculateDataSetPosition(adapterPosition: Int): Int

}