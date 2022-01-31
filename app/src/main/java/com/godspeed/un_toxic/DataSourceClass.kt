package com.godspeed.un_toxic

class DataSourceClass {
    fun loadIimages(): List<DataClass> {
        return listOf<DataClass>(
            DataClass(R.drawable.smkoingtwins,R.string.title1 ,R.string.affirmation1),
            DataClass(R.drawable.mouthcancer ,R.string.title2,R.string.affirmation2),
            DataClass(R.drawable.lungs ,R.string.title3,R.string.affirmation3),
            DataClass(R.drawable.bones ,R.string.title4,R.string.affirmation4),
            DataClass(R.drawable.facerinkles ,R.string.title5,R.string.affirmation5),
            DataClass(R.drawable.teethissue ,R.string.title6,R.string.affirmation6),
            DataClass(R.drawable.birthissue ,R.string.title7,R.string.affirmation7),
            DataClass(R.drawable.hairloss ,R.string.title8,R.string.affirmation8),
            DataClass(R.drawable.eyes ,R.string.title9,R.string.affirmation9),
            DataClass(R.drawable.hand ,R.string.title10,R.string.affirmation10),
            DataClass(R.drawable.blood ,R.string.title11,R.string.affirmation11),



            )
    }
}