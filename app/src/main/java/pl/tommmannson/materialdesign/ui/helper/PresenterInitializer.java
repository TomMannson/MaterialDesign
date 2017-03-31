package pl.tommmannson.materialdesign.ui.helper;

/**
 * Created by tomasz.krol on 2017-03-31.
 */

public interface PresenterInitializer {

    void onCreateComponent();

    boolean isComponentRestored();
}
