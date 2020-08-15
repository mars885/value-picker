package com.paulrybitskyi.valuepicker.scrollhelpers

internal object ScrollHelperFactory {


    fun create(isInfinite: Boolean): ScrollHelper {
        return if(isInfinite) {
            InfiniteScrollHelper()
        } else {
            RegularScrollHelper()
        }
    }


}