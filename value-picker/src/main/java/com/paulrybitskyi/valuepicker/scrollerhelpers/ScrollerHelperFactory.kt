package com.paulrybitskyi.valuepicker.scrollerhelpers

import com.paulrybitskyi.valuepicker.scrollerhelpers.concrete.InfiniteScrollerHelper
import com.paulrybitskyi.valuepicker.scrollerhelpers.concrete.RegularScrollerHelper

internal object ScrollerHelperFactory {


    @JvmStatic
    fun create(isInfinite: Boolean, dataSetItemCount: Int): ScrollerHelper {
        return if(isInfinite) {
            InfiniteScrollerHelper(dataSetItemCount)
        } else {
            RegularScrollerHelper(dataSetItemCount)
        }
    }


}