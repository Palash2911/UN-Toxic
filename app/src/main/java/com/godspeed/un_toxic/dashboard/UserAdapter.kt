package com.godspeed.un_toxic.dashboard


import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.godspeed.un_toxic.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.w3c.dom.Text

val database = Firebase.firestore

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
        holder.cost.text=newlist.usercost
        database.collection("Profiles").document(FirebaseAuth.getInstance().uid.toString()).collection("Goals").get().addOnSuccessListener { result->
            for(doc in result)
            {
                val intialp = Integer.parseInt(doc.data["saved"].toString()).toDouble()
                val cost = Integer.parseInt(doc.data["usercost"].toString()).toDouble()
                val progr = ((intialp/cost)*100).toInt()
                database.collection("Profiles/" + FirebaseAuth.getInstance().uid.toString() + "/Goals").document(doc.id).update("progress", progr.toString()).addOnSuccessListener {
                    }
            }
        }
        holder.progressBar.setProgress(newlist.progress.toInt(), true)
    }

    override fun getItemCount() : Int{
        return userList.size
    }

}