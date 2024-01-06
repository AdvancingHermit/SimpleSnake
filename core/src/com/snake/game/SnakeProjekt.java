package com.snake.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class SnakeProjekt extends ApplicationAdapter {
	SpriteBatch batch;
	Grid grid;
	ShapeRenderer shape;
	public static OrthographicCamera camera;
	FitViewport viewport;
	int screenWidth;
	int screenHeight;
	private int n;
	private int m;

	SnakeProjekt(int n, int m) {
		super();
		this.n = n;
		this.m = m;
	}

	@Override
	public void create() {
		grid = new Grid(n, m);
		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();
		batch = new SpriteBatch();
		shape = new ShapeRenderer();
		camera = new OrthographicCamera();
		viewport = new FitViewport(screenWidth, screenHeight, camera);
	}

	@Override
	public void render() {
		checkForESC();
		ScreenUtils.clear(0, 0, 1, 1);
		camera.update();
		grid.update();
		drawGrid();
	}

	private void drawGrid() {
		shape.begin(ShapeRenderer.ShapeType.Filled);
		shape.setColor(0, 0, 0, 1);
		int[][] showGrid = grid.getGrid();
		int squareSize = grid.getSquareSize();

		int xOffSet = (screenWidth - (n * squareSize)) / 2;
		int yOffSet = (screenHeight - (m * squareSize)) / 2;

		for (int i = 0; i < showGrid.length; i++) {
			for (int j = 0; j < showGrid[i].length; j++) {
				if (showGrid[i][j] == 0) {
					shape.setColor(Color.WHITE);
					shape.rect(i * squareSize + xOffSet, j * squareSize + yOffSet, squareSize, squareSize);
				} else if (showGrid[i][j] == 1) {
					shape.setColor(Color.BLACK);
					shape.rect(i * squareSize + xOffSet, j * squareSize + yOffSet, squareSize, squareSize);
				} else if (showGrid[i][j] == 2) {
					shape.setColor(Color.RED);
					shape.rect(i * squareSize + xOffSet, j * squareSize + yOffSet, squareSize, squareSize);
				}
			}
		}

		shape.end();
	}

	public void checkForESC() {
		if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
			Gdx.app.exit();
		}
	}

	@Override
	public void dispose() {
		batch.dispose();
		shape.dispose();
	}

}
