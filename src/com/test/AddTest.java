package com.test;

import java.util.ArrayList;
import java.util.List;

public class AddTest {

    public static void main(String[] args) {

        int [] arr={1,2,3,4,5,6};
        int key=50;
        List<Integer> list=new ArrayList<>();



        for (int i=0;i<arr.length;i++){
            list.add(arr[i]);
        }
        list.add(5,key);
        System.out.println(list);



    }
}
