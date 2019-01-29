package com.fernando.mylauncher

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_adapter.view.*

class ItemAdapter(private val appContext: Context, private val itemList: List<Item>) :
    RecyclerView.Adapter<ItemAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder =
        ListViewHolder(LayoutInflater.from(appContext).inflate(R.layout.item_adapter, parent, false))

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.view.tvItem.text = itemList[position].name
        itemList[position].icon.let {
            Glide.with(appContext)
                .load(it)
                .into(holder.view.ivItem)
        }
    }

    // ViewHolder com as views
    class ListViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}