package edu.csc413.tankgame.model;

public abstract class Entity {
    protected double MOVEMENT_SPEED;
    protected double TURN_SPEED;
    protected boolean readyToShoot;

    private final String id;
    private double x;
    private double y;
    private double angle;
    private int health;
    private boolean powerUpEnabled = false;
    private int powerUpCountdown = 0;

    public Entity(String id, double x, double y, double angle) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.angle = angle;
    }

    public void setMOVEMENT_SPEED(double MOVEMENT_SPEED) {
        this.MOVEMENT_SPEED = MOVEMENT_SPEED;
    }
    public void setTURN_SPEED(double TURN_SPEED) {
        this.TURN_SPEED = TURN_SPEED;
    }

    public String getId() {
        return id;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getAngle() {
        return angle;
    }

    // TODO: The methods below are provided so you don't have to do the math for movement. However, note that they are
    // protected. You should not be calling these methods directly from outside the Tank class hierarchy. Instead,
    // consider how to design your Tank class(es) so that a Tank can represent both a player-controlled tank and an AI
    // controlled tank.


    public void setPowerUpEnabled(boolean powerUpEnabled) {
        this.powerUpEnabled = powerUpEnabled;
    }
    public boolean getPowerUpEnabled() {
        return powerUpEnabled;
    }
    public void incrementPowerUpCountdown() {
        powerUpCountdown++;
        if (powerUpCountdown > 1000) {
            setMOVEMENT_SPEED(1);
            powerUpEnabled = false;
            powerUpCountdown = 0;
        }
    }

    public void setHealth(int health) {
        this.health = health;
    }
    public int getHealth() {
        return health;
    }

    public boolean readyToShoot() {
        return readyToShoot;
    }
    public void setReadyToShoot(boolean input) {
        readyToShoot = input;
    }

    public abstract double getXBound();
    public abstract double getYBound();
    public abstract void move(GameState gameState);

    protected void moveForward() {
        x += MOVEMENT_SPEED * Math.cos(angle);
        y += MOVEMENT_SPEED * Math.sin(angle);
    }

    protected void moveBackward() {
        x -= MOVEMENT_SPEED * Math.cos(angle);
        y -= MOVEMENT_SPEED * Math.sin(angle);
    }

    protected void turnLeft() {
        angle -= TURN_SPEED;
    }

    protected void turnRight() {
        angle += TURN_SPEED;
    }

    public abstract void setPosition(double x, double y);
}
