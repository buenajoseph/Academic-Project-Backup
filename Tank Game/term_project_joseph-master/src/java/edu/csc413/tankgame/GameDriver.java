package edu.csc413.tankgame;

import com.sun.tools.javac.Main;
import edu.csc413.tankgame.model.GameState;
import edu.csc413.tankgame.view.MainView;
import edu.csc413.tankgame.view.RunGameView;
import edu.csc413.tankgame.model.*;
import edu.csc413.tankgame.view.StartMenuView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * GameDriver is the primary controller class for the tank game. The game is launched from GameDriver.main, and
 * GameDriver is responsible for running the game loop while coordinating the views and the data models.
 */

public class GameDriver {
    // TODO: Implement.
    // Add the instance variables, constructors, and other methods needed for this class. GameDriver is the centerpiece
    // for the tank game, and should store and manage the other components (i.e. the views and the models). It also is
    // responsible for running the game loop.
    private final MainView mainView;
    private final RunGameView runGameView;
    private final GameState gameState;


    public GameDriver() {
        MenuSelectListener menuSelectListener = new MenuSelectListener();
        gameState = new GameState();
        mainView = new MainView(menuSelectListener, gameState);
        runGameView = mainView.getRunGameView();
    }

    public void start() {
        // TODO: Implement.
        // This should set the MainView's screen to the start menu screen.
        mainView.setScreen(MainView.Screen.START_MENU_SCREEN);
    }

