package com.example.findaconference.adapters

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.findaconference.R
import com.example.findaconference.database.Favourites
import kotlinx.android.synthetic.main.favourites_list_item.view.*

class FavouritesAdapter internal constructor(
    context: Context
): RecyclerView.Adapter<FavouritesAdapter.FavouritesViewHolder>(){

    private val context: Context? = null

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var favourites = emptyList<Favourites>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouritesViewHolder {
        val itemView = inflater.inflate(R.layout.favourites_list_item, parent, false)
        return FavouritesViewHolder(itemView)
    }

    override fun getItemCount(): Int = favourites.size

    override fun onBindViewHolder(holder: FavouritesViewHolder, position: Int) {
        val current = favourites[position]

        holder.title?.text = current.name
        holder.venue?.text = current.venue

        val getImage = context?.assets?.open(current.image)
        holder.image?.setImageDrawable(Drawable.createFromStream(getImage, null))
    }

    internal fun setFavourites(favourites: List<Favourites>) {
        this.favourites = favourites
        notifyDataSetChanged()
    }

    inner class FavouritesViewHolder(view:View): RecyclerView.ViewHolder(view) {

        var image: ImageView? = null
        var title: TextView? = null
        var venue: TextView? = null
    }
}