package develop.abdusamid.networkingvolley.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.VolleyLog
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import develop.abdusamid.networkingvolley.adapters.RvAdapter
import develop.abdusamid.networkingvolley.databinding.ActivityGithubBinding
import develop.abdusamid.networkingvolley.models.User

class GithubActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGithubBinding
    private lateinit var userAdapter: RvAdapter
    private lateinit var requestQueue: RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGithubBinding.inflate(layoutInflater)
        setContentView(binding.root)

        requestQueue = Volley.newRequestQueue(this)
        loadList()

    }

    private fun loadList() {
        VolleyLog.DEBUG = true
        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET,
            "https://api.github.com/users",
            null,
            {
                val type = object : TypeToken<ArrayList<User>>() {}.type
                val list = Gson().fromJson<ArrayList<User>>(it.toString(), type)
                userAdapter = RvAdapter(list)
                binding.rv.adapter = userAdapter
            },
            {
                Toast.makeText(this, "Load List", Toast.LENGTH_SHORT).show()
            }
        )
        requestQueue.add(jsonArrayRequest)
    }

}