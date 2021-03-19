package com.example.justfollow

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import kotlinx.android.synthetic.main.activity_local03.*
import kotlinx.android.synthetic.main.activity_main.*

class Local08 : AppCompatActivity() {

    private lateinit var adapter: RecyclerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_local08)



        val list = ArrayList<YoutubeItem>()
        adapter = RecyclerAdapter(list)
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(
                DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Log.d("Local08",
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
        list.add(YoutubeItem(getDrawable(R.drawable.image8_1)!!, getString(R.string.title08_1)))
        list.add(YoutubeItem(getDrawable(R.drawable.image8_2)!!, getString(R.string.title08_2)))
        list.add(YoutubeItem(getDrawable(R.drawable.image8_3)!!, getString(R.string.title08_3)))
        list.add(YoutubeItem(getDrawable(R.drawable.image8_4)!!, getString(R.string.title08_4)))
        list.add(YoutubeItem(getDrawable(R.drawable.image8_5)!!, getString(R.string.title08_5)))
        adapter.items = list
        adapter.notifyDataSetChanged()
    }

    fun Shopping() {
        val list = ArrayList<YoutubeItem>()
        list.add(YoutubeItem(getDrawable(R.drawable.image8_6)!!, getString(R.string.title08_6)))
        list.add(YoutubeItem(getDrawable(R.drawable.image8_7)!!, getString(R.string.title08_7)))
        list.add(YoutubeItem(getDrawable(R.drawable.image8_8)!!, getString(R.string.title08_8)))
        list.add(YoutubeItem(getDrawable(R.drawable.image8_9)!!, getString(R.string.title08_9)))
        list.add(YoutubeItem(getDrawable(R.drawable.image8_10)!!, getString(R.string.title08_10)))
        adapter.items = list
        adapter.notifyDataSetChanged()
    }
    fun Accommodation(){
        val list = ArrayList<YoutubeItem>()
        list.add(YoutubeItem(getDrawable(R.drawable.image8_11)!!, getString(R.string.title08_11)))
        list.add(YoutubeItem(getDrawable(R.drawable.image8_12)!!, getString(R.string.title08_12)))
        list.add(YoutubeItem(getDrawable(R.drawable.image8_13)!!, getString(R.string.title08_13)))
        list.add(YoutubeItem(getDrawable(R.drawable.image8_14)!!, getString(R.string.title08_14)))
        list.add(YoutubeItem(getDrawable(R.drawable.image8_15)!!, getString(R.string.title08_15)))
        adapter.items = list
        adapter.notifyDataSetChanged()
    }
    fun Dining(){
        val list = ArrayList<YoutubeItem>()
        list.add(YoutubeItem(getDrawable(R.drawable.image8_16)!!, getString(R.string.title08_16)))
        list.add(YoutubeItem(getDrawable(R.drawable.image8_17)!!, getString(R.string.title08_17)))
        list.add(YoutubeItem(getDrawable(R.drawable.image8_18)!!, getString(R.string.title08_18)))
        list.add(YoutubeItem(getDrawable(R.drawable.image8_19)!!, getString(R.string.title08_19)))
        list.add(YoutubeItem(getDrawable(R.drawable.image8_20)!!, getString(R.string.title08_20)))
        adapter.items = list
        adapter.notifyDataSetChanged()
    }
    fun cultural(){
        val list = ArrayList<YoutubeItem>()
        list.add(YoutubeItem(getDrawable(R.drawable.image8_21)!!, getString(R.string.title08_21)))
        list.add(YoutubeItem(getDrawable(R.drawable.image8_22)!!, getString(R.string.title08_22)))
        list.add(YoutubeItem(getDrawable(R.drawable.image8_23)!!, getString(R.string.title08_23)))
        list.add(YoutubeItem(getDrawable(R.drawable.image8_24)!!, getString(R.string.title08_24)))
        list.add(YoutubeItem(getDrawable(R.drawable.image8_25)!!, getString(R.string.title08_25)))
        adapter.items = list
        adapter.notifyDataSetChanged()
    }

}