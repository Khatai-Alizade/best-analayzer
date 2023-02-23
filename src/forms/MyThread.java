package forms;


public class MyThread implements Runnable {
    private Runnable methodToRun;

    public MyThread(Runnable method) {
        this.methodToRun = method;
    }

    @Override
    public void run() {
        methodToRun.run();
    }
}


