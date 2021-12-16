package org.daheyang.weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.volley.toolbox.Volley
import java.text.SimpleDateFormat

class MainActivity : AppCompatActivity() {
    val symbolView by lazy { mission1_symbol }
    val temperatureView by lazy { mission1_temperature }
    val upView by lazy { mission1_up_text }
    val downView by lazy { mission1_down_text }
    val recyclerView by lazy { mission1_recycler }
    val queue by lazy { Volley.newRequestQueue(this) }

    val list = mutableListOf<>()
    val adapter = MyAdapter(list)
    val strintToDate = SimpleDateFormat("yyyy-mm-dd")
 /*   override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)*/
    }
}