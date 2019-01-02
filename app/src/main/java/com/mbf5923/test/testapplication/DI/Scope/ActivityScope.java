package com.mbf5923.test.testapplication.DI.Scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by IT-10 on 1/9/2018.
 */

@Scope
@Retention(RetentionPolicy.CLASS)
public @interface ActivityScope {
}
