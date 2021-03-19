package com.example.justfollow

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import kotlinx.android.synthetic.main.activity_local03.*
import kotlinx.android.synthetic.main.activity_main.*

class Local05 : AppCompatActivity() {

    private lateinit var adapter: RecyclerAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_local05)



        val list = ArrayList<YoutubeItem>()
        adapter = RecyclerAdapter(list)
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(
                DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Log.d("Local05",
                        "onItemSelected : $position, ${spinner.getItemAtPosition(position)}")
                when (spinner.getItemAtPosition(position)) {
                    "Historic Tourist Site" -> {    //관광지
                        Historic()
                    }
                    "Shopping" -> { //쇼핑
                        Shopping()
                    }
                    "Accommodation " -> {   //숙박
                        Accommodation()
                    }
                    "Dining" ->{        //음식
                        Dining()
                    }
                    "cultural facilities" -> {      //문화시설
                        cultural()
                    }
                    else -> {
                        Historic()
                    }
                }
            }
        }
    }

    fun Historic() {
        val list = ArrayList<YoutubeItem>()
        list.add(YoutubeItem(getDrawable(R.drawable.image5_1)!!, getString(R.string.title05_1)))
        list.add(YoutubeItem(getDrawable(R.drawable.image5_2)!!, getString(R.string.title05_2)))
        list.add(YoutubeItem(getDrawable(R.drawable.image5_3)!!, getString(R.string.title05_3)))
        list.add(YoutubeItem(getDrawable(R.drawable.image5_4)!!, getString(R.string.title05_4)))
        list.add(YoutubeItem(getDrawable(R.drawable.image5_5)!!, getString(R.string.title05_5)))
        adapter.items = list
        adapter.notifyDataSetChanged()
    }

    fun Shopping() {
        val list = ArrayList<YoutubeItem>()
        list.add(YoutubeItem(getDrawable(R.drawable.image5_6)!!, getString(R.string.title05_6)))
        list.add(YoutubeItem(getDrawable(R.drawable.image5_7)!!, getString(R.string.title05_7)))
        list.add(YoutubeItem(getDrawable(R.drawable.image5_8)!!, getString(R.string.title05_8)))
        list.add(YoutubeItem(getDrawable(R.drawable.image5_9)!!, getString(R.string.title05_9)))
        list.add(YoutubeItem(getDrawable(R.drawable.image5_10)!!, getString(R.string.title05_10)))
        adapter.items = list
        adapter.notifyDataSetChanged()
    }
    fun Accommodation(){
        val list = ArrayList<YoutubeItem>()
        list.add(YoutubeItem(getDrawable(R.drawable.image5_11)!!, getString(R.string.title05_11)))
        list.add(YoutubeItem(getDrawable(R.drawable.image5_12)!!, getString(R.string.title05_12)))
        list.add(YoutubeItem(getDrawable(R.drawable.image5_13)!!, getString(R.string.title05_13)))
        list.add(YoutubeItem(getDrawable(R.drawable.image5_14)!!, getString(R.string.title05_14)))
        list.add(YoutubeItem(getDrawable(R.drawable.image5_15)!!, getString(R.string.title05_15)))
        adapter.items = list
        adapter.notifyDataSetChanged()
    }
    fun Dining(){
        val list = ArrayList<YoutubeItem>()
        list.add(YoutubeItem(getDrawable(R.drawable.image5_16)!!, getString(R.string.title05_16)))
        list.add(YoutubeItem(getDrawable(R.drawable.image5_17)!!, getString(R.string.title05_17)))
        list.add(YoutubeItem(getDrawable(R.drawable.image5_18)!!, getString(R.string.title05_18)))
        list.add(YoutubeItem(getDrawable(R.drawable.image5_19)!!, getString(R.string.title05_19)))
        list.add(YoutubeItem(getDrawable(R.drawable.image5_20)!!, getString(R.string.title05_20)))
        adapter.items = list
        adapter.notifyDataSetChanged()
    }
    fun cultural(){
        val list = ArrayList<YoutubeItem>()
        list.add(YoutubeItem(getDrawable(R.drawable.image5_21)!!, getString(R.string.title05_21)))
        list.add(YoutubeItem(getDrawable(R.drawable.image5_22)!!, getString(R.string.title05_22)))
        list.add(YoutubeItem(getDrawable(R.drawable.image5_23)!!, getString(R.string.title05_23)))
        list.add(YoutubeItem(getDrawable(R.drawable.image5_24)!!, getString(R.string.title05_24)))
        list.add(YoutubeItem(getDrawable(R.drawable.image5_25)!!, getString(R.string.title05_25)))
        adapter.items = list
        adapter.notifyDataSetChanged()
    }

}