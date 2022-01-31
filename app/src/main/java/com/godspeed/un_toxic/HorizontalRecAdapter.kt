package com.godspeed.un_toxic

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HorizontalRecAdapter(private val context: Context, private val dataset: List<DataClass>):
    RecyclerView.Adapter<HorizontalRecAdapter.myviewholder>(){

    class myviewholder(view: View): RecyclerView.ViewHolder(view)
    {

        val imageView: ImageView =view.findViewById(R.id.imagv)
        val titletext: TextView =view.findViewById(R.id.titlefortext)
        val textvw: TextView =view.findViewById(R.id.info)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myviewholder {
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.horizontal_list, parent, false)

        return myviewholder(adapterLayout)
    }

    override fun onBindViewHolder(holder: myviewholder, position: Int) {
        val item = dataset[position]

        holder.imageView.setImageResource(item.Imageres)
        holder.titletext.text=context.resources.getString(item.Titleres)
        holder.textvw.text=context.resources.getString(item.Stringrres)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}