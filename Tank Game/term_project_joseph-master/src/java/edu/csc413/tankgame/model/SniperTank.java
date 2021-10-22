package edu.csc413.tankgame.model;

public class SniperTank extends Tank{

    public SniperTank(String id, double x, double y, double angle, GameState gameState) {
        super(id, x, y, angle);
        setTURN_SPEED(Math.toRadians(5));
    }

    public void move(GameState gameState) {
        super.move(gameState);
        if (!gameState.gameOver()) {
            Entity playerTank = gameState.getEntity(GameState.PLAYER_TANK_ID);
            double dx = playerTank.getX() - getX();
            double dy = playerTank.getY() - getY();
            double distance = Math.sqrt((dy * dy) + (dx * dx));
            double angleToPlayer = Math.atan2(dy, dx);
            double angleDifference = getAngle() - angleToPlayer;
            angleDifference -= Math.floor(angleDifference / Math.toRadians(360.0) + 0.5) *
                    Math.toRadians(360.0);

            if (distance < 400) {
                moveBackward();
            } else if (distance > 550) {
                moveForward();
            }
            if (angleDifference < -Math.toRadians(3.0)) {
                turnRight();
            } else if (angleDifference > Math.toRadians(3.0)) {
                turnLeft();
            }
        }
    }
}