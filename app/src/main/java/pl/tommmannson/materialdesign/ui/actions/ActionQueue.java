package pl.tommmannson.materialdesign.ui.actions;

import android.os.Handler;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Created by tomasz.krol on 2017-03-30.
 */

public class ActionQueue {

    private Queue<Action> queue = new ArrayDeque<>();
    private Handler handler = new Handler();
    private boolean paused = true;

    public static ActionQueue startWith(Action action){

        ActionQueue actionQueue = new ActionQueue();
        actionQueue.queue.add(action);
        return actionQueue;
    }

    public ActionQueue followedBy(Action action){
        queue.add(action);
        return this;
    }

    public void pause(){
        paused = true;
    }

    public void resume(){
        if(paused) {
            paused = false;
            doNextAction();
        }
    }

    public void notifyActionFinished(Action action) {
        if(paused){
            return;
        }

        doNextAction();
    }

    private void doNextAction() {
        final Action actionToInvoke = queue.poll();

        if(actionToInvoke != null) {
            actionToInvoke.queue = this;
            handler.post(new Runnable() {
                @Override
                public void run() {
                    actionToInvoke.doAction();
                }
            });
        }
    }
}
