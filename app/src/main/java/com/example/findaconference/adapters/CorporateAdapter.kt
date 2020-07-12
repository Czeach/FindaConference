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
import com.example.findaconference.models.CorporateItem
import com.example.findaconference.models.LitigationItem
import kotlinx.android.synthetic.main.list_item.view.*
import kotlin.random.Random

typealias corporateItemClickListener = (CorporateItem) -> Unit

class CorporateAdapter(private var list: List<CorporateItem>, private val clickListener: corporateItemClickListener):
    RecyclerView.Adapter<CorporateAdapter.CorporateViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CorporateViewHolder {
        return CorporateViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: CorporateViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class CorporateViewHolder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener {

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

        fun bind(corporateItem: CorporateItem) {

            title?.text = corporateItem.name
            venue?.text = corporateItem.venue
            regFee?.text = corporateItem.registration

            poster?.layoutParams?.height = getRandomIntInRange()
            val getImage = itemView.context.assets.open(corporateItem.image)
            poster?.setImageDrawable(Drawable.createFromStream(getImage, null))
        }

        private fun getRandomIntInRange(min: Int = 320, max: Int = 380): Int {
            return random.nextInt(max - min + min).plus(min)
        }

        override fun onClick(v: View?) {
            val corporate = list[adapterPosition]
            clickListener.invoke(corporate)
        }
    }
}