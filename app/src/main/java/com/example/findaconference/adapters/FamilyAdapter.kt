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

class FamilyAdapter(private var list: List<FamilyItem>):
    RecyclerView.Adapter<FamilyAdapter.FamilyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FamilyViewHolder {
        return FamilyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: FamilyViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class FamilyViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private val random = Random

        private var poster: ImageView = itemView.poster
        private var title: TextView = itemView.name_text
        private var venue: TextView = itemView.venue_text
        private var regFee: TextView = itemView.reg_fee

        fun bind(familyItem: FamilyItem) {

            title.text = familyItem.name
            venue.text = familyItem.venue
            regFee.text = familyItem.registration

            poster.layoutParams.height = getRandomIntInRange()
            val getImage = itemView.context.assets.open(familyItem.image)
            poster.setImageDrawable(Drawable.createFromStream(getImage, null))
        }

        private fun getRandomIntInRange(min: Int = 320, max: Int = 380): Int {
            return random.nextInt(max - min + min).plus(min)
        }
    }
}