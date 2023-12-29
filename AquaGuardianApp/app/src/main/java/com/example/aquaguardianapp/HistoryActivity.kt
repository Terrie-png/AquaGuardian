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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up the RecyclerView with an empty adapter initially
        binding.rvHistoryList.layoutManager = LinearLayoutManager(this)
        val historyAdapter = HistoryAdapter(emptyList())
        binding.rvHistoryList.adapter = historyAdapter

        val retrofit = ServiceBuilder.buildService(APIInterface::class.java)
        // Fetch history items
        retrofit.getHistory().enqueue(object : Callback<HistoryResponse> {
            override fun onResponse(
                call: Call<HistoryResponse>,
                response: Response<HistoryResponse>
            ) {
                if (response.isSuccessful) {
                    // Update the adapter with the fetched history items
                    historyAdapter.items = response.body()?.history ?: emptyList()
                    historyAdapter.notifyDataSetChanged()
                } else {
                    Log.e("HistoryActivity", "Response not successful")
                }
            }

            override fun onFailure(call: Call<HistoryResponse>, t: Throwable) {
                Log.e("HistoryActivity", "Error fetching history", t)
                Toast.makeText(this@HistoryActivity, "Failed to fetch history: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })

        // Set the OnClickListener for the Clear History button
        binding.btnClearHistory.setOnClickListener {
            clearHistory()
        }
    }

    // Inside HistoryActivity
    private fun clearHistory() {
        // Clear the history from the UI
        (binding.rvHistoryList.adapter as HistoryAdapter).items = emptyList()
        binding.rvHistoryList.adapter?.notifyDataSetChanged()

        // To clear the history from the backend,should make an API call
        // val retrofit = ServiceBuilder.buildService(APIInterface::class.java)
        // retrofit.clearHistory().enqueue(/* Implement the callback */)

        // Show confirmation message
        Toast.makeText(this, "History cleared", Toast.LENGTH_SHORT).show()
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
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
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
