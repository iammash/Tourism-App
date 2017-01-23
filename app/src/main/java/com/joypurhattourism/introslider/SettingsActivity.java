package com.joypurhattourism.introslider;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import com.joypurhattourism.introslider.SettingsFragment.onLanguageChangeListener;
import java.util.Locale;

/**
 * Created by mdsami on 1/23/2017.
 */


@SuppressLint({"NewApi"})
public class SettingsActivity extends AppCompatActivity {
    private static final String TAG = "com.joypurhattourism.introslider.SettingsActivity";
    ActionBar mActionBar;
    Context mContext;
    private Locale mLocale;
    SharedPreferences mSettings;
    Toolbar mToolBar;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        this.mContext = this;
        this.mToolBar = (Toolbar) findViewById(R.id.toolbar);
        if (this.mToolBar != null) {
            setSupportActionBar(this.mToolBar);
            this.mActionBar = getSupportActionBar();
            this.mActionBar.setDisplayHomeAsUpEnabled(true);
        }
        this.mSettings = PreferenceManager.getDefaultSharedPreferences(this);
        SettingsFragment settingsFragment = new SettingsFragment();
        getFragmentManager().beginTransaction().replace(R.id.frame_settings_fragment, settingsFragment).commit();
        settingsFragment.setOnLanguageChangeListener(new onLanguageChangeListener() {
            public void onLanguageChanged(String language) {
                SettingsActivity.this.loadLocal(language);
                SettingsActivity.this.recreate();
            }
        });
    }

    protected void onResume() {
        super.onResume();
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
}

