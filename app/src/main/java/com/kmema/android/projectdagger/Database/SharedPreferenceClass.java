package com.kmema.android.projectdagger.Database;

import android.content.SharedPreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by kmema on 8/9/2017.
 */

/*This class is annotated with @Singleton to make this a singleton class in the dependency graph of Dagger.*/
@Singleton
public class SharedPreferenceClass {

public static String PREFENCE_STRING_ACCESS_TOKEN = "accessToken";
    private SharedPreferences mSharedPreferences;

/*    This class also gets SharedPreferences dependency through Dagger which is expressed by the @Inject on the constructor.*/
    @Inject
    public SharedPreferenceClass(SharedPreferences sharedPreferences)
    {
        mSharedPreferences = sharedPreferences;
    }

    public void put(String key, String value)
    {
        mSharedPreferences.edit().putString(key,value).apply();
    }

    public void put(String key, int value) {
        mSharedPreferences.edit().putInt(key, value).apply();
    }

    public void put(String key, float value) {
        mSharedPreferences.edit().putFloat(key, value).apply();
    }

    public void put(String key, boolean value) {
        mSharedPreferences.edit().putBoolean(key, value).apply();
    }

    public String get(String key, String defaultValue) {
        return mSharedPreferences.getString(key, defaultValue);
    }

    public Integer get(String key, int defaultValue) {
        return mSharedPreferences.getInt(key, defaultValue);
    }

    public Float get(String key, float defaultValue) {
        return mSharedPreferences.getFloat(key, defaultValue);
    }

    public Boolean get(String key, boolean defaultValue) {
        return mSharedPreferences.getBoolean(key, defaultValue);
    }

    public void deleteSavedData(String key) {
        mSharedPreferences.edit().remove(key).apply();
    }
}
