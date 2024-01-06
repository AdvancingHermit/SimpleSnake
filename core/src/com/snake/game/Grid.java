package com.snake.game;

import java.util.ArrayList;
import java.util.Random;
import java.lang.Math;

import com.snake.game.util.Vector;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class Grid {

    int[][] grid;
    ArrayList<Vector> snakeBody;
    Vector vel;
    int squareSize = 20;
    private int counter = 0;
    private Vector fruit;
    private boolean fruitExists = false;
    private boolean fruitEaten = false;
    private boolean isDead = false;
    private Vector lastVel;

    public Grid(int n, int m) {
        grid = new int[n][m];
        initSnake();
        initFruit();
        pupulateGrid();
    }

    private void pupulateGrid() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                grid[i][j] = 0;
            }
        }
        grid[fruit.x][fruit.y] = 2;
    }

    private void initSnake() {
        snakeBody = new ArrayList<>();
        snakeBody.add(new Vector(grid.length / 2, grid[0].length / 2 - 1));
        snakeBody.add(new Vector(grid.length / 2, grid[0].length / 2));
        vel = new Vector(1, 0);
    }

    private void placeSnake() {
        for (Vector vector : snakeBody) {
            grid[vector.x][vector.y] = 1;
        }
    }

    private void initFruit() {
        Random rand = new Random(System.currentTimeMillis());
        while (!fruitExists) {
            int posX = rand.nextInt(grid.length);
            int posY = rand.nextInt(grid[0].length);
            if (grid[posX][posY] == 0) {
                fruit = new Vector(posX, posY);
                fruitExists = true;
            }
        }
    }

    private void moveSnake() {
        snakeBody.add(snakeBody.get(snakeBody.size() - 1).add(vel));

        if (!fruitEaten) {
            snakeBody.remove(0);
        } else {
            fruitEaten = false;
        }

        for (int i = 0; i < snakeBody.size(); i++) {
            if (snakeBody.get(i).x == -1 || snakeBody.get(i).x == grid.length) {
                snakeBody.get(i).x = grid.length - Math.abs(snakeBody.get(i).x);
            }
            if (snakeBody.get(i).y == -1 || snakeBody.get(i).y == grid[0].length) {
                snakeBody.get(i).y = grid[0].length - Math.abs(snakeBody.get(i).y);
            }
        }

    }

    private void updateVel() {
        if (Gdx.input.isKeyPressed(Input.Keys.A) && lastVel.x != 1) {
            vel.x = -1;
            vel.y = 0;
        } else if (Gdx.input.isKeyPressed(Input.Keys.D) && lastVel.x != -1) {
            vel.x = 1;
            vel.y = 0;
        } else if (Gdx.input.isKeyPressed(Input.Keys.W) && lastVel.y != -1) {
            vel.x = 0;
            vel.y = 1;
        } else if (Gdx.input.isKeyPressed(Input.Keys.S) && lastVel.y != 1) {
            vel.x = 0;
            vel.y = -1;
        }
    }

    private void checkCollision() {
        for (int i = 0; i < snakeBody.size() - 1; i++) {
            if (snakeBody.get(snakeBody.size() - 1).x == snakeBody.get(i).x
                    && snakeBody.get(snakeBody.size() - 1).y == snakeBody.get(i).y) {
                System.out.println("YOOO");
                isDead = true;
            }
        }
        if (snakeBody.get(snakeBody.size() - 1).x == fruit.x && snakeBody.get(snakeBody.size() - 1).y == fruit.y) {
            fruitExists = false;
            System.out.println("FRUIT MUNCHER");
            fruitEaten = true;
        }

    }

    private void updateGrid() {
        pupulateGrid();
        placeSnake();
    }

    public int[][] getGrid() {
        return grid;
    }

    public int getSquareSize() {
        return squareSize;
    }

    public void update() {
        if (!isDead) {
            updateVel();

            if (counter % 10 == 0) {
                moveSnake();
                checkCollision();
                updateGrid();
                System.out.println(fruit.x + " " + fruit.y);
                if (fruitExists == false) {
                    initFruit();
                }
                lastVel = new Vector(vel.x, vel.y);
            }
            counter++;
        }
    }
}