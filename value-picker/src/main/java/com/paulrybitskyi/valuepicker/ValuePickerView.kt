package com.paulrybitskyi.valuepicker

import android.content.Context
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import androidx.annotation.ColorInt
import androidx.core.content.withStyledAttributes
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.paulrybitskyi.valuepicker.scrollhelpers.ScrollHelper
import com.paulrybitskyi.valuepicker.scrollhelpers.ScrollHelperFactory
import com.paulrybitskyi.commons.ktx.*
import com.paulrybitskyi.commons.ktx.drawing.getTextBounds
import com.paulrybitskyi.commons.recyclerview.utils.disableAnimations
import com.paulrybitskyi.commons.recyclerview.utils.recreateItemViews
import com.paulrybitskyi.commons.utils.observeChanges
import com.paulrybitskyi.valuepicker.utils.getColor


private const val DEFAULT_MAX_VISIBLE_ITEMS = 3
private const val FLING_SPEED_FACTOR = 0.3f


class ValuePickerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {


    private val defaultValueItemMinWidth = getDimensionPixelSize(R.dimen.default_value_item_min_width)
    private val defaultValueItemMinHeight = getDimensionPixelSize(R.dimen.default_value_item_min_height)
    private val defaultValueItemPadding = getDimensionPixelSize(R.dimen.default_value_item_padding)
    private val defaultValueItemTextSize = getDimension(R.dimen.default_value_item_text_size)

    private lateinit var valueItemViewPaint: Paint
    private val valueItemViewTextBounds = Rect()

    private var valueItemConfig by observeChanges(VALUE_ITEM_CONFIG_STUB) { _, config ->
        valuePickerAdapter.valueItemConfig = config
        recreateItemViews()
    }

    private var _selectedItem: Item? = null

    private val itemCount: Int
        get() = items.size

    var maxVisibleItems: Int = DEFAULT_MAX_VISIBLE_ITEMS
        set(value) {
            require(value.isOdd) { "The max visible items value must be odd." }
            field = value
            valuePickerItemDecorator.maxVisibleItems = value
        }

    @get:ColorInt
    var textColor: Int
        set(@ColorInt value) {
            valueItemViewPaint.color = value
            updateValueItemConfig(valueItemConfig.copy(textColor = value))
        }
        get() = valueItemConfig.textColor

    @get:ColorInt
    var dividerColor by observeChanges<Int?>(null) { _, _ ->
        dividerDrawable = dividerDrawable
    }

    var textSize: Float
        set(value) {
            valueItemViewPaint.textSize = value
            updateValueItemConfig(valueItemConfig.copy(textSize = value))
        }
        get() = valueItemConfig.textSize

    var typeface: Typeface
        set(value) {
            valueItemViewPaint.typeface = value
            updateValueItemConfig(valueItemConfig.copy(typeface = value))
        }
        get() = valueItemConfig.typeface

    val selectedItem: Item?
        get() = _selectedItem

    var fixedItemSize by observeChanges<Size?>(null) { _, _ ->
        recalculateValueItemSize()
        reconfigureRecyclerView()
        recreateItemViews()
    }

    var items by observeChanges<List<Item>>(listOf()) { _, items ->
        require(itemCount > 1) { "The item list must contain at least two items." }

        recalculateValueItemSize()
        recalculateMaxVisibleItems(itemCount)
        reconfigureRecyclerView()
        valuePickerAdapter.items = items
        _selectedItem?.let(::scrollToItem) ?: setSelectedItem(items.first())
    }

    private lateinit var valuePickerItemDecorator: ValuePickerItemDecorator
    private lateinit var valuePickerAdapter: ValuePickerRecyclerViewAdapter

    var dividerDrawable: Drawable? = getDrawable(R.drawable.value_picker_divider_drawable)
        set(value) {
            field = dividerColor?.let { value?.setColor(it) } ?: value
            field?.let { valuePickerItemDecorator.dividerDrawable = it }
        }

    private val hasItems: Boolean
        get() = items.isNotEmpty()

    private val hasFixedItemSize: Boolean
        get() = (fixedItemSize?.hasBothDimensions == true)

    private val hasFixedItemWidth: Boolean
        get() = (fixedItemSize?.hasWidth == true)

    private val hasFixedItemHeight: Boolean
        get() = (fixedItemSize?.hasHeight == true)

    var areDividersEnabled by observeChanges(true) { _, newValue ->
        valuePickerItemDecorator.areDividersEnabled = newValue
    }

    var isInfiniteScrollEnabled by observeChanges(false) { _, _ ->
        valuePickerAdapter.scrollHelper = initScrollHelper()
        recreateItemViews()
    }

    var onItemSelectionListener: ((Item) -> Unit)? = null


