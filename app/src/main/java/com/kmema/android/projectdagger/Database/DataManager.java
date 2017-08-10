package com.kmema.android.projectdagger.Database;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;

import com.kmema.android.projectdagger.Annotation.ApplicationContext;
import com.kmema.android.projectdagger.Model.User;

import javax.inject.Inject;


public class DataManager {

    private Context mContext;
    private DbHelper mdbHelper;
    private SharedPreferenceClass mSharedPreferenceClass;


/*    expressing dependencies of Application Context, DbHelper and SharedPrefsHelper in the contructor.
        It provides all the apis to access the data of the application.*/
    @Inject
    public DataManager(@ApplicationContext Context context,
                       DbHelper dbHelper,
                       SharedPreferenceClass sharedPreferenceClass) {
        mContext = context;
        mdbHelper = dbHelper;
        mSharedPreferenceClass = sharedPreferenceClass;
    }

    public void saveAccessToken(String accessToken)
    {
        mSharedPreferenceClass.put(SharedPreferenceClass.PREFENCE_STRING_ACCESS_TOKEN,accessToken);
    }

    public String getAccessToken()
    {
        return mSharedPreferenceClass.get(SharedPreferenceClass.PREFENCE_STRING_ACCESS_TOKEN,null);
    }

    public Long createUser(User user) throws Exception
    {
        return mdbHelper.insertUser(user);
    }
    public User getUser(Long userId) throws Resources.NotFoundException, NullPointerException {
        return mdbHelper.getUser(userId);
    }
}
