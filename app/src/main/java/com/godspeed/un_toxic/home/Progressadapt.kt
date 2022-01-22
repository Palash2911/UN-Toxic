package com.godspeed.un_toxic.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.godspeed.un_toxic.R
import com.godspeed.un_toxic.home.Progressadapt.*

class Progressadapt (private var progress: ArrayList<Progressdata>) : RecyclerView.Adapter<ItemViewHolder>() {
    class ItemViewHolder(v: View): RecyclerView.ViewHolder(v) {
            val uname = v.findViewById<TextView>(R.id.nameuser)
            val money = v.findViewById<TextView>(R.id.Totalmoneysave)
            val noofsmoke = v.findViewById<TextView>(R.id.smokenumber)
            val tottime = v.findViewById<TextView>(R.id.totaltime)
            val reward = v.findViewById<TextView>(R.id.rewardearn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adaptlayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.progresscard, parent, false)

        return ItemViewHolder(adaptlayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val proitems=progress[position]

        holder.uname.text = "Welcome " + proitems.username
        holder.money.text = proitems.totalmoney.toString()
        holder.noofsmoke.text=proitems.totcigrat.toString()
        holder.tottime.text="0"
        holder.reward.text="0"
    }

    override fun getItemCount() = progress.size

}