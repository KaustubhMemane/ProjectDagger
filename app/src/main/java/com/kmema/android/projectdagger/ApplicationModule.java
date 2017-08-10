package com.kmema.android.projectdagger;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.kmema.android.projectdagger.Annotation.ApplicationContext;
import com.kmema.android.projectdagger.Annotation.DatabaseInfo;

import dagger.Module;
import dagger.Provides;

/**
 * Created by kmema on 8/9/2017.
 */


/*this class defines the methods that provide the dependency. A Module class is identified by @Module*/
@Module
public class ApplicationModule {
private final Application mApplication;

    public ApplicationModule(Application mApplication) {
        this.mApplication = mApplication;
    }


/*    the dependency provider method in identified by @Provides .*/
    @Provides
    @ApplicationContext
    Context provideContext()
    {
      return mApplication;
    }

    /*    the dependency provider method in identified by @Provides .*/
    @Provides
    Application provideApplication()
    {
        return mApplication;
    }

    /*    the dependency provider method in identified by @Provides .*/
    @Provides
    @DatabaseInfo
    String provideDataBaseName()
    {
        return "daggerApp.db";
    }

    /*    the dependency provider method in identified by @Provides .*/
    @Provides
    @DatabaseInfo
    Integer provideDatabaseVersion() {
        return 2;
    }

    /*    the dependency provider method in identified by @Provides .*/
    @Provides
    SharedPreferences provideSharedPrefs() {
        return mApplication.getSharedPreferences("demo-prefs", Context.MODE_PRIVATE);
    }
}
