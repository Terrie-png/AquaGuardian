package com.example.aquaguardianapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aquaguardianapp.databinding.ActivityHistoryBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HistoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHistoryBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: HistoryAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = findViewById(R.id.rvHistoryList)
        adapter = HistoryAdapter(listOf()) // Initialize with empty list
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val retrofit = ServiceBuilder.buildService(APIInterface::class.java)

        // Fetch history items
        retrofit.getHistory().enqueue(object : Callback<HistoryResponse> {
            override fun onResponse(
                call: Call<HistoryResponse>,
                response: Response<HistoryResponse>
            ) {
                if (response.isSuccessful) {
                    val historyList = response.body()?.history ?: emptyList()
                    adapter.items = historyList
                    adapter.notifyDataSetChanged()
                }
            }
            override fun onFailure(call: Call<HistoryResponse>, t: Throwable) {
                // Log the error as well, which is useful for debugging purposes
                Log.e("HistoryActivity", "Error fetching history", t)

                // Show an error message to the user
                runOnUiThread {
                    Toast.makeText(this@HistoryActivity, "Failed to fetch history: ${t.message}", Toast.LENGTH_LONG).show()
                }
            }
        })

//        retrofit.getHistory().enqueue(
//            object : Callback<HistoryResponse> { override fun onResponse(
//                call: Call<HistoryResponse>,
//                response: Response<HistoryResponse>
//            ) {
//                if (response.isSuccessful) {
//                    // This is where you access 'history'
//                    val historyList = response.body()?.history ?: emptyList()
//                    // ... use historyList
//                } else {
//                    // Handle error...
//                }
//            }
//
//            override fun onFailure(call: Call<HistoryResponse>, t: Throwable) {
//                // Handle failure...
//            }
//        })

    }
}

class HistoryAdapter(var items: List<HistoryItem>) : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.history_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount() = items.size
    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val timeTextView: TextView = itemView.findViewById(R.id.tvTime)
        private val dateTextView: TextView = itemView.findViewById(R.id.tvDate)
        private val locationTextView: TextView = itemView.findViewById(R.id.tvLocation)

        fun bind(historyItem: HistoryItem) {
            timeTextView.text = historyItem.time
            dateTextView.text = historyItem.date
            locationTextView.text = historyItem.location
        }
    }

}
