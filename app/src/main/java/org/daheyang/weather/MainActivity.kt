package org.daheyang.weather

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Response
import com.android.volley.toolbox.ImageLoader
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.w3c.dom.Element
import org.xml.sax.InputSource
import java.io.StringReader
import java.lang.Exception
import java.text.SimpleDateFormat
import javax.xml.parsers.DocumentBuilderFactory

class MainActivity : AppCompatActivity() {
    val symbolView by lazy { "mission1_symbol" }
    val temperatureView by lazy { "mission1_temperature" }
    val upView by lazy { "mission1_up_text" }
    val downView by lazy { "mission1_down_text" }
    val recyclerView by lazy { "mission1_recycler" }
    val queue by lazy { Volley.newRequestQueue(this) }

    val list = mutableListOf<WeatherDate>()
    val adapter = MyAdapter(list)
    val stringToDate = SimpleDateFormat("yyyy-mm-dd")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.let {
            val layoutManager = LinearLayoutManager(this)
            layoutManager.orientation = LinearLayoutManager.HORIZONTAL
            it.layoutManager = layoutManager
            it.adapter = adapter
        }

        val currentRequest = StringRequest(Request.Method.POST,
            "api.openweathermap.org/data/2.5/forecast?q=Seoul&mode=xml&units=metric&appid=0c43cda442e9a24ff16c1f9cda79e554"
                    Response.Listener { response -> parse }, Response.ErrorListener { })

        val forecastRequest = StringRequest(Request.Method.POST,
            "api.openweathermap.org/data/2.5/forecast?q=Seoul&mode=xml&units=metric&appid=0c43cda442e9a24ff16c1f9cda79e554"
                    Response.Listener { response -> parseXMLForecast(response) },
            Response.ErrorListener { })

        queue.add(currentRequest)
        queue.add(forecastRequest)
    }
}
    
    fun parseXMLForecast(response: String) {
        try {
            val factory = DocumentBuilderFactory.newInstance()
            val builder = factory.newDocumentBuilder()
            val doc = builder.parse(InputSource(StringReader(response)))
            doc.documentElement.normalize()

            val tempElement = doc.getElementsByTagName("temperature").item(0) as Element
            val temperature = tempElement.getAttribute("value")

            temperatureView.text = temperature
            upView.text = tempElement.getAttribute("max")
            downView.text = tempElement.getAttribute("min")

            val weatherElement = doc.getElementsByTagName("weather").item(0) as Element
            val symbol = weatherElement.getAttribute("icon")

            val imageLoader = ImageLoader(queue, object: ImageLoader.ImageCache {
                override fun getBitmap(url: String?): Bitmap? {
                    return null
                }

                public override fun putBitmap(url: String?, bitmap: Bitmap?) {
                }
            })

            val uriString = "http://openweathermap.org/img/w/$symbol.png"
            symbolView.setImageUrl(uriString, imageLoader)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }