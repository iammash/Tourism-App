package com.joypurhattourism.introslider;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;


//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        findViewById(R.id.btn_play_again).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // We normally won't show the welcome slider again in real app
//                // but this is for testing
//                PrefManager prefManager = new PrefManager(getApplicationContext());
//
//                // make first time launch TRUE
//                prefManager.setFirstTimeLaunch(true);
//
//                startActivity(new Intent(MainActivity.this, WelcomeActivity.class));
//                finish();
//            }
//        });
//    }

    // Make sure to be using android.support.v7.app.ActionBarDrawerToggle version.
    // The android.support.v4.app.ActionBarDrawerToggle has been deprecated.
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        // ...From section above...
        // Find our drawer view
        nvDrawer = (NavigationView) findViewById(R.id.nvView);
        // Setup drawer view
        setupDrawerContent(nvDrawer);

        // Set a Toolbar to replace the ActionBar.
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Find our drawer view
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawer.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
    // ...



    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {
        // Create a new fragment and specify the fragment to show based on nav item clicked
        Fragment fragment = null;
        Class fragmentClass;
        switch(menuItem.getItemId()) {
            case R.id.home:
                fragmentClass = HomeFragment.class;
                break;
            case R.id.featured:
                fragmentClass = NearbyFragment.class;
                break;
            case R.id.tourist:
                fragmentClass = HotelsFragment.class;
                break;
            case R.id.nearby:
                fragmentClass = HomeFragment.class;
                break;

            // 2nd Block menu
            case R.id.transport:
                fragmentClass = NearbyFragment.class;
                break;
            case R.id.hotel:
                fragmentClass = HotelsFragment.class;
                break;
            case R.id.restaurants:
                fragmentClass = HomeFragment.class;
                break;
            case R.id.shopping:
                fragmentClass = NearbyFragment.class;
                break;
            case R.id.entertainment:
                fragmentClass = HotelsFragment.class;
                break;
            case R.id.atm:
                fragmentClass = HomeFragment.class;
                break;
            case R.id.prayer:
                fragmentClass = NearbyFragment.class;
                break;
            case R.id.hospital:
                fragmentClass = HotelsFragment.class;
                break;
            case R.id.policestation:
                fragmentClass = HomeFragment.class;
                break;

            // 3rd Block menu
            case R.id.setting:
                fragmentClass = NearbyFragment.class;
                break;
            case R.id.rateus:
                fragmentClass = HotelsFragment.class;
                break;
            case R.id.help:
                fragmentClass = HomeFragment.class;
                break;
            case R.id.about:
                fragmentClass = NearbyFragment.class;
                break;
            default:
                fragmentClass = HomeFragment.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);
        // Set action bar title
        setTitle(menuItem.getTitle());
        // Close the navigation drawer
        mDrawer.closeDrawers();
    }



}
