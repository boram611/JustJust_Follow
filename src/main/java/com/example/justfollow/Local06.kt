package com.example.justfollow

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import kotlinx.android.synthetic.main.activity_local03.*
import kotlinx.android.synthetic.main.activity_main.*

class Local06 : AppCompatActivity() {

    private lateinit var adapter: RecyclerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_local06)



        val list = ArrayList<YoutubeItem>()
        adapter = RecyclerAdapter(list)
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(
                DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Log.d("Local06",
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
        list.add(YoutubeItem(getDrawable(R.drawable.image6_1)!!, getString(R.string.title06_1)))
        list.add(YoutubeItem(getDrawable(R.drawable.image6_2)!!, getString(R.string.title06_2)))
        list.add(YoutubeItem(getDrawable(R.drawable.image6_3)!!, getString(R.string.title06_3)))
        list.add(YoutubeItem(getDrawable(R.drawable.image6_4)!!, getString(R.string.title06_4)))
        list.add(YoutubeItem(getDrawable(R.drawable.image6_5)!!, getString(R.string.title06_5)))
        adapter.items = list
        adapter.notifyDataSetChanged()
    }

    fun Shopping() {
        val list = ArrayList<YoutubeItem>()
        list.add(YoutubeItem(getDrawable(R.drawable.image6_6)!!, getString(R.string.title06_6)))
        list.add(YoutubeItem(getDrawable(R.drawable.image6_7)!!, getString(R.string.title06_7)))
        list.add(YoutubeItem(getDrawable(R.drawable.image6_8)!!, getString(R.string.title06_8)))
        list.add(YoutubeItem(getDrawable(R.drawable.image6_9)!!, getString(R.string.title06_9)))
        list.add(YoutubeItem(getDrawable(R.drawable.image6_10)!!, getString(R.string.title06_10)))
        adapter.items = list
        adapter.notifyDataSetChanged()
    }
    fun Accommodation(){
        val list = ArrayList<YoutubeItem>()
        list.add(YoutubeItem(getDrawable(R.drawable.image6_11)!!, getString(R.string.title06_11)))
        list.add(YoutubeItem(getDrawable(R.drawable.image6_12)!!, getString(R.string.title06_12)))
        list.add(YoutubeItem(getDrawable(R.drawable.image6_13)!!, getString(R.string.title06_13)))
        list.add(YoutubeItem(getDrawable(R.drawable.image6_14)!!, getString(R.string.title06_14)))
        list.add(YoutubeItem(getDrawable(R.drawable.image6_15)!!, getString(R.string.title06_15)))
        adapter.items = list
        adapter.notifyDataSetChanged()
    }
    fun Dining(){
        val list = ArrayList<YoutubeItem>()
        list.add(YoutubeItem(getDrawable(R.drawable.image6_16)!!, getString(R.string.title06_16)))
        list.add(YoutubeItem(getDrawable(R.drawable.image6_17)!!, getString(R.string.title06_17)))
        list.add(YoutubeItem(getDrawable(R.drawable.image6_18)!!, getString(R.string.title06_18)))
        list.add(YoutubeItem(getDrawable(R.drawable.image6_19)!!, getString(R.string.title06_19)))
        list.add(YoutubeItem(getDrawable(R.drawable.image6_20)!!, getString(R.string.title06_20)))
        adapter.items = list
        adapter.notifyDataSetChanged()
    }
    fun cultural(){
        val list = ArrayList<YoutubeItem>()
        list.add(YoutubeItem(getDrawable(R.drawable.image6_21)!!, getString(R.string.title06_21)))
        list.add(YoutubeItem(getDrawable(R.drawable.image6_22)!!, getString(R.string.title06_22)))
        list.add(YoutubeItem(getDrawable(R.drawable.image6_23)!!, getString(R.string.title06_23)))
        list.add(YoutubeItem(getDrawable(R.drawable.image6_24)!!, getString(R.string.title06_24)))
        list.add(YoutubeItem(getDrawable(R.drawable.image6_25)!!, getString(R.string.title06_25)))
        adapter.items = list
        adapter.notifyDataSetChanged()
    }

}