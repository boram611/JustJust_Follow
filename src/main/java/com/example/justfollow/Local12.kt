package com.example.justfollow

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import kotlinx.android.synthetic.main.activity_local03.*
import kotlinx.android.synthetic.main.activity_main.*

class Local12 : AppCompatActivity() {

    private lateinit var adapter: RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_local12)



        val list = ArrayList<YoutubeItem>()
        adapter = RecyclerAdapter(list)
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(
                DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Log.d("Local12",
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
        list.add(YoutubeItem(getDrawable(R.drawable.image12_1)!!, getString(R.string.title12_1)))
        list.add(YoutubeItem(getDrawable(R.drawable.image12_2)!!, getString(R.string.title12_2)))
        list.add(YoutubeItem(getDrawable(R.drawable.image12_3)!!, getString(R.string.title12_3)))
        list.add(YoutubeItem(getDrawable(R.drawable.image12_4)!!, getString(R.string.title12_4)))
        list.add(YoutubeItem(getDrawable(R.drawable.image12_5)!!, getString(R.string.title12_5)))
        adapter.items = list
        adapter.notifyDataSetChanged()
    }

    fun Shopping() {
        val list = ArrayList<YoutubeItem>()
        list.add(YoutubeItem(getDrawable(R.drawable.image12_6)!!, getString(R.string.title12_6)))
        list.add(YoutubeItem(getDrawable(R.drawable.image12_7)!!, getString(R.string.title12_7)))
        list.add(YoutubeItem(getDrawable(R.drawable.image12_8)!!, getString(R.string.title12_8)))
        list.add(YoutubeItem(getDrawable(R.drawable.image12_9)!!, getString(R.string.title12_9)))
        list.add(YoutubeItem(getDrawable(R.drawable.image12_10)!!, getString(R.string.title12_10)))
        adapter.items = list
        adapter.notifyDataSetChanged()
    }
    fun Accommodation(){
        val list = ArrayList<YoutubeItem>()
        list.add(YoutubeItem(getDrawable(R.drawable.image12_11)!!, getString(R.string.title12_11)))
        list.add(YoutubeItem(getDrawable(R.drawable.image12_12)!!, getString(R.string.title12_12)))
        list.add(YoutubeItem(getDrawable(R.drawable.image12_13)!!, getString(R.string.title12_13)))
        list.add(YoutubeItem(getDrawable(R.drawable.image12_14)!!, getString(R.string.title12_14)))
        list.add(YoutubeItem(getDrawable(R.drawable.image12_15)!!, getString(R.string.title12_15)))
        adapter.items = list
        adapter.notifyDataSetChanged()
    }
    fun Dining(){
        val list = ArrayList<YoutubeItem>()
        list.add(YoutubeItem(getDrawable(R.drawable.image12_16)!!, getString(R.string.title12_16)))
        list.add(YoutubeItem(getDrawable(R.drawable.image12_17)!!, getString(R.string.title12_17)))
        list.add(YoutubeItem(getDrawable(R.drawable.image12_18)!!, getString(R.string.title12_18)))
        list.add(YoutubeItem(getDrawable(R.drawable.image12_19)!!, getString(R.string.title12_19)))
        list.add(YoutubeItem(getDrawable(R.drawable.image12_20)!!, getString(R.string.title12_20)))
        adapter.items = list
        adapter.notifyDataSetChanged()
    }
    fun cultural(){
        val list = ArrayList<YoutubeItem>()
        list.add(YoutubeItem(getDrawable(R.drawable.image12_21)!!, getString(R.string.title12_21)))
        list.add(YoutubeItem(getDrawable(R.drawable.image12_22)!!, getString(R.string.title12_22)))
        list.add(YoutubeItem(getDrawable(R.drawable.image12_23)!!, getString(R.string.title12_23)))
        list.add(YoutubeItem(getDrawable(R.drawable.image12_24)!!, getString(R.string.title12_24)))
        list.add(YoutubeItem(getDrawable(R.drawable.image12_25)!!, getString(R.string.title12_25)))
        adapter.items = list
        adapter.notifyDataSetChanged()
    }

}