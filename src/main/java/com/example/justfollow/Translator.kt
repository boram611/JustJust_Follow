package com.example.justfollow

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import com.google.gson.JsonParser
import kotlinx.android.synthetic.main.activity_translator.*
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder

public class Translator : AppCompatActivity() {

    var sourceLang = ""
    var targetLang = ""

    private var myClipboard: ClipboardManager? = null
    private var myClip: ClipData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_translator)

        myClipboard = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager?



        sourcebtn.setOnClickListener {
            val popup = PopupMenu(this,sourcebtn)
            popup.inflate(R.menu.lang)
            popup.setOnMenuItemClickListener {
                sourcebtn.text = it.title
                true
            }
            popup.show()
        }

        targetbtn.setOnClickListener {
            val popup = PopupMenu(this,targetbtn)
            popup.inflate(R.menu.lang)
            popup.setOnMenuItemClickListener {
                targetbtn.text = it.title
                true
            }
            popup.show()
        }

        changebtn.setOnClickListener {
            val temp = sourcebtn.text

            sourcebtn.text = targetbtn.text
            targetbtn.text = temp
        }



        translatebtn.setOnClickListener {

            if(editText.text.toString().length == 0){
                Toast.makeText(this,"Please enter what you want to translate.",Toast.LENGTH_SHORT).show()
            }

            else if(sourcebtn.text == targetbtn.text){
                Toast.makeText(this,"Translation language is the same. Please select another language.",Toast.LENGTH_SHORT).show()
            }

            else{

                if (sourcebtn.text == "English"){
                    sourceLang = "en"
                }
                else if (sourcebtn.text == "Korean"){
                    sourceLang = "ko"
                }
                else if (sourcebtn.text == "Japanese"){
                    sourceLang = "ja"
                }
                else if (sourcebtn.text == "Chinese"){
                    sourceLang = "zh-CN"
                }

                if (targetbtn.text == "English"){
                    targetLang = "en"
                }
                else if (targetbtn.text == "Korean"){
                    targetLang = "ko"
                }
                else if (targetbtn.text == "Japanese"){
                    targetLang = "ja"
                }
                else if (targetbtn.text == "Chinese"){
                    targetLang = "zh-CN"
                }



                val asyncTask = NaverTranslateTask()
                asyncTask.execute(editText.text.toString())


            }

        }
    }

    inner class NaverTranslateTask() : AsyncTask<String, Void, String>() {


        val clientId = "KVrY7WYkUzaAvMz2ZbwX"
        val clientSecret = "f56J_M9pIw"

//        val sourceLang = "en"
//        val targetLang = "ko"


        override fun onPreExecute() {
            super.onPreExecute()
        }

        override fun doInBackground(vararg params: String?): String {

            val sourceText = params[0]
            try {
                val text = URLEncoder.encode(sourceText, "UTF-8")
                val apiURL = "https://openapi.naver.com/v1/papago/n2mt";
                val url = URL(apiURL);
                val con: HttpURLConnection = url.openConnection() as HttpURLConnection;
                con.requestMethod = "POST";
                con.setRequestProperty("X-Naver-Client-Id", clientId);
                con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
                // post request
                val postParams = "source=" + sourceLang + "&target=" + targetLang + "&text=" + text;
                con.doOutput = true;
                val wr = DataOutputStream(con.getOutputStream());
                wr.writeBytes(postParams);
                wr.flush();
                wr.close();
                val responseCode = con.responseCode;
                var br: BufferedReader
                if (responseCode == 200) { // 정상 호출
                    br = BufferedReader(InputStreamReader(con.getInputStream()))
                } else {  // 에러 발생
                    br = BufferedReader(InputStreamReader(con.getErrorStream()))
                }
                var inputLine: String? = null
                val response = StringBuffer();
                while ({ inputLine = br.readLine(); inputLine }() != null) {
                    response.append(inputLine)
                }
                br.close()
                //System.out.println(response.toString());
                return response.toString()


            } catch (e:Exception){
                //System.out.println(e);

                return ""
            }
        }




        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)

            Log.d("background result1", result.toString())

            val gson: Gson =  GsonBuilder().create();
            val parser = JsonParser();
            val rootObj: JsonElement = parser.parse(result.toString())
                //원하는 데이터 까지 찾아 들어간다.
                .asJsonObject.get("message")
                .asJsonObject.get("result")
            //안드로이드 객체에 담기
            val items: TranslatedItem = gson.fromJson(rootObj.toString(), TranslatedItem::class.java)
            //Log.d("result", items.getTranslatedText());
            //번역결과를 텍스트뷰에 넣는다.
            papago.text = items.translatedText






        }


    }


}
