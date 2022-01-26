package com.godspeed.un_toxic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.godspeed.un_toxic.databinding.ActivityProfileBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Profile : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private val TAG = "Profiles"
    private val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val intent = Intent()
        val ss: String = intent.getStringExtra("Number").toString()
        Log.d("Profiles", ss)

        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.submitbtn.setOnClickListener {
            submitProf()
        }
    }
    private fun submitProf() {
        val profile:HashMap<String, String> = HashMap()
        if(binding.Name.text.isEmpty()){
            binding.Name.error = "Name Required !!"
            return
        }
        if(binding.Number.text.isEmpty()){
            binding.Number.error = "Phone Number Required !!"
            return
        }
        if(binding.numbersm.text.isEmpty()){
            binding.numbersm.error = "Number of Cigarettes Required !!"
            return
        }
        profile["Name"] = binding.Name.text.toString()
        profile["Number"] = binding.Number.text.toString()
        val num = profile["Number"]
        profile["Number of Smoke"] = binding.numbersm.text.toString()
        profile["Price"] = binding.price.text.toString()
        profile["Uid"] = Firebase.auth.uid.toString()

        db.collection("Profiles").document(Firebase.auth.currentUser?.uid.toString())
            .set(profile).addOnCompleteListener{task->
                if (task.isSuccessful){
                    val intent = Intent(this, Homepage::class.java)
                    intent.putExtra("Number", 9920063906)
                    startActivity(intent)
                    finish()
                    Toast.makeText(this, "Welcome Champion !! ", Toast.LENGTH_SHORT).show()
                } else {
                    Log.d(TAG, "Error saving profile! ", task.exception)
                    Toast.makeText(applicationContext, "Something went wrong!!", Toast.LENGTH_SHORT).show()
                }
            }
        }
   }
