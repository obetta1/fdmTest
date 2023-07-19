package com.ben.papytest.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.ben.papytest.data.PlaceAdapter
import com.ben.papytest.createDummyData
import com.ben.papytest.databinding.ActivityScheduleBinding

class ScheduleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScheduleBinding
    private lateinit var placeScheduleAdapter: PlaceAdapter
    private lateinit var dataList: List<Any>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScheduleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dataList = createDummyData() // this will be replaced with actual data source

        // Set up the RecyclerView with LinearLayoutManager
        val layoutManager = LinearLayoutManager(this)
        binding.placeRecyclerview.layoutManager = layoutManager

        placeScheduleAdapter = PlaceAdapter(dataList, this)
        binding.placeRecyclerview.adapter = placeScheduleAdapter

        binding.include.imageView1.setOnClickListener {
            startActivity(Intent(this, MapsActivity::class.java))
        }
    }
}