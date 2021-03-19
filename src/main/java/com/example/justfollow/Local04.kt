package com.example.justfollow

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import kotlinx.android.synthetic.main.activity_local03.*
import kotlinx.android.synthetic.main.activity_main.*

class Local04 : AppCompatActivity() {

    private lateinit var adapter: RecyclerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_local04)



        val list = ArrayList<YoutubeItem>()
        adapter = RecyclerAdapter(list)
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(
                DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Log.d("Local04",
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
        list.add(YoutubeItem(getDrawable(R.drawable.image4_1)!!, getString(R.string.title04_1)))
        list.add(YoutubeItem(getDrawable(R.drawable.image4_2)!!, getString(R.string.title04_2)))
        list.add(YoutubeItem(getDrawable(R.drawable.image4_3)!!, getString(R.string.title04_3)))
        list.add(YoutubeItem(getDrawable(R.drawable.image4_4)!!, getString(R.string.title04_4)))
        list.add(YoutubeItem(getDrawable(R.drawable.image4_5)!!, getString(R.string.title04_5)))
        adapter.items = list
        adapter.notifyDataSetChanged()
    }

    fun Shopping() {
        val list = ArrayList<YoutubeItem>()
        list.add(YoutubeItem(getDrawable(R.drawable.image4_6)!!, getString(R.string.title04_6)))
        list.add(YoutubeItem(getDrawable(R.drawable.image4_7)!!, getString(R.string.title04_7)))
        list.add(YoutubeItem(getDrawable(R.drawable.image4_8)!!, getString(R.string.title04_8)))
        adapter.items = list
        adapter.notifyDataSetChanged()
    }
    fun Accommodation(){
        val list = ArrayList<YoutubeItem>()
        list.add(YoutubeItem(getDrawable(R.drawable.image4_11)!!, getString(R.string.title04_11)))
        list.add(YoutubeItem(getDrawable(R.drawable.image4_12)!!, getString(R.string.title04_12)))
        adapter.items = list
        adapter.notifyDataSetChanged()
    }
    fun Dining(){
        val list = ArrayList<YoutubeItem>()
        list.add(YoutubeItem(getDrawable(R.drawable.image4_16)!!, getString(R.string.title04_16)))
        list.add(YoutubeItem(getDrawable(R.drawable.image4_17)!!, getString(R.string.title04_17)))
        list.add(YoutubeItem(getDrawable(R.drawable.image4_18)!!, getString(R.string.title04_18)))
        list.add(YoutubeItem(getDrawable(R.drawable.image4_19)!!, getString(R.string.title04_19)))
        list.add(YoutubeItem(getDrawable(R.drawable.image4_20)!!, getString(R.string.title04_20)))
        adapter.items = list
        adapter.notifyDataSetChanged()
    }
    fun cultural(){
        val list = ArrayList<YoutubeItem>()
        list.add(YoutubeItem(getDrawable(R.drawable.image4_21)!!, getString(R.string.title04_21)))
        list.add(YoutubeItem(getDrawable(R.drawable.image4_22)!!, getString(R.string.title04_22)))
        list.add(YoutubeItem(getDrawable(R.drawable.image4_23)!!, getString(R.string.title04_23)))
        list.add(YoutubeItem(getDrawable(R.drawable.image4_24)!!, getString(R.string.title04_24)))
        list.add(YoutubeItem(getDrawable(R.drawable.image4_25)!!, getString(R.string.title04_25)))
        adapter.items = list
        adapter.notifyDataSetChanged()
    }

}