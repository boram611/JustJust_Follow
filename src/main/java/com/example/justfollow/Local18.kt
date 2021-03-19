package com.example.justfollow

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import kotlinx.android.synthetic.main.activity_local03.*
import kotlinx.android.synthetic.main.activity_main.*

class Local18 : AppCompatActivity() {

    private lateinit var adapter: RecyclerAdapter

    var images = intArrayOf(
            R.drawable.image2_1,
            R.drawable.image2_2,
            R.drawable.image2_3,
            R.drawable.image2_4,
            R.drawable.image2_5,
            R.drawable.image2_6,
            R.drawable.image2_7,
            R.drawable.image2_8,
            R.drawable.image2_9,
            R.drawable.image2_10,
            R.drawable.image2_11,
            R.drawable.image2_12,
            R.drawable.image2_13,
            R.drawable.image2_14,
            R.drawable.image2_15,
            R.drawable.image2_16,
            R.drawable.image2_17,
            R.drawable.image2_18,
            R.drawable.image2_19,
            R.drawable.image2_20,
            R.drawable.image2_21,
            R.drawable.image2_22,
            R.drawable.image2_23,
            R.drawable.image2_24,
            R.drawable.image2_25
    )

    var Name = arrayOf(
            "Spa Dohang",
            "NengIn Seonwon",
            "Aell Cre",
            "AHC Spa",
            "BongEnunSa"
    )

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
                Log.d("Local01",
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
        list.add(YoutubeItem(getDrawable(R.drawable.image2_1)!!, getString(R.string.title01_1)))
        list.add(YoutubeItem(getDrawable(R.drawable.image2_2)!!, getString(R.string.title01_2)))
        list.add(YoutubeItem(getDrawable(R.drawable.image2_3)!!, getString(R.string.title01_3)))
        list.add(YoutubeItem(getDrawable(R.drawable.image2_4)!!, getString(R.string.title01_4)))
        list.add(YoutubeItem(getDrawable(R.drawable.image2_5)!!, getString(R.string.title01_5)))
        adapter.items = list
        adapter.notifyDataSetChanged()
    }

    fun Shopping() {
        val list = ArrayList<YoutubeItem>()
        list.add(YoutubeItem(getDrawable(R.drawable.image2_6)!!, getString(R.string.title01_6)))
        list.add(YoutubeItem(getDrawable(R.drawable.image2_7)!!, getString(R.string.title01_7)))
        list.add(YoutubeItem(getDrawable(R.drawable.image2_8)!!, getString(R.string.title01_8)))
        list.add(YoutubeItem(getDrawable(R.drawable.image2_9)!!, getString(R.string.title01_9)))
        list.add(YoutubeItem(getDrawable(R.drawable.image2_10)!!, getString(R.string.title01_10)))
        adapter.items = list
        adapter.notifyDataSetChanged()
    }
    fun Accommodation(){
        val list = ArrayList<YoutubeItem>()
        list.add(YoutubeItem(getDrawable(R.drawable.image2_11)!!, getString(R.string.title01_11)))
        list.add(YoutubeItem(getDrawable(R.drawable.image2_12)!!, getString(R.string.title01_12)))
        list.add(YoutubeItem(getDrawable(R.drawable.image2_13)!!, getString(R.string.title01_13)))
        list.add(YoutubeItem(getDrawable(R.drawable.image2_14)!!, getString(R.string.title01_14)))
        list.add(YoutubeItem(getDrawable(R.drawable.image2_15)!!, getString(R.string.title01_15)))
        adapter.items = list
        adapter.notifyDataSetChanged()
    }
    fun Dining(){
        val list = ArrayList<YoutubeItem>()
        list.add(YoutubeItem(getDrawable(R.drawable.image2_16)!!, getString(R.string.title01_16)))
        list.add(YoutubeItem(getDrawable(R.drawable.image2_17)!!, getString(R.string.title01_17)))
        list.add(YoutubeItem(getDrawable(R.drawable.image2_18)!!, getString(R.string.title01_18)))
        list.add(YoutubeItem(getDrawable(R.drawable.image2_19)!!, getString(R.string.title01_19)))
        list.add(YoutubeItem(getDrawable(R.drawable.image2_20)!!, getString(R.string.title01_20)))
        adapter.items = list
        adapter.notifyDataSetChanged()
    }
    fun cultural(){
        val list = ArrayList<YoutubeItem>()
        list.add(YoutubeItem(getDrawable(R.drawable.image2_21)!!, getString(R.string.title01_21)))
        list.add(YoutubeItem(getDrawable(R.drawable.image2_22)!!, getString(R.string.title01_22)))
        list.add(YoutubeItem(getDrawable(R.drawable.image2_23)!!, getString(R.string.title01_23)))
        list.add(YoutubeItem(getDrawable(R.drawable.image2_24)!!, getString(R.string.title01_24)))
        list.add(YoutubeItem(getDrawable(R.drawable.image2_25)!!, getString(R.string.title01_25)))
        adapter.items = list
        adapter.notifyDataSetChanged()
    }

}