package com.example.justfollow

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import kotlinx.android.synthetic.main.activity_local03.*
import kotlinx.android.synthetic.main.activity_main.*

class Local16 : AppCompatActivity() {

    private lateinit var adapter: RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_local16)



        val list = ArrayList<YoutubeItem>()
        adapter = RecyclerAdapter(list)
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(
                DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Log.d("Local16",
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
        list.add(YoutubeItem(getDrawable(R.drawable.image16_1)!!, getString(R.string.title16_1)))
        list.add(YoutubeItem(getDrawable(R.drawable.image16_2)!!, getString(R.string.title16_2)))
        list.add(YoutubeItem(getDrawable(R.drawable.image16_3)!!, getString(R.string.title16_3)))
        list.add(YoutubeItem(getDrawable(R.drawable.image16_4)!!, getString(R.string.title16_4)))
        list.add(YoutubeItem(getDrawable(R.drawable.image16_5)!!, getString(R.string.title16_5)))
        adapter.items = list
        adapter.notifyDataSetChanged()
    }

    fun Shopping() {
        val list = ArrayList<YoutubeItem>()
        list.add(YoutubeItem(getDrawable(R.drawable.image16_6)!!, getString(R.string.title16_6)))
        list.add(YoutubeItem(getDrawable(R.drawable.image16_7)!!, getString(R.string.title16_7)))
        list.add(YoutubeItem(getDrawable(R.drawable.image16_8)!!, getString(R.string.title16_8)))
        list.add(YoutubeItem(getDrawable(R.drawable.image16_9)!!, getString(R.string.title16_9)))
        list.add(YoutubeItem(getDrawable(R.drawable.image16_10)!!, getString(R.string.title16_10)))
        adapter.items = list
        adapter.notifyDataSetChanged()
    }
    fun Accommodation(){
        val list = ArrayList<YoutubeItem>()
        list.add(YoutubeItem(getDrawable(R.drawable.image16_11)!!, getString(R.string.title16_11)))
        list.add(YoutubeItem(getDrawable(R.drawable.image16_12)!!, getString(R.string.title16_12)))
        list.add(YoutubeItem(getDrawable(R.drawable.image16_13)!!, getString(R.string.title16_13)))
        list.add(YoutubeItem(getDrawable(R.drawable.image16_14)!!, getString(R.string.title16_14)))
        list.add(YoutubeItem(getDrawable(R.drawable.image16_15)!!, getString(R.string.title16_15)))
        adapter.items = list
        adapter.notifyDataSetChanged()
    }
    fun Dining(){
        val list = ArrayList<YoutubeItem>()
        list.add(YoutubeItem(getDrawable(R.drawable.image16_16)!!, getString(R.string.title16_16)))
        list.add(YoutubeItem(getDrawable(R.drawable.image16_17)!!, getString(R.string.title16_17)))
        list.add(YoutubeItem(getDrawable(R.drawable.image16_18)!!, getString(R.string.title16_18)))
        list.add(YoutubeItem(getDrawable(R.drawable.image16_19)!!, getString(R.string.title16_19)))
        list.add(YoutubeItem(getDrawable(R.drawable.image16_20)!!, getString(R.string.title16_20)))
        adapter.items = list
        adapter.notifyDataSetChanged()
    }
    fun cultural(){
        val list = ArrayList<YoutubeItem>()
        list.add(YoutubeItem(getDrawable(R.drawable.image16_21)!!, getString(R.string.title16_21)))
        list.add(YoutubeItem(getDrawable(R.drawable.image16_22)!!, getString(R.string.title16_22)))
        list.add(YoutubeItem(getDrawable(R.drawable.image16_23)!!, getString(R.string.title16_23)))
        list.add(YoutubeItem(getDrawable(R.drawable.image16_24)!!, getString(R.string.title16_24)))
        list.add(YoutubeItem(getDrawable(R.drawable.image16_25)!!, getString(R.string.title16_25)))
        adapter.items = list
        adapter.notifyDataSetChanged()
    }

}