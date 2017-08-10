package com.kmema.android.projectdagger;

import android.content.Context;

import com.kmema.android.projectdagger.Annotation.ApplicationContext;
import com.kmema.android.projectdagger.Database.DataManager;
import com.kmema.android.projectdagger.Database.DbHelper;
import com.kmema.android.projectdagger.Database.DemoApplication;
import com.kmema.android.projectdagger.Database.SharedPreferenceClass;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by kmema on 8/9/2017.
 */

/*this class defines the methods that provide the dependency. A Module class is identified by @Module*/
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(DemoApplication demoApplication);

    @ApplicationContext
    Context getContext();

    DataManager getDataManager();

    SharedPreferenceClass getSharedPreferenceClass();

    DbHelper getDbHelper();

}
