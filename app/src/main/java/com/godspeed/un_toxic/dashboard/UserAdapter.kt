package com.godspeed.un_toxic.dashboard


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.godspeed.un_toxic.R

class UserAdapter(val c: DashboardFragment, val userList:ArrayList<UserData>):RecyclerView.Adapter<UserAdapter.UserViewHolder>(){

    inner class UserViewHolder(val v: View):RecyclerView.ViewHolder(v)
    {
        val goal=v.findViewById<TextView>(R.id.mTitle)
        val cost=v.findViewById<TextView>(R.id.mSubTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {

        val inflater=LayoutInflater.from(parent.context)
        val v=inflater.inflate(R.layout.list_item_for_rewards,parent,false)
        return UserViewHolder(v)


    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val newlist= userList[position]
        holder.goal.text=newlist.usergoal
        holder.cost.text=newlist.usercost

    }

    override fun getItemCount(): Int {
        return userList.size
    }

}