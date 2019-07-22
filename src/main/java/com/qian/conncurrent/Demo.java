package com.qian.conncurrent;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author wuhuaiqian
 */
@Slf4j
public class Demo {
    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        MyBlockinQueue myBlockinQueue = new MyBlockinQueue();

        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        log.info("take {}", myBlockinQueue.take()
                        );
                    }
                });
                thread.setName(i+"");
                thread.start();
            }
            else {
                Thread.sleep(5000);
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Integer o = random.nextInt();
                        myBlockinQueue.add(o);
                        log.info("add {}", o);
                    }
                });
                thread.setName(i+"");
                thread.start();
            }
        }
    }
}
