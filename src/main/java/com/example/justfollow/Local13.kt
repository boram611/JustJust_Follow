package com.example.justfollow

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import kotlinx.android.synthetic.main.activity_local03.*
import kotlinx.android.synthetic.main.activity_main.*

class Local13 : AppCompatActivity() {

    private lateinit var adapter: RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_local13)



        val list = ArrayList<YoutubeItem>()
        adapter = RecyclerAdapter(list)
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(
                DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Log.d("Local13",
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
        list.add(YoutubeItem(getDrawable(R.drawable.image13_1)!!, getString(R.string.title13_1)))
        list.add(YoutubeItem(getDrawable(R.drawable.image13_2)!!, getString(R.string.title13_2)))
        list.add(YoutubeItem(getDrawable(R.drawable.image13_3)!!, getString(R.string.title13_3)))
        list.add(YoutubeItem(getDrawable(R.drawable.image13_4)!!, getString(R.string.title13_4)))
        list.add(YoutubeItem(getDrawable(R.drawable.image13_5)!!, getString(R.string.title13_5)))
        adapter.items = list
        adapter.notifyDataSetChanged()
    }

    fun Shopping() {
        val list = ArrayList<YoutubeItem>()
        list.add(YoutubeItem(getDrawable(R.drawable.image13_6)!!, getString(R.string.title13_6)))
        list.add(YoutubeItem(getDrawable(R.drawable.image13_7)!!, getString(R.string.title13_7)))
        list.add(YoutubeItem(getDrawable(R.drawable.image13_8)!!, getString(R.string.title13_8)))
        list.add(YoutubeItem(getDrawable(R.drawable.image13_9)!!, getString(R.string.title13_9)))
        list.add(YoutubeItem(getDrawable(R.drawable.image13_10)!!, getString(R.string.title13_10)))
        adapter.items = list
        adapter.notifyDataSetChanged()
    }
    fun Accommodation(){
        val list = ArrayList<YoutubeItem>()
        list.add(YoutubeItem(getDrawable(R.drawable.image13_11)!!, getString(R.string.title13_11)))
        list.add(YoutubeItem(getDrawable(R.drawable.image13_12)!!, getString(R.string.title13_12)))
        list.add(YoutubeItem(getDrawable(R.drawable.image13_13)!!, getString(R.string.title13_13)))
        list.add(YoutubeItem(getDrawable(R.drawable.image13_14)!!, getString(R.string.title13_14)))
        list.add(YoutubeItem(getDrawable(R.drawable.image13_15)!!, getString(R.string.title13_15)))
        adapter.items = list
        adapter.notifyDataSetChanged()
    }
    fun Dining(){
        val list = ArrayList<YoutubeItem>()
        list.add(YoutubeItem(getDrawable(R.drawable.image13_16)!!, getString(R.string.title13_16)))
        list.add(YoutubeItem(getDrawable(R.drawable.image13_17)!!, getString(R.string.title13_17)))
        list.add(YoutubeItem(getDrawable(R.drawable.image13_18)!!, getString(R.string.title13_18)))
        list.add(YoutubeItem(getDrawable(R.drawable.image13_19)!!, getString(R.string.title13_19)))
        list.add(YoutubeItem(getDrawable(R.drawable.image13_20)!!, getString(R.string.title13_20)))
        adapter.items = list
        adapter.notifyDataSetChanged()
    }
    fun cultural(){
        val list = ArrayList<YoutubeItem>()
        list.add(YoutubeItem(getDrawable(R.drawable.image13_21)!!, getString(R.string.title13_21)))
        list.add(YoutubeItem(getDrawable(R.drawable.image13_22)!!, getString(R.string.title13_22)))
        list.add(YoutubeItem(getDrawable(R.drawable.image13_23)!!, getString(R.string.title13_23)))
        list.add(YoutubeItem(getDrawable(R.drawable.image13_24)!!, getString(R.string.title13_24)))
        list.add(YoutubeItem(getDrawable(R.drawable.image13_25)!!, getString(R.string.title13_25)))
        adapter.items = list
        adapter.notifyDataSetChanged()
    }

}