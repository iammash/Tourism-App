package com.joypurhattourism.introslider;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Toast;
import com.joypurhattourism.introslider.HomeFragment.OnItemClickListener;
import com.joypurhattourism.introslider.app.Config;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements OnNavigationItemSelectedListener {
    public static final int NAVIGATION_ITEM_ATMS = 10;
    public static final int NAVIGATION_ITEM_DASHBOARD = 0;
    public static final int NAVIGATION_ITEM_DISTRICT_WISE = 3;
    public static final int NAVIGATION_ITEM_ENTERTAINMENT = 9;
    public static final int NAVIGATION_ITEM_FAVORITE = 22;
    public static final int NAVIGATION_ITEM_FEATURED = 1;
    public static final int NAVIGATION_ITEM_HOSPITALS = 12;
    public static final int NAVIGATION_ITEM_HOTELS = 6;
    public static final int NAVIGATION_ITEM_NEAR_BY = 4;
    public static final int NAVIGATION_ITEM_POLICE_STATIONS = 13;
    public static final int NAVIGATION_ITEM_RELIGION = 11;
    public static final int NAVIGATION_ITEM_RESTAURANTS = 7;
    public static final int NAVIGATION_ITEM_SHOPPING = 8;
    public static final int NAVIGATION_ITEM_TOURIST_SPOTS = 2;
    public static final int NAVIGATION_ITEM_TRANSPORTATION = 5;
    public static final int NAVIGATION_ITEM_VISITED = 42;
    private static final int PERMISSION_REQUEST_CODE = 1001;
    private static final String TAG = MainActivity.class.getSimpleName();
    Activity activity;
    boolean isDoubleBackToExitPressedOnce = false;
    private String language;
    ActionBar mActionBar;
    ActionBarDrawerToggle mActionBarDrawerToggle;
    Context mContext;
    private int mCurrentItem;
    DrawerLayout mDrawerLayout;
    FloatingActionButton mFab;
    FragmentTransaction mFragmentTransition;
    private double mLatitude = Config.LATITUDE_BARISAL;
    private Locale mLocale;
    private double mLongitude = Config.LONGITUDE_BARISAL;
    NavigationView mNavigationView;
    private SharedPreferences mSettings;
    TabLayout mTabLayout;
    Toolbar mToolBar;
    private View view;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.mContext = getApplicationContext();
        this.activity = this;
        loadSettings();
        Window window = getWindow();
        if (VERSION.SDK_INT >= 21) {
            window.addFlags(Integer.MIN_VALUE);
            window.clearFlags(67108864);
            window.setStatusBarColor(this.mContext.getResources().getColor(R.color.colorPrimary));
        }
        this.mToolBar = (Toolbar) findViewById(R.id.toolbar);
        if (this.mToolBar != null) {
            setSupportActionBar(this.mToolBar);
            this.mActionBar = getSupportActionBar();
            this.mActionBar.setDisplayHomeAsUpEnabled(true);
        }
        this.view = (CoordinatorLayout) findViewById(R.id.container);
        this.mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        this.mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        this.mTabLayout.setTabGravity(NAVIGATION_ITEM_DASHBOARD);
        this.mActionBarDrawerToggle = new ActionBarDrawerToggle(this, this.mDrawerLayout, this.mToolBar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        this.mDrawerLayout.setDrawerListener(this.mActionBarDrawerToggle);
        this.mActionBarDrawerToggle.syncState();
        this.mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        this.mNavigationView.setNavigationItemSelectedListener(this);
//        this.mFab = (FloatingActionButton) findViewById(R.id.fab_map);
//        this.mFab.setOnClickListener(new OnClickListener() {
//            public void onClick(View v) {
//                if (MainActivity.this.checkPermission()) {
//                    MainActivity.this.showMap();
//                } else {
//                    MainActivity.this.requestPermission();
//                }
//            }
//        });
        if (savedInstanceState != null) {
            this.mCurrentItem = savedInstanceState.getInt("SAVE_INSTANCE_STATE_NAV_DRAWER_ACTIVE_MENU");
            displayViewWithFragment(this.mCurrentItem);
            return;
        }
        this.mCurrentItem = NAVIGATION_ITEM_DASHBOARD;
        displayViewWithFragment(this.mCurrentItem);
    }

    protected void onResume() {
        super.onResume();
        if (isLanguageChanged()) {
            loadLocal(this.language);
            recreate();
        }
    }

    protected void onStop() {
        super.onStop();
    }

    public void onBackPressed() {
        if (this.mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.mDrawerLayout.closeDrawer(GravityCompat.END);
        } else if (this.mCurrentItem != 0) {
            this.mCurrentItem = NAVIGATION_ITEM_DASHBOARD;
            displayViewWithFragment(this.mCurrentItem);
        } else if (this.isDoubleBackToExitPressedOnce) {
            super.onBackPressed();
        } else {
            this.isDoubleBackToExitPressedOnce = true;
            Toast.makeText(this, getResources().getString(R.string.lbl_back_press), NAVIGATION_ITEM_DASHBOARD).show();
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    MainActivity.this.isDoubleBackToExitPressedOnce = false;
                }
            }, 2000);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_tourist_spots /*2131624239*/:
                this.mCurrentItem = NAVIGATION_ITEM_TOURIST_SPOTS;
                displayViewWithFragment(this.mCurrentItem);
                break;
            case R.id.nav_transportation /*2131624240*/:
                this.mCurrentItem = NAVIGATION_ITEM_TRANSPORTATION;
                displayViewWithFragment(this.mCurrentItem);
                break;
            case R.id.nav_hotels /*2131624241*/:
                this.mCurrentItem = NAVIGATION_ITEM_HOTELS;
                displayViewWithFragment(this.mCurrentItem);
                break;
            case R.id.nav_restaurants /*2131624242*/:
                this.mCurrentItem = NAVIGATION_ITEM_RESTAURANTS;
                displayViewWithFragment(this.mCurrentItem);
                break;
            case R.id.nav_shopping /*2131624243*/:
                this.mCurrentItem = NAVIGATION_ITEM_SHOPPING;
                displayViewWithFragment(this.mCurrentItem);
                break;
            case R.id.nav_entertainment /*2131624244*/:
                this.mCurrentItem = NAVIGATION_ITEM_ENTERTAINMENT;
                displayViewWithFragment(this.mCurrentItem);
                break;
            case R.id.nav_atms /*2131624245*/:
                this.mCurrentItem = NAVIGATION_ITEM_ATMS;
                displayViewWithFragment(this.mCurrentItem);
                break;
            case R.id.nav_religion /*2131624246*/:
                this.mCurrentItem = NAVIGATION_ITEM_RELIGION;
                displayViewWithFragment(this.mCurrentItem);
                break;
            case R.id.nav_hospitals /*2131624247*/:
                this.mCurrentItem = NAVIGATION_ITEM_HOSPITALS;
                displayViewWithFragment(this.mCurrentItem);
                break;
            case R.id.nav_police_stations /*2131624248*/:
                this.mCurrentItem = NAVIGATION_ITEM_POLICE_STATIONS;
                displayViewWithFragment(this.mCurrentItem);
                break;
            case R.id.nav_dashboard /*2131624250*/:
                this.mCurrentItem = NAVIGATION_ITEM_DASHBOARD;
                displayViewWithFragment(this.mCurrentItem);
                break;
            case R.id.nav_featured /*2131624252*/:
                this.mCurrentItem = NAVIGATION_ITEM_FEATURED;
                displayViewWithFragment(this.mCurrentItem);
                break;
