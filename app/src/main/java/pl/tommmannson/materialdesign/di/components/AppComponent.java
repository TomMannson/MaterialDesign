package pl.tommmannson.materialdesign.di.components;

import javax.inject.Singleton;

import dagger.Component;
import pl.tommmannson.materialdesign.di.modules.AppModule;
//import pl.tommmannson.dagger2newtest.App;
//import pl.tommmannson.dagger2newtest.dependency.IData;
//import pl.tommmannson.dagger2newtest.di.modules.AppModule;

/**
 * Created by tomasz.krol on 2017-03-28.
 */

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

//    public void inject(App app);
//
//    public IData data();
}
