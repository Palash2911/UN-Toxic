package com.godspeed.un_toxic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class EffectActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_effect)
        supportActionBar?.setTitle("EFFECTS OF SMOKING")

        val myDataset = DataSourceClass().loadIimages()

        val recyclerView = findViewById<RecyclerView>(R.id.rec_view)
        recyclerView.adapter = HorizontalRecAdapter(this,myDataset)
        recyclerView.layoutManager= LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
        recyclerView.setHasFixedSize(true)
    }
}