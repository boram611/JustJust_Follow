package com.example.justfollow

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import kotlinx.android.synthetic.main.activity_local02.*
import kotlinx.android.synthetic.main.activity_main.*

class Local02 : AppCompatActivity() {

    private lateinit var adapter: RecyclerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_local02)



        val list = ArrayList<YoutubeItem>()
        adapter = RecyclerAdapter(list)
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(
                DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Log.d("Local02",
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
        list.add(YoutubeItem(getDrawable(R.drawable.image2_1)!!, getString(R.string.title02_1)))
        list.add(YoutubeItem(getDrawable(R.drawable.image2_2)!!, getString(R.string.title02_2)))
        list.add(YoutubeItem(getDrawable(R.drawable.image2_3)!!, getString(R.string.title02_3)))
        list.add(YoutubeItem(getDrawable(R.drawable.image2_4)!!, getString(R.string.title02_4)))
        list.add(YoutubeItem(getDrawable(R.drawable.image2_5)!!, getString(R.string.title02_5)))
        adapter.items = list
        adapter.notifyDataSetChanged()
    }

    fun Shopping() {
        val list = ArrayList<YoutubeItem>()
        list.add(YoutubeItem(getDrawable(R.drawable.image2_6)!!, getString(R.string.title02_6)))
        list.add(YoutubeItem(getDrawable(R.drawable.image2_7)!!, getString(R.string.title02_7)))
        list.add(YoutubeItem(getDrawable(R.drawable.image2_8)!!, getString(R.string.title02_8)))
        list.add(YoutubeItem(getDrawable(R.drawable.image2_9)!!, getString(R.string.title02_9)))
        list.add(YoutubeItem(getDrawable(R.drawable.image2_10)!!, getString(R.string.title02_10)))
        adapter.items = list
        adapter.notifyDataSetChanged()
    }
    fun Accommodation(){
        val list = ArrayList<YoutubeItem>()
        list.add(YoutubeItem(getDrawable(R.drawable.image2_11)!!, getString(R.string.title02_11)))
        list.add(YoutubeItem(getDrawable(R.drawable.image2_12)!!, getString(R.string.title02_12)))
        list.add(YoutubeItem(getDrawable(R.drawable.image2_13)!!, getString(R.string.title02_13)))
        list.add(YoutubeItem(getDrawable(R.drawable.image2_14)!!, getString(R.string.title02_14)))
        list.add(YoutubeItem(getDrawable(R.drawable.image2_15)!!, getString(R.string.title02_15)))
        adapter.items = list
        adapter.notifyDataSetChanged()
    }
    fun Dining(){
        val list = ArrayList<YoutubeItem>()
        list.add(YoutubeItem(getDrawable(R.drawable.image2_16)!!, getString(R.string.title02_16)))
        list.add(YoutubeItem(getDrawable(R.drawable.image2_17)!!, getString(R.string.title02_17)))
        list.add(YoutubeItem(getDrawable(R.drawable.image2_18)!!, getString(R.string.title02_18)))
        list.add(YoutubeItem(getDrawable(R.drawable.image2_19)!!, getString(R.string.title02_19)))
        list.add(YoutubeItem(getDrawable(R.drawable.image2_20)!!, getString(R.string.title02_20)))
        adapter.items = list
        adapter.notifyDataSetChanged()
    }
    fun cultural(){
        val list = ArrayList<YoutubeItem>()
        list.add(YoutubeItem(getDrawable(R.drawable.image2_21)!!, getString(R.string.title02_21)))
        list.add(YoutubeItem(getDrawable(R.drawable.image2_22)!!, getString(R.string.title02_22)))
        list.add(YoutubeItem(getDrawable(R.drawable.image2_23)!!, getString(R.string.title02_23)))
        list.add(YoutubeItem(getDrawable(R.drawable.image2_24)!!, getString(R.string.title02_24)))
        list.add(YoutubeItem(getDrawable(R.drawable.image2_25)!!, getString(R.string.title02_25)))
        adapter.items = list
        adapter.notifyDataSetChanged()
    }

}