package com.example.findaconference.adapters

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.findaconference.R
import com.example.findaconference.models.BankingItem
import kotlinx.android.synthetic.main.list_item.view.*
import kotlin.random.Random

class BankingAdapter(private var list: List<BankingItem>):
    RecyclerView.Adapter<BankingAdapter.BankingViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BankingViewHolder {
        return BankingViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: BankingViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class BankingViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private val random = Random

        private var poster: ImageView = itemView.poster
        private var title: TextView = itemView.name_text
        private var venue: TextView = itemView.venue_text
        private var regFee: TextView = itemView.reg_fee

        fun bind(bankingItem: BankingItem) {

            title.text = bankingItem.name
            venue.text = bankingItem.venue
            regFee.text = bankingItem.registration

            poster.layoutParams.height = getRandomIntInRange()
            val getImage = itemView.context.assets.open(bankingItem.image)
            poster.setImageDrawable(Drawable.createFromStream(getImage, null))
        }

        private fun getRandomIntInRange(min: Int = 320, max: Int = 380): Int {
            return random.nextInt(max - min + min).plus(min)
        }
    }
}