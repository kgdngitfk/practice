package com.qian.conncurrent;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class TestVolatile {
    private static boolean stop;
    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            int i=0;
            while (!stop){
                i++;
                log.info("{}",i);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        TimeUnit.SECONDS.sleep(3);
        stop=true;
    }
}
