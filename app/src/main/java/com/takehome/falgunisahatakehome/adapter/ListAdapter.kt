package com.takehome.falgunisahatakehome.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.takehome.falgunisahatakehome.R
import com.takehome.falgunisahatakehome.model.UserRepoResponse


class ListAdapter (private val list: List<UserRepoResponse>, listener: ClickListener) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    private var clickListener: ClickListener = listener
    private var totalForks: Int = 0

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_list_item, parent, false)
        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = list[position]

        // sets the image to the imageview from our itemHolder class
        holder.tile.text = item.name

        // sets the text to the textview from our itemHolder class
        holder.description.text = item.description
        holder.itemView.setOnClickListener {
            clickListener.onItemClick(item)
        }

        totalForks += list[position].forks

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return list.size
    }

    fun totalNumberOfForks(): Int = totalForks

    // Holds the views for adding it to image and text
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tile: TextView = itemView.findViewById(R.id.name)
        val description: TextView = itemView.findViewById(R.id.description)
    }

    interface ClickListener{
        fun onItemClick(item: UserRepoResponse)
    }
}