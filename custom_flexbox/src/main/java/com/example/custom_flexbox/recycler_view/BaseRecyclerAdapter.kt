package com.example.library.recycler_view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.z5x.jalesh.recycler_view.OnItemClickListener

open abstract class BaseRecyclerAdapter<T, L : OnItemClickListener, VH : BaseViewHolder<T, L?>> :
        RecyclerView.Adapter<VH> {

    private var listener: L? = null
    private var items: MutableList<T>
    private var layoutInflater: LayoutInflater? = null

    public constructor(context: Context?) {
        layoutInflater = LayoutInflater.from(context)
        items = ArrayList()
    }

    public constructor(context: Context, listener: L) {
        layoutInflater = LayoutInflater.from(context)
        items = ArrayList()
        this.listener = listener
    }

    override abstract fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item: T = items.get(position)
        holder.onBind(item, listener)
    }

    override fun getItemCount(): Int {
        if (!items.isNullOrEmpty())
            return items.size
        else
            return 0
    }

    fun hasItems(): Boolean {
        return !items.isNullOrEmpty()
    }

    public fun getListener(): L? {
        return listener
    }

    public fun setListener(listener: L) {
        this.listener = listener
    }

    public fun getItems(): List<T> {
        return items
    }

    public fun setItems(items: MutableList<T>) {
        if (!items.isNullOrEmpty()) {
            this.items.clear()
            this.items.addAll(items)
            notifyDataSetChanged()
        }
    }

    public fun getItem(position: Int): T {
        return items.get(position)
    }

    public fun add(item: T) {
        items.add(item)
        notifyItemInserted(items.size - 1)
    }

    public fun addAll(items: MutableList<T>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    public fun clear() {
        items.clear()
        notifyDataSetChanged()
    }

    public fun remove(item: T) {
        val position = items.indexOf(item)
        if (position > -1) {
            items.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    public fun isEmpty(): Boolean {
        return getItemCount() == 0
    }

    override fun setHasStableIds(hasStableIds: Boolean) {
        super.setHasStableIds(hasStableIds)
    }

    protected fun inflate(layout: Int, parent: ViewGroup, attachToRoot: Boolean): View? {
        return layoutInflater?.inflate(layout, parent, attachToRoot)
    }

    protected fun inflate(layout: Int, parent: ViewGroup): View? {
        return layoutInflater?.inflate(layout, parent)
    }
}