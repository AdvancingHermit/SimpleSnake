package com.snake.game.util;

public class Vector{
    public int x;
    public int y;

    public Vector(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Vector add(Vector other) {
        return new Vector(x + other.x, y + other.y);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Vector){
            Vector other = (Vector) obj;
            return x == other.x && y == other.y;
        }
        return false;
    }


    public String toString(){
        return "X: " + x + " Y:" + y;
    }
}