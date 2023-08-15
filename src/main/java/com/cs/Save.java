package com.cs;

public class Save {

    static Query Q = new Query();
    static Write W = new Write(new int[]{1, 2, 3, 4, 5, 6, 1});

    public static void main(String[] args) {

        Thread q = new Thread(() -> {
            Q.qCount();
        });
        q.start();
        try {
            q.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Public.SUM);
        W.save();
    }

}
