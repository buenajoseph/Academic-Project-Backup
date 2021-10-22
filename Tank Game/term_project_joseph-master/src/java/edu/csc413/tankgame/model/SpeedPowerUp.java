package edu.csc413.tankgame.model;


//power up refills health and doubles speed and health for a set time
public class SpeedPowerUp extends Entity{
    private static final String POWER_UP_ID = "power-up";
    public static final String POWER_UP_IMAGE_FILE = "big-explosion-4.png";
    private static long uniqueId = 0L;

    public SpeedPowerUp(double x, double y) {
        super(POWER_UP_ID, x, y, 0);
        setHealth(1);
    }

    @Override
    public double getXBound() {
        return getX() + 120/2;
    }

    @Override
    public double getYBound() {
        return getY() + 120/2;
    }

    @Override
    public void move(GameState gameState) {    }

    @Override
    public void setPosition(double x, double y) {
    }
}
