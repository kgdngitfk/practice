package com.qian.conncurrent;

/**
 * @author wuhuaiqian
 */
public class MyBlockinQueue {
    Object [] a=new Object[10];
    int size =10;
    int index =0;

    public void add(Object o) {
        synchronized (this){
            while (index==10){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            a[index++]=o;
            notifyAll();
        }
    }
    public Object take(){
        synchronized (this){
            while (index==0){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return a[index-1];
        }
    }
}
