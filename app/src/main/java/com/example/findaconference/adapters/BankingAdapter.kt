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
import com.example.findaconference.models.LitigationItem
import kotlinx.android.synthetic.main.list_item.view.*
import kotlin.random.Random

typealias bankingItemClickListener = (BankingItem) -> Unit

class BankingAdapter(private var list: List<BankingItem>, private val clickListener: bankingItemClickListener):
    RecyclerView.Adapter<BankingAdapter.BankingViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BankingViewHolder {
        return BankingViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: BankingViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class BankingViewHolder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener {

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

        fun bind(bankingItem: BankingItem) {

            title?.text = bankingItem.name
            venue?.text = bankingItem.venue
            regFee?.text = bankingItem.registration

            poster?.layoutParams?.height = getRandomIntInRange()
            val getImage = itemView.context.assets.open(bankingItem.image)
            poster?.setImageDrawable(Drawable.createFromStream(getImage, null))
        }

        private fun getRandomIntInRange(min: Int = 320, max: Int = 380): Int {
            return random.nextInt(max - min + min).plus(min)
        }

        override fun onClick(v: View?) {
            val banking = list[adapterPosition]
            clickListener.invoke(banking)
        }
    }
}