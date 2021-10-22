package edu.csc413.tankgame.model;

import edu.csc413.tankgame.view.RunGameView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * GameState represents the state of the game "world." The GameState object tracks all of the moving entities like tanks
 * and shells, and provides the controller of the program (i.e. the GameDriver) access to whatever information it needs
 * to run the game. Essentially, GameState is the "data context" needed for the rest of the program.
 */
public class GameState {
    public static final double TANK_X_LOWER_BOUND = 30.0;
    public static final double TANK_X_UPPER_BOUND = RunGameView.SCREEN_DIMENSIONS.width - 100.0;
    public static final double TANK_Y_LOWER_BOUND = 30.0;
    public static final double TANK_Y_UPPER_BOUND = RunGameView.SCREEN_DIMENSIONS.height - 120.0;

    public static final double SHELL_X_LOWER_BOUND = -10.0;
    public static final double SHELL_X_UPPER_BOUND = RunGameView.SCREEN_DIMENSIONS.width;
    public static final double SHELL_Y_LOWER_BOUND = -10.0;
    public static final double SHELL_Y_UPPER_BOUND = RunGameView.SCREEN_DIMENSIONS.height;

    public static final String PLAYER_TANK_ID = "player-tank";
    public static final String AI_TANK_ID = "ai-tank-1";
    public static final String AI_TANK_ID2 = "ai-tank-2";
    // TODO: Feel free to add more tank IDs if you want to support multiple AI tanks! Just make sure they're unique.

    // TODO: Implement.
    // There's a lot of information the GameState will need to store to provide contextual information. Add whatever
    // instance variables, constructors, and methods are needed.
    private final List<Entity> entities = new ArrayList<>();
    private static boolean gameOver = false;
    private static int gameOverTimer = 0;

    private static int enemyCount = 0;

    public static void setEnemyCount(int enemyCount) {
        GameState.enemyCount = enemyCount;
    }

    public static int getEnemyCount() {
        return enemyCount;
    }

    public void setGameOver(boolean game) {
        gameOver = game;
    }
    public boolean gameOver() {
        return gameOver;
    }
    public boolean incrementGameOverTimer() {
        gameOverTimer++;
        return gameOverTimer > 500;
    }
    public void resetGameOver() {
        gameOverTimer = 0;
        enemyCount = 0;
    }

    public void clearEntities() {
        while (entities.size() >= 1) {
            entities.remove(0);
        }
    }

    public void addEntity(Entity entity) {

        entities.add(entity);
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public Entity getEntity(String id) {
        for (Entity entity : entities) {
            if (entity.getId().equals(id)) {
                return entity;
            }
        }
        return null;
    }

    private boolean wPressed = false;
    private boolean sPressed = false;
    private boolean aPressed = false;
    private boolean dPressed = false;
    private boolean spacePressed = false;
    private boolean exitButtonPressed = false;

    public boolean wPressed() {
        return wPressed;
    }
    public boolean sPressed() {
        return sPressed;
    }
    public boolean aPressed() {
        return aPressed;
    }
    public boolean dPressed() {
        return dPressed;
    }
    public boolean spacePressed() { return spacePressed; }
    public boolean exitButtonPressed() { return exitButtonPressed; }

    public void setPressW(boolean input) {
        wPressed = input;
    }
    public void setPressS(boolean input) {
        sPressed = input;
    }
    public void setPressA(boolean input) {
        aPressed = input;
    }
    public void setPressD(boolean input) {
        dPressed = input;
    }
    public void setPressSpace(boolean input) { spacePressed = input; }
    public void setExitButtonPressed(boolean input) { exitButtonPressed = input; }
}
