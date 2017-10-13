package com.mobileallin.mybakingapp.dagger;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by Dawid on 2017-10-12.
 */

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface IoScheduler {
}
