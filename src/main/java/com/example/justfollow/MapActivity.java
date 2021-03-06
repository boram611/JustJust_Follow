package com.example.justfollow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.crowdfire.cfalertdialog.CFAlertDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.example.justfollow.R;
import com.example.justfollow.adapter.LocationAdapter;
import com.example.justfollow.api.ApiClient;
import com.example.justfollow.api.ApiInterface;
import com.example.justfollow.model.category_search.Document;
import com.example.justfollow.model.category_search.CategoryResult;
import com.example.justfollow.utils.BusProvider;
import com.example.justfollow.utils.IntentKey;
import com.naver.maps.map.NaverMapSdk;
import com.shashank.sony.fancytoastlib.FancyToast;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;


import net.daum.mf.map.api.MapCircle;
import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;


import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MapActivity extends AppCompatActivity implements MapView.MapViewEventListener, MapView.POIItemEventListener, MapView.OpenAPIKeyAuthenticationResultListener, View.OnClickListener, MapView.CurrentLocationEventListener {
    final static String TAG = "MapTAG";
    //xml
    MapView mMapView;
    ViewGroup mMapViewContainer;
    EditText mSearchEdit;
    private Animation fab_open, fab_close;
    private Boolean isFabOpen = false;
    private FloatingActionButton fab, fab1, fab2, fab3, searchDetailFab, stopTrackingFab;
    RelativeLayout mLoaderLayout;
    RecyclerView recyclerView;

    //value
    MapPoint currentMapPoint;
    private double mCurrentLng; //Long = X, Lat = Y???
    private double mCurrentLat;
    private double mSearchLng = -1;
    private double mSearchLat = -1;
    private String mSearchName;
    boolean isTrackingMode = false; //????????? ???????????? (3?????? ?????? ???????????? ?????? ????????? ?????? true?????? stop ?????? ????????? false??? ??????)
    Bus bus = BusProvider.getInstance();

    ArrayList<Document> bigMartList = new ArrayList<>(); //???????????? MT1
    ArrayList<Document> gs24List = new ArrayList<>(); //????????? CS2
    ArrayList<Document> schoolList = new ArrayList<>(); //?????? SC4
    ArrayList<Document> academyList = new ArrayList<>(); //?????? AC5
    ArrayList<Document> subwayList = new ArrayList<>(); //????????? SW8
    ArrayList<Document> bankList = new ArrayList<>(); //?????? BK9
    ArrayList<Document> hospitalList = new ArrayList<>(); //?????? HP8
    ArrayList<Document> pharmacyList = new ArrayList<>(); //?????? PM9
    ArrayList<Document> cafeList = new ArrayList<>(); //??????

    ArrayList<Document> documentArrayList = new ArrayList<>(); //????????? ?????? ?????? ?????????

    MapPOIItem searchMarker = new MapPOIItem();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_map);
        bus.register(this); //????????? ??????
        initView();

    }


    private void initView() {
        //binding
        mSearchEdit = findViewById(R.id.map_et_search);
        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        fab = findViewById(R.id.fab);
        fab1 = findViewById(R.id.fab1);
        fab2 = findViewById(R.id.fab2);
        fab3 = findViewById(R.id.fab3);
        searchDetailFab = findViewById(R.id.fab_detail);
        stopTrackingFab = findViewById(R.id.fab_stop_tracking);
        mLoaderLayout = findViewById(R.id.loaderLayout);
        mMapView = new MapView(this);
        mMapViewContainer = findViewById(R.id.map_mv_mapcontainer);
        mMapViewContainer.addView(mMapView);
        recyclerView = findViewById(R.id.map_recyclerview);
        LocationAdapter locationAdapter = new LocationAdapter(documentArrayList, getApplicationContext(), mSearchEdit, recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false); //????????????????????? ??????
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL)); //??????????????? ??????
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(locationAdapter);

        //??? ?????????
        mMapView.setMapViewEventListener(this); // this??? MapView.MapViewEventListener ??????.
        mMapView.setPOIItemEventListener(this);
        mMapView.setOpenAPIKeyAuthenticationResultListener(this);

        //???????????????
        fab.setOnClickListener(this);
        fab1.setOnClickListener(this);
        fab2.setOnClickListener(this);
        fab3.setOnClickListener(this);
        searchDetailFab.setOnClickListener(this);
        stopTrackingFab.setOnClickListener(this);

        Toast.makeText(this, "Load the map", Toast.LENGTH_SHORT).show();

        //??? ????????? (???????????? ????????????)
        mMapView.setCurrentLocationEventListener(this);
        //setCurrentLocationTrackingMode (????????? ???????????? ?????? ???????????? ???????????????.)
        mMapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading);
        mLoaderLayout.setVisibility(View.VISIBLE);

        // editText ?????? ??????????????????
        mSearchEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
                // ???????????? ??????
                recyclerView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                if (charSequence.length() >= 1) {
                    // if (SystemClock.elapsedRealtime() - mLastClickTime < 500) {

                    documentArrayList.clear();
                    locationAdapter.clear();
                    locationAdapter.notifyDataSetChanged();
                    ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
                    Call<CategoryResult> call = apiInterface.getSearchLocation(getString(R.string.restapi_key), charSequence.toString(), 15);
                    call.enqueue(new Callback<CategoryResult>() {
                        @Override
                        public void onResponse(@NotNull Call<CategoryResult> call, @NotNull Response<CategoryResult> response) {
                            if (response.isSuccessful()) {
                                assert response.body() != null;
                                for (Document document : response.body().getDocuments()) {
                                    locationAdapter.addItem(document);
                                }
                                locationAdapter.notifyDataSetChanged();
                            }
                        }

                        @Override
                        public void onFailure(@NotNull Call<CategoryResult> call, @NotNull Throwable t) {

                        }
                    });
                    //}
                    //mLastClickTime = SystemClock.elapsedRealtime();
                } else {
                    if (charSequence.length() <= 0) {
                        recyclerView.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // ????????? ????????? ???
            }
        });

        mSearchEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                } else {
                    recyclerView.setVisibility(View.GONE);
                }
            }
        });
        mSearchEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FancyToast.makeText(getApplicationContext(), "Please select a location from the search list.", FancyToast.LENGTH_SHORT, FancyToast.INFO, true).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.fab:
                FancyToast.makeText(this, "Button 1: Search 1km by search coordinate" +
                        "\nButton 2: Searching for surroundings based on current location\n" +
                        "\nButton 3: Track and Update Your Current Location", FancyToast.LENGTH_SHORT, FancyToast.INFO, true).show();
                anim();
                break;
            case R.id.fab1: //???????????????????????? 1~3???
                FancyToast.makeText(this, "Start tracking current location", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, true).show();
                searchDetailFab.setVisibility(View.GONE);
                mLoaderLayout.setVisibility(View.VISIBLE);
                isTrackingMode = true;
                mMapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithHeading);
                anim();
                stopTrackingFab.setVisibility(View.VISIBLE);
                mLoaderLayout.setVisibility(View.GONE);
                break;
            case R.id.fab2:
                isTrackingMode = false;
                FancyToast.makeText(this, "Initiate 1km search based on current location", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, true).show();
                stopTrackingFab.setVisibility(View.GONE);
                mLoaderLayout.setVisibility(View.VISIBLE);
                anim();
                //?????? ?????? ???????????? 1km ??????
                mMapView.removeAllPOIItems();
                mMapView.removeAllCircles();
                requestSearchLocal(mCurrentLng, mCurrentLat);
                mMapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading);
                break;
            case R.id.fab3:
                isTrackingMode = false;
                mMapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOff);
                mLoaderLayout.setVisibility(View.VISIBLE);
                anim();
                if (mSearchLat != -1 && mSearchLng != -1) {
                    mMapView.removeAllPOIItems();
                    mMapView.removeAllCircles();
                    mMapView.addPOIItem(searchMarker);
                    requestSearchLocal(mSearchLng, mSearchLat);
                } else {
                    FancyToast.makeText(this, "Please search first.", FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();
                }
                mLoaderLayout.setVisibility(View.GONE);
                break;
            case R.id.fab_detail:
                FancyToast.makeText(this, "View search results in detail", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, true).show();
                Intent detailIntent = new Intent(MapActivity.this, MapSearchDetailActivity.class);
                detailIntent.putParcelableArrayListExtra(IntentKey.CATEGOTY_SEARCH_MODEL_EXTRA1, bigMartList);
                detailIntent.putParcelableArrayListExtra(IntentKey.CATEGOTY_SEARCH_MODEL_EXTRA2, gs24List);
                detailIntent.putParcelableArrayListExtra(IntentKey.CATEGOTY_SEARCH_MODEL_EXTRA3, schoolList);
                detailIntent.putParcelableArrayListExtra(IntentKey.CATEGOTY_SEARCH_MODEL_EXTRA4, academyList);
                detailIntent.putParcelableArrayListExtra(IntentKey.CATEGOTY_SEARCH_MODEL_EXTRA5, subwayList);
                detailIntent.putParcelableArrayListExtra(IntentKey.CATEGOTY_SEARCH_MODEL_EXTRA6, bankList);
                detailIntent.putParcelableArrayListExtra(IntentKey.CATEGOTY_SEARCH_MODEL_EXTRA7, hospitalList);
                detailIntent.putParcelableArrayListExtra(IntentKey.CATEGOTY_SEARCH_MODEL_EXTRA8, pharmacyList);
                detailIntent.putParcelableArrayListExtra(IntentKey.CATEGOTY_SEARCH_MODEL_EXTRA9, cafeList);
                overridePendingTransition(R.anim.fade_in_splash, R.anim.fade_out_splash);
                startActivity(detailIntent);
                Log.d(TAG, "fab_detail");
                break;
            case R.id.fab_stop_tracking:
                isTrackingMode = false;
                mMapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOff);
                stopTrackingFab.setVisibility(View.GONE);
                FancyToast.makeText(this, "End current location tracking", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, true).show();
                break;
        }
    }

    private void requestSearchLocal(double x, double y) {
        bigMartList.clear();
        gs24List.clear();
        schoolList.clear();
        academyList.clear();
        subwayList.clear();
        bankList.clear();
        hospitalList.clear();
        pharmacyList.clear();
        cafeList.clear();
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<CategoryResult> call = apiInterface.getSearchCategory(getString(R.string.restapi_key), "MT1", x + "", y + "", 1000);
        call.enqueue(new Callback<CategoryResult>() {
            @Override
            public void onResponse(@NotNull Call<CategoryResult> call, @NotNull Response<CategoryResult> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    if (response.body().getDocuments() != null) {
                        Log.d(TAG, "bigMartList Success");
                        bigMartList.addAll(response.body().getDocuments());
                    }
                    call = apiInterface.getSearchCategory(getString(R.string.restapi_key), "CS2", x + "", y + "", 1000);
                    call.enqueue(new Callback<CategoryResult>() {
                        @Override
                        public void onResponse(@NotNull Call<CategoryResult> call, @NotNull Response<CategoryResult> response) {
                            if (response.isSuccessful()) {
                                assert response.body() != null;
                                Log.d(TAG, "gs24List Success");
                                gs24List.addAll(response.body().getDocuments());
                                call = apiInterface.getSearchCategory(getString(R.string.restapi_key), "SC4", x + "", y + "", 1000);
                                call.enqueue(new Callback<CategoryResult>() {
                                    @Override
                                    public void onResponse(@NotNull Call<CategoryResult> call, @NotNull Response<CategoryResult> response) {
                                        if (response.isSuccessful()) {
                                            assert response.body() != null;
                                            Log.d(TAG, "schoolList Success");
                                            schoolList.addAll(response.body().getDocuments());
                                            call = apiInterface.getSearchCategory(getString(R.string.restapi_key), "AC5", x + "", y + "", 1000);
                                            call.enqueue(new Callback<CategoryResult>() {
                                                @Override
                                                public void onResponse(@NotNull Call<CategoryResult> call, @NotNull Response<CategoryResult> response) {
                                                    if (response.isSuccessful()) {
                                                        assert response.body() != null;
                                                        Log.d(TAG, "academyList Success");
                                                        academyList.addAll(response.body().getDocuments());
                                                        call = apiInterface.getSearchCategory(getString(R.string.restapi_key), "SW8", x + "", y + "", 1000);
                                                        call.enqueue(new Callback<CategoryResult>() {
                                                            @Override
                                                            public void onResponse(@NotNull Call<CategoryResult> call, @NotNull Response<CategoryResult> response) {
                                                                if (response.isSuccessful()) {
                                                                    assert response.body() != null;
                                                                    Log.d(TAG, "subwayList Success");
                                                                    subwayList.addAll(response.body().getDocuments());
                                                                    call = apiInterface.getSearchCategory(getString(R.string.restapi_key), "BK9", x + "", y + "", 1000);
                                                                    call.enqueue(new Callback<CategoryResult>() {
                                                                        @Override
                                                                        public void onResponse(@NotNull Call<CategoryResult> call, @NotNull Response<CategoryResult> response) {
                                                                            if (response.isSuccessful()) {
                                                                                assert response.body() != null;
                                                                                Log.d(TAG, "bankList Success");
                                                                                bankList.addAll(response.body().getDocuments());
                                                                                call = apiInterface.getSearchCategory(getString(R.string.restapi_key), "HP8", x + "", y + "", 1000);
                                                                                call.enqueue(new Callback<CategoryResult>() {
                                                                                    @Override
                                                                                    public void onResponse(@NotNull Call<CategoryResult> call, @NotNull Response<CategoryResult> response) {
                                                                                        if (response.isSuccessful()) {
                                                                                            assert response.body() != null;
                                                                                            Log.d(TAG, "hospitalList Success");
                                                                                            hospitalList.addAll(response.body().getDocuments());
                                                                                            call = apiInterface.getSearchCategory(getString(R.string.restapi_key), "PM9", x + "", y + "", 1000);
                                                                                            call.enqueue(new Callback<CategoryResult>() {
                                                                                                @Override
                                                                                                public void onResponse(@NotNull Call<CategoryResult> call, @NotNull Response<CategoryResult> response) {
                                                                                                    if (response.isSuccessful()) {
                                                                                                        assert response.body() != null;
                                                                                                        Log.d(TAG, "pharmacyList Success");
                                                                                                        pharmacyList.addAll(response.body().getDocuments());
                                                                                                        call = apiInterface.getSearchCategory(getString(R.string.restapi_key), "CE7", x + "", y + "", 1000);
                                                                                                        call.enqueue(new Callback<CategoryResult>() {
                                                                                                            @Override
                                                                                                            public void onResponse(@NotNull Call<CategoryResult> call, @NotNull Response<CategoryResult> response) {
                                                                                                                if (response.isSuccessful()) {
                                                                                                                    assert response.body() != null;
                                                                                                                    Log.d(TAG, "cafeList Success");
                                                                                                                    cafeList.addAll(response.body().getDocuments());
                                                                                                                    //?????? ?????? ?????? ??? circle ??????
                                                                                                                    MapCircle circle1 = new MapCircle(
                                                                                                                            MapPoint.mapPointWithGeoCoord(y, x), // center
                                                                                                                            1000, // radius
                                                                                                                            Color.argb(128, 255, 0, 0), // strokeColor
                                                                                                                            Color.argb(128, 0, 255, 0) // fillColor
                                                                                                                    );
                                                                                                                    circle1.setTag(5678);
                                                                                                                    mMapView.addCircle(circle1);
                                                                                                                    Log.d("SIZE1", bigMartList.size() + "");
                                                                                                                    Log.d("SIZE2", gs24List.size() + "");
                                                                                                                    Log.d("SIZE3", schoolList.size() + "");
                                                                                                                    Log.d("SIZE4", academyList.size() + "");
                                                                                                                    Log.d("SIZE5", subwayList.size() + "");
                                                                                                                    Log.d("SIZE6", bankList.size() + "");
                                                                                                                    //?????? ??????
                                                                                                                    int tagNum = 10;
                                                                                                                    for (Document document : bigMartList) {
                                                                                                                        MapPOIItem marker = new MapPOIItem();
                                                                                                                        marker.setItemName(document.getPlaceName());
                                                                                                                        marker.setTag(tagNum++);
                                                                                                                        double x = Double.parseDouble(document.getY());
                                                                                                                        double y = Double.parseDouble(document.getX());
                                                                                                                        //??????????????? ????????? new MapPoint()???  ????????????. ??????????????? ???????????? ????????? ???????????? ???????????????
                                                                                                                        MapPoint mapPoint = MapPoint.mapPointWithGeoCoord(x, y);
                                                                                                                        marker.setMapPoint(mapPoint);
                                                                                                                        marker.setMarkerType(MapPOIItem.MarkerType.CustomImage); // ??????????????? ????????? ????????? ??????.
                                                                                                                        marker.setCustomImageResourceId(R.drawable.ic_big_mart_marker); // ?????? ?????????.
                                                                                                                        marker.setCustomImageAutoscale(false); // hdpi, xhdpi ??? ??????????????? ???????????? ???????????? ????????? ?????? ?????? ?????????????????? ????????? ????????? ??????.
                                                                                                                        marker.setCustomImageAnchor(0.5f, 1.0f); // ?????? ???????????? ????????? ?????? ??????(???????????????) ?????? - ?????? ????????? ?????? ?????? ?????? x(0.0f ~ 1.0f), y(0.0f ~ 1.0f) ???.
                                                                                                                        mMapView.addPOIItem(marker);
                                                                                                                    }
                                                                                                                    for (Document document : gs24List) {
                                                                                                                        MapPOIItem marker = new MapPOIItem();
                                                                                                                        marker.setItemName(document.getPlaceName());
                                                                                                                        marker.setTag(tagNum++);
                                                                                                                        double x = Double.parseDouble(document.getY());
                                                                                                                        double y = Double.parseDouble(document.getX());
                                                                                                                        //??????????????? ????????? new MapPoint()???  ????????????. ??????????????? ???????????? ????????? ???????????? ???????????????
                                                                                                                        MapPoint mapPoint = MapPoint.mapPointWithGeoCoord(x, y);
                                                                                                                        marker.setMapPoint(mapPoint);
                                                                                                                        marker.setMarkerType(MapPOIItem.MarkerType.CustomImage); // ??????????????? ????????? ????????? ??????.
                                                                                                                        marker.setCustomImageResourceId(R.drawable.ic_24_mart_marker); // ?????? ?????????.
                                                                                                                        marker.setCustomImageAutoscale(false); // hdpi, xhdpi ??? ??????????????? ???????????? ???????????? ????????? ?????? ?????? ?????????????????? ????????? ????????? ??????.
                                                                                                                        marker.setCustomImageAnchor(0.5f, 1.0f);
                                                                                                                        mMapView.addPOIItem(marker);
                                                                                                                    }
                                                                                                                    for (Document document : schoolList) {
                                                                                                                        MapPOIItem marker = new MapPOIItem();
                                                                                                                        marker.setItemName(document.getPlaceName());
                                                                                                                        marker.setTag(tagNum++);
                                                                                                                        double x = Double.parseDouble(document.getY());
                                                                                                                        double y = Double.parseDouble(document.getX());
                                                                                                                        //??????????????? ????????? new MapPoint()???  ????????????. ??????????????? ???????????? ????????? ???????????? ???????????????
                                                                                                                        MapPoint mapPoint = MapPoint.mapPointWithGeoCoord(x, y);
                                                                                                                        marker.setMapPoint(mapPoint);
                                                                                                                        marker.setMarkerType(MapPOIItem.MarkerType.CustomImage); // ??????????????? ????????? ????????? ??????.
                                                                                                                        marker.setCustomImageResourceId(R.drawable.ic_school_marker); // ?????? ?????????.
                                                                                                                        marker.setCustomImageAutoscale(false); // hdpi, xhdpi ??? ??????????????? ???????????? ???????????? ????????? ?????? ?????? ?????????????????? ????????? ????????? ??????.
                                                                                                                        marker.setCustomImageAnchor(0.5f, 1.0f);
                                                                                                                        mMapView.addPOIItem(marker);
                                                                                                                    }
                                                                                                                    for (Document document : academyList) {
                                                                                                                        MapPOIItem marker = new MapPOIItem();
                                                                                                                        marker.setItemName(document.getPlaceName());
                                                                                                                        marker.setTag(tagNum++);
                                                                                                                        double x = Double.parseDouble(document.getY());
                                                                                                                        double y = Double.parseDouble(document.getX());
                                                                                                                        //??????????????? ????????? new MapPoint()???  ????????????. ??????????????? ???????????? ????????? ???????????? ???????????????
                                                                                                                        MapPoint mapPoint = MapPoint.mapPointWithGeoCoord(x, y);
                                                                                                                        marker.setMapPoint(mapPoint);
                                                                                                                        marker.setMarkerType(MapPOIItem.MarkerType.CustomImage); // ??????????????? ????????? ????????? ??????.
                                                                                                                        marker.setCustomImageResourceId(R.drawable.ic_academy_marker); // ?????? ?????????.
                                                                                                                        marker.setCustomImageAutoscale(false); // hdpi, xhdpi ??? ??????????????? ???????????? ???????????? ????????? ?????? ?????? ?????????????????? ????????? ????????? ??????.
                                                                                                                        marker.setCustomImageAnchor(0.5f, 1.0f);
                                                                                                                        mMapView.addPOIItem(marker);
                                                                                                                    }
                                                                                                                    for (Document document : subwayList) {
                                                                                                                        MapPOIItem marker = new MapPOIItem();
                                                                                                                        marker.setItemName(document.getPlaceName());
                                                                                                                        marker.setTag(tagNum++);
                                                                                                                        double x = Double.parseDouble(document.getY());
                                                                                                                        double y = Double.parseDouble(document.getX());
                                                                                                                        //??????????????? ????????? new MapPoint()???  ????????????. ??????????????? ???????????? ????????? ???????????? ???????????????
                                                                                                                        MapPoint mapPoint = MapPoint.mapPointWithGeoCoord(x, y);
                                                                                                                        marker.setMapPoint(mapPoint);
                                                                                                                        marker.setMarkerType(MapPOIItem.MarkerType.CustomImage); // ??????????????? ????????? ????????? ??????.
                                                                                                                        marker.setCustomImageResourceId(R.drawable.ic_subway_marker); // ?????? ?????????.
                                                                                                                        marker.setCustomImageAutoscale(false); // hdpi, xhdpi ??? ??????????????? ???????????? ???????????? ????????? ?????? ?????? ?????????????????? ????????? ????????? ??????.
                                                                                                                        marker.setCustomImageAnchor(0.5f, 1.0f);
                                                                                                                        mMapView.addPOIItem(marker);
                                                                                                                    }
                                                                                                                    for (Document document : bankList) {
                                                                                                                        MapPOIItem marker = new MapPOIItem();
                                                                                                                        marker.setItemName(document.getPlaceName());
                                                                                                                        marker.setTag(tagNum++);
                                                                                                                        double x = Double.parseDouble(document.getY());
                                                                                                                        double y = Double.parseDouble(document.getX());
                                                                                                                        //??????????????? ????????? new MapPoint()???  ????????????. ??????????????? ???????????? ????????? ???????????? ???????????????
                                                                                                                        MapPoint mapPoint = MapPoint.mapPointWithGeoCoord(x, y);
                                                                                                                        marker.setMapPoint(mapPoint);
                                                                                                                        marker.setMarkerType(MapPOIItem.MarkerType.CustomImage); // ??????????????? ????????? ????????? ??????.
                                                                                                                        marker.setCustomImageResourceId(R.drawable.ic_bank_marker); // ?????? ?????????.
                                                                                                                        marker.setCustomImageAutoscale(false); // hdpi, xhdpi ??? ??????????????? ???????????? ???????????? ????????? ?????? ?????? ?????????????????? ????????? ????????? ??????.
                                                                                                                        marker.setCustomImageAnchor(0.5f, 1.0f);
                                                                                                                        mMapView.addPOIItem(marker);
                                                                                                                    }
                                                                                                                    for (Document document : hospitalList) {
                                                                                                                        MapPOIItem marker = new MapPOIItem();
                                                                                                                        marker.setItemName(document.getPlaceName());
                                                                                                                        marker.setTag(tagNum++);
                                                                                                                        double x = Double.parseDouble(document.getY());
                                                                                                                        double y = Double.parseDouble(document.getX());
                                                                                                                        //??????????????? ????????? new MapPoint()???  ????????????. ??????????????? ???????????? ????????? ???????????? ???????????????
                                                                                                                        MapPoint mapPoint = MapPoint.mapPointWithGeoCoord(x, y);
                                                                                                                        marker.setMapPoint(mapPoint);
                                                                                                                        marker.setMarkerType(MapPOIItem.MarkerType.CustomImage); // ??????????????? ????????? ????????? ??????.
                                                                                                                        marker.setCustomImageResourceId(R.drawable.ic_hospital_marker); // ?????? ?????????.
                                                                                                                        marker.setCustomImageAutoscale(false); // hdpi, xhdpi ??? ??????????????? ???????????? ???????????? ????????? ?????? ?????? ?????????????????? ????????? ????????? ??????.
                                                                                                                        marker.setCustomImageAnchor(0.5f, 1.0f);
                                                                                                                        mMapView.addPOIItem(marker);
                                                                                                                    }
                                                                                                                    for (Document document : pharmacyList) {
                                                                                                                        MapPOIItem marker = new MapPOIItem();
                                                                                                                        marker.setItemName(document.getPlaceName());
                                                                                                                        marker.setTag(tagNum++);
                                                                                                                        double x = Double.parseDouble(document.getY());
                                                                                                                        double y = Double.parseDouble(document.getX());
                                                                                                                        //??????????????? ????????? new MapPoint()???  ????????????. ??????????????? ???????????? ????????? ???????????? ???????????????
                                                                                                                        MapPoint mapPoint = MapPoint.mapPointWithGeoCoord(x, y);
                                                                                                                        marker.setMapPoint(mapPoint);
                                                                                                                        marker.setMarkerType(MapPOIItem.MarkerType.CustomImage); // ??????????????? ????????? ????????? ??????.
                                                                                                                        marker.setCustomImageResourceId(R.drawable.ic_pharmacy_marker); // ?????? ?????????.
                                                                                                                        marker.setCustomImageAutoscale(false); // hdpi, xhdpi ??? ??????????????? ???????????? ???????????? ????????? ?????? ?????? ?????????????????? ????????? ????????? ??????.
                                                                                                                        marker.setCustomImageAnchor(0.5f, 1.0f);
                                                                                                                        mMapView.addPOIItem(marker);
                                                                                                                        //??????????????? fab ?????? ?????????
                                                                                                                        mLoaderLayout.setVisibility(View.GONE);
                                                                                                                        searchDetailFab.setVisibility(View.VISIBLE);
                                                                                                                    }
                                                                                                                    for (Document document : cafeList) {
                                                                                                                        MapPOIItem marker = new MapPOIItem();
                                                                                                                        marker.setItemName(document.getPlaceName());
                                                                                                                        marker.setTag(tagNum++);
                                                                                                                        double x = Double.parseDouble(document.getY());
                                                                                                                        double y = Double.parseDouble(document.getX());
                                                                                                                        //??????????????? ????????? new MapPoint()???  ????????????. ??????????????? ???????????? ????????? ???????????? ???????????????
                                                                                                                        MapPoint mapPoint = MapPoint.mapPointWithGeoCoord(x, y);
                                                                                                                        marker.setMapPoint(mapPoint);
                                                                                                                        marker.setMarkerType(MapPOIItem.MarkerType.CustomImage); // ??????????????? ????????? ????????? ??????.
                                                                                                                        marker.setCustomImageResourceId(R.drawable.ic_cafe_marker); // ?????? ?????????.
                                                                                                                        marker.setCustomImageAutoscale(false); // hdpi, xhdpi ??? ??????????????? ???????????? ???????????? ????????? ?????? ?????? ?????????????????? ????????? ????????? ??????.
                                                                                                                        marker.setCustomImageAnchor(0.5f, 1.0f);
                                                                                                                        mMapView.addPOIItem(marker);
                                                                                                                        //??????????????? fab ?????? ?????????
                                                                                                                        mLoaderLayout.setVisibility(View.GONE);
                                                                                                                        searchDetailFab.setVisibility(View.VISIBLE);
                                                                                                                    }
                                                                                                                }
                                                                                                            }

                                                                                                            @Override
                                                                                                            public void onFailure(@NotNull Call<CategoryResult> call, @NotNull Throwable t) {

                                                                                                            }
                                                                                                        });
                                                                                                    }
                                                                                                }

                                                                                                @Override
                                                                                                public void onFailure(@NotNull Call<CategoryResult> call, Throwable t) {

                                                                                                }
                                                                                            });
                                                                                        }
                                                                                    }

                                                                                    @Override
                                                                                    public void onFailure(@NotNull Call<CategoryResult> call, @NotNull Throwable t) {

                                                                                    }
                                                                                });
                                                                            }
                                                                        }

                                                                        @Override
                                                                        public void onFailure(@NotNull Call<CategoryResult> call, @NotNull Throwable t) {

                                                                        }
                                                                    });
                                                                }
                                                            }

                                                            @Override
                                                            public void onFailure(@NotNull Call<CategoryResult> call, @NotNull Throwable t) {

                                                            }
                                                        });
                                                    }
                                                }

                                                @Override
                                                public void onFailure(@NotNull Call<CategoryResult> call, @NotNull Throwable t) {

                                                }
                                            });
                                        }
                                    }

                                    @Override
                                    public void onFailure(@NotNull Call<CategoryResult> call, @NotNull Throwable t) {

                                    }
                                });
                            }
                        }

                        @Override
                        public void onFailure(@NotNull Call<CategoryResult> call, @NotNull Throwable t) {

                        }
                    });
                }
            }

            @Override
            public void onFailure(@NotNull Call<CategoryResult> call, @NotNull Throwable t) {
                Log.d(TAG, "FAIL");
            }
        });
    }

    public void anim() {
        if (isFabOpen) {
            fab1.startAnimation(fab_close);
            fab2.startAnimation(fab_close);
            fab3.startAnimation(fab_close);
            fab1.setClickable(false);
            fab2.setClickable(false);
            fab3.setClickable(false);
            isFabOpen = false;
        } else {
            fab1.startAnimation(fab_open);
            fab2.startAnimation(fab_open);
            fab3.startAnimation(fab_open);
            fab1.setClickable(true);
            fab2.setClickable(true);
            fab3.setClickable(true);
            isFabOpen = true;
        }
    }


    @Override
    public void onMapViewInitialized(MapView mapView) {
    }

    @Override
    public void onMapViewCenterPointMoved(MapView mapView, MapPoint mapPoint) {
    }

    @Override
    public void onMapViewZoomLevelChanged(MapView mapView, int i) {
    }

    //??? ?????? ????????? ??????
    @Override
    public void onMapViewSingleTapped(MapView mapView, MapPoint mapPoint) {
        //???????????????????????? ??????????????? ????????? ???????????????
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void onMapViewDoubleTapped(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewLongPressed(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewDragStarted(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewDragEnded(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewMoveFinished(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onDaumMapOpenAPIKeyAuthenticationResult(MapView mapView, int i, String s) {

    }

    @Override
    public void onPOIItemSelected(MapView mapView, MapPOIItem mapPOIItem) {

    }

    // ????????? ???????????? ??????( ?????????????????? ?????? ?????? ?????????????????? ????????? ??????)
    public void showMap(Uri geoLocation) {
        Intent intent;
        try {
            FancyToast.makeText(getApplicationContext(), "Try to find a way with Kakao Map.", FancyToast.LENGTH_SHORT, FancyToast.INFO, true).show();
            intent = new Intent(Intent.ACTION_VIEW, geoLocation);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } catch (Exception e) {
            FancyToast.makeText(getApplicationContext(), "Kakao Map is required to find directions. Please download it..", FancyToast.LENGTH_SHORT, FancyToast.INFO, true).show();
            intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://play.google.com/store/apps/details?id=net.daum.android.map&hl=ko"));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }

    @Override
    public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem) {
    }

    //?????????(POLLITEM) ????????? ??????
    @Override
    public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem, MapPOIItem.CalloutBalloonButtonType calloutBalloonButtonType) {
        double lat = mapPOIItem.getMapPoint().getMapPointGeoCoord().latitude;
        double lng = mapPOIItem.getMapPoint().getMapPointGeoCoord().longitude;
        Toast.makeText(this, mapPOIItem.getItemName(), Toast.LENGTH_SHORT).show();
        CFAlertDialog.Builder builder = new CFAlertDialog.Builder(this);
        builder.setDialogStyle(CFAlertDialog.CFAlertStyle.ALERT);
        builder.setTitle("Please choose.");
        builder.setSingleChoiceItems(new String[]{"location information", "Finding a way"}, 2, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int index) {
                if (index == 0) {
                    mLoaderLayout.setVisibility(View.VISIBLE);
                    ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
                    Call<CategoryResult> call = apiInterface.getSearchLocationDetail(getString(R.string.restapi_key), mapPOIItem.getItemName(), String.valueOf(lat), String.valueOf(lng), 1);
                    call.enqueue(new Callback<CategoryResult>() {
                        @Override
                        public void onResponse(@NotNull Call<CategoryResult> call, @NotNull Response<CategoryResult> response) {
                            mLoaderLayout.setVisibility(View.GONE);
                            if (response.isSuccessful()) {
                                Intent intent = new Intent(MapActivity.this, PlaceDetailActivity.class);
                                assert response.body() != null;
                                intent.putExtra(IntentKey.PLACE_SEARCH_DETAIL_EXTRA, response.body().getDocuments().get(0));
                                startActivity(intent);
                            }
                        }

                        @Override
                        public void onFailure(Call<CategoryResult> call, Throwable t) {
                            FancyToast.makeText(getApplicationContext(), "There is no detailed information about the location..", FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();
                            mLoaderLayout.setVisibility(View.GONE);
                            Intent intent = new Intent(MapActivity.this, PlaceDetailActivity.class);
                            startActivity(intent);
                        }
                    });
                } else if (index == 1) {
                    showMap(Uri.parse("daummaps://route?sp=" + mCurrentLat + "," + mCurrentLng + "&ep=" + lat + "," + lng + "&by=FOOT"));
                }
            }
        });
        builder.addButton("??????", -1, -1, CFAlertDialog.CFAlertActionStyle.POSITIVE, CFAlertDialog.CFAlertActionAlignment.END, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();

    }

    // ?????? ?????????????????? ??????
    @Override
    public void onDraggablePOIItemMoved(MapView mapView, MapPOIItem mapPOIItem, MapPoint mapPoint) {
        MapPoint.GeoCoordinate mapPointGeo = mapPoint.getMapPointGeoCoord();
        mSearchName = "dragged place";
        mSearchLng = mapPointGeo.longitude;
        mSearchLat = mapPointGeo.latitude;
        mMapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(mSearchLat, mSearchLng), true);
        searchMarker.setItemName(mSearchName);
        MapPoint mapPoint2 = MapPoint.mapPointWithGeoCoord(mSearchLat, mSearchLng);
        searchMarker.setMapPoint(mapPoint2);
        searchMarker.setMarkerType(MapPOIItem.MarkerType.BluePin); // ???????????? ???????????? BluePin ?????? ??????.
        searchMarker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // ????????? ???????????????, ???????????? ???????????? RedPin ?????? ??????.
        searchMarker.setDraggable(true);
        mMapView.addPOIItem(searchMarker);
    }

    /*
     *  ?????? ?????? ????????????(setCurrentLocationEventListener)
     */
    @Override
    public void onCurrentLocationUpdate(MapView mapView, MapPoint mapPoint, float accuracyInMeters) {
        MapPoint.GeoCoordinate mapPointGeo = mapPoint.getMapPointGeoCoord();
        Log.i(TAG, String.format("MapView onCurrentLocationUpdate (%f,%f) accuracy (%f)", mapPointGeo.latitude, mapPointGeo.longitude, accuracyInMeters));
        currentMapPoint = MapPoint.mapPointWithGeoCoord(mapPointGeo.latitude, mapPointGeo.longitude);
        //??? ????????? ?????? ?????? ??????
        mMapView.setMapCenterPoint(currentMapPoint, true);
        //??????????????? ?????? ?????? ??????
        mCurrentLat = mapPointGeo.latitude;
        mCurrentLng = mapPointGeo.longitude;
        Log.d(TAG, "Current location => " + mCurrentLat + "  " + mCurrentLng);
        mLoaderLayout.setVisibility(View.GONE);
        //????????? ????????? ?????? ?????? ???????????? ??????????????? ??????, ????????? ?????? ?????????????????? ???????????? ??????????????? ?????? ??????
        if (!isTrackingMode) {
            mMapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOff);
        }
    }

    @Override
    public void onCurrentLocationDeviceHeadingUpdate(MapView mapView, float v) {

    }

    @Override
    public void onCurrentLocationUpdateFailed(MapView mapView) {
        Log.i(TAG, "onCurrentLocationUpdateFailed");
        mMapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading);
    }

    @Override
    public void onCurrentLocationUpdateCancelled(MapView mapView) {
        Log.i(TAG, "onCurrentLocationUpdateCancelled");
        mMapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading);
    }

    @Subscribe //???????????? ????????? ????????? ????????????
    public void search(Document document) {//public?????? ???????????????
        FancyToast.makeText(getApplicationContext(), document.getPlaceName() + " Search", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, true).show();
        mSearchName = document.getPlaceName();
        mSearchLng = Double.parseDouble(document.getX());
        mSearchLat = Double.parseDouble(document.getY());
        mMapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(mSearchLat, mSearchLng), true);
        mMapView.removePOIItem(searchMarker);
        searchMarker.setItemName(mSearchName);
        searchMarker.setTag(10000);
        MapPoint mapPoint = MapPoint.mapPointWithGeoCoord(mSearchLat, mSearchLng);
        searchMarker.setMapPoint(mapPoint);
        searchMarker.setMarkerType(MapPOIItem.MarkerType.BluePin); // ???????????? ???????????? BluePin ?????? ??????.
        searchMarker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // ????????? ???????????????, ???????????? ???????????? RedPin ?????? ??????.
        //?????? ????????? ???????????? ??????
        searchMarker.setDraggable(true);
        mMapView.addPOIItem(searchMarker);
    }


    @Override
    public void finish() {
        super.finish();
        bus.unregister(this); //??????????????? ????????? ????????? ????????????
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOff);
        mMapView.setShowCurrentLocationMarker(false);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
