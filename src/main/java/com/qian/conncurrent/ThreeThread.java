package com.qian.conncurrent;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Slf4j
public class ThreeThread implements Runnable {
    public static final Object lock = new Object();
    public static final int a = 10;
    public int index;

    public static void main(String[] args) {
        ThreeThread threeThread = new ThreeThread();
        threeThread.index = 0;
        ThreeThread threeThread1 = new ThreeThread();
        threeThread.index = 1;
        ThreeThread threeThread2 = new ThreeThread();
        threeThread.index = 2;

        new Thread(threeThread).start();
        new Thread(threeThread1).start();
        new Thread(threeThread2).start();

    }

    @Override
    public void run() {
        synchronized (lock) {
            while (a % 3 != index) {
                try {
                    log.info("{}", new Date());
                    lock.wait();
                    lock.notifyAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