    init {
        initRecyclerView(context)
        initValueItemConfig()
        initValueItemViewPaint()
        initDefaults()

        attrs?.let { extractAttributes(it, defStyleAttr) }
    }


    private fun extractAttributes(attrs: AttributeSet, defStyle: Int) {
        context.withStyledAttributes(
            set = attrs,
            attrs = R.styleable.ValuePickerView,
            defStyleAttr = defStyle
        ) {
            maxVisibleItems = getInteger(R.styleable.ValuePickerView_valuePickerView_maxVisibleItems, maxVisibleItems)
            textColor = getColor(R.styleable.ValuePickerView_valuePickerView_textColor, textColor)
            dividerColor = getColor(R.styleable.ValuePickerView_valuePickerView_dividerColor, dividerColor)
            textSize = getDimension(R.styleable.ValuePickerView_valuePickerView_textSize, textSize)
            dividerDrawable = getDrawable(R.styleable.ValuePickerView_valuePickerView_divider, dividerDrawable)
            areDividersEnabled = getBoolean(R.styleable.ValuePickerView_valuePickerView_areDividersEnabled, areDividersEnabled)
            isInfiniteScrollEnabled = getBoolean(R.styleable.ValuePickerView_valuePickerView_isInfiniteScrollEnabled, isInfiniteScrollEnabled)
        }
    }


    private fun initRecyclerView(context: Context) {
        clipToPadding = false
        overScrollMode = OVER_SCROLL_NEVER
        disableAnimations()
        addItemDecoration(initValuePickerItemDecorator())
        initSnapHelper()
        layoutManager = initLayoutManager(context)
        adapter = initAdapter()
    }


    private fun initValuePickerItemDecorator(): ValuePickerItemDecorator {
        return ValuePickerItemDecorator(
            areDividersEnabled = areDividersEnabled,
            dividerDrawable = requireNotNull(dividerDrawable),
            maxVisibleItems = maxVisibleItems,
            valueItemConfigProvider = { valueItemConfig }
        ).also { valuePickerItemDecorator = it }
    }


    private fun initSnapHelper() {
        LinearSnapHelper().attachToRecyclerView(this)
    }


    private fun initLayoutManager(context: Context): LayoutManager {
        return ValuePickerLayoutManager(context, VERTICAL, false)
            .apply { onViewSelectedListener = ::handleItemViewSelection }
    }


    private fun handleItemViewSelection(view: View) {
        val position = getChildLayoutPosition(view)

        valuePickerAdapter.getItem(position)?.let {
            setSelectedItem(
                item = it,
                scrollToPosition = false
            )
        }
    }


    private fun reportItemSelection(item: Item) {
        onItemSelectionListener?.invoke(item)
    }


    private fun initAdapter(): ValuePickerRecyclerViewAdapter {
        return ValuePickerRecyclerViewAdapter(
            items = this.items,
            valueItemConfig = this.valueItemConfig,
            scrollHelper = initScrollHelper()
        )
        .apply { onItemClickListener = ::handleItemClick }
        .also { valuePickerAdapter = it }
    }


    private fun initScrollHelper(): ScrollHelper {
        return ScrollHelperFactory.create(isInfiniteScrollEnabled)
            .apply { realItemCount = itemCount }
    }


    private fun handleItemClick(view: View) {
        smoothScrollToView(view)
    }


    private fun smoothScrollToView(view: View) {
        smoothScrollToPosition(getChildAdapterPosition(view))
    }


    private fun initValueItemConfig() {
        valueItemConfig = ValueItemConfig(
            size = getDefaultValueItemSize(),
            textColor = getColor(R.color.default_value_item_text_color),
            textSize = getDimension(R.dimen.default_value_item_text_size),
            typeface = Typeface.SANS_SERIF
        )
    }


    private fun getDefaultValueItemSize(): Size {
        return sizeOf(defaultValueItemMinWidth, defaultValueItemMinHeight)
    }


    private fun initValueItemViewPaint() {
        valueItemViewPaint = Paint().apply {
            isAntiAlias = true
            textAlign = Paint.Align.CENTER
            color = valueItemConfig.textColor
            textSize = valueItemConfig.textSize
            typeface = valueItemConfig.typeface
        }
    }


    private fun initDefaults() {
        maxVisibleItems = maxVisibleItems
        textColor = textColor
        dividerColor = dividerColor
        textSize = textSize
        dividerDrawable = dividerDrawable
        areDividersEnabled = areDividersEnabled
        isInfiniteScrollEnabled = isInfiniteScrollEnabled
    }


    private fun recalculateValueItemSize() {
        valueItemConfig = valueItemConfig.withRecalculatedItemSize()
    }


