package project_1;

import java.security.Provider.Service;
import javafx.concurrent.Task;

class Notifications extends javafx.concurrent.Service<Void> {

    private int dur;

    public Notifications() {
        this.dur = 2000;
    }

    public Notifications(int dur) {
        this.dur = dur;
    }
    
    public void setDurration ( int duration ) {
        this.dur = duration;
    }

    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws InterruptedException {
                Thread.sleep(dur);
                return null;
            }
        };
    }
}