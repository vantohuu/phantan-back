package com.example.demo.filter;

import java.util.LinkedList;
import java.util.Queue;

public class QueueClass {
    private static Queue<String> userQueue = new LinkedList<String>();

    private static boolean flag = false;

    public static Queue<String> getUserQueue() {
        return userQueue;
    }

    public static boolean isFlag() {
        return flag;
    }

    public static void setFlag(boolean flag) {
        QueueClass.flag = flag;
    }


    public static String getPriorityUser() {
        return userQueue.peek();
    }

    public static String removeUser() {
        return userQueue.poll();
    }


//    public static void setUserQueue(Queue<String> userQueue) {
//        QueueClass.userQueue = userQueue;
//    }

    public static void addUser(String username)
    {
        QueueClass.userQueue.add(username);
    }

}
