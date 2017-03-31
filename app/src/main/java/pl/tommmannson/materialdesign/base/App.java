package pl.tommmannson.materialdesign.base;

import android.app.Application;

import pl.tommmannson.materialdesign.di.components.AppComponent;
import pl.tommmannson.materialdesign.di.components.DaggerAppComponent;
import pl.tommmannson.materialdesign.di.modules.AppModule;

/**
 * Created by tomasz.krol on 2017-03-31.
 */

public class App extends Application {

    AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerAppComponent.builder()
                .appModule(new AppModule("test", 1))
                .build();

    }

    public AppComponent getComponent() {
        return component;
    }
}
