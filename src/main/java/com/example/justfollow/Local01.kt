package com.example.justfollow

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import kotlinx.android.synthetic.main.activity_local01.*

class Local01 : AppCompatActivity() {

    private lateinit var adapter: RecyclerAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_local01)



        val list = ArrayList<YoutubeItem>()
        adapter = RecyclerAdapter(list)
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(
                DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Log.d("MainActivity",
                        "onItemSelected : $position, ${spinner.getItemAtPosition(position)}")
                when (spinner.getItemAtPosition(position)) {
                    "Historic" -> { //관광지
                        Historic()
                    }
                    "Shopping" -> { //쇼핑
                        Shopping()
                    }
                    "Accommodation" -> {  //숙박
                        Accommodation()
                    }
                    "Dining" ->{  //음식
                        Dining()
                    }
                    "cultural" -> { //문화시설
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
        list.add(YoutubeItem(getDrawable(R.drawable.image1_1)!!, getString(R.string.title01_1)))
        list.add(YoutubeItem(getDrawable(R.drawable.image1_2)!!, getString(R.string.title01_2)))
        list.add(YoutubeItem(getDrawable(R.drawable.image1_3)!!, getString(R.string.title01_3)))
        list.add(YoutubeItem(getDrawable(R.drawable.image1_4)!!, getString(R.string.title01_4)))
        list.add(YoutubeItem(getDrawable(R.drawable.image1_5)!!, getString(R.string.title01_5)))
        adapter.items = list
        adapter.notifyDataSetChanged()
    }

    fun Shopping() {
        val list = ArrayList<YoutubeItem>()
        list.add(YoutubeItem(getDrawable(R.drawable.image1_6)!!, getString(R.string.title01_6)))
        list.add(YoutubeItem(getDrawable(R.drawable.image1_7)!!, getString(R.string.title01_7)))
        list.add(YoutubeItem(getDrawable(R.drawable.image1_8)!!, getString(R.string.title01_8)))
        list.add(YoutubeItem(getDrawable(R.drawable.image1_9)!!, getString(R.string.title01_9)))
        list.add(YoutubeItem(getDrawable(R.drawable.image1_10)!!, getString(R.string.title01_10)))
        adapter.items = list
        adapter.notifyDataSetChanged()
    }
    fun Accommodation(){
        val list = ArrayList<YoutubeItem>()
        list.add(YoutubeItem(getDrawable(R.drawable.image1_11)!!, getString(R.string.title01_11)))
        list.add(YoutubeItem(getDrawable(R.drawable.image1_12)!!, getString(R.string.title01_12)))
        list.add(YoutubeItem(getDrawable(R.drawable.image1_13)!!, getString(R.string.title01_13)))
        list.add(YoutubeItem(getDrawable(R.drawable.image1_14)!!, getString(R.string.title01_14)))
        list.add(YoutubeItem(getDrawable(R.drawable.image1_15)!!, getString(R.string.title01_15)))
        adapter.items = list
        adapter.notifyDataSetChanged()
    }
    fun Dining(){
        val list = ArrayList<YoutubeItem>()
        list.add(YoutubeItem(getDrawable(R.drawable.image1_16)!!, getString(R.string.title01_16)))
        list.add(YoutubeItem(getDrawable(R.drawable.image1_17)!!, getString(R.string.title01_17)))
        list.add(YoutubeItem(getDrawable(R.drawable.image1_18)!!, getString(R.string.title01_18)))
        list.add(YoutubeItem(getDrawable(R.drawable.image1_19)!!, getString(R.string.title01_19)))
        list.add(YoutubeItem(getDrawable(R.drawable.image1_20)!!, getString(R.string.title01_20)))
        adapter.items = list
        adapter.notifyDataSetChanged()
    }
    fun cultural(){
        val list = ArrayList<YoutubeItem>()
        list.add(YoutubeItem(getDrawable(R.drawable.image1_21)!!, getString(R.string.title01_21)))
        list.add(YoutubeItem(getDrawable(R.drawable.image1_22)!!, getString(R.string.title01_22)))
        list.add(YoutubeItem(getDrawable(R.drawable.image1_23)!!, getString(R.string.title01_23)))
        list.add(YoutubeItem(getDrawable(R.drawable.image1_24)!!, getString(R.string.title01_24)))
        list.add(YoutubeItem(getDrawable(R.drawable.image1_25)!!, getString(R.string.title01_25)))
        adapter.items = list
        adapter.notifyDataSetChanged()
    }

}