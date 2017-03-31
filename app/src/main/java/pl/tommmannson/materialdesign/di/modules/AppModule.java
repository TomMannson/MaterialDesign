package pl.tommmannson.materialdesign.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by tomasz.krol on 2017-03-28.
 */

@Module
public class AppModule {
    String name;
    int number;

    public AppModule(String name, int number) {
        this.name = name;
        this.number = number;
    }

//    @Provides
//    @Singleton
//    public IData data(/*Data data*/){
//        return new Data(name, number);
//    }
}