//            case R.id.nav_districts /*2131624253*/:
//                this.mCurrentItem = NAVIGATION_ITEM_DISTRICT_WISE;
//                displayViewWithFragment(this.mCurrentItem);
//                break;
            case R.id.nav_nearby /*2131624254*/:
                if (isLocationEnabled()) {
                    this.mCurrentItem = NAVIGATION_ITEM_NEAR_BY;
                    displayViewWithFragment(this.mCurrentItem);
                    break;
                }
                showAlert();
                return false;
            case R.id.nav_settings /*2131624257*/:
                goToSettings();
                break;
            case R.id.nav_rate_us /*2131624258*/:
                rateUs();
                break;
            case R.id.nav_help_feedback /*2131624259*/:
                sendEmail();
                break;
            case R.id.nav_about /*2131624260*/:
                startActivity(new Intent(this, AboutActivity.class));
                break;
        }
        if (this.mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.mDrawerLayout.closeDrawer(GravityCompat.END);
        }
        return true;
    }

    public void displayViewWithFragment(int item) {
        this.mFab.setVisibility(NAVIGATION_ITEM_DASHBOARD);
        this.mTabLayout.setVisibility(NAVIGATION_ITEM_SHOPPING);
        switch (item) {
            case NAVIGATION_ITEM_DASHBOARD /*0*/:
                this.mFab.setVisibility(NAVIGATION_ITEM_SHOPPING);
                HomeFragment fragmentHome = new HomeFragment();
                this.mFragmentTransition = getSupportFragmentManager().beginTransaction();
                this.mFragmentTransition.replace(R.id.frame_layout, fragmentHome);
                this.mFragmentTransition.commit();
                fragmentHome.setOnItemClickListener(new OnItemClickListener() {
                    public void onItemClicked(int position) {
                        MainActivity.this.mCurrentItem = position;
                        MainActivity.this.displayViewWithFragment(MainActivity.this.mCurrentItem);
                    }
                });
                this.mActionBar.setTitle(R.string.lbl_navigation_item_dashboard);
                this.mNavigationView.setCheckedItem(R.id.nav_dashboard);
                return;
            case NAVIGATION_ITEM_FEATURED /*1*/:
                FragmentFeaturedPlaces fragmentFeaturedPlaces = FragmentFeaturedPlaces.newInstance(this.mLatitude, this.mLongitude);
                this.mFragmentTransition = getSupportFragmentManager().beginTransaction();
                this.mFragmentTransition.replace(R.id.frame_layout, fragmentFeaturedPlaces);
                this.mFragmentTransition.commit();
                this.mActionBar.setTitle(R.string.lbl_navigation_item_featured);
                this.mNavigationView.setCheckedItem(R.id.nav_featured);
                return;
            case NAVIGATION_ITEM_TOURIST_SPOTS /*2*/:
                FragmentTouristSpots fragmentTouristSpots = FragmentTouristSpots.newInstance(this.mLatitude, this.mLongitude);
                this.mFragmentTransition = getSupportFragmentManager().beginTransaction();
                this.mFragmentTransition.replace(R.id.frame_layout, fragmentTouristSpots);
                this.mFragmentTransition.commit();
                this.mActionBar.setTitle(R.string.lbl_navigation_item_tourist_spots);
                this.mNavigationView.setCheckedItem(R.id.nav_tourist_spots);
                return;
//            case NAVIGATION_ITEM_DISTRICT_WISE /*3*/:
//                this.mTabLayout.setVisibility(NAVIGATION_ITEM_DASHBOARD);
//                final FragmentDistrictsTabs fragmentDistrictsTabs = new FragmentDistrictsTabs();
//                this.mFragmentTransition = getSupportFragmentManager().beginTransaction();
//                this.mFragmentTransition.replace(R.id.frame_layout, fragmentDistrictsTabs);
//                this.mFragmentTransition.commitAllowingStateLoss();
//                this.mTabLayout.post(new Runnable() {
//                    public void run() {
//                        MainActivity.this.mTabLayout.setupWithViewPager(fragmentDistrictsTabs.mViewPager);
//                    }
//                });
//                //this.mActionBar.setTitle(R.string.lbl_navigation_item_districts);
//                //this.mNavigationView.setCheckedItem(R.id.nav_districts);
//                return;
            case NAVIGATION_ITEM_NEAR_BY /*4*/:
                this.mTabLayout.setVisibility(NAVIGATION_ITEM_DASHBOARD);
                final FragmentNearbyTabs fragmentNearByTabs = new FragmentNearbyTabs();
                this.mFragmentTransition = getSupportFragmentManager().beginTransaction();
                this.mFragmentTransition.replace(R.id.frame_layout, fragmentNearByTabs);
                this.mFragmentTransition.commitAllowingStateLoss();
                this.mTabLayout.post(new Runnable() {
                    public void run() {
                        MainActivity.this.mTabLayout.setupWithViewPager(fragmentNearByTabs.mViewPager);
                    }
                });
                this.mActionBar.setTitle(R.string.lbl_navigation_item_nearby);
                this.mNavigationView.setCheckedItem(R.id.nav_nearby);
                return;
            case NAVIGATION_ITEM_TRANSPORTATION /*5*/:
                FragmentTransportation fragmentTransportation = FragmentTransportation.newInstance(this.mLatitude, this.mLongitude);
                this.mFragmentTransition = getSupportFragmentManager().beginTransaction();
                this.mFragmentTransition.replace(R.id.frame_layout, fragmentTransportation);
                this.mFragmentTransition.commit();
                this.mActionBar.setTitle(R.string.lbl_navigation_item_transportation);
                this.mNavigationView.setCheckedItem(R.id.nav_transportation);
                return;
            case NAVIGATION_ITEM_HOTELS /*6*/:
                FragmentHotels fragmentHotels = FragmentHotels.newInstance(this.mLatitude, this.mLongitude);
                this.mFragmentTransition = getSupportFragmentManager().beginTransaction();
                this.mFragmentTransition.replace(R.id.frame_layout, fragmentHotels);
                this.mFragmentTransition.commit();
                this.mActionBar.setTitle(R.string.lbl_navigation_item_hotels);
                this.mNavigationView.setCheckedItem(R.id.nav_hotels);
                return;
            case NAVIGATION_ITEM_RESTAURANTS /*7*/:
                FragmentRestaurants fragmentRestaurants = FragmentRestaurants.newInstance(this.mLatitude, this.mLongitude);
                this.mFragmentTransition = getSupportFragmentManager().beginTransaction();
                this.mFragmentTransition.replace(R.id.frame_layout, fragmentRestaurants);
                this.mFragmentTransition.commit();
                this.mActionBar.setTitle(R.string.lbl_navigation_item_restaurants);
                this.mNavigationView.setCheckedItem(R.id.nav_restaurants);
                return;
            case NAVIGATION_ITEM_SHOPPING /*8*/:
                FragmentShopping fragmentShopping = FragmentShopping.newInstance(this.mLatitude, this.mLongitude);
                this.mFragmentTransition = getSupportFragmentManager().beginTransaction();
                this.mFragmentTransition.replace(R.id.frame_layout, fragmentShopping);
                this.mFragmentTransition.commit();
                this.mActionBar.setTitle(R.string.lbl_navigation_item_shopping);
                this.mNavigationView.setCheckedItem(R.id.nav_shopping);
                return;
            case NAVIGATION_ITEM_ENTERTAINMENT /*9*/:
                FragmentEntertainment fragmentEntertainment = FragmentEntertainment.newInstance(this.mLatitude, this.mLongitude);
                this.mFragmentTransition = getSupportFragmentManager().beginTransaction();
                this.mFragmentTransition.replace(R.id.frame_layout, fragmentEntertainment);
                this.mFragmentTransition.commit();
                this.mActionBar.setTitle(R.string.lbl_navigation_item_entertainment);
                this.mNavigationView.setCheckedItem(R.id.nav_entertainment);
                return;
            case NAVIGATION_ITEM_ATMS /*10*/:
                FragmentAtms fragmentAtms = FragmentAtms.newInstance(this.mLatitude, this.mLongitude);
                this.mFragmentTransition = getSupportFragmentManager().beginTransaction();
                this.mFragmentTransition.replace(R.id.frame_layout, fragmentAtms);
                this.mFragmentTransition.commit();
                this.mActionBar.setTitle(R.string.lbl_navigation_item_atms);
                this.mNavigationView.setCheckedItem(R.id.nav_atms);
                return;
            case NAVIGATION_ITEM_RELIGION /*11*/:
                FragmentReligion fragmentReligion = FragmentReligion.newInstance(this.mLatitude, this.mLongitude);
                this.mFragmentTransition = getSupportFragmentManager().beginTransaction();
                this.mFragmentTransition.replace(R.id.frame_layout, fragmentReligion);
                this.mFragmentTransition.commit();
                this.mActionBar.setTitle(R.string.lbl_navigation_item_religion);
                this.mNavigationView.setCheckedItem(R.id.nav_religion);
                return;
            case NAVIGATION_ITEM_HOSPITALS /*12*/:
                FragmentHospitals fragmentHospitals = FragmentHospitals.newInstance(this.mLatitude, this.mLongitude);
                this.mFragmentTransition = getSupportFragmentManager().beginTransaction();
                this.mFragmentTransition.replace(R.id.frame_layout, fragmentHospitals);
                this.mFragmentTransition.commit();
                this.mActionBar.setTitle(R.string.lbl_navigation_item_hospitals);
                this.mNavigationView.setCheckedItem(R.id.nav_hospitals);
                return;
            case NAVIGATION_ITEM_POLICE_STATIONS /*13*/:
                FragmentPoliceStations fragmentPoliceStations = FragmentPoliceStations.newInstance(this.mLatitude, this.mLongitude);
                this.mFragmentTransition = getSupportFragmentManager().beginTransaction();
                this.mFragmentTransition.replace(R.id.frame_layout, fragmentPoliceStations);
                this.mFragmentTransition.commit();
                this.mActionBar.setTitle(R.string.lbl_navigation_item_police_station);
                this.mNavigationView.setCheckedItem(R.id.nav_police_stations);
                return;
            default:
                return;
        }
    }

    public void showMap() {
        String category = Config.CATEGORY_FEATURED_PLACE;
        switch (this.mCurrentItem) {
            case NAVIGATION_ITEM_FEATURED /*1*/:
                category = Config.CATEGORY_FEATURED_PLACE;
                break;
            case NAVIGATION_ITEM_TOURIST_SPOTS /*2*/:
                category = Config.CATEGORY_TOURIST_SPOT;
                break;
            case NAVIGATION_ITEM_TRANSPORTATION /*5*/:
                category = Config.CATEGORY_TRANSPORTATION;
                break;
            case NAVIGATION_ITEM_HOTELS /*6*/:
                category = Config.CATEGORY_HOTEL;
                break;
            case NAVIGATION_ITEM_RESTAURANTS /*7*/:
                category = Config.CATEGORY_RESTAURANT;
                break;
            case NAVIGATION_ITEM_SHOPPING /*8*/:
                category = Config.CATEGORY_SHOPPING;
                break;
            case NAVIGATION_ITEM_ENTERTAINMENT /*9*/:
                category = Config.CATEGORY_ENTERTAINMENT;
                break;
            case NAVIGATION_ITEM_ATMS /*10*/:
                category = Config.CATEGORY_ATM_BOOTH;
                break;
            case NAVIGATION_ITEM_RELIGION /*11*/:
                category = Config.CATEGORY_RELIGION;
                break;
            case NAVIGATION_ITEM_HOSPITALS /*12*/:
                category = Config.CATEGORY_HOSPITAL;
                break;
            case NAVIGATION_ITEM_POLICE_STATIONS /*13*/:
                category = Config.CATEGORY_POLICE_STATION;
                break;
        }
        Intent mapIntent = new Intent(this.mContext, MapsActivity.class);
        mapIntent.putExtra(Config.KEY_LATITUDE, this.mLatitude);
        mapIntent.putExtra(Config.KEY_LONGITUDE, this.mLongitude);
        mapIntent.putExtra(MapsActivity.KEY_CATEGORY, category);
        startActivity(mapIntent);
    }

    public void goToSettings() {
        startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
    }

    public void rateUs() {
        startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.joypurhattourism.introslider")));
    }

    public void sendEmail() {
        Intent emailIntent = new Intent("android.intent.action.SEND");
        String subject = "[Feedback][Joypurhat Tourism][v" + getResources().getString(R.string.app_version_code) + "]";
        emailIntent.setType("plain/text");
        String[] strArr = new String[NAVIGATION_ITEM_FEATURED];
        strArr[NAVIGATION_ITEM_DASHBOARD] = "demo@gmail.com";
        emailIntent.putExtra("android.intent.extra.EMAIL", strArr);
        emailIntent.putExtra("android.intent.extra.SUBJECT", subject);
        startActivity(Intent.createChooser(emailIntent, "Send Email..."));
    }

    private boolean isLocationEnabled() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled("gps") || locationManager.isProviderEnabled("network");
    }

    private void showAlert() {
        Builder dialog = new Builder(this);
        String strTitle = getResources().getString(R.string.dlg_header_title_enable_location);
        String strContent = getResources().getString(R.string.dlg_content_enable_location);
        String strButtonYes = getResources().getString(R.string.dlg_button_yes);
        dialog.setTitle(strTitle).setMessage(strContent).setPositiveButton(strButtonYes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                MainActivity.this.startActivity(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"));
            }
        }).setNegativeButton(getResources().getString(R.string.dlg_button_no), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface paramDialogInterface, int paramInt) {
            }
        });
        dialog.show();
    }

    private boolean checkPermission() {
        if (ContextCompat.checkSelfPermission(this.mContext, "android.permission.ACCESS_FINE_LOCATION") == 0) {
            return true;
        }
        return false;
    }

    private void requestPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this.activity, "android.permission.ACCESS_FINE_LOCATION")) {
            Activity activity = this.activity;
            String[] strArr = new String[NAVIGATION_ITEM_FEATURED];
            strArr[NAVIGATION_ITEM_DASHBOARD] = "android.permission.ACCESS_FINE_LOCATION";
            ActivityCompat.requestPermissions(activity, strArr, PERMISSION_REQUEST_CODE);
            Toast.makeText(this.mContext, "GPS permission allows us to access location data. Please allow in App Settings for additional functionality.", NAVIGATION_ITEM_FEATURED).show();
            return;
        }
