package com.example.justfollow

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import kotlinx.android.synthetic.main.activity_local03.*
import kotlinx.android.synthetic.main.activity_main.*

class Local09 : AppCompatActivity() {

    private lateinit var adapter: RecyclerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_local09)



        val list = ArrayList<YoutubeItem>()
        adapter = RecyclerAdapter(list)
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(
                DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Log.d("Local09",
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
        list.add(YoutubeItem(getDrawable(R.drawable.image9_1)!!, getString(R.string.title09_1)))
        list.add(YoutubeItem(getDrawable(R.drawable.image9_2)!!, getString(R.string.title09_2)))
        list.add(YoutubeItem(getDrawable(R.drawable.image9_3)!!, getString(R.string.title09_3)))
        list.add(YoutubeItem(getDrawable(R.drawable.image9_4)!!, getString(R.string.title09_4)))
        list.add(YoutubeItem(getDrawable(R.drawable.image9_5)!!, getString(R.string.title09_5)))
        adapter.items = list
        adapter.notifyDataSetChanged()
    }

    fun Shopping() {
        val list = ArrayList<YoutubeItem>()
        list.add(YoutubeItem(getDrawable(R.drawable.image9_6)!!, getString(R.string.title09_6)))
        list.add(YoutubeItem(getDrawable(R.drawable.image9_7)!!, getString(R.string.title09_7)))
        list.add(YoutubeItem(getDrawable(R.drawable.image9_8)!!, getString(R.string.title09_8)))
        list.add(YoutubeItem(getDrawable(R.drawable.image9_9)!!, getString(R.string.title09_9)))
        list.add(YoutubeItem(getDrawable(R.drawable.image9_10)!!, getString(R.string.title09_10)))
        adapter.items = list
        adapter.notifyDataSetChanged()
    }
    fun Accommodation(){
        val list = ArrayList<YoutubeItem>()
        list.add(YoutubeItem(getDrawable(R.drawable.image9_11)!!, getString(R.string.title09_11)))
        list.add(YoutubeItem(getDrawable(R.drawable.image9_12)!!, getString(R.string.title09_12)))
        list.add(YoutubeItem(getDrawable(R.drawable.image9_13)!!, getString(R.string.title09_13)))
        list.add(YoutubeItem(getDrawable(R.drawable.image9_14)!!, getString(R.string.title09_14)))
        list.add(YoutubeItem(getDrawable(R.drawable.image9_15)!!, getString(R.string.title09_15)))
        adapter.items = list
        adapter.notifyDataSetChanged()
    }
    fun Dining(){
        val list = ArrayList<YoutubeItem>()
        list.add(YoutubeItem(getDrawable(R.drawable.image9_16)!!, getString(R.string.title09_16)))
        list.add(YoutubeItem(getDrawable(R.drawable.image9_17)!!, getString(R.string.title09_17)))
        list.add(YoutubeItem(getDrawable(R.drawable.image9_18)!!, getString(R.string.title09_18)))
        list.add(YoutubeItem(getDrawable(R.drawable.image9_19)!!, getString(R.string.title09_19)))
        list.add(YoutubeItem(getDrawable(R.drawable.image9_20)!!, getString(R.string.title09_20)))
        adapter.items = list
        adapter.notifyDataSetChanged()
    }
    fun cultural(){
        val list = ArrayList<YoutubeItem>()
        list.add(YoutubeItem(getDrawable(R.drawable.image9_21)!!, getString(R.string.title09_21)))
        list.add(YoutubeItem(getDrawable(R.drawable.image9_22)!!, getString(R.string.title09_22)))
        list.add(YoutubeItem(getDrawable(R.drawable.image9_23)!!, getString(R.string.title09_23)))
        list.add(YoutubeItem(getDrawable(R.drawable.image9_24)!!, getString(R.string.title09_24)))
        list.add(YoutubeItem(getDrawable(R.drawable.image9_25)!!, getString(R.string.title09_25)))
        adapter.items = list
        adapter.notifyDataSetChanged()
    }

}