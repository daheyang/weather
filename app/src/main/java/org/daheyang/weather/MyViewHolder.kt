package org.daheyang.weather

import android.graphics.Bitmap
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class WeatherDate(var max: String, var min: String, var day: String, var img: Bitmap)

class MyViewHolder (view: View): RecyclerView.ViewHolder(view){
    val dayView = view.findViewById<TextView>(R.id.mission1_item_day)
    val minView = view.findViewById<TextView>(R.id.mission1_item_min)
    val maxView = view.findViewById<TextView>(R.id.mission1_item_max)
    val imgView = view.findViewById<ImageView>(R.id.mission1_item_image)

}