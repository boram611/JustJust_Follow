package com.example.justfollow

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import kotlinx.android.synthetic.main.activity_local03.*
import kotlinx.android.synthetic.main.activity_main.*

class Local17 : AppCompatActivity() {

    private lateinit var adapter: RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_local17)



        val list = ArrayList<YoutubeItem>()
        adapter = RecyclerAdapter(list)
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(
                DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Log.d("Local17",
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
        list.add(YoutubeItem(getDrawable(R.drawable.image17_1)!!, getString(R.string.title17_1)))
        list.add(YoutubeItem(getDrawable(R.drawable.image17_2)!!, getString(R.string.title17_2)))
        list.add(YoutubeItem(getDrawable(R.drawable.image17_3)!!, getString(R.string.title17_3)))
        list.add(YoutubeItem(getDrawable(R.drawable.image17_4)!!, getString(R.string.title17_4)))
        list.add(YoutubeItem(getDrawable(R.drawable.image17_5)!!, getString(R.string.title17_5)))
        adapter.items = list
        adapter.notifyDataSetChanged()
    }

    fun Shopping() {
        val list = ArrayList<YoutubeItem>()
        list.add(YoutubeItem(getDrawable(R.drawable.image17_6)!!, getString(R.string.title17_6)))
        list.add(YoutubeItem(getDrawable(R.drawable.image17_7)!!, getString(R.string.title17_7)))
        list.add(YoutubeItem(getDrawable(R.drawable.image17_8)!!, getString(R.string.title17_8)))
        list.add(YoutubeItem(getDrawable(R.drawable.image17_9)!!, getString(R.string.title17_9)))
        list.add(YoutubeItem(getDrawable(R.drawable.image17_10)!!, getString(R.string.title17_10)))
        adapter.items = list
        adapter.notifyDataSetChanged()
    }
    fun Accommodation(){
        val list = ArrayList<YoutubeItem>()
        list.add(YoutubeItem(getDrawable(R.drawable.image17_11)!!, getString(R.string.title17_11)))
        list.add(YoutubeItem(getDrawable(R.drawable.image17_12)!!, getString(R.string.title17_12)))
        list.add(YoutubeItem(getDrawable(R.drawable.image17_13)!!, getString(R.string.title17_13)))
        list.add(YoutubeItem(getDrawable(R.drawable.image17_14)!!, getString(R.string.title17_14)))
        list.add(YoutubeItem(getDrawable(R.drawable.image17_15)!!, getString(R.string.title17_15)))
        adapter.items = list
        adapter.notifyDataSetChanged()
    }
    fun Dining(){
        val list = ArrayList<YoutubeItem>()
        list.add(YoutubeItem(getDrawable(R.drawable.image17_16)!!, getString(R.string.title17_16)))
        list.add(YoutubeItem(getDrawable(R.drawable.image17_17)!!, getString(R.string.title17_17)))
        list.add(YoutubeItem(getDrawable(R.drawable.image17_18)!!, getString(R.string.title17_18)))
        list.add(YoutubeItem(getDrawable(R.drawable.image17_19)!!, getString(R.string.title17_19)))
        list.add(YoutubeItem(getDrawable(R.drawable.image17_20)!!, getString(R.string.title17_20)))
        adapter.items = list
        adapter.notifyDataSetChanged()
    }
    fun cultural(){
        val list = ArrayList<YoutubeItem>()
        list.add(YoutubeItem(getDrawable(R.drawable.image17_21)!!, getString(R.string.title17_21)))
        list.add(YoutubeItem(getDrawable(R.drawable.image17_22)!!, getString(R.string.title17_22)))
        list.add(YoutubeItem(getDrawable(R.drawable.image17_23)!!, getString(R.string.title17_23)))
        list.add(YoutubeItem(getDrawable(R.drawable.image17_24)!!, getString(R.string.title17_24)))
        list.add(YoutubeItem(getDrawable(R.drawable.image17_25)!!, getString(R.string.title17_25)))
        adapter.items = list
        adapter.notifyDataSetChanged()
    }

}