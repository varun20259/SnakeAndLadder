package com;

import java.util.HashMap;

public class Snake {
    private static HashMap<Integer, Integer> snake = new HashMap<>();
    Snake(){
        snake.put(16,13);
        snake.put(31,4);
        snake.put(47,25);
        snake.put(63,60);
        snake.put(97,75);
        snake.put(66,52);
    }
    static boolean search(int a){
        return snake.containsKey(a);
    }
    static int get(int x){
        return snake.get(x);
    }
}
