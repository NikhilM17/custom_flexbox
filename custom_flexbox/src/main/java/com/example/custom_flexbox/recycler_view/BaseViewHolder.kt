package com.example.library.recycler_view

import android.view.View
import android.widget.AdapterView
import androidx.annotation.Nullable
import androidx.recyclerview.widget.RecyclerView
import com.z5x.jalesh.recycler_view.OnItemClickListener

abstract class BaseViewHolder<T, L : OnItemClickListener?> : RecyclerView.ViewHolder {

    constructor(itemView: View) : super(itemView)

    constructor(itemView: View, listener: OnItemClickListener) : super(itemView)

    abstract fun onBind(item: T, @Nullable listener: L?)

}