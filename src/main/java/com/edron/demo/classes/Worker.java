package com.edron.demo.classes;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

public class Worker implements Runnable{
    private  Thread thread;
    //private Runnable task;
    private Task task;
    private AtomicBoolean started; // R/W are sync between threads (can't write while reading)
    private CountDownLatch latch;

    public Worker(Task task, CountDownLatch latch){
        thread = new Thread(this);
        started = new AtomicBoolean(false);
        this.latch = latch;
        this.task = task;
    }

    public  void start(){
        if (!started.getAndSet(true)){ // only start first time
            thread.start();
        }
    }
    @Override
    public void run(){
        task.getTask().run();
        this.latch.countDown(); // broadcast task completed
    }
}
