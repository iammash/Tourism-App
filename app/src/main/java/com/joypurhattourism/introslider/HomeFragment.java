package com.joypurhattourism.introslider;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.LocationManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.support.v7.app.AlertDialog.Builder;
import android.widget.RelativeLayout;
import android.view.View.OnClickListener;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;




import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;


/**
 * Created by mdsami on 1/20/2017.
 */

public class HomeFragment extends Fragment {





    RelativeLayout layoutAtms;
    RelativeLayout layoutDistrictWise;
    RelativeLayout layoutEntertainment;
    RelativeLayout layoutFeatured;
    RelativeLayout layoutHospitals;
    RelativeLayout layoutHotels;
    RelativeLayout layoutNearby;
    RelativeLayout layoutPoliceStation;
    RelativeLayout layoutPrayer;
    RelativeLayout layoutRestaurants;
    RelativeLayout layoutShopping;
    RelativeLayout layoutTouristSpots;
    RelativeLayout layoutTransportation;
    SharedPreferences mSettings;
    SliderLayout mSliderLayout;
    private OnItemClickListener onItemClickListener;
    //private AdapterView.OnItemClickListener onItemClickListener;
    public interface OnItemClickListener {
        void onItemClicked(int i);
    }



    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);





          this.layoutFeatured = (RelativeLayout) view.findViewById(R.id.layout_featured);
          this.layoutTouristSpots = (RelativeLayout) view.findViewById(R.id.layout_tourist_spots);
//        this.layoutDistrictWise = (RelativeLayout) view.findViewById(R.id.layout_district_wise);
//        this.layoutNearby = (RelativeLayout) view.findViewById(R.id.layout_near_by);
//        this.layoutTransportation = (RelativeLayout) view.findViewById(R.id.layout_transportation);
//        this.layoutHotels = (RelativeLayout) view.findViewById(R.id.layout_hotels);
//        this.layoutRestaurants = (RelativeLayout) view.findViewById(R.id.layout_restaurants);
//        this.layoutShopping = (RelativeLayout) view.findViewById(R.id.layout_shopping);
//        this.layoutEntertainment = (RelativeLayout) view.findViewById(R.id.layout_entertainment);
//        this.layoutAtms = (RelativeLayout) view.findViewById(R.id.layout_atms);
//        this.layoutPrayer = (RelativeLayout) view.findViewById(R.id.layout_prayer);
//        this.layoutHospitals = (RelativeLayout) view.findViewById(R.id.layout_hospitals);
//        this.layoutPoliceStation = (RelativeLayout) view.findViewById(R.id.layout_police_stations);
       this.mSliderLayout = (SliderLayout) view.findViewById(R.id.slider);
//        DefaultSliderView slide0 = new DefaultSliderView(getActivity());
//        slide0.image((int) R.drawable.slide_0);
        DefaultSliderView slide1 = new DefaultSliderView(getActivity());
        slide1.image((int) R.drawable.slide_9);
        DefaultSliderView slide2 = new DefaultSliderView(getActivity());
        slide2.image((int) R.drawable.slide_2);
       DefaultSliderView slide3 = new DefaultSliderView(getActivity());
        slide3.image((int) R.drawable.slide_3);
        DefaultSliderView slide4 = new DefaultSliderView(getActivity());
        slide4.image((int) R.drawable.slide_5);
       DefaultSliderView slide5 = new DefaultSliderView(getActivity());
       slide5.image((int) R.drawable.slide_6);
       DefaultSliderView slide6 = new DefaultSliderView(getActivity());
        slide6.image((int) R.drawable.slide_4);
       DefaultSliderView slide7 = new DefaultSliderView(getActivity());
        slide7.image((int) R.drawable.slide_8);
        DefaultSliderView slide8 = new DefaultSliderView(getActivity());
        slide8.image((int) R.drawable.slide_7);
        DefaultSliderView slide9 = new DefaultSliderView(getActivity());
        slide9.image((int) R.drawable.slide_1);
//        this.mSliderLayout.addSlider(slide0);
        this.mSliderLayout.addSlider(slide1);
        this.mSliderLayout.addSlider(slide2);
        this.mSliderLayout.addSlider(slide3);
        this.mSliderLayout.addSlider(slide4);
        this.mSliderLayout.addSlider(slide5);
        this.mSliderLayout.addSlider(slide6);
        this.mSliderLayout.addSlider(slide7);
        this.mSliderLayout.addSlider(slide8);
        this.mSliderLayout.addSlider(slide9);
        this.mSliderLayout.setCustomIndicator((PagerIndicator) view.findViewById(R.id.custom_indicator));
        this.layoutFeatured.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                HomeFragment.this.onItemClickListener.onItemClicked(1);
            }
        });

        return view;
    }

    public void onStop() {
        this.mSliderLayout.stopAutoCycle();
        super.onStop();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }






    private boolean isLocationEnabled() {
        FragmentActivity activity = getActivity();
        getActivity();
        LocationManager locationManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled("gps") || locationManager.isProviderEnabled("network");
    }


    private void showAlert() {
        Builder dialog = new Builder(getActivity());
        String strTitle = getResources().getString(R.string.dlg_header_title_enable_location);
        String strContent = getResources().getString(R.string.dlg_content_enable_location);
        String strButtonYes = getResources().getString(R.string.dlg_button_yes);
        dialog.setTitle(strTitle).setMessage(strContent).setPositiveButton(strButtonYes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                HomeFragment.this.startActivity(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"));
            }
        }).setNegativeButton(getResources().getString(R.string.dlg_button_no), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface paramDialogInterface, int paramInt) {
            }
        });
        dialog.show();
    }
//
//    public void loadSettings() {
//        this.mSettings = PreferenceManager.getDefaultSharedPreferences(getActivity());
//        this.language = this.mSettings.getString(SettingsFragment.KEY_PREF_LANGUAGE, SettingsFragment.LANGUAGE_ENGLISH);
//    }






}
