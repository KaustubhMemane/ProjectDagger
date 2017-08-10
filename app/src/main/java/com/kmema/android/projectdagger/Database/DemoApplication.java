package com.kmema.android.projectdagger.Database;

import android.app.Application;
import android.content.Context;

import com.kmema.android.projectdagger.ApplicationComponent;
import com.kmema.android.projectdagger.ApplicationModule;
import com.kmema.android.projectdagger.DaggerApplicationComponent;

import javax.inject.Inject;

/**
 * Created by kmema on 8/9/2017.
 */

public class DemoApplication extends Application{


    protected ApplicationComponent mApplicationComponent;

    /*gets DataManager through Dependency injection*/
    @Inject DataManager dataManager;

    public static DemoApplication get(Context context)
    {
        return (DemoApplication)context.getApplicationContext();
    }


    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationComponent =
                DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        mApplicationComponent.inject(this);
    }

    public ApplicationComponent getComponent(){
        return mApplicationComponent;
    }
}
