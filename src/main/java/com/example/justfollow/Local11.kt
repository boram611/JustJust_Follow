package com.example.justfollow

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import kotlinx.android.synthetic.main.activity_local03.*
import kotlinx.android.synthetic.main.activity_main.*

class Local11 : AppCompatActivity() {

    private lateinit var adapter: RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_local11)



        val list = ArrayList<YoutubeItem>()
        adapter = RecyclerAdapter(list)
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(
                DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Log.d("Local11",
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
        list.add(YoutubeItem(getDrawable(R.drawable.image11_1)!!, getString(R.string.title11_1)))
        list.add(YoutubeItem(getDrawable(R.drawable.image11_2)!!, getString(R.string.title11_2)))
        list.add(YoutubeItem(getDrawable(R.drawable.image11_3)!!, getString(R.string.title11_3)))
        list.add(YoutubeItem(getDrawable(R.drawable.image11_4)!!, getString(R.string.title11_4)))
        list.add(YoutubeItem(getDrawable(R.drawable.image11_5)!!, getString(R.string.title11_5)))
        adapter.items = list
        adapter.notifyDataSetChanged()
    }

    fun Shopping() {
        val list = ArrayList<YoutubeItem>()
        list.add(YoutubeItem(getDrawable(R.drawable.image11_6)!!, getString(R.string.title11_6)))
        list.add(YoutubeItem(getDrawable(R.drawable.image11_7)!!, getString(R.string.title11_7)))
        list.add(YoutubeItem(getDrawable(R.drawable.image11_8)!!, getString(R.string.title11_8)))
        list.add(YoutubeItem(getDrawable(R.drawable.image11_9)!!, getString(R.string.title11_9)))
        list.add(YoutubeItem(getDrawable(R.drawable.image11_10)!!, getString(R.string.title11_10)))
        adapter.items = list
        adapter.notifyDataSetChanged()
    }
    fun Accommodation(){
        val list = ArrayList<YoutubeItem>()
        list.add(YoutubeItem(getDrawable(R.drawable.image11_11)!!, getString(R.string.title11_11)))
        list.add(YoutubeItem(getDrawable(R.drawable.image11_12)!!, getString(R.string.title11_12)))
        list.add(YoutubeItem(getDrawable(R.drawable.image11_13)!!, getString(R.string.title11_13)))
        list.add(YoutubeItem(getDrawable(R.drawable.image11_14)!!, getString(R.string.title11_14)))
        list.add(YoutubeItem(getDrawable(R.drawable.image11_15)!!, getString(R.string.title11_15)))
        adapter.items = list
        adapter.notifyDataSetChanged()
    }
    fun Dining(){
        val list = ArrayList<YoutubeItem>()
        list.add(YoutubeItem(getDrawable(R.drawable.image11_16)!!, getString(R.string.title11_16)))
        list.add(YoutubeItem(getDrawable(R.drawable.image11_17)!!, getString(R.string.title11_17)))
        list.add(YoutubeItem(getDrawable(R.drawable.image11_18)!!, getString(R.string.title11_18)))
        list.add(YoutubeItem(getDrawable(R.drawable.image11_19)!!, getString(R.string.title11_19)))
        list.add(YoutubeItem(getDrawable(R.drawable.image11_20)!!, getString(R.string.title11_20)))
        adapter.items = list
        adapter.notifyDataSetChanged()
    }
    fun cultural(){
        val list = ArrayList<YoutubeItem>()
        list.add(YoutubeItem(getDrawable(R.drawable.image11_21)!!, getString(R.string.title11_21)))
        list.add(YoutubeItem(getDrawable(R.drawable.image11_22)!!, getString(R.string.title11_22)))
        list.add(YoutubeItem(getDrawable(R.drawable.image11_23)!!, getString(R.string.title11_23)))
        list.add(YoutubeItem(getDrawable(R.drawable.image11_24)!!, getString(R.string.title11_24)))
        list.add(YoutubeItem(getDrawable(R.drawable.image11_25)!!, getString(R.string.title11_25)))
        adapter.items = list
        adapter.notifyDataSetChanged()
    }

}