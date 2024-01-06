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
    private int counter = 0;
    private Vector fruit;
    private boolean fruitExists = false;
    private boolean fruitEaten = false;
    private boolean isDead = false;
    private Vector lastVel;
    int score = 0;

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
        placeSnake();
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
        checkCollision();
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
            if (snakeBody.get(snakeBody.size() - 1).equals(snakeBody.get(i))) {
                isDead = true;
            }
        }
        if (snakeBody.get(snakeBody.size() - 1).equals(fruit)) {
            fruitExists = false;
            fruitEaten = true;
            score++;
        }

    }

    public int[][] getGrid() {
        return grid;
    }

    public void update() {
        if (!isDead) {
            updateVel();
            if (counter % 10 == 0) {
                moveSnake();
                pupulateGrid();
                if (fruitExists == false) {
                    initFruit();
                }
                lastVel = new Vector(vel.x, vel.y);
            }
            counter++;
        }
    }
}