    public class MenuSelectListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String actCommand = e.getActionCommand();
            if(actCommand.equals(StartMenuView.START_BUTTON_ACTION_COMMAND)) {
                gameState.setGameOver(false);
                mainView.setScreen(MainView.Screen.RUN_GAME_SCREEN);
                runGame();
            }
            else if (actCommand.equals(StartMenuView.EXIT_BUTTON_ACTION_COMMAND)) {
                mainView.closeGame();
            }
        }
    }


    private void runGame() {
        PlayerTank playerTank = new PlayerTank(GameState.PLAYER_TANK_ID, RunGameView.PLAYER_TANK_INITIAL_X,
                RunGameView.PLAYER_TANK_INITIAL_Y, RunGameView.PLAYER_TANK_INITIAL_ANGLE);
        SmartAITank aiTank = new SmartAITank(GameState.AI_TANK_ID, RunGameView.AI_TANK_INITIAL_X,
                RunGameView.AI_TANK_INITIAL_Y, RunGameView.AI_TANK_INITIAL_ANGLE, gameState);
        SniperTank aiTank2 = new SniperTank(GameState.AI_TANK_ID2, RunGameView.AI_TANK_2_INITIAL_X,
                RunGameView.AI_TANK_2_INITIAL_Y, RunGameView.AI_TANK_2_INITIAL_ANGLE,
                gameState);
        gameState.addEntity(playerTank);
        gameState.addEntity(aiTank);
        gameState.setEnemyCount(gameState.getEnemyCount() + 1);
        gameState.addEntity(aiTank2);
        gameState.setEnemyCount(gameState.getEnemyCount() + 1);

        SpeedPowerUp powerUp = new SpeedPowerUp(480, 50);
        gameState.addEntity(powerUp);

        for (WallImageInfo wallInfo : WallImageInfo.readWalls()) {
            Wall wall = new Wall(wallInfo.getX(), wallInfo.getY());
            gameState.addEntity(wall);
            runGameView.addDrawableEntity(wall.getId(), wallInfo.getImageFile(), wall.getX(),
                    wall.getY(), wall.getAngle());
        }

        runGameView.addDrawableEntity(GameState.PLAYER_TANK_ID, RunGameView.PLAYER_TANK_IMAGE_FILE,
                playerTank.getX(), playerTank.getY(), playerTank.getAngle());
        runGameView.addDrawableEntity(GameState.AI_TANK_ID, RunGameView.AI_TANK_IMAGE_FILE,
                aiTank.getX(), aiTank.getY(), aiTank.getAngle());
        runGameView.addDrawableEntity(GameState.AI_TANK_ID2, RunGameView.AI_TANK_IMAGE_FILE,
                aiTank2.getX(), aiTank2.getY(), aiTank2.getAngle());
        runGameView.addDrawableEntity(powerUp.getId(), powerUp.POWER_UP_IMAGE_FILE,
                powerUp.getX(), powerUp.getY(), powerUp.getAngle());

        Runnable gameRunner = () -> {
            while (update()) {
                runGameView.repaint();
                try {
                    Thread.sleep(8L);
                } catch (InterruptedException exception) {
                    throw new RuntimeException(exception);
                }
            }
        };
        new Thread(gameRunner).start();
    }

    // TODO: Implement.
    // update should handle one frame of gameplay. All tanks and shells move one step, and all drawn entities
    // should be updated accordingly. It should return true as long as the game continues.
    private boolean update() {
        // esc pressed
        if(gameState.exitButtonPressed()) {
            mainView.setScreen(MainView.Screen.END_MENU_SCREEN);
            gameState.setExitButtonPressed(false);
            gameState.clearEntities();
            gameState.resetGameOver();
            runGameView.reset();
            return false;
        }
        // power up counter checker
        if (!gameState.gameOver()) {
            if (gameState.getEntity(GameState.PLAYER_TANK_ID).getPowerUpEnabled()) {
                gameState.getEntity(GameState.PLAYER_TANK_ID).incrementPowerUpCountdown();
            }
        }
        // end game timer
        if (gameState.gameOver()) {
            if (gameState.incrementGameOverTimer()) {
                mainView.setScreen(MainView.Screen.END_MENU_SCREEN);
                gameState.setExitButtonPressed(false);
                gameState.clearEntities();
                gameState.resetGameOver();
                runGameView.reset();
                return false;
            }
        }
        // Ask all entities to move
        for (Entity entity : gameState.getEntities()) {
            entity.move(gameState);
        }

        // Ask all entities to check bounds
        for (Entity entity : gameState.getEntities()) {
            if (!entity.getId().contains("shell")) {
                if (entity.getX() < gameState.TANK_X_LOWER_BOUND) {
                    entity.setPosition(gameState.TANK_X_LOWER_BOUND, entity.getY());
                }
                if (entity.getX() > gameState.TANK_X_UPPER_BOUND) {
                    entity.setPosition(gameState.TANK_X_UPPER_BOUND, entity.getY());
                }
                if (entity.getY() < gameState.TANK_Y_LOWER_BOUND) {
                    entity.setPosition(entity.getX(), gameState.TANK_Y_LOWER_BOUND);
                }
                if (entity.getY() > gameState.TANK_Y_UPPER_BOUND) {
                    entity.setPosition(entity.getX(), gameState.TANK_Y_UPPER_BOUND);
                }
            }
            else {
                if (entity.getX() < gameState.SHELL_X_LOWER_BOUND ||
                        entity.getX() > gameState.SHELL_X_UPPER_BOUND ||
                        entity.getY() < gameState.SHELL_Y_LOWER_BOUND ||
                        entity.getY() > gameState.SHELL_Y_UPPER_BOUND) {
                    entity.setHealth(0);
                }
            }

        }

        // Collision check
        for (int in = 0; in < gameState.getEntities().size() - 1; in++) {
            for (int j = in + 1; j < gameState.getEntities().size(); j++) {
                if (entitiesOverlap(gameState.getEntities().get(in), gameState.getEntities().get(j))) {
                    handleCollision(gameState.getEntities().get(in), gameState.getEntities().get(j));
                }
            }
        }

        // GameState - new entities to draw
        // if so, call addDrawableEntity
            for (int in = 0; in < gameState.getEntities().size(); in++) {
                Entity entity = gameState.getEntities().get(in);
                if (!entity.getId().contains("shell")) {
                    if (entity.readyToShoot()) {
                        Shell shell;
                        double x = 0, y = 0, angle = 0;
                        boolean shoot = false;
                        if (entity.getId().equals(GameState.PLAYER_TANK_ID) && gameState.spacePressed() &&
                        entity.getHealth() > 0) {
                            x = gameState.getEntity(GameState.PLAYER_TANK_ID).getX() + 35.0 *
                                    (Math.cos(gameState.getEntity(GameState.PLAYER_TANK_ID).getAngle()) + 0.5);
                            y = gameState.getEntity(GameState.PLAYER_TANK_ID).getY() + 35.0 *
                                    (Math.sin(gameState.getEntity(GameState.PLAYER_TANK_ID).getAngle()) + 0.5);
                            angle = gameState.getEntity(GameState.PLAYER_TANK_ID).getAngle();
                            shoot = true;
                        }
                        else {
                            if (!entity.getId().equals(GameState.PLAYER_TANK_ID) && entity.readyToShoot() &&
                            !gameState.gameOver()) {
                                x = entity.getX() + 35.0 *
                                        (Math.cos(entity.getAngle()) + 0.5);
                                y = entity.getY() + 35.0 *
                                        (Math.sin(entity.getAngle()) + 0.5);
                                angle = entity.getAngle();
                                shoot = true;
                            }
                        }

                        if (shoot) {
                            shell = new Shell(x, y, angle, entity.getId());
                            gameState.addEntity(shell);
                            runGameView.addDrawableEntity(shell.getId(), RunGameView.SHELL_IMAGE_FILE,
                                    shell.getX(), shell.getY(), shell.getAngle());
                            entity.setReadyToShoot(false);
                        }
                    }
                }
            }

        // GameState - new entities to remove
        // if so, call removeDrawableEntity
        for (int in = 0; in < gameState.getEntities().size(); in++) {
            if (gameState.getEntities().get(in).getHealth() == 0) {
                if (gameState.getEntities().get(in).getId() == GameState.PLAYER_TANK_ID){
                    gameState.setGameOver(true);
                }

                if (gameState.getEntities().get(in).getId().contains("ai-tank") &&
                        !gameState.getEntities().get(in).getId().contains("shell")) {
                    gameState.setEnemyCount(gameState.getEnemyCount() - 1);
                    if (gameState.getEnemyCount() < 1) {
                        gameState.setGameOver(true);
                    }
                }

                runGameView.removeDrawableEntity(gameState.getEntities().get(in).getId());
                gameState.getEntities().remove(in);
                in--;
            }
        }

        // set new locations and angles of every drawable entity
        for (Entity entity : gameState.getEntities()) {
            runGameView.setDrawableEntityLocationAndAngle(entity.getId(), entity.getX(), entity.getY(), entity.getAngle());
        }
        return true;
    }

    //given through handout
    private boolean entitiesOverlap(Entity entity1, Entity entity2) {
        return entity1.getX() < entity2.getXBound()
                && entity1.getXBound() > entity2.getX()
                && entity1.getY() < entity2.getYBound()
                && entity1.getYBound() > entity2.getY();
    }

    private void handleCollision(Entity entity1, Entity entity2) {
        // variable name: overlap(direction tank1 moves)
        double overlapLeft = entity1.getXBound() - entity2.getX();
        double overlapRight = entity2.getXBound() - entity1.getX();
        double overlapUp= entity1.getYBound() - entity2.getY();
        double overlapDown = entity2.getYBound() - entity1.getY();
        double min = Math.min(
                Math.min(overlapLeft, overlapRight), Math.min(overlapDown, overlapUp));
        if(entity1.getId().contains("tank") && entity2.getId().contains("tank") &&
        !entity1.getId().contains("shell") && !entity2.getId().contains("shell"))
        {
            //tank-tank
            //action
            if (min == overlapRight) {
                //entity1.setPosition(entity1.getX() - (1/2)*min*10, entity1.getY());
                entity1.setPosition(entity1.getX() + min,entity1.getY());
                entity2.setPosition(entity2.getX() - min, entity2.getY());
                //System.out.println("overlap right");
            }
            else if (min == overlapLeft) {
                entity1.setPosition(entity1.getX() - min, entity1.getY());
                entity2.setPosition(entity2.getX() + min, entity2.getY());
                //System.out.println("overlap left");
            }
            else if (min == overlapUp) {
                entity1.setPosition(entity1.getX(), entity1.getY() - min);
                entity2.setPosition(entity2.getX(), entity2.getY() + min);
                //System.out.println("overlap up");
            }
            else if (min == overlapDown){
                entity1.setPosition(entity1.getX(), entity1.getY() + min);
                entity2.setPosition(entity2.getX(), entity2.getY() - min);
                //System.out.println("overlap down");
            }
        }
        else if(entity1.getId().contains("tank") && entity2.getId().contains("shell"))
        {
            //tank-shell
            if(min < 55 && !entity2.getId().contains(entity1.getId())) {
                entity1.setHealth(entity1.getHealth() - 1);
                entity2.setHealth(0);
            }

        }
        else if(entity1.getId().contains("tank") && entity2.getId().contains("wall") &&
        !entity1.getId().contains("shell"))
        {
            //tank-wall
            if (min == overlapRight) {
                //entity1.setPosition(entity1.getX() - (1/2)*min*10, entity1.getY());
                entity1.setPosition(entity1.getX() + min,entity1.getY());
                //System.out.println("overlap right");
            }
            else if (min == overlapLeft) {
                entity1.setPosition(entity1.getX() - min, entity1.getY());
                //System.out.println("overlap left");
            }
            else if (min == overlapUp) {
                entity1.setPosition(entity1.getX(), entity1.getY() - min);
                //System.out.println("overlap up");
            }
            else if (min == overlapDown){
                entity1.setPosition(entity1.getX(), entity1.getY() + min);
                //System.out.println("overlap down");
            }
        }
        else if(entity1.getId().contains("shell") && entity2.getId().contains("shell"))
        {
            //shell-shell
            if(min < 24) {
                entity1.setHealth(0);
                entity2.setHealth(0);
            }
        }
        else if(entity1.getId().contains("wall") && entity2.getId().contains("shell"))
        {
            //shell-wall
            if(min < 32) {
                entity1.setHealth(entity1.getHealth() - 1);
                entity2.setHealth(0);
            }
        }
        else if (entity1.getId().contains("tank") && entity2.getId().contains("power-up") &&
        !entity1.getId().contains("shell")) {
            entity2.setHealth(0);
            entity1.setPowerUpEnabled(true);
            entity1.setHealth(8);
            entity1.setMOVEMENT_SPEED(2);
        }
    }


    public static void main(String[] args) {
        GameDriver gameDriver = new GameDriver();
        gameDriver.start();
    }


}
