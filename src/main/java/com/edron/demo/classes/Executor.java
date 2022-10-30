package com.edron.demo.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;

public class Executor extends Thread{
    private ConcurrentLinkedQueue<Worker> workers;
    private Callback callback;
    private CountDownLatch latch;

    public Executor(List<Task> tasks, Callback callback){
        this.callback = callback;
        workers = new ConcurrentLinkedQueue<>();
        latch = new CountDownLatch(tasks.size()); // number of tasks in parallel

        // create workers
        for(Task task: tasks){
            workers.add(new Worker(task, latch));
        }
    }

    public void execute(){
        start();
    }
    @Override
    public void run(){
        // call start() of all workers
        while (true){
            Worker worker = workers.poll();
            if (worker == null){
                break;
            }
            worker.start();
        }
        // wait until all workers call countdownlatch
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (callback != null){
            callback.onComplete();
        }
    }

    public static class Builder{
        private List<Task> tasks = new ArrayList<>();
        private Callback callback;

        public Builder add(Task task){
            tasks.add(task);
            return this;
        }

        public Builder callback(Callback callback){
            this.callback = callback;
            return this;
        }

        public Executor build(){
            return new Executor(tasks, callback);
        }
    }
    public interface Callback{
        void onComplete();
    }
}
