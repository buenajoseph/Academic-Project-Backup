package edu.csc413.tankgame.model;

public class AiTank extends Tank{
    public AiTank(String id, double x, double y, double angle) {
        super(id, x, y, angle);
    }
    public void move(GameState gameState) {
        super.move(gameState);
        moveForward();
        turnLeft();
    }
}
