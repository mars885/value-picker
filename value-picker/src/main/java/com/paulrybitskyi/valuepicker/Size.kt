package com.paulrybitskyi.valuepicker


private const val DIMENSION_NOT_SET = -1


class Size internal constructor(
    val width: Int,
    val height: Int
) {


    companion object {

        fun withFixedWidth(width: Int): Size {
            return Size(width = width, height = DIMENSION_NOT_SET)
        }


        fun withFixedHeight(height: Int): Size {
            return Size(width = DIMENSION_NOT_SET, height = height)
        }


        fun withFixedSize(width: Int, height: Int): Size {
            return Size(width = width, height = height)
        }

    }


    internal val hasWidth: Boolean
        get() = (width != DIMENSION_NOT_SET)


    internal val hasHeight: Boolean
        get() = (height != DIMENSION_NOT_SET)


    internal val hasBothDimensions: Boolean
        get() = (hasWidth && hasHeight)


}


internal fun sizeOf(width: Int, height: Int): Size {
    return Size(width, height)
}