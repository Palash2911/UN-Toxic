package com.godspeed.un_toxic.home

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import com.godspeed.un_toxic.EffectActivity
import com.godspeed.un_toxic.R
import com.godspeed.un_toxic.dashboard.UserData
import com.godspeed.un_toxic.home.Progressadapt.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


private lateinit var database:FirebaseFirestore
private val auth = FirebaseAuth.getInstance()
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
        context = parent.context;
        database = FirebaseFirestore.getInstance();

        return ItemViewHolder(adaptlayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int ) {
        val proitems=progress[position]

        holder.money.text = proitems.totalmoney.toString()
        holder.reward.text=proitems.rewardsearned.toString()
        holder.weekmon.text = "Rs. " + proitems.weekm
        holder.monthmon.text = "Rs. " + proitems.monthm
        holder.halfyearmon.text = "Rs. " + proitems.halfm
        holder.yearmon.text = "Rs. " + proitems.yearm

        holder.effectbtn.setOnClickListener {
            Toast.makeText(this.context, "Press Back Button To Return To HomePage", Toast.LENGTH_SHORT).show()
            val intent = Intent(this.context, EffectActivity::class.java)
            startActivity(this.context, intent, Bundle())
        }

        holder.addFunds.setOnClickListener{
            val inflter = LayoutInflater.from(context)
            val v = inflter.inflate(R.layout.add_funds,null)
            /**set view*/
            val amount = v.findViewById<EditText>(R.id.fund);

            val addDialog = AlertDialog.Builder(context)

            addDialog.setView(v)
            addDialog.setPositiveButton("Ok"){
                    dialog,_->
               val upfund = ((amount.text.toString().toLong()) + (proitems.totalmoney.toString().toLong())).toString()
                var fundsreward=upfund.toLong()
                database.collection("Profiles").document(FirebaseAuth.getInstance().uid.toString())
                    .update("Price", upfund).addOnSuccessListener {
                        Toast.makeText(context, "Funds added to your wallet", Toast.LENGTH_SHORT)
                            .show();
                    }
                database.collection("Profiles").document(FirebaseAuth.getInstance().uid.toString()).collection("Goals").get().addOnSuccessListener { result->
                        for(documents in result)
                        {
                            val save = documents.data["saved"].toString().toLong()
                            val cos = documents.data["usercost"].toString().toLong()
//                            Log.d("NSAVe", nsave.toString())
                            if(fundsreward>0)
                            {
                                if(save==cos)
                                {
                                    fundsreward-=save
                                }
                            }
                            else
                            {
                                break
                            }
                        }
                    }
                database.collection("Profiles").document(FirebaseAuth.getInstance().uid.toString()).collection("Goals").get().addOnSuccessListener { result->
                    for(documents in result)
                    {
                        val save = documents.data["saved"].toString().toLong()
                        val cos = documents.data["usercost"].toString().toLong()
                        val nsave = fundsreward
//                            Log.d("NSAVe", nsave.toString())
                        if(fundsreward>0)
                        {
                            if((nsave+save)<=cos)
                            {
                                val newsaved = (nsave+save)
                                fundsreward-=newsaved
                                database.collection("Profiles").document(FirebaseAuth.getInstance().uid.toString()).collection("Goals").document(documents.id).update("saved", newsaved.toString()).addOnSuccessListener {
                                    Toast.makeText(context,"Money Added to Rewards", Toast.LENGTH_LONG).show()
                                }.addOnFailureListener{
                                    Toast.makeText(context,"Reward Earned Failed", Toast.LENGTH_LONG).show()
                                }
                            }
                            else
                            {
                                var newsaved = (cos-save)
                                fundsreward-=newsaved
                                newsaved+=save
                                database.collection("Profiles").document(FirebaseAuth.getInstance().uid.toString()).collection("Goals").document(documents.id).update("saved", newsaved.toString()).addOnSuccessListener {
                                    Toast.makeText(context,"Money Added to Rewards", Toast.LENGTH_LONG).show()
                                }.addOnFailureListener{
                                    Toast.makeText(context,"Reward Earned Failed", Toast.LENGTH_LONG).show()
                                }
                            }
                        }
                        else
                        {
                            break
                        }
                    }
                }
            }
            addDialog.setNegativeButton("Cancel"){
                    dialog,_->
                dialog.dismiss()
                Toast.makeText(context,"Cancel",Toast.LENGTH_SHORT).show()

            }
            addDialog.create()
            addDialog.show()
        }
    }
    override fun getItemCount() = progress.size
}