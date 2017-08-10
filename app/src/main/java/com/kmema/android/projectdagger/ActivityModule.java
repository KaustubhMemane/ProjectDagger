package com.kmema.android.projectdagger;

import android.app.Activity;
import android.content.Context;

import com.kmema.android.projectdagger.Annotation.ActivityContext;
import com.kmema.android.projectdagger.Annotation.ApplicationContext;

import dagger.Module;
import dagger.Provides;

/**
 * Created by kmema on 8/9/2017.
 */


@Module
public class ActivityModule {

    private Activity mActivity;

    public ActivityModule(Activity activity)
    {
        mActivity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext()
    {
        return mActivity;
    }

    @Provides
    Activity provideActivity()
    {
        return mActivity;
    }
}
