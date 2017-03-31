package pl.tommmannson.materialdesign.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tommannson.viewstate.ActivityPersistLoader;
import com.tommannson.viewstate.Persistable;

import pl.tommmannson.materialdesign.di.components.AppComponent;
import pl.tommmannson.materialdesign.ui.helper.PresenterInitializer;


/**
 * Created by tomasz.krol on 2017-03-31.
 */

public abstract class BaseActivity extends AppCompatActivity implements Persistable, PresenterInitializer {

//    T component;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityPersistLoader.load(this);

        if(!isComponentRestored()){
            onCreateComponent();
        }
    }


    public AppComponent getAppComponent(){
        return ((App)getApplication()).getComponent();
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return ActivityPersistLoader.persist(this);
    }
}
