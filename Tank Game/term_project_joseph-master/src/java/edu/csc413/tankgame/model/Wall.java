package edu.csc413.tankgame.model;

import edu.csc413.tankgame.WallImageInfo;

public class Wall extends Entity{
    private static final String WALL_ID_PREFIX = "wall-";
    private static long uniqueId = 0L;
    public Wall(double x, double y) {
        super(getUniqueId(), x, y, 0);
        setHealth(4);
    }


    private static String getUniqueId() {
        return WALL_ID_PREFIX + uniqueId++;
    }

    @Override
    public double getXBound() {
        return getX() + 32/2;
    }

    @Override
    public double getYBound() {
        return getY() + 32/2;
    }

    @Override
    public void move(GameState gameState) {    }

    @Override
    public void setPosition(double x, double y) {
    }
}
