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
    private AdapterView.OnItemClickListener onItemClickListener;
    public interface OnItemClickListener {
        void onItemClicked(int i);
    }

//    class C04251 implements OnClickListener {
//        C04251() {
//        }
//
//        public void onClick(View v) {
//            HomeFragment.this.onItemClickListener.onItemClicked(2);
//        }
//    }
//
//    /* renamed from: com.ferdous.barisaltourism.HomeFragment.2 */
//    class C04262 implements OnClickListener {
//        C04262() {
//        }
//
//        public void onClick(View v) {
//            HomeFragment.this.onItemClickListener.onItemClicked(1);
//        }
//    }
//
//    /* renamed from: com.ferdous.barisaltourism.HomeFragment.3 */
//    class C04273 implements OnClickListener {
//        C04273() {
//        }
//
//        public void onClick(View v) {
//            HomeFragment.this.onItemClickListener.onItemClicked(3);
//        }
//    }
//
//    /* renamed from: com.ferdous.barisaltourism.HomeFragment.4 */
//    class C04284 implements OnClickListener {
//        C04284() {
//        }
//
//        public void onClick(View v) {
//            if (HomeFragment.this.isLocationEnabled()) {
//                HomeFragment.this.onItemClickListener.onItemClicked(4);
//            } else {
//                HomeFragment.this.showAlert();
//            }
//        }
//    }
//
//    /* renamed from: com.ferdous.barisaltourism.HomeFragment.5 */
//    class C04295 implements OnClickListener {
//        C04295() {
//        }
//
//        public void onClick(View v) {
//            HomeFragment.this.onItemClickListener.onItemClicked(5);
//        }
//    }
//
//    /* renamed from: com.ferdous.barisaltourism.HomeFragment.6 */
//    class C04306 implements OnClickListener {
//        C04306() {
//        }
//
//        public void onClick(View v) {
//            HomeFragment.this.onItemClickListener.onItemClicked(6);
//        }
//    }
//
//    /* renamed from: com.ferdous.barisaltourism.HomeFragment.7 */
//    class C04317 implements OnClickListener {
//        C04317() {
//        }
//
//        public void onClick(View v) {
//            HomeFragment.this.onItemClickListener.onItemClicked(7);
//        }
//    }
//
//    /* renamed from: com.ferdous.barisaltourism.HomeFragment.8 */
//    class C04328 implements OnClickListener {
//        C04328() {
//        }
//
//        public void onClick(View v) {
//            HomeFragment.this.onItemClickListener.onItemClicked(8);
//        }
//    }
//
//    /* renamed from: com.ferdous.barisaltourism.HomeFragment.9 */
//    class C04339 implements OnClickListener {
//        C04339() {
//        }
//
//        public void onClick(View v) {
//            HomeFragment.this.onItemClickListener.onItemClicked(9);
//        }
//    }
//
//    public interface OnItemClickListener {
//        void onItemClicked(int i);
//    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

//        GridView gridView = (GridView) findViewById(R.id.gridview);
//        final BooksAdapter booksAdapter = new BooksAdapter(this, bookClasses);
//        gridView.setAdapter(booksAdapter);
//
//        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Book_class bookClass = bookClasses[position];
//                bookClass.toggleFavorite();
//                booksAdapter.notifyDataSetChanged();
//            }
//        });


//       this.layoutTouristSpots = (RelativeLayout) view.findViewById(R.id.layout_tourist_spots);
//        this.layoutFeatured = (RelativeLayout) view.findViewById(R.id.layout_featured);
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
//        this.mSliderLayout.setCustomIndicator((PagerIndicator) view.findViewById(R.id.custom_indicator));
//        this.layoutTouristSpots.setOnClickListener(new C04251());
//        this.layoutFeatured.setOnClickListener(new C04262());
//        this.layoutDistrictWise.setOnClickListener(new C04273());
//        this.layoutNearby.setOnClickListener(new C04284());
//        this.layoutTransportation.setOnClickListener(new C04295());
//        this.layoutHotels.setOnClickListener(new C04306());
//        this.layoutRestaurants.setOnClickListener(new C04317());
//        this.layoutShopping.setOnClickListener(new C04328());
//        this.layoutEntertainment.setOnClickListener(new C04339());
//        this.layoutAtms.setOnClickListener(new OnClickListener() {
//            public void onClick(View v) {
//                HomeFragment.this.onItemClickListener.onItemClicked(10);
//            }
//        });
//        this.layoutPrayer.setOnClickListener(new OnClickListener() {
//            public void onClick(View v) {
//                HomeFragment.this.onItemClickListener.onItemClicked(11);
//            }
//        });
//        this.layoutHospitals.setOnClickListener(new OnClickListener() {
//            public void onClick(View v) {
//                HomeFragment.this.onItemClickListener.onItemClicked(12);
//            }
//        });
//        this.layoutPoliceStation.setOnClickListener(new OnClickListener() {
//            public void onClick(View v) {
//                HomeFragment.this.onItemClickListener.onItemClicked(13);
//            }
//        });
        return view;
    }

    public void onStop() {
        this.mSliderLayout.stopAutoCycle();
        super.onStop();
    }

//    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
//        this.onItemClickListener = onItemClickListener;
//    }

//    private boolean isLocationEnabled() {
//        FragmentActivity activity = getActivity();
//        getActivity();
//        LocationManager locationManager = (LocationManager) activity.getSystemService("location");
//        return locationManager.isProviderEnabled("gps") || locationManager.isProviderEnabled("network");
//    }
//
//    private void showAlert() {
//        NotificationCompat.Action.Builder dialog = new NotificationCompat.Action.Builder(getActivity());
//        CharSequence strTitle = getResources().getString(R.string.dlg_header_title_enable_location);
//        CharSequence strContent = getResources().getString(R.string.dlg_content_enable_location);
//        CharSequence strButtonYes = getResources().getString(R.string.dlg_button_yes);
//        dialog.setTitle(strTitle).setMessage(strContent).setPositiveButton(strButtonYes, new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface paramDialogInterface, int paramInt) {
//                HomeFragment.this.startActivity(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"));
//            }
//        }).setNegativeButton(getResources().getString(R.string.dlg_button_no), new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface paramDialogInterface, int paramInt) {
//            }
//        });
//        dialog.show();
//    }
//
//    public void loadSettings() {
//        this.mSettings = PreferenceManager.getDefaultSharedPreferences(getActivity());
//        this.language = this.mSettings.getString(SettingsFragment.KEY_PREF_LANGUAGE, SettingsFragment.LANGUAGE_ENGLISH);
//    }






}
