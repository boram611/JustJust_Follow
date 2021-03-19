package com.example.justfollow;

import androidx.appcompat.app.AppCompatActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;
import android.view.View;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class BusRoute extends AppCompatActivity {
    TextView textView;
    TextView busNumer;
    String getData;
    int busNumber = 0;
    int busNum = 503;
    String strSrch = busNumber + "";
    int count;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_route);

        textView = (TextView) findViewById(R.id.data);
        busNumer = (TextView) findViewById(R.id.bus);

        String serviceUrl = "http://ws.bus.go.kr/api/rest/busRouteInfo/getBusRouteList";
        String sercviceKey = "jfA2uDMy8G10CrocaUVfAvuH54UX3MwjWQ3UT%2FbXAvl6tOM%2BTFj8D2CkDa46e2klNMol%2FbXVx7sSxaSKS5AGDg%3D%3D";

        String strUrl = serviceUrl + "?ServiceKey=" + sercviceKey + "&strSrch=" + strSrch;
        BusRoute.DownloadWebContent dwcl = new BusRoute.DownloadWebContent();
        dwcl.execute(strUrl);
    }

    public class DownloadWebContent extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            try {
                return (String) downloadByUrl((String) urls[0]);
            } catch (IOException e) {
                return "fail download";
            }
        }

        protected void onPostExecute(String result) {
            String headerCd = "";
            String busRouteId = "";

            boolean bus_headerCd = false;
            boolean bus_busRouteId = false;

            try {
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(true);
                XmlPullParser xmlpp = factory.newPullParser();

                xmlpp.setInput(new StringReader(result));
                int eventType = xmlpp.getEventType();
                while (eventType != XmlPullParser.END_DOCUMENT) {
                    if (eventType == XmlPullParser.START_DOCUMENT) {
                        ;
                    } else if (eventType == XmlPullParser.START_TAG) {
                        String tag_name = xmlpp.getName();
                        if (tag_name.equals("headerCd"))
                            bus_headerCd = true;
                        if (tag_name.equals("busRouteId"))
                            bus_busRouteId = true;
                    } else if (eventType == XmlPullParser.TEXT) {
                        if (bus_headerCd) {
                            headerCd = xmlpp.getText();
                            bus_headerCd = false;
                        }
                        if (headerCd.equals("0")) {
                            if (bus_busRouteId) {
                                busRouteId = xmlpp.getText();
                                bus_busRouteId = false;
                            }
                        }

                    } else if (eventType == XmlPullParser.END_TAG) {
                        ;
                    }
                    eventType = xmlpp.next();
                }
            } catch (Exception e) {
                textView.setText(e.getMessage());
            }
            String serviceUrl = "http://ws.bus.go.kr/api/rest/busRouteInfo/getStaionByRoute";
            String serviceKey = "jfA2uDMy8G10CrocaUVfAvuH54UX3MwjWQ3UT%2FbXAvl6tOM%2BTFj8D2CkDa46e2klNMol%2FbXVx7sSxaSKS5AGDg%3D%3D";
            String strUrl = serviceUrl + "?ServiiceKey=" + serviceKey + "&busRouteId=" + busRouteId;

            DownloadWebContent2 dwc2 = new DownloadWebContent2();
            dwc2.execute(strUrl);
        }

        public String downloadByUrl(String myurl) throws IOException{

        HttpURLConnection conn = null;
        try{
            URL url = new URL(myurl);
            conn = (HttpURLConnection) url.openConnection();

            BufferedInputStream buffer = new BufferedInputStream(conn.getInputStream());

            BufferedReader buffer_reader = new BufferedReader(new InputStreamReader(buffer, "utf-8"));

            String line = null;
            getData = "";
            while ((line = buffer_reader.readLine()) != null) {
                getData += line;
            }
            return getData;
        } finally{

                conn.disconnect();
    }
}
}
    public class DownloadWebContent2 extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            try {
                return (String) downloadByUrl((String) urls[0]);
            } catch (IOException e) {
                return "download Fail";
            }
        }

        protected void onPostExecute(String result) {
            String headerCd = "";
            String gpsX = "";
            String gpsY = "";
            String stationNm = "";
            String direction = "";
            String sectSpd = "";

            boolean bus_headerCd = false;
            boolean bus_gpsX = false;
            boolean bus_gpsY = false;
            boolean bus_stationNm = false;
            boolean bus_direction = false;
            boolean bus_sectSpd = false;

            textView.append("-Bus Location Search Results-\n");
            try {
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(true);
                XmlPullParser xmlpp = factory.newPullParser();

                xmlpp.setInput(new StringReader(result));
                int eventType = xmlpp.getEventType();

                count = 0;
                while (eventType != XmlPullParser.END_DOCUMENT) {
                    if (eventType == XmlPullParser.START_DOCUMENT) {
                        ;
                    } else if (eventType == XmlPullParser.START_TAG) {
                        String tag_name = xmlpp.getName();

                        switch (tag_name) {
                            case "headerCd":
                                bus_headerCd = true;
                                break;
                            case "gpsX":
                                bus_gpsX = true;
                                break;
                            case "gpsY":
                                bus_gpsY = true;
                                break;
                            case "sectSpd":
                                bus_sectSpd = true;
                                break;
                            case "stationNm":
                                bus_stationNm = true;
                                break;
                            case "direction":
                                bus_direction = true;
                                break;
                        }
                    } else if (eventType == XmlPullParser.TEXT) {
                        if (bus_headerCd) {
                            headerCd = xmlpp.getText();
                            // textView.append("headerCd: " + headerCd + "\n");
                            bus_headerCd = false;
                        }
                        if (headerCd.equals("0")) {
                            if (bus_gpsX) {
                                count++;

                                textView.append("-------------------\n");

                                gpsX = xmlpp.getText();
                                textView.append("(" + count + ") gpsX:" + gpsX + "\n");
                                bus_gpsX = false;
                            }
                            if (bus_gpsY) {
                                gpsY = xmlpp.getText();
                                textView.append("(" + count + ") gpsY:" + gpsY + "\n");
                                bus_gpsY = false;
                            }
                            if (bus_stationNm) {
                                stationNm = xmlpp.getText();
                                textView.append("(" + count + ") Station Name:" + stationNm + "\n");
                                bus_stationNm = false;
                            }
                            if (bus_direction) {
                                direction = xmlpp.getText();
                                textView.append("(" + count + ") direction of progress:" + direction + "\n");
                                bus_direction = false;
                            }
                            if (bus_sectSpd) {
                                sectSpd = xmlpp.getText();
                                textView.append("(" + count + ") Station Name:" + sectSpd + "\n");
                                bus_sectSpd = false;
                            }
                        }

                    } else if (eventType == XmlPullParser.END_TAG) {
                        ;
                    }
                    eventType = xmlpp.next();
                }
            } catch (Exception e) {


                textView.setText(e.getMessage());
            }
        }

        public String downloadByUrl(String myurl) throws IOException {

            HttpURLConnection conn = null;
            BufferedReader buffer_reader;
            try {
                URL url = new URL(myurl);
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");

                BufferedInputStream buffer = new BufferedInputStream(conn.getInputStream());
                buffer_reader = new BufferedReader(new InputStreamReader(buffer, "utf-8"));

                String line = null;
                getData = "";
                while ((line = buffer_reader.readLine()) != null) {
                    getData += line;
                }
                return getData;
            } finally {
                conn.disconnect();
            }
        }
    }
        public void plusBusNumber (View v){
        busNum+=1;

        String serviceUrl="http://ws.bus.go.kr/api/rest/busRouteInfo/getBusRouteList";
        String serviceKey="jfA2uDMy8G10CrocaUVfAvuH54UX3MwjWQ3UT%2FbXAvl6tOM%2BTFj8D2CkDa46e2klNMol%2FbXVx7sSxaSKS5AGDg%3D%3D";
        strSrch=busNum+"";

        String strUrl=serviceUrl+"?ServiceKey="+serviceKey+"&strSrch="+strSrch;

        DownloadWebContent dwcl = new DownloadWebContent();
        dwcl.execute(strUrl);
        textView.setText("");
        busNumer.setText("");
        busNumer.append("Bus Number");
        busNumer.append(strSrch+"\n");
        }
        public void minusBusNumber (View v){
        busNum-=1;

        String serviceUrl="http://ws.bus.go.kr/api/rest/busRouteInfo/getBusRouteList";
        String serviceKey="jfA2uDMy8G10CrocaUVfAvuH54UX3MwjWQ3UT%2FbXAvl6tOM%2BTFj8D2CkDa46e2klNMol%2FbXVx7sSxaSKS5AGDg%3D%3D";
        strSrch=busNum+"";

        String strUrl=serviceUrl+"?ServiceKey="+serviceKey+"&strSrch="+strSrch;

        DownloadWebContent dwcl=new DownloadWebContent();
        dwcl.execute(strUrl);
        textView.setText("");
        busNumer.setText("");
        busNumer.append("Bus Number");
        busNumer.append(strSrch+"\n");
        }
        public void resetCurrentBus (View v){
        busNum+=1;
        String serviceUrl="http://ws.bus.go.kr/api/rest/busRouteInfo/getBusRouteList";
        String serviceKey="jfA2uDMy8G10CrocaUVfAvuH54UX3MwjWQ3UT%2FbXAvl6tOM%2BTFj8D2CkDa46e2klNMol%2FbXVx7sSxaSKS5AGDg%3D%3D";
        strSrch=busNum+"";

        String strUrl=serviceUrl+"?ServiceKey="+serviceKey+"&strSrch="+strSrch;

        DownloadWebContent dwcl=new DownloadWebContent();
        dwcl.execute(strUrl);
        textView.setText("");
        busNumer.setText("");
        busNumer.append("Bus Number");
        busNumer.append(strSrch+"\n");
        }
        public void plusBaek (View v){
        busNum+=100;
        String serviceUrl="http://ws.bus.go.kr/api/rest/busRouteInfo/getBusRouteList";
        String serviceKey="jfA2uDMy8G10CrocaUVfAvuH54UX3MwjWQ3UT%2FbXAvl6tOM%2BTFj8D2CkDa46e2klNMol%2FbXVx7sSxaSKS5AGDg%3D%3D";
        strSrch=busNum+"";

        String strUrl=serviceUrl+"?ServiceKey="+serviceKey+"&strSrch="+strSrch;

        DownloadWebContent dwcl=new DownloadWebContent();
        dwcl.execute(strUrl);
        textView.setText("");
        busNumer.setText("");
        busNumer.append("Bus Number");
        busNumer.append(strSrch+"\n");
        }
        public void minusBaek (View v){
        busNum -= 100;
        String serviceUrl = "http://ws.bus.go.kr/api/rest/busRouteInfo/getBusRouteList";
        String serviceKey = "jfA2uDMy8G10CrocaUVfAvuH54UX3MwjWQ3UT%2FbXAvl6tOM%2BTFj8D2CkDa46e2klNMol%2FbXVx7sSxaSKS5AGDg%3D%3D";
        strSrch = busNum + "";

        String strUrl = serviceUrl + "?ServiceKey=" + serviceKey + "&strSrch=" + strSrch;

        DownloadWebContent dwcl = new DownloadWebContent();
        dwcl.execute(strUrl);
        textView.setText("");
        busNumer.setText("");
        busNumer.append("Bus Number");
        busNumer.append(strSrch + "\n");
    }
}