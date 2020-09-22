package com.wenmq.thread.concurrent;

/**
 * Created by  @ifans.wen
 *
 * @date 2020/9/22.
 */
public class SyncTest {


    public static void main(String[] args) {

        for (int i = 0; i < 5; i++) {
            getThread().start();
        }
    }

    private static Thread getThread() {
        return new Thread(new Runnable() {
            @Override
            public void run() {
                A a = new A();
                a.goSleep();
                a.goSleep2();
            }
        });
    }

    static class A {

        public synchronized void goSleep() {
            System.out.println(" before1");
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(" after1");
        }

        public void goSleep2() {
            synchronized (this) {
                System.out.println(" before2");
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(" after2");
            }
        }
    }
}
