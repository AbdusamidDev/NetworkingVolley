package develop.abdusamid.networkingvolley.activity

import android.graphics.Bitmap
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.ImageRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import develop.abdusamid.networkingvolley.databinding.ActivityMainBinding
import develop.abdusamid.networkingvolley.utils.MyNetworkHelper

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var myNetworkHelper: MyNetworkHelper
    private lateinit var requestQueue: RequestQueue
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        myNetworkHelper = MyNetworkHelper(this)
        requestQueue = Volley.newRequestQueue(this)

        loadObject()
    }

    private fun loadObject() {
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,
            "http://ip.jsontest.com",
            null,
            {
                //class olib kelish
                val strring = it?.getString("ip")
                binding.tvInfo.text = strring
            },
            {
                Toast.makeText(this, "Load Object", Toast.LENGTH_SHORT).show()
            }
        )
        requestQueue.add(jsonObjectRequest)
    }

    fun loadImage(imageView: ImageView, url: String) {
        val imageRequest = ImageRequest(
            url,
            {
                imageView.setImageBitmap(it)
            },
            0, 0, ImageView.ScaleType.CENTER_CROP,
            Bitmap.Config.ARGB_8888
        ) {
            Toast.makeText(this, "Load Image", Toast.LENGTH_SHORT).show()

        }
        requestQueue.add(imageRequest)
    }
}