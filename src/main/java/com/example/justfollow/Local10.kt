package com.example.justfollow

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import kotlinx.android.synthetic.main.activity_local03.*
import kotlinx.android.synthetic.main.activity_main.*

class Local10 : AppCompatActivity() {

    private lateinit var adapter: RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_local10)



        val list = ArrayList<YoutubeItem>()
        adapter = RecyclerAdapter(list)
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(
                DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Log.d("Local10",
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
        list.add(YoutubeItem(getDrawable(R.drawable.image10_1)!!, getString(R.string.title10_1)))
        list.add(YoutubeItem(getDrawable(R.drawable.image10_2)!!, getString(R.string.title10_2)))
        list.add(YoutubeItem(getDrawable(R.drawable.image10_3)!!, getString(R.string.title10_3)))
        list.add(YoutubeItem(getDrawable(R.drawable.image10_4)!!, getString(R.string.title10_4)))
        list.add(YoutubeItem(getDrawable(R.drawable.image10_5)!!, getString(R.string.title10_5)))
        adapter.items = list
        adapter.notifyDataSetChanged()
    }

    fun Shopping() {
        val list = ArrayList<YoutubeItem>()
        list.add(YoutubeItem(getDrawable(R.drawable.image10_6)!!, getString(R.string.title10_6)))
        list.add(YoutubeItem(getDrawable(R.drawable.image10_7)!!, getString(R.string.title10_7)))
        list.add(YoutubeItem(getDrawable(R.drawable.image10_8)!!, getString(R.string.title10_8)))
        list.add(YoutubeItem(getDrawable(R.drawable.image10_9)!!, getString(R.string.title10_9)))
        list.add(YoutubeItem(getDrawable(R.drawable.image10_10)!!, getString(R.string.title10_10)))
        adapter.items = list
        adapter.notifyDataSetChanged()
    }
    fun Accommodation(){
        val list = ArrayList<YoutubeItem>()
        list.add(YoutubeItem(getDrawable(R.drawable.image10_11)!!, getString(R.string.title10_11)))
        list.add(YoutubeItem(getDrawable(R.drawable.image10_12)!!, getString(R.string.title10_12)))
        list.add(YoutubeItem(getDrawable(R.drawable.image10_13)!!, getString(R.string.title10_13)))
        list.add(YoutubeItem(getDrawable(R.drawable.image10_14)!!, getString(R.string.title10_14)))
        list.add(YoutubeItem(getDrawable(R.drawable.image10_15)!!, getString(R.string.title10_15)))
        adapter.items = list
        adapter.notifyDataSetChanged()
    }
    fun Dining(){
        val list = ArrayList<YoutubeItem>()
        list.add(YoutubeItem(getDrawable(R.drawable.image10_16)!!, getString(R.string.title10_16)))
        list.add(YoutubeItem(getDrawable(R.drawable.image10_17)!!, getString(R.string.title10_17)))
        list.add(YoutubeItem(getDrawable(R.drawable.image10_18)!!, getString(R.string.title10_18)))
        list.add(YoutubeItem(getDrawable(R.drawable.image10_19)!!, getString(R.string.title10_19)))
        list.add(YoutubeItem(getDrawable(R.drawable.image10_20)!!, getString(R.string.title10_20)))
        adapter.items = list
        adapter.notifyDataSetChanged()
    }
    fun cultural(){
        val list = ArrayList<YoutubeItem>()
        list.add(YoutubeItem(getDrawable(R.drawable.image10_21)!!, getString(R.string.title10_21)))
        list.add(YoutubeItem(getDrawable(R.drawable.image10_22)!!, getString(R.string.title10_22)))
        list.add(YoutubeItem(getDrawable(R.drawable.image10_23)!!, getString(R.string.title10_23)))
        list.add(YoutubeItem(getDrawable(R.drawable.image10_24)!!, getString(R.string.title10_24)))
        list.add(YoutubeItem(getDrawable(R.drawable.image10_25)!!, getString(R.string.title10_25)))
        adapter.items = list
        adapter.notifyDataSetChanged()
    }

}