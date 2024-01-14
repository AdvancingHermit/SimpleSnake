package com.snake.game;

import java.util.Scanner;

import com.badlogic.gdx.Graphics.DisplayMode;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.glutils.HdpiMode;
import com.snake.game.SnakeProjekt;
import com.badlogic.gdx.graphics.glutils.HdpiMode;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
    public static void main(String[] args) {
        int n;
        int m;
        if(args.length > 0){
            n = Math.min(Math.max(5, Integer.parseInt(args[0])), 100);
            m = Math.min(Math.max(5, Integer.parseInt(args[1])), 100);
        }
        else{
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the width of the grid: ");
            n = Math.min(Math.max(5, scanner.nextInt()), 100);
            System.out.println("Enter the height of the grid: ");
            m = Math.min(Math.max(5, scanner.nextInt()), 100);
            scanner.close();
        }
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        DisplayMode desktopMode = Lwjgl3ApplicationConfiguration.getDisplayMode();
        config.setFullscreenMode(desktopMode);;
        config.setHdpiMode(HdpiMode.Pixels);
        config.setForegroundFPS(30);
        config.setTitle("IntroSnakeProjekt");
        new Lwjgl3Application(new SnakeProjekt(n, m), config);
    }
}