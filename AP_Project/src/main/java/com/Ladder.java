package com;

import java.util.HashMap;

public class Ladder {
    private static HashMap<Integer, Integer> ladder = new HashMap<>();
    Ladder(){
        getPosition();
        ladder.put(10,12);
        ladder.put(3,39);
        ladder.put(27,53);
        ladder.put(56,84);
        ladder.put(61,99);
        ladder.put(72,90);
    }
    static int x[] = new int[100];
    static int y[] = new int[100];
    static boolean search(int a){
         return ladder.containsKey(a);
    }
    static int get(int x){
        return ladder.get(x);
    }
    void getPosition(){
        int row=1;
        int a=-25;
        int b=475;
        for(int j=1;j<=100;j++){
            if(row %2==1){
                a+=50;
            }
            if(row %2==0){
                a-=50;
            }
            if(a > 475){
                a-=50;
                b-=50;
                row++;
            }
            if(a <25){
                a+=50;
                b-=50;
                row++;
            }
            x[j-1]=a;
            y[j-1]=b;
        }
    }
}
