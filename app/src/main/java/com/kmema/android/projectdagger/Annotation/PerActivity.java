package com.kmema.android.projectdagger.Annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;



/*@Qualifier is used to distinguish between objects of the same type but with different instances*/
/*@Scope is used to specify the scope in which a dependency object persists. If a class getting dependencies,
have members injected with classes annotated with a scope, then each instance of that class asking for dependencies
will get its own set of member variables.*/
@Scope
@Retention(RetentionPolicy.CLASS)
public @interface PerActivity {
}
