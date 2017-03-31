package pl.tommmannson.materialdesign.ui.actions;

/**
 * Created by tomasz.krol on 2017-03-30.
 */

public abstract class Action {

    ActionQueue queue;

    public static Action start(Runnable runnable){
        return new RunnableAction(runnable);
    }

    protected abstract void doAction();

    protected void notifyFinish(){
        queue.notifyActionFinished(this);
        queue = null;
    }

    void setQueue(ActionQueue queue) {
        this.queue = queue;
    }

    protected void cleanAfterAction(){}

}
