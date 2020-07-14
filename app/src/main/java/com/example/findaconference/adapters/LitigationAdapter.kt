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
import com.example.findaconference.models.ConferenceItem
import kotlinx.android.synthetic.main.litigation_list_item.view.*
import kotlin.random.Random

typealias litigationItemClickListener = (ConferenceItem) -> Unit
typealias favouriteClickListener = (ConferenceItem) -> Unit

class LitigationAdapter(private var list: List<ConferenceItem>, private val clickListener: litigationItemClickListener):
    RecyclerView.Adapter<LitigationAdapter.LitigationViewHolder>(){

    private val context: Context? = null

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

        fun bind(litigationItem: ConferenceItem) {

            title?.text = litigationItem.name
            venue?.text = litigationItem.venue
            regFee?.text = litigationItem.registration

            poster?.layoutParams?.height = getRandomIntInRange()
            val getImage = itemView.context.assets.open(litigationItem.image)
            poster?.setImageDrawable(Drawable.createFromStream(getImage, null))



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