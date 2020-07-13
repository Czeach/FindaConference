package com.example.findaconference.adapters

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.findaconference.R
import com.example.findaconference.database.Favourites
import com.example.findaconference.database.FavouritesDatabase
import com.example.findaconference.fragments.MainFragment
import com.example.findaconference.models.LitigationItem
import kotlinx.android.synthetic.main.litigation_list_item.view.*
import kotlin.random.Random

typealias litigationItemClickListener = (LitigationItem) -> Unit

class LitigationAdapter(private var list: List<LitigationItem>, private val clickListener: litigationItemClickListener):
    RecyclerView.Adapter<LitigationAdapter.LitigationViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LitigationViewHolder {
        return LitigationViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.litigation_list_item, parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: LitigationViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class LitigationViewHolder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener {

        private val random = Random

        private var poster: ImageView? = null
        private var title: TextView? = null
        private var venue: TextView? = null
        private var regFee: TextView? = null
        private var fav: ImageView? = null

        init {
            poster = itemView.poster
            title = itemView.name_text
            venue = itemView.venue_text
            regFee = itemView.reg_fee
            fav = itemView.fav
            itemView.setOnClickListener(this)
        }

        fun bind(litigationItem: LitigationItem) {

            title?.text = litigationItem.name
            venue?.text = litigationItem.venue
            regFee?.text = litigationItem.registration

            poster?.layoutParams?.height = getRandomIntInRange()
            val getImage = itemView.context.assets.open(litigationItem.image)
            poster?.setImageDrawable(Drawable.createFromStream(getImage, null))

            fav?.setOnClickListener {

                val favourites: Favourites? = null
                val favouritesDatabase: FavouritesDatabase? = null

                if (favouritesDatabase?.favouriteDao()?.isFavorite(1) != 1) {
                    fav?.setImageResource(R.drawable.like_red)
                } else {
                    fav?.setImageResource(R.drawable.like_white)
                    favouritesDatabase.favouriteDao().delete(favourites!!)
                }
            }

//            if (MainActivity.favoriteDatabase.favoriteDao().isFavorite(productList.getId())==1)
//                viewHolder.fav_btn.setImageResource(R.drawable.ic_favorite);
//            else
//                viewHolder.fav_btn.setImageResource(R.drawable.ic_favorite_border_black_24dp);
        }

        private fun getRandomIntInRange(min: Int = 320, max: Int = 380): Int {
            return random.nextInt(max - min + min).plus(min)
        }

        override fun onClick(v: View?) {
            val litigation = list[adapterPosition]
            clickListener.invoke(litigation)
        }
    }
}