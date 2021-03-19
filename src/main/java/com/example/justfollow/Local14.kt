package com.example.justfollow

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import kotlinx.android.synthetic.main.activity_local03.*
import kotlinx.android.synthetic.main.activity_main.*

class Local14 : AppCompatActivity() {

    private lateinit var adapter: RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_local14)



        val list = ArrayList<YoutubeItem>()
        adapter = RecyclerAdapter(list)
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(
                DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Log.d("Local14",
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
        list.add(YoutubeItem(getDrawable(R.drawable.image14_1)!!, getString(R.string.title14_1)))
        list.add(YoutubeItem(getDrawable(R.drawable.image14_2)!!, getString(R.string.title14_2)))
        list.add(YoutubeItem(getDrawable(R.drawable.image14_3)!!, getString(R.string.title14_3)))
        list.add(YoutubeItem(getDrawable(R.drawable.image14_4)!!, getString(R.string.title14_4)))
        list.add(YoutubeItem(getDrawable(R.drawable.image14_5)!!, getString(R.string.title14_5)))
        adapter.items = list
        adapter.notifyDataSetChanged()
    }

    fun Shopping() {
        val list = ArrayList<YoutubeItem>()
        list.add(YoutubeItem(getDrawable(R.drawable.image14_6)!!, getString(R.string.title14_6)))
        list.add(YoutubeItem(getDrawable(R.drawable.image14_7)!!, getString(R.string.title14_7)))
        list.add(YoutubeItem(getDrawable(R.drawable.image14_8)!!, getString(R.string.title14_8)))
        list.add(YoutubeItem(getDrawable(R.drawable.image14_9)!!, getString(R.string.title14_9)))
        list.add(YoutubeItem(getDrawable(R.drawable.image14_10)!!, getString(R.string.title14_10)))
        adapter.items = list
        adapter.notifyDataSetChanged()
    }
    fun Accommodation(){
        val list = ArrayList<YoutubeItem>()
        list.add(YoutubeItem(getDrawable(R.drawable.image14_11)!!, getString(R.string.title14_11)))
        list.add(YoutubeItem(getDrawable(R.drawable.image14_12)!!, getString(R.string.title14_12)))
        list.add(YoutubeItem(getDrawable(R.drawable.image14_13)!!, getString(R.string.title14_13)))
        list.add(YoutubeItem(getDrawable(R.drawable.image14_14)!!, getString(R.string.title14_14)))
        list.add(YoutubeItem(getDrawable(R.drawable.image14_15)!!, getString(R.string.title14_15)))
        adapter.items = list
        adapter.notifyDataSetChanged()
    }
    fun Dining(){
        val list = ArrayList<YoutubeItem>()
        list.add(YoutubeItem(getDrawable(R.drawable.image14_16)!!, getString(R.string.title14_16)))
        list.add(YoutubeItem(getDrawable(R.drawable.image14_17)!!, getString(R.string.title14_17)))
        list.add(YoutubeItem(getDrawable(R.drawable.image14_18)!!, getString(R.string.title14_18)))
        list.add(YoutubeItem(getDrawable(R.drawable.image14_19)!!, getString(R.string.title14_19)))
        list.add(YoutubeItem(getDrawable(R.drawable.image14_20)!!, getString(R.string.title14_20)))
        adapter.items = list
        adapter.notifyDataSetChanged()
    }
    fun cultural(){
        val list = ArrayList<YoutubeItem>()
        list.add(YoutubeItem(getDrawable(R.drawable.image14_21)!!, getString(R.string.title14_21)))
        list.add(YoutubeItem(getDrawable(R.drawable.image14_22)!!, getString(R.string.title14_22)))
        list.add(YoutubeItem(getDrawable(R.drawable.image14_23)!!, getString(R.string.title14_23)))
        list.add(YoutubeItem(getDrawable(R.drawable.image14_24)!!, getString(R.string.title14_24)))
        list.add(YoutubeItem(getDrawable(R.drawable.image14_25)!!, getString(R.string.title14_25)))
        adapter.items = list
        adapter.notifyDataSetChanged()
    }

}