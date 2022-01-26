package com.godspeed.un_toxic.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.godspeed.un_toxic.databinding.FragmentProgressBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.w3c.dom.Text
import android.content.Intent.getIntent
import android.widget.Toast


class HomeFragment : Fragment() {

    val db = Firebase.firestore

    private lateinit var binding: FragmentProgressBinding
    private lateinit var prolist: ArrayList<Progressdata>
    private lateinit var prorecycle: RecyclerView
    // This property is only valid between onCreateView and
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProgressBinding.inflate(inflater, container, false)

        prorecycle=binding.pro
        prorecycle.layoutManager= LinearLayoutManager(requireContext())
        prorecycle.setHasFixedSize(true)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val useruid = Firebase.auth.uid.toString()

        db.collection("Profiles").get().addOnSuccessListener {
                result->
            for(document in result){
                if(document.data["Uid"]==useruid) {
                    Log.d("ID", document.toString())
                    val money = (((document.data["Price"]).toString()).toLong())*(((document.data["Number of Smoke"]).toString()).toLong())
                    val smokes = Integer.parseInt( document.data["Number of Smoke"].toString())
                    val w = (Integer.parseInt(document.data["Number of Smoke"].toString()) * 7 * Integer.parseInt(document.data["Price"].toString())).toString()
                    val m = (Integer.parseInt(document.data["Number of Smoke"].toString()) * 30 * Integer.parseInt(document.data["Price"].toString())).toString()
                    val h = (Integer.parseInt(document.data["Number of Smoke"].toString()) * 183 * Integer.parseInt(document.data["Price"].toString())).toString()
                    val y = (Integer.parseInt(document.data["Number of Smoke"].toString())* 365 * Integer.parseInt(document.data["Price"].toString())).toString()
                    prolist.add(Progressdata(smokes, money, 10,w,m,h,y))
                    break
                }
            }

            val adapter = Progressadapt(prolist)
            prorecycle.adapter = adapter
        }.addOnFailureListener { exception ->
                Log.d("TAG", "Error getting documents: ", exception)
//                Toast.makeText(this,"Error getting documents: ", Toast.LENGTH_SHORT).show()
            }
        prolist = arrayListOf<Progressdata>()
    }
}


