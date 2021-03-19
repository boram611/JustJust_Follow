package com.example.justfollow

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import kotlinx.android.synthetic.main.activity_local03.*
import kotlinx.android.synthetic.main.activity_main.*

class Local07 : AppCompatActivity() {

    private lateinit var adapter: RecyclerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_local07)



        val list = ArrayList<YoutubeItem>()
        adapter = RecyclerAdapter(list)
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(
                DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Log.d("Local07",
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
        list.add(YoutubeItem(getDrawable(R.drawable.image7_1)!!, getString(R.string.title07_1)))
        list.add(YoutubeItem(getDrawable(R.drawable.image7_2)!!, getString(R.string.title07_2)))
        list.add(YoutubeItem(getDrawable(R.drawable.image7_3)!!, getString(R.string.title07_3)))
        list.add(YoutubeItem(getDrawable(R.drawable.image7_4)!!, getString(R.string.title07_4)))
        list.add(YoutubeItem(getDrawable(R.drawable.image7_5)!!, getString(R.string.title07_5)))
        adapter.items = list
        adapter.notifyDataSetChanged()
    }

    fun Shopping() {
        val list = ArrayList<YoutubeItem>()
        list.add(YoutubeItem(getDrawable(R.drawable.image7_6)!!, getString(R.string.title07_6)))
        list.add(YoutubeItem(getDrawable(R.drawable.image7_7)!!, getString(R.string.title07_7)))
        list.add(YoutubeItem(getDrawable(R.drawable.image7_8)!!, getString(R.string.title07_8)))
        list.add(YoutubeItem(getDrawable(R.drawable.image7_9)!!, getString(R.string.title07_9)))
        list.add(YoutubeItem(getDrawable(R.drawable.image7_10)!!, getString(R.string.title07_10)))
        adapter.items = list
        adapter.notifyDataSetChanged()
    }
    fun Accommodation(){
        val list = ArrayList<YoutubeItem>()
        list.add(YoutubeItem(getDrawable(R.drawable.image7_11)!!, getString(R.string.title07_11)))
        list.add(YoutubeItem(getDrawable(R.drawable.image7_12)!!, getString(R.string.title07_12)))
        list.add(YoutubeItem(getDrawable(R.drawable.image7_13)!!, getString(R.string.title07_13)))
        list.add(YoutubeItem(getDrawable(R.drawable.image7_14)!!, getString(R.string.title07_14)))
        list.add(YoutubeItem(getDrawable(R.drawable.image7_15)!!, getString(R.string.title07_15)))
        adapter.items = list
        adapter.notifyDataSetChanged()
    }
    fun Dining(){
        val list = ArrayList<YoutubeItem>()
        list.add(YoutubeItem(getDrawable(R.drawable.image7_16)!!, getString(R.string.title07_16)))
        list.add(YoutubeItem(getDrawable(R.drawable.image7_17)!!, getString(R.string.title07_17)))
        list.add(YoutubeItem(getDrawable(R.drawable.image7_18)!!, getString(R.string.title07_18)))
        list.add(YoutubeItem(getDrawable(R.drawable.image7_19)!!, getString(R.string.title07_19)))
        list.add(YoutubeItem(getDrawable(R.drawable.image7_20)!!, getString(R.string.title07_20)))
        adapter.items = list
        adapter.notifyDataSetChanged()
    }
    fun cultural(){
        val list = ArrayList<YoutubeItem>()
        list.add(YoutubeItem(getDrawable(R.drawable.image7_21)!!, getString(R.string.title07_21)))
        list.add(YoutubeItem(getDrawable(R.drawable.image7_22)!!, getString(R.string.title07_22)))
        list.add(YoutubeItem(getDrawable(R.drawable.image7_23)!!, getString(R.string.title07_23)))
        list.add(YoutubeItem(getDrawable(R.drawable.image7_24)!!, getString(R.string.title07_24)))
        list.add(YoutubeItem(getDrawable(R.drawable.image7_25)!!, getString(R.string.title07_25)))
        adapter.items = list
        adapter.notifyDataSetChanged()
    }

}