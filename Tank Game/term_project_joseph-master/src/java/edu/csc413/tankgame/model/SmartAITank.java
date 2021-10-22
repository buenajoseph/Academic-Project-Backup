package edu.csc413.tankgame.model;

public class SmartAITank extends Tank{
    public SmartAITank(String id, double x, double y, double angle, GameState gameState) {
        super(id, x, y, angle);
        setTURN_SPEED(Math.toRadians(3.0));
        setCooldownLimit(200);
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
            boolean dodging = false;

            for (int in = 0; in < gameState.getEntities().size() - 1; in++) {
                if (!gameState.getEntities().get(in).getId().equals(getId())) {
                    Entity e2 = gameState.getEntities().get(in);

                    if (entitiesNear(e2)) {
                        if (e2.getId().contains("shell") && !e2.getId().contains(getId())) {
                            dodging = true;
                            double dxShell = e2.getX() - getX();
                            double dyShell = e2.getY() - getY();
                            double distanceShell = Math.sqrt((dyShell * dyShell) + (dxShell * dxShell));
                            double angleToShell = Math.atan2(dyShell, dxShell);
                            double angleDifferenceToShell = getAngle() - angleToShell;
                            angleDifferenceToShell -= Math.floor(angleDifferenceToShell / Math.toRadians(360.0) + 0.5) *
                                    Math.toRadians(360.0);
                            if (angleDifferenceToShell < -Math.toRadians(3.0)) {
                                turnRight();
                            } else if (angleDifferenceToShell > Math.toRadians(3.0)) {
                                turnLeft();
                            }
                            moveBackward();
                        }
                    }
                }
            }
            if (!entitiesNear(playerTank) && !dodging) {
                if (distance < 250) {
                    moveBackward();
                } else if (distance > 350) {
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
    private boolean entitiesNear(Entity entity2) {
        return getX() < entity2.getXBound() + 50
                && getXBound() > entity2.getX() - 50
                && getY() < entity2.getYBound() + 50
                && getYBound() > entity2.getY() - 50;
    }
}
