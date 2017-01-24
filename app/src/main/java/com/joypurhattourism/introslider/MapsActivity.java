package com.joypurhattourism.introslider;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Spinner;
import android.widget.Toast;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.joypurhattourism.introslider.app.AppController;
import com.joypurhattourism.introslider.app.Config;
import com.joypurhattourism.introslider.model.SpinnerNavItem;
import com.joypurhattourism.introslider.utils.DividerItemDecoration;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition.Builder;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by mdsami on 1/24/2017.
 */

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {
    public static final String KEY_CATEGORY = "category";
    public static final String KEY_LATITUDE = "latitude";
    public static final String KEY_LONGITUDE = "longitude";
    private static final String TAG = MapsActivity.class.getSimpleName();
    String language;
    ActionBar mActionBar;
    private String mCategory;
    Context mContext;
    private double mLatitude;
    private double mLongitude;
    private GoogleMap mMap;
    SharedPreferences mSettings;
    Toolbar mToolBar;
    Spinner navSpinner;
    ArrayList<SpinnerNavItem> navSpinnerItems;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        this.mContext = this;
        this.mToolBar = (Toolbar) findViewById(R.id.toolbar);
        if (this.mToolBar != null) {
            setSupportActionBar(this.mToolBar);
            this.mActionBar = getSupportActionBar();
            this.mActionBar.setDisplayHomeAsUpEnabled(true);
            this.mActionBar.setTitle(getResources().getString(R.string.title_map));
        }
        Window window = getWindow();
        if (VERSION.SDK_INT >= 21) {
            window.addFlags(Integer.MIN_VALUE);
            window.clearFlags(67108864);
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        loadSettings();
        if (getIntent().getExtras() != null) {
            this.mLatitude = getIntent().getDoubleExtra(KEY_LATITUDE, Config.LATITUDE_BARISAL);
            this.mLongitude = getIntent().getDoubleExtra(KEY_LONGITUDE, Config.LONGITUDE_BARISAL);
            this.mCategory = getIntent().getStringExtra(KEY_CATEGORY);
        }
        ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMapAsync(this);
    }

    public void onMapReady(GoogleMap googleMap) {
        this.mMap = googleMap;
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(new Builder().target(new LatLng(this.mLatitude, this.mLongitude)).zoom(12.0f).build()));
        googleMap.setMyLocationEnabled(true);
        googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.getUiSettings().setZoomGesturesEnabled(true);
        googleMap.getUiSettings().setRotateGesturesEnabled(true);
        googleMap.getUiSettings().setCompassEnabled(true);
        String str = this.mCategory;
        boolean z = true;
        switch (str.hashCode()) {
            case -1772467395:
                if (str.equals(Config.CATEGORY_RESTAURANT)) {
                    z = true;
                    break;
                }
                break;
            case -1619439853:
                if (str.equals(Config.CATEGORY_POLICE_STATION)) {
                    z = true;
                    break;
                }
                break;
            case -547435215:
                if (str.equals(Config.CATEGORY_RELIGION)) {
                    z = true;
                    break;
                }
                break;
            case -505581073:
                if (str.equals(Config.CATEGORY_TOURIST_SPOT)) {
                    z = true;
                    break;
                }
                break;
            case -344460952:
                if (str.equals(Config.CATEGORY_SHOPPING)) {
                    z = true;
                    break;
                }
                break;
            case -303628742:
                if (str.equals(Config.CATEGORY_HOSPITAL)) {
                    z = true;
                    break;
                }
                break;
            case 99467700:
                if (str.equals(Config.CATEGORY_HOTEL)) {
                    z = true;
                    break;
                }
                break;
            case 177495911:
                if (str.equals(Config.CATEGORY_ATTRACTION)) {
                    z = true;
                    break;
                }
                break;
            case 500006792:
                if (str.equals(Config.CATEGORY_ENTERTAINMENT)) {
                    z = true;
                    break;
                }
                break;
            case 1840457169:
                if (str.equals(Config.CATEGORY_ATM_BOOTH)) {
                    z = true;
                    break;
                }
                break;
            case 1929598316:
                if (str.equals(Config.CATEGORY_TRANSPORTATION)) {
                    z = true;
                    break;
                }
                break;
            case 1950991798:
                if (str.equals(Config.CATEGORY_FEATURED_PLACE)) {
                    z = false;
                    break;
                }
                break;
        }
        switch (z) {
            case DividerItemDecoration.HORIZONTAL_LIST /*0*/:
                fetchFeaturedPlaces();
                return;
            case DividerItemDecoration.VERTICAL_LIST /*1*/:
                fetchTouristSpots();
                return;
            case Config.TAG_ATTRACTION /*2*/:
                fetchAttractionList();
                return;
            case Config.TAG_TRANSPORTATION /*3*/:
                fetchTransportationList();
                return;
            case Config.TAG_HOTEL /*4*/:
                fetchHotelList();
                return;
            case Config.TAG_RESTAURANT /*5*/:
                fetchRestaurantList();
                return;
            case Config.TAG_SHOPPING /*6*/:
                fetchShoppingList();
                return;
            case Config.TAG_ENTERTAINMENT /*7*/:
                fetchEntertainmentList();
                return;
            case Config.TAG_ATM_BOOTH /*8*/:
                fetchAtmList();
                return;
            case Config.TAG_RELIGION /*9*/:
                fetchReligionList();
                return;
            case Config.TAG_HOSPITAL /*10*/:
                fetchHospitalList();
                return;
            case Config.TAG_POLICE_STATION /*11*/:
                fetchPoliceStationList();
                return;
            default:
                return;
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.maps, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 16908332:
                finish();
                return true;
            case R.id.nav_featured_places /*2131624238*/:
                fetchFeaturedPlaces();
                return true;
            case R.id.nav_tourist_spots /*2131624239*/:
                fetchTouristSpots();
                return true;
            case R.id.nav_transportation /*2131624240*/:
                fetchTransportationList();
                return true;
            case R.id.nav_hotels /*2131624241*/:
                fetchHotelList();
                return true;
            case R.id.nav_restaurants /*2131624242*/:
                fetchRestaurantList();
                return true;
            case R.id.nav_shopping /*2131624243*/:
                fetchShoppingList();
                return true;
            case R.id.nav_entertainment /*2131624244*/:
                fetchEntertainmentList();
                return true;
            case R.id.nav_atms /*2131624245*/:
                fetchAtmList();
                return true;
            case R.id.nav_religion /*2131624246*/:
                fetchReligionList();
                return true;
            case R.id.nav_hospitals /*2131624247*/:
                fetchHospitalList();
                return true;
            case R.id.nav_police_stations /*2131624248*/:
                fetchPoliceStationList();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void fetchFeaturedPlaces() {
        this.mActionBar.setSubtitle(getResources().getString(R.string.lbl_navigation_item_featured));
        AppController.getInstance().addToRequestQueue(new JsonObjectRequest(0, Config.URL_FEATURED_PLACES, null, new Listener<JSONObject>() {
            public void onResponse(JSONObject response) {
                Log.d(MapsActivity.TAG, response.toString());
                try {
                    JSONArray jsonArray = response.getJSONArray(Config.KEY_RESPONSE_FEATURED_PLACES);
                    MapsActivity.this.mMap.clear();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                        int id = jsonObject.getInt(Config.KEY_ID);
                        String nameEN = jsonObject.getString(Config.KEY_NAME_EN);
                        String nameBN = jsonObject.getString(Config.KEY_NAME_BN);
                        String addressEN = jsonObject.getString(Config.KEY_ADDRESS_EN);
                        String addressBN = jsonObject.getString(Config.KEY_ADDRESS_BN);
                        String districtEN = jsonObject.getString(Config.KEY_DISTRICT_EN);
                        String districtBN = jsonObject.getString(Config.KEY_DISTRICT_BN);
                        String subDistrictEN = jsonObject.getString(Config.KEY_SUB_DISTRICT_EN);
                        String subDistrictBN = jsonObject.getString(Config.KEY_SUB_DISTRICT_BN);
                        String aboutEN = jsonObject.getString(Config.KEY_ABOUT_EN);
                        String aboutBN = jsonObject.getString(Config.KEY_ABOUT_BN);
                        String travelEN = jsonObject.getString(Config.KEY_TRAVEL_EN);
                        String travelBN = jsonObject.getString(Config.KEY_TRAVEL_BN);
                        String distanceEN = jsonObject.getString(Config.KEY_DISTRICT_EN);
                        String distanceBN = jsonObject.getString(Config.KEY_DISTRICT_BN);
                        String visitTimeEN = jsonObject.getString(Config.KEY_VISIT_TIME_EN);
                        String visitTimeBN = jsonObject.getString(Config.KEY_VISIT_TIME_BN);
                        String idealStayEN = jsonObject.getString(Config.KEY_IDEAL_STAY_EN);
                        String idealStayBN = jsonObject.getString(Config.KEY_IDEAL_STAY_BN);
                        double latitude = jsonObject.getDouble(MapsActivity.KEY_LATITUDE);
                        double longitude = jsonObject.getDouble(MapsActivity.KEY_LONGITUDE);
                        String photo = jsonObject.getString(Config.KEY_PHOTO);
                        if (MapsActivity.this.language.equalsIgnoreCase(SettingsFragment.LANGUAGE_BANGLA)) {
                            MapsActivity.this.addMarkerToMap(Config.CATEGORY_FEATURED_PLACE, latitude, longitude, nameBN, addressBN);
                        } else {
                            MapsActivity.this.addMarkerToMap(Config.CATEGORY_FEATURED_PLACE, latitude, longitude, nameEN, addressEN);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(MapsActivity.this.mContext, "Error: " + e.getMessage(), 1).show();
                }
            }
        }, new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(MapsActivity.TAG, new Object[]{"Error: " + error.getMessage()});
                Toast.makeText(MapsActivity.this.mContext, error.getMessage(), 0).show();
            }
        }));
    }

    private void fetchTouristSpots() {
        this.mActionBar.setSubtitle(getResources().getString(R.string.lbl_navigation_item_tourist_spots));
        AppController.getInstance().addToRequestQueue(new JsonObjectRequest(0, "http://barisaltourism.com/api/v1/tourist_spots/" + this.mLatitude + "/" + this.mLongitude, null, new Listener<JSONObject>() {
            public void onResponse(JSONObject response) {
                Log.d(MapsActivity.TAG, response.toString());
                try {
                    JSONArray jsonArray = response.getJSONArray(Config.KEY_RESPONSE_TOURIST_SPOTS);
                    MapsActivity.this.mMap.clear();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                        int id = jsonObject.getInt(Config.KEY_ID);
                        String nameEN = jsonObject.getString(Config.KEY_NAME_EN);
                        String nameBN = jsonObject.getString(Config.KEY_NAME_BN);
                        String addressEN = jsonObject.getString(Config.KEY_ADDRESS_EN);
                        String addressBN = jsonObject.getString(Config.KEY_ADDRESS_BN);
                        String districtEN = jsonObject.getString(Config.KEY_DISTRICT_EN);
                        String districtBN = jsonObject.getString(Config.KEY_DISTRICT_BN);
                        String subDistrictEN = jsonObject.getString(Config.KEY_SUB_DISTRICT_EN);
                        String subDistrictBN = jsonObject.getString(Config.KEY_SUB_DISTRICT_BN);
                        String aboutEN = jsonObject.getString(Config.KEY_ABOUT_EN);
                        String aboutBN = jsonObject.getString(Config.KEY_ABOUT_BN);
                        String travelEN = jsonObject.getString(Config.KEY_TRAVEL_EN);
                        String travelBN = jsonObject.getString(Config.KEY_TRAVEL_BN);
                        String distanceEN = jsonObject.getString(Config.KEY_DISTRICT_EN);
                        String distanceBN = jsonObject.getString(Config.KEY_DISTRICT_BN);
                        String visitTimeEN = jsonObject.getString(Config.KEY_VISIT_TIME_EN);
                        String visitTimeBN = jsonObject.getString(Config.KEY_VISIT_TIME_BN);
                        String idealStayEN = jsonObject.getString(Config.KEY_IDEAL_STAY_EN);
                        String idealStayBN = jsonObject.getString(Config.KEY_IDEAL_STAY_BN);
                        double latitude = jsonObject.getDouble(MapsActivity.KEY_LATITUDE);
                        double longitude = jsonObject.getDouble(MapsActivity.KEY_LONGITUDE);
                        double distance = jsonObject.getDouble(Config.KEY_DISTANCE);
                        String photo = jsonObject.getString(Config.KEY_PHOTO);
                        if (MapsActivity.this.language.equalsIgnoreCase(SettingsFragment.LANGUAGE_BANGLA)) {
                            MapsActivity.this.addMarkerToMap(Config.CATEGORY_TOURIST_SPOT, latitude, longitude, nameBN, addressBN);
                        } else {
                            MapsActivity.this.addMarkerToMap(Config.CATEGORY_TOURIST_SPOT, latitude, longitude, nameEN, addressEN);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(MapsActivity.this.mContext, "Error: " + e.getMessage(), 1).show();
                }
            }
        }, new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(MapsActivity.TAG, new Object[]{"Error: " + error.getMessage()});
                Toast.makeText(MapsActivity.this.mContext, error.getMessage(), 0).show();
            }
        }));
    }

    private void fetchAttractionList() {
        this.mActionBar.setSubtitle(getResources().getString(R.string.lbl_navigation_item_tourist_spots));
        AppController.getInstance().addToRequestQueue(new JsonObjectRequest(0, "http://barisaltourism.com/api/v1/tourist_spot/1/attractions", null, new Listener<JSONObject>() {
            public void onResponse(JSONObject response) {
                Log.d(MapsActivity.TAG, response.toString());
                try {
                    JSONArray jsonArray = response.getJSONArray(Config.KEY_RESPONSE_ATTRACTIONS);
                    MapsActivity.this.mMap.clear();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                        int id = jsonObject.getInt(Config.KEY_ID);
                        int touristSpotId = jsonObject.getInt(Config.KEY_TOURIST_SPOT_ID);
                        String nameEN = jsonObject.getString(Config.KEY_NAME_EN);
                        String nameBN = jsonObject.getString(Config.KEY_NAME_BN);
                        double latitude = jsonObject.getDouble(MapsActivity.KEY_LATITUDE);
                        double longitude = jsonObject.getDouble(MapsActivity.KEY_LONGITUDE);
                        if (MapsActivity.this.language.equalsIgnoreCase(SettingsFragment.LANGUAGE_BANGLA)) {
                            MapsActivity.this.addMarkerToMap(Config.CATEGORY_ATTRACTION, latitude, longitude, nameBN, String.valueOf(MapsActivity.this.mLatitude) + ", " + String.valueOf(MapsActivity.this.mLongitude));
                        } else {
                            MapsActivity.this.addMarkerToMap(Config.CATEGORY_ATTRACTION, latitude, longitude, nameEN, String.valueOf(MapsActivity.this.mLatitude) + ", " + String.valueOf(MapsActivity.this.mLongitude));
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(MapsActivity.this.mContext, "Error: " + e.getMessage(), 1).show();
                }
            }
        }, new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(MapsActivity.TAG, new Object[]{"Error: " + error.getMessage()});
                Toast.makeText(MapsActivity.this.mContext, error.getMessage(), 0).show();
            }
        }));
    }

    private void fetchAtmList() {
        this.mActionBar.setSubtitle(getResources().getString(R.string.lbl_navigation_item_atms));
        AppController.getInstance().addToRequestQueue(new JsonObjectRequest(0, "http://barisaltourism.com/api/v1/atm_booths/" + this.mLatitude + "/" + this.mLongitude, null, new Listener<JSONObject>() {
            public void onResponse(JSONObject response) {
                Log.d(MapsActivity.TAG, response.toString());
                try {
                    JSONArray jsonArray = response.getJSONArray(Config.KEY_RESPONSE_ATM_BOOTHS);
                    MapsActivity.this.mMap.clear();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                        int id = jsonObject.getInt(Config.KEY_ID);
                        String nameEN = jsonObject.getString(Config.KEY_NAME_EN);
                        String nameBN = jsonObject.getString(Config.KEY_NAME_BN);
                        String addressEN = jsonObject.getString(Config.KEY_ADDRESS_EN);
                        String addressBN = jsonObject.getString(Config.KEY_ADDRESS_BN);
                        double latitude = jsonObject.getDouble(MapsActivity.KEY_LATITUDE);
                        double longitude = jsonObject.getDouble(MapsActivity.KEY_LONGITUDE);
                        if (MapsActivity.this.language.equalsIgnoreCase(SettingsFragment.LANGUAGE_BANGLA)) {
                            MapsActivity.this.addMarkerToMap(Config.CATEGORY_ATM_BOOTH, latitude, longitude, nameBN, addressBN);
                        } else {
                            MapsActivity.this.addMarkerToMap(Config.CATEGORY_ATM_BOOTH, latitude, longitude, nameEN, addressEN);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(MapsActivity.this.mContext, "Error: " + e.getMessage(), 1).show();
                }
            }
        }, new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(MapsActivity.TAG, new Object[]{"Error: " + error.getMessage()});
                Toast.makeText(MapsActivity.this.mContext, error.getMessage(), 0).show();
            }
        }));
    }

    private void fetchEntertainmentList() {
        this.mActionBar.setSubtitle(getResources().getString(R.string.lbl_navigation_item_entertainment));
        AppController.getInstance().addToRequestQueue(new JsonObjectRequest(0, "http://barisaltourism.com/api/v1/entertainment_places/" + this.mLatitude + "/" + this.mLongitude, null, new Listener<JSONObject>() {
            public void onResponse(JSONObject response) {
                Log.d(MapsActivity.TAG, response.toString());
                try {
                    JSONArray jsonArray = response.getJSONArray(Config.KEY_RESPONSE_ENTERTAINMENT);
                    MapsActivity.this.mMap.clear();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                        int id = jsonObject.getInt(Config.KEY_ID);
                        String nameEN = jsonObject.getString(Config.KEY_NAME_EN);
                        String nameBN = jsonObject.getString(Config.KEY_NAME_BN);
                        String addressEN = jsonObject.getString(Config.KEY_ADDRESS_EN);
                        String addressBN = jsonObject.getString(Config.KEY_ADDRESS_BN);
                        double latitude = jsonObject.getDouble(MapsActivity.KEY_LATITUDE);
                        double longitude = jsonObject.getDouble(MapsActivity.KEY_LONGITUDE);
                        if (MapsActivity.this.language.equalsIgnoreCase(SettingsFragment.LANGUAGE_BANGLA)) {
                            MapsActivity.this.addMarkerToMap(Config.CATEGORY_ENTERTAINMENT, latitude, longitude, nameBN, addressBN);
                        } else {
                            MapsActivity.this.addMarkerToMap(Config.CATEGORY_ENTERTAINMENT, latitude, longitude, nameEN, addressEN);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(MapsActivity.this.mContext, "Error: " + e.getMessage(), 1).show();
                }
            }
        }, new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(MapsActivity.TAG, new Object[]{"Error: " + error.getMessage()});
                Toast.makeText(MapsActivity.this.mContext, error.getMessage(), 0).show();
            }
        }));
    }

    private void fetchHospitalList() {
        this.mActionBar.setSubtitle(getResources().getString(R.string.lbl_navigation_item_hospitals));
        AppController.getInstance().addToRequestQueue(new JsonObjectRequest(0, "http://barisaltourism.com/api/v1/hospitals/" + this.mLatitude + "/" + this.mLongitude, null, new Listener<JSONObject>() {
            public void onResponse(JSONObject response) {
                Log.d(MapsActivity.TAG, response.toString());
                try {
                    JSONArray jsonArray = response.getJSONArray(Config.KEY_RESPONSE_HOSPITALS);
                    MapsActivity.this.mMap.clear();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                        int id = jsonObject.getInt(Config.KEY_ID);
                        String nameEN = jsonObject.getString(Config.KEY_NAME_EN);
                        String nameBN = jsonObject.getString(Config.KEY_NAME_BN);
                        String addressEN = jsonObject.getString(Config.KEY_ADDRESS_EN);
                        String addressBN = jsonObject.getString(Config.KEY_ADDRESS_BN);
                        double latitude = jsonObject.getDouble(MapsActivity.KEY_LATITUDE);
                        double longitude = jsonObject.getDouble(MapsActivity.KEY_LONGITUDE);
                        if (MapsActivity.this.language.equalsIgnoreCase(SettingsFragment.LANGUAGE_BANGLA)) {
                            MapsActivity.this.addMarkerToMap(Config.CATEGORY_HOSPITAL, latitude, longitude, nameBN, addressBN);
                        } else {
                            MapsActivity.this.addMarkerToMap(Config.CATEGORY_HOSPITAL, latitude, longitude, nameEN, addressEN);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(MapsActivity.this.mContext, "Error: " + e.getMessage(), 1).show();
                }
            }
        }, new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(MapsActivity.TAG, new Object[]{"Error: " + error.getMessage()});
                Toast.makeText(MapsActivity.this.mContext, error.getMessage(), 0).show();
            }
        }));
    }

    private void fetchHotelList() {
        this.mActionBar.setSubtitle(getResources().getString(R.string.lbl_navigation_item_hotels));
        AppController.getInstance().addToRequestQueue(new JsonObjectRequest(0, "http://barisaltourism.com/api/v1/hotels/" + this.mLatitude + "/" + this.mLongitude, null, new Listener<JSONObject>() {
            public void onResponse(JSONObject response) {
                Log.d(MapsActivity.TAG, response.toString());
                try {
                    JSONArray jsonArray = response.getJSONArray(Config.KEY_RESPONSE_HOTELS);
                    MapsActivity.this.mMap.clear();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                        int id = jsonObject.getInt(Config.KEY_ID);
                        String nameEN = jsonObject.getString(Config.KEY_NAME_EN);
                        String nameBN = jsonObject.getString(Config.KEY_NAME_BN);
                        String addressEN = jsonObject.getString(Config.KEY_ADDRESS_EN);
                        String addressBN = jsonObject.getString(Config.KEY_ADDRESS_BN);
                        double latitude = jsonObject.getDouble(MapsActivity.KEY_LATITUDE);
                        double longitude = jsonObject.getDouble(MapsActivity.KEY_LONGITUDE);
                        if (MapsActivity.this.language.equalsIgnoreCase(SettingsFragment.LANGUAGE_BANGLA)) {
                            MapsActivity.this.addMarkerToMap(Config.CATEGORY_HOTEL, latitude, longitude, nameBN, addressBN);
                        } else {
                            MapsActivity.this.addMarkerToMap(Config.CATEGORY_HOTEL, latitude, longitude, nameEN, addressEN);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(MapsActivity.this.mContext, "Error: " + e.getMessage(), 1).show();
                }
            }
        }, new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(MapsActivity.TAG, new Object[]{"Error: " + error.getMessage()});
                Toast.makeText(MapsActivity.this.mContext, error.getMessage(), 0).show();
            }
        }));
    }

    private void fetchPoliceStationList() {
        this.mActionBar.setSubtitle(getResources().getString(R.string.lbl_navigation_item_police_station));
        AppController.getInstance().addToRequestQueue(new JsonObjectRequest(0, "http://barisaltourism.com/api/v1/police_stations/" + this.mLatitude + "/" + this.mLongitude, null, new Listener<JSONObject>() {
            public void onResponse(JSONObject response) {
                Log.d(MapsActivity.TAG, response.toString());
                try {
                    JSONArray jsonArray = response.getJSONArray(Config.KEY_RESPONSE_POLICE_STATIONS);
                    MapsActivity.this.mMap.clear();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                        int id = jsonObject.getInt(Config.KEY_ID);
                        String nameEN = jsonObject.getString(Config.KEY_NAME_EN);
                        String nameBN = jsonObject.getString(Config.KEY_NAME_BN);
                        String addressEN = jsonObject.getString(Config.KEY_ADDRESS_EN);
                        String addressBN = jsonObject.getString(Config.KEY_ADDRESS_BN);
                        double latitude = jsonObject.getDouble(MapsActivity.KEY_LATITUDE);
                        double longitude = jsonObject.getDouble(MapsActivity.KEY_LONGITUDE);
                        if (MapsActivity.this.language.equalsIgnoreCase(SettingsFragment.LANGUAGE_BANGLA)) {
                            MapsActivity.this.addMarkerToMap(Config.CATEGORY_POLICE_STATION, latitude, longitude, nameBN, addressBN);
                        } else {
                            MapsActivity.this.addMarkerToMap(Config.CATEGORY_POLICE_STATION, latitude, longitude, nameEN, addressEN);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(MapsActivity.this.mContext, "Error: " + e.getMessage(), 1).show();
                }
            }
        }, new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(MapsActivity.TAG, new Object[]{"Error: " + error.getMessage()});
                Toast.makeText(MapsActivity.this.mContext, error.getMessage(), 0).show();
            }
        }));
    }

    private void fetchReligionList() {
        this.mActionBar.setSubtitle(getResources().getString(R.string.lbl_navigation_item_religion));
        AppController.getInstance().addToRequestQueue(new JsonObjectRequest(0, "http://barisaltourism.com/api/v1/religion_places/" + this.mLatitude + "/" + this.mLongitude, null, new Listener<JSONObject>() {
            public void onResponse(JSONObject response) {
                Log.d(MapsActivity.TAG, response.toString());
                try {
                    JSONArray jsonArray = response.getJSONArray(Config.KEY_RESPONSE_RELIGION);
                    MapsActivity.this.mMap.clear();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                        int id = jsonObject.getInt(Config.KEY_ID);
                        String nameEN = jsonObject.getString(Config.KEY_NAME_EN);
                        String nameBN = jsonObject.getString(Config.KEY_NAME_BN);
                        String addressEN = jsonObject.getString(Config.KEY_ADDRESS_EN);
                        String addressBN = jsonObject.getString(Config.KEY_ADDRESS_BN);
                        double latitude = jsonObject.getDouble(MapsActivity.KEY_LATITUDE);
                        double longitude = jsonObject.getDouble(MapsActivity.KEY_LONGITUDE);
                        if (MapsActivity.this.language.equalsIgnoreCase(SettingsFragment.LANGUAGE_BANGLA)) {
                            MapsActivity.this.addMarkerToMap(Config.CATEGORY_RELIGION, latitude, longitude, nameBN, addressBN);
                        } else {
                            MapsActivity.this.addMarkerToMap(Config.CATEGORY_RELIGION, latitude, longitude, nameEN, addressEN);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(MapsActivity.this.mContext, "Error: " + e.getMessage(), 1).show();
                }
            }
        }, new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(MapsActivity.TAG, new Object[]{"Error: " + error.getMessage()});
                Toast.makeText(MapsActivity.this.mContext, error.getMessage(), 0).show();
            }
        }));
    }

    private void fetchRestaurantList() {
        this.mActionBar.setSubtitle(getResources().getString(R.string.lbl_navigation_item_restaurants));
        AppController.getInstance().addToRequestQueue(new JsonObjectRequest(0, "http://barisaltourism.com/api/v1/restaurants/" + this.mLatitude + "/" + this.mLongitude, null, new Listener<JSONObject>() {
            public void onResponse(JSONObject response) {
                Log.d(MapsActivity.TAG, response.toString());
                try {
                    JSONArray jsonArray = response.getJSONArray(Config.KEY_RESPONSE_RESTAURANTS);
                    MapsActivity.this.mMap.clear();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                        int id = jsonObject.getInt(Config.KEY_ID);
                        String nameEN = jsonObject.getString(Config.KEY_NAME_EN);
                        String nameBN = jsonObject.getString(Config.KEY_NAME_BN);
                        String addressEN = jsonObject.getString(Config.KEY_ADDRESS_EN);
                        String addressBN = jsonObject.getString(Config.KEY_ADDRESS_BN);
                        double latitude = jsonObject.getDouble(MapsActivity.KEY_LATITUDE);
                        double longitude = jsonObject.getDouble(MapsActivity.KEY_LONGITUDE);
                        if (MapsActivity.this.language.equalsIgnoreCase(SettingsFragment.LANGUAGE_BANGLA)) {
                            MapsActivity.this.addMarkerToMap(Config.CATEGORY_RESTAURANT, latitude, longitude, nameBN, addressBN);
                        } else {
                            MapsActivity.this.addMarkerToMap(Config.CATEGORY_RESTAURANT, latitude, longitude, nameEN, addressEN);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(MapsActivity.this.mContext, "Error: " + e.getMessage(), 1).show();
                }
            }
        }, new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(MapsActivity.TAG, new Object[]{"Error: " + error.getMessage()});
                Toast.makeText(MapsActivity.this.mContext, error.getMessage(), 0).show();
            }
        }));
    }

    private void fetchShoppingList() {
        this.mActionBar.setSubtitle(getResources().getString(R.string.lbl_navigation_item_shopping));
        AppController.getInstance().addToRequestQueue(new JsonObjectRequest(0, "http://barisaltourism.com/api/v1/shopping_places/" + this.mLatitude + "/" + this.mLongitude, null, new Listener<JSONObject>() {
            public void onResponse(JSONObject response) {
                Log.d(MapsActivity.TAG, response.toString());
                try {
                    JSONArray jsonArray = response.getJSONArray(Config.KEY_RESPONSE_SHOPPING);
                    MapsActivity.this.mMap.clear();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                        int id = jsonObject.getInt(Config.KEY_ID);
                        String nameEN = jsonObject.getString(Config.KEY_NAME_EN);
                        String nameBN = jsonObject.getString(Config.KEY_NAME_BN);
                        String addressEN = jsonObject.getString(Config.KEY_ADDRESS_EN);
                        String addressBN = jsonObject.getString(Config.KEY_ADDRESS_BN);
                        double latitude = jsonObject.getDouble(MapsActivity.KEY_LATITUDE);
                        double longitude = jsonObject.getDouble(MapsActivity.KEY_LONGITUDE);
                        if (MapsActivity.this.language.equalsIgnoreCase(SettingsFragment.LANGUAGE_BANGLA)) {
                            MapsActivity.this.addMarkerToMap(Config.CATEGORY_SHOPPING, latitude, longitude, nameBN, addressBN);
                        } else {
                            MapsActivity.this.addMarkerToMap(Config.CATEGORY_SHOPPING, latitude, longitude, nameEN, addressEN);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(MapsActivity.this.mContext, "Error: " + e.getMessage(), 1).show();
                }
            }
        }, new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(MapsActivity.TAG, new Object[]{"Error: " + error.getMessage()});
                Toast.makeText(MapsActivity.this.mContext, error.getMessage(), 0).show();
            }
        }));
    }

    private void fetchTransportationList() {
        this.mActionBar.setSubtitle(getResources().getString(R.string.lbl_navigation_item_transportation));
        AppController.getInstance().addToRequestQueue(new JsonObjectRequest(0, "http://barisaltourism.com/api/v1/transportations/" + this.mLatitude + "/" + this.mLongitude, null, new Listener<JSONObject>() {
            public void onResponse(JSONObject response) {
                Log.d(MapsActivity.TAG, response.toString());
                try {
                    JSONArray jsonArray = response.getJSONArray(Config.KEY_RESPONSE_TRANSPORTATIONS);
                    MapsActivity.this.mMap.clear();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                        int id = jsonObject.getInt(Config.KEY_ID);
                        int parentId = jsonObject.getInt(Config.KEY_PARENT_ID);
                        int type = jsonObject.getInt(Config.KEY_TYPE);
                        String nameEN = jsonObject.getString(Config.KEY_NAME_EN);
                        String nameBN = jsonObject.getString(Config.KEY_NAME_BN);
                        String addressEN = jsonObject.getString(Config.KEY_ADDRESS_EN);
                        String addressBN = jsonObject.getString(Config.KEY_ADDRESS_BN);
                        double latitude = jsonObject.getDouble(MapsActivity.KEY_LATITUDE);
                        double longitude = jsonObject.getDouble(MapsActivity.KEY_LONGITUDE);
                        if (MapsActivity.this.language.equalsIgnoreCase(SettingsFragment.LANGUAGE_BANGLA)) {
                            MapsActivity.this.addMarkerToMap(Config.CATEGORY_TRANSPORTATION, latitude, longitude, nameBN, addressBN);
                        } else {
                            MapsActivity.this.addMarkerToMap(Config.CATEGORY_TRANSPORTATION, latitude, longitude, nameEN, addressEN);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(MapsActivity.this.mContext, "Error: " + e.getMessage(), 1).show();
                }
            }
        }, new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(MapsActivity.TAG, new Object[]{"Error: " + error.getMessage()});
                Toast.makeText(MapsActivity.this.mContext, error.getMessage(), 0).show();
            }
        }));
    }

    public void addMarkerToMap(String category, double latitude, double longitude, String title, String description) {
        MarkerOptions marker = new MarkerOptions().position(new LatLng(latitude, longitude)).title(title).snippet(description);
        Object obj = -1;
        switch (category.hashCode()) {
            case -1772467395:
                if (category.equals(Config.CATEGORY_RESTAURANT)) {
                    obj = 4;
                    break;
                }
                break;
            case -1619439853:
                if (category.equals(Config.CATEGORY_POLICE_STATION)) {
                    obj = 10;
                    break;
                }
                break;
            case -547435215:
                if (category.equals(Config.CATEGORY_RELIGION)) {
                    obj = 8;
                    break;
                }
                break;
            case -505581073:
                if (category.equals(Config.CATEGORY_TOURIST_SPOT)) {
                    obj = 1;
                    break;
                }
                break;
            case -344460952:
                if (category.equals(Config.CATEGORY_SHOPPING)) {
                    obj = 5;
                    break;
                }
                break;
            case -303628742:
                if (category.equals(Config.CATEGORY_HOSPITAL)) {
                    obj = 9;
                    break;
                }
                break;
            case 99467700:
                if (category.equals(Config.CATEGORY_HOTEL)) {
                    obj = 3;
                    break;
                }
                break;
            case 500006792:
                if (category.equals(Config.CATEGORY_ENTERTAINMENT)) {
                    obj = 6;
                    break;
                }
                break;
            case 1840457169:
                if (category.equals(Config.CATEGORY_ATM_BOOTH)) {
                    obj = 7;
                    break;
                }
                break;
            case 1929598316:
                if (category.equals(Config.CATEGORY_TRANSPORTATION)) {
                    obj = 2;
                    break;
                }
                break;
            case 1950991798:
                if (category.equals(Config.CATEGORY_FEATURED_PLACE)) {
                    obj = null;
                    break;
                }
                break;
        }
        switch (obj) {
            case DividerItemDecoration.HORIZONTAL_LIST /*0*/:
                marker.icon(BitmapDescriptorFactory.defaultMarker(0.0f));
                break;
            case DividerItemDecoration.VERTICAL_LIST /*1*/:
                marker.icon(BitmapDescriptorFactory.defaultMarker(210.0f));
                break;
            case Config.TAG_ATTRACTION /*2*/:
                marker.icon(BitmapDescriptorFactory.defaultMarker(30.0f));
                break;
            case Config.TAG_TRANSPORTATION /*3*/:
                marker.icon(BitmapDescriptorFactory.defaultMarker(120.0f));
                break;
            case Config.TAG_HOTEL /*4*/:
                marker.icon(BitmapDescriptorFactory.defaultMarker(180.0f));
                break;
            case Config.TAG_RESTAURANT /*5*/:
                marker.icon(BitmapDescriptorFactory.defaultMarker(60.0f));
                break;
            case Config.TAG_SHOPPING /*6*/:
                marker.icon(BitmapDescriptorFactory.defaultMarker(330.0f));
                break;
            case Config.TAG_ENTERTAINMENT /*7*/:
                marker.icon(BitmapDescriptorFactory.defaultMarker(210.0f));
                break;
            case Config.TAG_ATM_BOOTH /*8*/:
                marker.icon(BitmapDescriptorFactory.defaultMarker(120.0f));
                break;
            case Config.TAG_RELIGION /*9*/:
                marker.icon(BitmapDescriptorFactory.defaultMarker(0.0f));
                break;
            case Config.TAG_HOSPITAL /*10*/:
                marker.icon(BitmapDescriptorFactory.defaultMarker(60.0f));
                break;
        }
        this.mMap.addMarker(marker);
    }

    public void loadSettings() {
        this.mSettings = PreferenceManager.getDefaultSharedPreferences(this.mContext);
        this.language = this.mSettings.getString(SettingsFragment.KEY_PREF_LANGUAGE, SettingsFragment.LANGUAGE_ENGLISH);
    }
}
