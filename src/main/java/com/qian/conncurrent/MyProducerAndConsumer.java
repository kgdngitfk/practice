package com.qian.conncurrent;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.List;

@Slf4j
public class MyProducerAndConsumer {
    static class Producer extends Thread {
        List a;
        Object lock;

        public Producer(List a, Object lock ) {
            this.a = a;
            this.lock=lock;
        }

        @Override
        public void run() {
            while (true){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (a){
                    if(a.size()==0){
                        Object o=new Object();
                        log.info("{}",o);
                        a.add(o);
                        a.notifyAll();
                    }else {
                        try {
                            a.wait();
                        } catch (InterruptedException e) {

                        }
                    }
                }

            }
        }
    }

    static class Consumer extends Thread {
        List a;
        Object lock;


        public Consumer(List a, Object lock ) {
            this.a = a;
            this.lock=lock;

        }

        @Override
        public void run() {
            while (true){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (a){
                  if(a.size()>0){
                      log.info("{}",a.get(0));
                      a.remove(0);
                      a.notifyAll();
                  }else {
                      try {
                          a.wait();
                      } catch (InterruptedException e) {
                          e.printStackTrace();
                      }
                  }
                }
            }

        }
    }

    public static void main(String[] args) {
        Object lock = new Object();

        LinkedList<Object> objects = new LinkedList<>();
        new Producer(objects,lock).start();
        new Consumer(objects,lock).start();
    }
}
