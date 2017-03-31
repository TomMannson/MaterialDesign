package pl.tommmannson.materialdesign.ui.actions;

/**
 * Created by tomasz.krol on 2017-03-30.
 */

public class RunnableAction extends Action {

    private Runnable runnable;

    public RunnableAction(Runnable runnable){
        this.runnable = runnable;
    }

    @Override
    protected void doAction() {
        runnable.run();
        //notifyFinish();
    }
}