    private fun ValueItemConfig.withRecalculatedItemSize(): ValueItemConfig {
        return calculateValueItemSize().let {
            copy(size = sizeOf(it.width, it.height))
        }
    }


    private fun calculateValueItemSize(): Size {
        if(!hasItems) return getDefaultValueItemSize()
        if(hasFixedItemSize) return requireNotNull(fixedItemSize)

        val valueItemTextMaxSize = calculateValueItemTextMaxSize()
        val valueItemTextSizeRatio = (valueItemConfig.textSize / defaultValueItemTextSize)
        val valueItemPadding = (defaultValueItemPadding.toFloat() * valueItemTextSizeRatio).toInt()
        val valueItemDividerHeight = getDividerDrawableHeight()
        val valueItemWidth = (valueItemTextMaxSize.width + (valueItemPadding * 2))
        val valueItemHeight = (valueItemTextMaxSize.height + (valueItemPadding * 2) + (valueItemDividerHeight * 2))
        val valueItemMinWidth = (defaultValueItemMinWidth.toFloat() * valueItemTextSizeRatio).toInt()
        val valueItemMinHeight = (defaultValueItemMinHeight.toFloat() * valueItemTextSizeRatio).toInt()
        val finalValueItemWidth = calculateFinalValueItemWidth(valueItemWidth, valueItemMinWidth)
        val finalValueItemHeight = calculateFinalValueItemHeight(valueItemHeight, valueItemMinHeight)

        return sizeOf(width = finalValueItemWidth, height = finalValueItemHeight)
    }


    private fun calculateValueItemTextMaxSize(): Size {
        var valueItemTextMaxWidth = 0
        var valueItemTextMaxHeight = 0

        for(item in items) {
            valueItemViewPaint.getTextBounds(item.title, valueItemViewTextBounds)

            val valueItemTextWidth = valueItemViewTextBounds.width()
            val valueItemTextHeight = valueItemViewTextBounds.height()

            if(valueItemTextMaxWidth < valueItemTextWidth) {
                valueItemTextMaxWidth = valueItemTextWidth
            }

            if(valueItemTextMaxHeight < valueItemTextHeight) {
                valueItemTextMaxHeight = valueItemTextHeight
            }
        }

        return sizeOf(valueItemTextMaxWidth, valueItemTextMaxHeight)
    }


    private fun getDividerDrawableHeight(): Int {
        return (if(areDividersEnabled) checkNotNull(dividerDrawable).intrinsicHeight else 0)
    }


    private fun calculateFinalValueItemWidth(calculatedValueItemWidth: Int, minValueItemWidth: Int): Int {
        if(hasFixedItemWidth) return requireNotNull(fixedItemSize).width

        if(calculatedValueItemWidth < minValueItemWidth) {
            return minValueItemWidth
        }

        return calculatedValueItemWidth
    }


    private fun calculateFinalValueItemHeight(calculatedValueItemHeight: Int, minValueItemHeight: Int): Int {
        if(hasFixedItemHeight) return requireNotNull(fixedItemSize).height

        if(calculatedValueItemHeight < minValueItemHeight) {
            return minValueItemHeight
        }

        return calculatedValueItemHeight
    }


    private fun updateValueItemConfig(newConfig: ValueItemConfig) {
        valueItemConfig = newConfig.withRecalculatedItemSize()
    }


    private fun recalculateMaxVisibleItems(itemSize: Int) {
        if(itemSize >= maxVisibleItems) {
            return
        }

        maxVisibleItems = when {
            (itemSize <= DEFAULT_MAX_VISIBLE_ITEMS) -> DEFAULT_MAX_VISIBLE_ITEMS
            itemSize.isOdd -> itemSize
            else -> (itemSize - 1)
        }
    }


    private fun reconfigureRecyclerView() {
        val valueItemViewHeight = valueItemConfig.size.height
        val rvHeight = (valueItemViewHeight * maxVisibleItems)
        val maxVisibleValuesFromCenter = (maxVisibleItems / 2)
        val verticalPadding = (valueItemViewHeight * maxVisibleValuesFromCenter)

        layoutParamsHeight = rvHeight
        setVerticalPadding(verticalPadding)
    }


    fun setSelectedItem(item: Item) {
        setSelectedItem(item, scrollToPosition = true)
    }


    private fun setSelectedItem(item: Item, scrollToPosition: Boolean) {
        _selectedItem = item

        if(scrollToPosition) {
            scrollToItem(item)
        }

        reportItemSelection(item)
    }


    private fun scrollToItem(item: Item) {
        valuePickerAdapter.getItemPosition(item)?.let(::scrollToPosition)
    }


    override fun fling(velocityX: Int, velocityY: Int): Boolean {
        return super.fling(
            velocityX,
            (velocityY * FLING_SPEED_FACTOR).toInt()
        )
    }


}