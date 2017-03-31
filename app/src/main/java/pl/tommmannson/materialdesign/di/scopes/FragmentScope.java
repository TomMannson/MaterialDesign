package pl.tommmannson.materialdesign.di.scopes;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by tomasz.krol on 2017-03-28.
 */

@Retention(value = RetentionPolicy.RUNTIME)
@Scope
public @interface FragmentScope {
}
