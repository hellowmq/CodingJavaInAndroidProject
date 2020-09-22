package com.wenmq.thread.concurrent;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by  @ifans.wen
 *
 * @date 2020/9/22.
 */
public class CopyOnWriteTest {


    public static void main(String[] args) {
        List<String> userNames = new CopyOnWriteArrayList<String>() {{
            add("Hollis");
            add("hollis");
            add("HollisChuang");
            add("H");
        }};

        Iterator it = userNames.iterator();

        for (String userName : userNames) {
            if (userName.equals("Hollis")) {
                userNames.remove(userName);
            }
        }

        System.out.println(userNames);

        while(it.hasNext()){
            System.out.println(it.next());
        }
    }
}
