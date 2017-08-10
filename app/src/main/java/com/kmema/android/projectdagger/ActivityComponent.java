package com.kmema.android.projectdagger;

import com.kmema.android.projectdagger.Activity.MainActivity;
import com.kmema.android.projectdagger.Annotation.ActivityContext;
import com.kmema.android.projectdagger.Annotation.PerActivity;

import dagger.Component;

/**
 * Created by kmema on 8/9/2017.
 */




@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

}
