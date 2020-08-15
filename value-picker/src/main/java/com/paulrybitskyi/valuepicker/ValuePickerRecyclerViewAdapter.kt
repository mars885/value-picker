package com.paulrybitskyi.valuepicker

import android.content.Context
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.paulrybitskyi.valuepicker.ValuePickerRecyclerViewAdapter.ViewHolder
import com.paulrybitskyi.valuepicker.scrollhelpers.ScrollHelper
import com.paulrybitskyi.commons.utils.observeChanges

internal class ValuePickerRecyclerViewAdapter(
    items: List<Item>,
    var valueItemConfig: ValueItemConfig,
    var scrollHelper: ScrollHelper
) : RecyclerView.Adapter<ViewHolder>() {


    var items by observeChanges(items) { _, newItems ->
        scrollHelper.realItemCount = newItems.size
        notifyDataSetChanged()
    }

    var onItemClickListener: ((View) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(createTextView(parent.context))
    }


    private fun createTextView(context: Context): TextView {
        return TextView(context).apply {
            layoutParams = RecyclerView.LayoutParams(
                valueItemConfig.size.width,
                valueItemConfig.size.height
            )
            gravity = Gravity.CENTER
            setTextColor(valueItemConfig.textColor)
            setTextSize(TypedValue.COMPLEX_UNIT_PX, valueItemConfig.textSize)
            typeface = valueItemConfig.typeface
        }
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) = with(holder.valueTv) {
        text = items[scrollHelper.calculateCurrentPosition(position)].title
        setOnClickListener(onItemClickListener)
    }


    override fun getItemCount(): Int {
        return scrollHelper.adapterItemCount
    }


    fun getItemPosition(item: Item): Int? {
        return items.indexOfFirst { it.id == item.id }
            .takeIf { it != -1 }
            ?.let { scrollHelper.calculateInitialPosition(it) }
    }


    fun getItem(position: Int): Item? {
        return items.getOrNull(scrollHelper.calculateCurrentPosition(position))
    }


    class ViewHolder(val valueTv: TextView) : RecyclerView.ViewHolder(valueTv)


}