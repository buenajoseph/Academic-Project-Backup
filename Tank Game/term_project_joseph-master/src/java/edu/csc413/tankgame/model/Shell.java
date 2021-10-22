package edu.csc413.tankgame.model;

/**
 * Model class representing a shell that has been fired by a tank. A shell has a position and an angle, as well as a
 * speed. Shells by default should be unable to turn and only move forward.
 */
// TODO: Notice that Shell has a lot in common with Tank. For full credit, you will need to find a way to share code
// between the two classes so that the logic for e.g. moveForward, etc. are not duplicated.
public class Shell extends Entity{
    private static final String SHELL_ID_PREFIX = "shell-";
    private static long uniqueId = 0L;

    public Shell(double x, double y, double angle, String tankId) {
        super(getUniqueId(tankId), x, y, angle);
        setMOVEMENT_SPEED(1.0);
        setHealth(1);
    }

    private static String getUniqueId(String input) {
        return SHELL_ID_PREFIX + uniqueId++ + " " + input;
    }

    @Override
    public double getXBound() {
        return getX() + 24/2;
    }

    @Override
    public double getYBound() {
        return getY() + 24/2;
    }

    @Override
    public void move(GameState gameState) {
        super.moveForward();
    }

    @Override
    public void setPosition(double x, double y) {
        setX(x);
        setY(y);
    }
}
