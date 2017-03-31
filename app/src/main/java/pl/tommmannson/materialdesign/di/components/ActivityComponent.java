package pl.tommmannson.materialdesign.di.components;

import dagger.Component;
import pl.tommmannson.materialdesign.di.modules.ActivityModule;
import pl.tommmannson.materialdesign.di.scopes.ActivityScope;

/**
 * Created by tomasz.krol on 2017-03-28.
 */

@ActivityScope
@Component(dependencies = {AppComponent.class}, modules = {ActivityModule.class})
public interface ActivityComponent extends AppComponent{

//    public void inject(Main2Activity activity);
//    public void inject(TestActivity activity);
//
//    IData2 data2();
}
