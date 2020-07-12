package com.example.findaconference.adapters

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.findaconference.R
import com.example.findaconference.models.CorporateItem
import com.example.findaconference.models.FamilyItem
import kotlinx.android.synthetic.main.list_item.view.*
import kotlin.random.Random

typealias familyItemClickListener = (FamilyItem) -> Unit

class FamilyAdapter(private var list: List<FamilyItem>, private val clickListener: familyItemClickListener):
    RecyclerView.Adapter<FamilyAdapter.FamilyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FamilyViewHolder {
        return FamilyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: FamilyViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class FamilyViewHolder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener {

        private val random = Random

        private var poster: ImageView? = null
        private var title: TextView? = null
        private var venue: TextView? = null
        private var regFee: TextView? = null

        init {
            poster = itemView.poster
            title = itemView.name_text
            venue = itemView.venue_text
            regFee = itemView.reg_fee
            itemView.setOnClickListener(this)
        }

        fun bind(familyItem: FamilyItem) {

            title?.text = familyItem.name
            venue?.text = familyItem.venue
            regFee?.text = familyItem.registration

            poster?.layoutParams?.height = getRandomIntInRange()
            val getImage = itemView.context.assets.open(familyItem.image)
            poster?.setImageDrawable(Drawable.createFromStream(getImage, null))
        }

        private fun getRandomIntInRange(min: Int = 320, max: Int = 380): Int {
            return random.nextInt(max - min + min).plus(min)
        }

        override fun onClick(v: View?) {
            val family = list[adapterPosition]
            clickListener.invoke(family)
        }
    }
}