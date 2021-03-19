package com.example.justfollow

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import kotlinx.android.synthetic.main.activity_local03.*
import kotlinx.android.synthetic.main.activity_main.*

class Local15 : AppCompatActivity() {

    private lateinit var adapter: RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_local15)



        val list = ArrayList<YoutubeItem>()
        adapter = RecyclerAdapter(list)
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(
                DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Log.d("Local15",
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
        list.add(YoutubeItem(getDrawable(R.drawable.image15_1)!!, getString(R.string.title15_1)))
        list.add(YoutubeItem(getDrawable(R.drawable.image15_2)!!, getString(R.string.title15_2)))
        list.add(YoutubeItem(getDrawable(R.drawable.image15_3)!!, getString(R.string.title15_3)))
        list.add(YoutubeItem(getDrawable(R.drawable.image15_4)!!, getString(R.string.title15_4)))
        list.add(YoutubeItem(getDrawable(R.drawable.image15_5)!!, getString(R.string.title15_5)))
        adapter.items = list
        adapter.notifyDataSetChanged()
    }

    fun Shopping() {
        val list = ArrayList<YoutubeItem>()
        list.add(YoutubeItem(getDrawable(R.drawable.image15_6)!!, getString(R.string.title15_6)))
        list.add(YoutubeItem(getDrawable(R.drawable.image15_7)!!, getString(R.string.title15_7)))
        list.add(YoutubeItem(getDrawable(R.drawable.image15_8)!!, getString(R.string.title15_8)))
        list.add(YoutubeItem(getDrawable(R.drawable.image15_9)!!, getString(R.string.title15_9)))
        list.add(YoutubeItem(getDrawable(R.drawable.image15_10)!!, getString(R.string.title15_10)))
        adapter.items = list
        adapter.notifyDataSetChanged()
    }
    fun Accommodation(){
        val list = ArrayList<YoutubeItem>()
        list.add(YoutubeItem(getDrawable(R.drawable.image15_11)!!, getString(R.string.title15_11)))
        list.add(YoutubeItem(getDrawable(R.drawable.image15_12)!!, getString(R.string.title15_12)))
        list.add(YoutubeItem(getDrawable(R.drawable.image15_13)!!, getString(R.string.title15_13)))
        list.add(YoutubeItem(getDrawable(R.drawable.image15_14)!!, getString(R.string.title15_14)))
        list.add(YoutubeItem(getDrawable(R.drawable.image15_15)!!, getString(R.string.title15_15)))
        adapter.items = list
        adapter.notifyDataSetChanged()
    }
    fun Dining(){
        val list = ArrayList<YoutubeItem>()
        list.add(YoutubeItem(getDrawable(R.drawable.image15_16)!!, getString(R.string.title15_16)))
        list.add(YoutubeItem(getDrawable(R.drawable.image15_17)!!, getString(R.string.title15_17)))
        list.add(YoutubeItem(getDrawable(R.drawable.image15_18)!!, getString(R.string.title15_18)))
        list.add(YoutubeItem(getDrawable(R.drawable.image15_19)!!, getString(R.string.title15_19)))
        list.add(YoutubeItem(getDrawable(R.drawable.image15_20)!!, getString(R.string.title15_20)))
        adapter.items = list
        adapter.notifyDataSetChanged()
    }
    fun cultural(){
        val list = ArrayList<YoutubeItem>()
        list.add(YoutubeItem(getDrawable(R.drawable.image15_21)!!, getString(R.string.title15_21)))
        list.add(YoutubeItem(getDrawable(R.drawable.image15_22)!!, getString(R.string.title15_22)))
        list.add(YoutubeItem(getDrawable(R.drawable.image15_23)!!, getString(R.string.title15_23)))
        list.add(YoutubeItem(getDrawable(R.drawable.image15_24)!!, getString(R.string.title15_24)))
        list.add(YoutubeItem(getDrawable(R.drawable.image15_25)!!, getString(R.string.title15_25)))
        adapter.items = list
        adapter.notifyDataSetChanged()
    }

}