package pl.tommmannson.materialdesign.di.components;

import dagger.Component;
import pl.tommmannson.materialdesign.di.modules.FragmentModule;
import pl.tommmannson.materialdesign.di.scopes.FragmentScope;

/**
 * Created by tomasz.krol on 2017-03-28.
 */
@FragmentScope
@Component(dependencies = {ActivityComponent.class}, modules = {FragmentModule.class})
public interface FragmentComponent extends ActivityComponent{

//    void inject(DaggerFragment fragment);
//
//    IData3 data3();
}