//        activity = this.activity;
//        strArr = new String[NAVIGATION_ITEM_FEATURED];
//        strArr[NAVIGATION_ITEM_DASHBOARD] = "android.permission.ACCESS_FINE_LOCATION";
//        ActivityCompat.requestPermissions(activity, strArr, PERMISSION_REQUEST_CODE);
    }

//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        switch (requestCode) {
//            case PERMISSION_REQUEST_CODE /*1001*/:
//                if (grantResults.length <= 0 || grantResults[NAVIGATION_ITEM_DASHBOARD] != 0) {
//                    Snackbar.make(this.view, "Permission Denied, You cannot access location data.", NAVIGATION_ITEM_DASHBOARD).show();
//                    return;
//                }
//                Snackbar.make(this.view, "Permission Granted, Now you can access location data.", NAVIGATION_ITEM_DASHBOARD).show();
//                showMap();
//                return;
//            default:
//                return;
//        }
//    }

    public boolean isLanguageChanged() {
        String currentLanguage = PreferenceManager.getDefaultSharedPreferences(this.mContext).getString(SettingsFragment.KEY_PREF_LANGUAGE, SettingsFragment.LANGUAGE_ENGLISH);
        if (currentLanguage.equalsIgnoreCase(this.language)) {
            return false;
        }
        this.language = currentLanguage;
        return true;
    }

    public void loadLocal(String language) {
        if (!language.equalsIgnoreCase(BuildConfig.FLAVOR)) {
            this.mLocale = new Locale(language);
            Locale.setDefault(this.mLocale);
            Configuration config = new Configuration();
            config.locale = this.mLocale;
            getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        }
    }

    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("SAVE_INSTANCE_STATE_NAV_DRAWER_ACTIVE_MENU", this.mCurrentItem);
        super.onSaveInstanceState(outState);
    }

    public void loadSettings() {
        this.mSettings = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        this.language = this.mSettings.getString(SettingsFragment.KEY_PREF_LANGUAGE, SettingsFragment.LANGUAGE_ENGLISH);
    }
}
