package com.godspeed.un_toxic.dashboard


import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.godspeed.un_toxic.R

class UserAdapter(val c: DashboardFragment, val userList:ArrayList<UserData>):RecyclerView.Adapter<UserAdapter.UserViewHolder>(){

    inner class UserViewHolder(val v: View):RecyclerView.ViewHolder(v)
    {
        val goal=v.findViewById<TextView>(R.id.mTitle)
        val cost=v.findViewById<TextView>(R.id.mSubTitle)
        val progressBar = v.findViewById<ProgressBar>(R.id.progressBar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {

        val inflater=LayoutInflater.from(parent.context)
        val v=inflater.inflate(R.layout.list_item_for_rewards,parent,false)
        return UserViewHolder(v)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val newlist= userList[position]
        holder.goal.text=newlist.usergoal
        holder.cost.text=newlist.usercost.toString()
        var progress:Int;
        if(Integer.parseInt(newlist.saved)>0){
           progress = (((Integer.parseInt(newlist.saved))/(Integer.parseInt(newlist.usercost)))*100) as Int
        }
        else{
            progress = 0;
        }

        holder.progressBar.setProgress(progress,true);
    }

    override fun getItemCount(): Int {
        return userList.size
    }

}