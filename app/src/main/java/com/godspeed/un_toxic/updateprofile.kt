package com.godspeed.un_toxic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.godspeed.un_toxic.databinding.ActivityProfileBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class updateprofile : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    private val TAG = "Profiles"
    private val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

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
        profile["Number of Smoke"] = binding.numbersm.text.toString()
        profile["Price"] = binding.price.text.toString()
        profile["Uid"] = Firebase.auth.uid.toString()

        db.collection("Profiles").document(Firebase.auth.currentUser?.uid.toString())
            .update(profile as Map<String, Any>).addOnCompleteListener{ task->
                if (task.isSuccessful){
                    val intent = Intent(this, Homepage::class.java)
                    startActivity(intent)
                    finish()
                    Toast.makeText(this, "Profile Updated Successfully", Toast.LENGTH_SHORT).show()
                } else {
                    Log.d(TAG, "Error saving profile! ", task.exception)
                    Toast.makeText(applicationContext, "Something went wrong!!", Toast.LENGTH_SHORT).show()
                }
            }
    }
}