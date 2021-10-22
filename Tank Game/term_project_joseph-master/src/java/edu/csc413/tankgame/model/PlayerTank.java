package edu.csc413.tankgame.model;

public class PlayerTank extends Tank{
    public PlayerTank(String id, double x, double y, double angle) {
        super(id, x, y, angle);
        // godmode:setHealth(10000);
    }

    public void move(GameState gameState) {
        super.move(gameState);
        if (gameState.wPressed()) {
            moveForward();
        }
        if (gameState.sPressed()) {
            moveBackward();
        }
        if (gameState.aPressed()) {
            turnLeft();
        }
        if (gameState.dPressed()) {
            turnRight();
        }
    }
}
