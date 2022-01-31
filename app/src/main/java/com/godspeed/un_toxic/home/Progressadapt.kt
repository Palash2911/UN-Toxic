package com.godspeed.un_toxic.home

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.godspeed.un_toxic.R
import com.godspeed.un_toxic.dashboard.UserData
import com.godspeed.un_toxic.home.Progressadapt.*
import com.godspeed.un_toxic.updateprofile
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


val database = Firebase.firestore

class Progressadapt (private var progress: ArrayList<Progressdata> , private  var context: Context) : RecyclerView.Adapter<ItemViewHolder>() {
    class ItemViewHolder(v: View): RecyclerView.ViewHolder(v) {
            val money = v.findViewById<TextView>(R.id.Totalmoneysave)
            val reward = v.findViewById<TextView>(R.id.rewardearn)
            val weekmon = v.findViewById<TextView>(R.id.weekmoney)
            val monthmon = v.findViewById<TextView>(R.id.monthmoney)
            val halfyearmon = v.findViewById<TextView>(R.id.halfyearmoney)
            val yearmon = v.findViewById<TextView>(R.id.yearmoney)
            val addFunds = v.findViewById<Button>(R.id.addFunds);
            val effectbtn = v.findViewById<Button>(R.id.effectbtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adaptlayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.progresscard, parent, false)
        return ItemViewHolder(adaptlayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int ) {
        val proitems = progress[position]

        holder.money.text = proitems.totalmoney.toString()
        holder.reward.text = proitems.rewardsearned.toString()
        holder.weekmon.text = "Rs. " + proitems.weekm
        holder.monthmon.text = "Rs. " + proitems.monthm
        holder.halfyearmon.text = "Rs. " + proitems.halfm
        holder.yearmon.text = "Rs. " + proitems.yearm

        holder.effectbtn.setOnClickListener {
            val intent = Intent(context, updateprofile::class.java)
            startActivity(context, intent, Bundle())
        }
        holder.addFunds.setOnClickListener {
            val inflter = LayoutInflater.from(context)
            val v = inflter.inflate(R.layout.add_funds, null)
            /**set view*/
            val amount = v.findViewById<EditText>(R.id.fund);

            val addDialog = AlertDialog.Builder(context)

            addDialog.setView(v)
            addDialog.setPositiveButton("Ok") { dialog, _ ->
                val updatefund =
                    ((amount.text.toString().toLong()) + (proitems.totalmoney.toString()
                        .toLong())).toString()
                database.collection("Profiles").document(FirebaseAuth.getInstance().uid.toString())
                    .update("Price", updatefund).addOnSuccessListener {
                        Toast.makeText(context, "Funds added to your wallet", Toast.LENGTH_SHORT)
                            .show();
                    }
                addDialog.setNegativeButton("Cancel") { dialog, _ ->
                    dialog.dismiss()
                    Toast.makeText(context, "Cancel", Toast.LENGTH_SHORT).show()

                }
                addDialog.create()
                addDialog.show()
            }
        }
    }

    override fun getItemCount() = progress.size
}