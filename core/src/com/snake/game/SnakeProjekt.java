package com.snake.game;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class SnakeProjekt extends ApplicationAdapter {

	enum Scene {
		Main_Scene, Main_Game, Main_Setting, Main_Enable_Features
	}

	Scene currentSceen = Scene.Main_Game;
	SpriteBatch batch;
	Grid grid;
	ShapeRenderer shape;
	public static OrthographicCamera camera;
	FitViewport viewport;
	Texture snakeBodySprite;
	Texture snakeHeadSprite;
	Texture snakeHeadSidewaysSprite;
	int screenWidth;
	int screenHeight;
	private int n = 20;
	private int m = 20;

	@Override
	public void create() {

		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();
		batch = new SpriteBatch();
		snakeBodySprite = new Texture((Gdx.files.internal("snakebody.png")));
		snakeHeadSprite = new Texture((Gdx.files.internal("snakehead.png")));
		snakeHeadSidewaysSprite = new Texture((Gdx.files.internal("snakeheadsideways.png")));
		shape = new ShapeRenderer();
		camera = new OrthographicCamera();
		viewport = new FitViewport(screenWidth, screenHeight, camera);
		grid = new Grid(n, m);
	}

	@Override
	public void render() {
		checkForESC();
		switch (currentSceen) {
			case Main_Game:
			ScreenUtils.clear(0, 0, 1, 1);
			camera.update();
			grid.update();
			drawGrid();

			break;
		}
	}

	private void drawGrid() {
		shape.begin(ShapeRenderer.ShapeType.Filled);
		shape.setColor(0, 0, 0, 1);
		int[][] showGrid = grid.getGrid();
		int squareSize = grid.getSquareSize();

		for(int i = 0; i < showGrid.length; i++){
			for(int j = 0; j < showGrid[i].length; j++){
				if(showGrid[i][j] == 0){
					shape.setColor(Color.WHITE);
					shape.rect(i * squareSize, j * squareSize, squareSize, squareSize);
				}
				else if(showGrid[i][j] == 1){
					shape.setColor(Color.BLACK);
					shape.rect(i * squareSize, j * squareSize, squareSize, squareSize);
				}
				else if(showGrid[i][j] == 2){
					shape.setColor(Color.RED);
					shape.rect(i * squareSize, j * squareSize, squareSize, squareSize);
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
