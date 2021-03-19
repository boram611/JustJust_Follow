package com.example.justfollow

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import kotlinx.android.synthetic.main.activity_local03.*
import kotlinx.android.synthetic.main.activity_main.*

class Local03 : AppCompatActivity() {

    private lateinit var adapter: RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_local03)



        val list = ArrayList<YoutubeItem>()
        adapter = RecyclerAdapter(list)
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(
                DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Log.d("Local03",
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
        list.add(YoutubeItem(getDrawable(R.drawable.image3_1)!!, getString(R.string.title03_1)))
        list.add(YoutubeItem(getDrawable(R.drawable.image3_2)!!, getString(R.string.title03_2)))
        list.add(YoutubeItem(getDrawable(R.drawable.image3_3)!!, getString(R.string.title03_3)))
        list.add(YoutubeItem(getDrawable(R.drawable.image3_4)!!, getString(R.string.title03_4)))
        list.add(YoutubeItem(getDrawable(R.drawable.image3_5)!!, getString(R.string.title03_5)))
        adapter.items = list
        adapter.notifyDataSetChanged()
    }

    fun Shopping() {
        val list = ArrayList<YoutubeItem>()
        list.add(YoutubeItem(getDrawable(R.drawable.image3_6)!!, getString(R.string.title03_6)))
        list.add(YoutubeItem(getDrawable(R.drawable.image3_7)!!, getString(R.string.title03_7)))
        list.add(YoutubeItem(getDrawable(R.drawable.image3_8)!!, getString(R.string.title03_8)))
        list.add(YoutubeItem(getDrawable(R.drawable.image3_9)!!, getString(R.string.title03_9)))
        list.add(YoutubeItem(getDrawable(R.drawable.image3_10)!!, getString(R.string.title03_10)))
        adapter.items = list
        adapter.notifyDataSetChanged()
    }
    fun Accommodation(){
        val list = ArrayList<YoutubeItem>()
        list.add(YoutubeItem(getDrawable(R.drawable.image3_11)!!, getString(R.string.title03_11)))
        list.add(YoutubeItem(getDrawable(R.drawable.image3_12)!!, getString(R.string.title03_12)))
        list.add(YoutubeItem(getDrawable(R.drawable.image3_13)!!, getString(R.string.title03_13)))
        list.add(YoutubeItem(getDrawable(R.drawable.image3_14)!!, getString(R.string.title03_14)))
        list.add(YoutubeItem(getDrawable(R.drawable.image3_15)!!, getString(R.string.title03_15)))
        adapter.items = list
        adapter.notifyDataSetChanged()
    }
    fun Dining(){
        val list = ArrayList<YoutubeItem>()
        list.add(YoutubeItem(getDrawable(R.drawable.image3_16)!!, getString(R.string.title03_16)))
        list.add(YoutubeItem(getDrawable(R.drawable.image3_17)!!, getString(R.string.title03_17)))
        list.add(YoutubeItem(getDrawable(R.drawable.image3_18)!!, getString(R.string.title03_18)))
        list.add(YoutubeItem(getDrawable(R.drawable.image3_19)!!, getString(R.string.title03_19)))
        list.add(YoutubeItem(getDrawable(R.drawable.image3_20)!!, getString(R.string.title03_20)))
        adapter.items = list
        adapter.notifyDataSetChanged()
    }
    fun cultural(){
        val list = ArrayList<YoutubeItem>()
        list.add(YoutubeItem(getDrawable(R.drawable.image3_21)!!, getString(R.string.title03_21)))
        list.add(YoutubeItem(getDrawable(R.drawable.image3_22)!!, getString(R.string.title03_22)))
        list.add(YoutubeItem(getDrawable(R.drawable.image3_23)!!, getString(R.string.title03_23)))
        list.add(YoutubeItem(getDrawable(R.drawable.image3_24)!!, getString(R.string.title03_24)))
        list.add(YoutubeItem(getDrawable(R.drawable.image3_25)!!, getString(R.string.title03_25)))
        adapter.items = list
        adapter.notifyDataSetChanged()
    }

}