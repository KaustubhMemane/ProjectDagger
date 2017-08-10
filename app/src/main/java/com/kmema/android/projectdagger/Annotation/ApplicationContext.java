package com.kmema.android.projectdagger.Annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by kmema on 8/9/2017.
 */


/*@Qualifier is used to distinguish between objects of the same type but with different instances*/
@Qualifier
@Retention(RetentionPolicy.CLASS)
public @interface ApplicationContext {
}
