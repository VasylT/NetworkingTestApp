package com.fromitt.stackoverflowtestapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

/*
* @author Tkachov Vasyl
* @since 13.10.2020
*/
class ListAdapter(context: Context) : RecyclerView.Adapter<ListAdapter.ItemViewHolder>() {

    private var dimension: Int = 64

    init {
        val density = context.resources.displayMetrics.density
        dimension = (density * 64).toInt()
        hasStableIds()
    }

    var items: MutableList<Item> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var listener: Listener? = null

    interface Listener {
        fun onItemClicked(item: Item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ItemViewHolder(v)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    private fun getItem(position: Int): Item = items[position]

    override fun getItemId(position: Int): Long = items[position].id

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleView: TextView = itemView.findViewById(R.id.title_view)
        private val pictureView: SquareCellView = itemView.findViewById(R.id.picture_view)

        fun onBind(item: Item) {
            titleView.text = item.title

            if (item.url != null) {
                Picasso.get()
                    .load(item.url)
                    .resize(dimension, dimension)
                    .centerCrop()
                    .error(R.drawable.ic_baseline_broken_image_24)
                    .into(pictureView)
            } else {
                pictureView.setImageResource(R.drawable.ic_baseline_image_24)
            }

            itemView.setOnClickListener {
                listener?.onItemClicked(item)
            }
        }
    }
}