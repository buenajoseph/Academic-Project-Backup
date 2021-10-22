package edu.csc413.tankgame.model;

/**
 * Model class representing a tank in the game. A tank has a position and an angle. It has a movement speed and a turn
 * speed, both represented below as constants.
 */
// TODO: Notice that Tank has a lot in common with Shell. For full credit, you will need to find a way to share code
// between the two classes so that the logic for e.g. moveForward, etc. are not duplicated.
public class Tank extends Entity{
    private int cooldown;
    private int cdLimit;
    private boolean readyToShoot;
    public Tank(String id, double x, double y, double angle) {
        super(id, x, y, angle);
        setMOVEMENT_SPEED(1);
        setTURN_SPEED(Math.toRadians(3.0));
        setHealth(3);
        cooldown = 0;
        cdLimit = 125;
    }

    public void setPosition(double x, double y) {
        setX(x);
        setY(y);
    }

    @Override
    public double getXBound() {
        return getX() + 55;
    }

    @Override
    public double getYBound() {
        return getY() + 55;
    }

    @Override
    public void move(GameState gameState) {
        if (!readyToShoot) {
            cooldown++;
            if (cooldown > cdLimit) {
                readyToShoot = true;
                cooldown = 0;
            }
        }
    }
    protected void setCooldownLimit(int CDL) {
        cdLimit = CDL;
    }

    public boolean readyToShoot() {
        return readyToShoot;
    }
    public void setReadyToShoot(boolean input) {
        readyToShoot = input;
    }

    // The following methods will be useful for determining where a shell should be spawned when it
    // is created by this tank. It needs a slight offset so it appears from the front of the tank,
    // even if the tank is rotated. The shell should have the same angle as the tank.

    private double getShellX() {
        return getX() + 30.0 * (Math.cos(getAngle()) + 0.5);
    }

    private double getShellY() {
        return getY() + 30.0 * (Math.sin(getAngle()) + 0.5);
    }
}
