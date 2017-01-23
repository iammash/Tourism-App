package com.joypurhattourism.introslider;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.support.v7.app.ActionBar;

/**
 * Created by mdsami on 1/22/2017.
 */

public class SettingsFragment extends PreferenceFragment implements OnSharedPreferenceChangeListener {
    public static final String KEY_PREF_FIRST_TIME_LAUNCH = "pref_key_first_time_launch";
    public static final String KEY_PREF_LANGUAGE = "pref_key_language";
    public static final String LANGUAGE_BANGLA = "bn";
    public static final String LANGUAGE_ENGLISH = "en";
    private static final String TAG = "com.joypurhattourism.introslider.SettingsFragment";
    ActionBar mActionBar;
    Context mContext;
    onLanguageChangeListener mLanguageChangeListener;
    SharedPreferences mSettings;
    ListPreference preferenceLanguage;
    PreferenceScreen preferenceScreen;

    public interface onLanguageChangeListener {
        void onLanguageChanged(String str);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preference_settings);
        this.mContext = getActivity();
        this.preferenceScreen = getPreferenceScreen();
        this.mSettings = PreferenceManager.getDefaultSharedPreferences(this.mContext);
        this.preferenceLanguage = (ListPreference) this.preferenceScreen.findPreference(KEY_PREF_LANGUAGE);
        initPreferences();
    }

    public void initPreferences() {
        this.preferenceLanguage.setSummary(getSelectedLanguageTitle(this.mSettings.getString(KEY_PREF_LANGUAGE, LANGUAGE_BANGLA)));
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPref, String key) {
        if (key.equals(KEY_PREF_LANGUAGE)) {
            String selectedLanguage = this.mSettings.getString(KEY_PREF_LANGUAGE, LANGUAGE_BANGLA);
            this.preferenceLanguage.setSummary(getSelectedLanguageTitle(selectedLanguage));
            this.mLanguageChangeListener.onLanguageChanged(selectedLanguage);
        }
    }

    public String getSelectedLanguageTitle(String language) {
        String[] languages = this.mContext.getResources().getStringArray(R.array.list_language);
        if (language.equalsIgnoreCase(LANGUAGE_BANGLA)) {
            return languages[0];
        }
        if (language.equalsIgnoreCase(LANGUAGE_ENGLISH)) {
            return languages[1];
        }
        return null;
    }

    public void setOnLanguageChangeListener(onLanguageChangeListener listener) {
        this.mLanguageChangeListener = listener;
    }

    public void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    public void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }
}
