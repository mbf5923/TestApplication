package com.mbf5923.test.testapplication.DI.Quantifier;

/**
 * Created by IT-10 on 1/9/2018.
 */

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivityContext {
}
