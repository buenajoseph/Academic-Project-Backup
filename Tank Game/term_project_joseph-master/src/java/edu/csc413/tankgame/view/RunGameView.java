package edu.csc413.tankgame.view;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

public class RunGameView extends JPanel {
  public static final Dimension SCREEN_DIMENSIONS = new Dimension(1024, 768);

  public static final String PLAYER_TANK_IMAGE_FILE = "player-tank.png";
  public static final double PLAYER_TANK_INITIAL_X = 250.0;
  public static final double PLAYER_TANK_INITIAL_Y = 200.0;
  public static final double PLAYER_TANK_INITIAL_ANGLE = Math.toRadians(0.0);

  public static final String AI_TANK_IMAGE_FILE = "ai-tank.png";
  public static final double AI_TANK_INITIAL_X = 700.0;
  public static final double AI_TANK_INITIAL_Y = 500.0;
  public static final double AI_TANK_INITIAL_ANGLE = Math.toRadians(180.0);

  public static final double AI_TANK_2_INITIAL_X = 700.0;
  public static final double AI_TANK_2_INITIAL_Y = 200.0;
  public static final double AI_TANK_2_INITIAL_ANGLE = Math.toRadians(180.0);

  public static final String SHELL_IMAGE_FILE = "shell.png";

  private static final String SHELL_EXPLOSION_FILE_PREFIX = "shell-explosion-";
  private static final String SHELL_EXPLOSION_FILE_SUFFIX = ".png";

  public static final AnimationResource SHELL_EXPLOSION_ANIMATION =
      new AnimationResource(SHELL_EXPLOSION_FILE_PREFIX, SHELL_EXPLOSION_FILE_SUFFIX, 6);
  public static final int SHELL_EXPLOSION_FRAME_DELAY = 3;
  public static final double SHELL_EXPLOSION_WIDTH = 32.0;
  public static final double SHELL_EXPLOSION_HEIGHT = 32.0;

  private static final String BIG_EXPLOSION_FILE_PREFIX = "big-explosion-";
  private static final String BIG_EXPLOSION_FILE_SUFFIX = ".png";

  public static final AnimationResource BIG_EXPLOSION_ANIMATION =
      new AnimationResource(BIG_EXPLOSION_FILE_PREFIX, BIG_EXPLOSION_FILE_SUFFIX, 7);
  public static final int BIG_EXPLOSION_FRAME_DELAY = 4;
  public static final double BIG_EXPLOSION_WIDTH = 64.0;
  public static final double BIG_EXPLOSION_HEIGHT = 64.0;

  private final BufferedImage worldImage;
  private final Map<String, DrawableEntity> drawableEntitiesById = new HashMap<>();
  private final List<Animation> animations = new LinkedList<>();

  public RunGameView() {
    worldImage = new BufferedImage(SCREEN_DIMENSIONS.width, SCREEN_DIMENSIONS.height, BufferedImage.TYPE_INT_RGB);
    setBackground(Color.BLACK);
  }

  public void reset() {
    drawableEntitiesById.clear();
  }

  public void addDrawableEntity(
      String id, String entityImageFile, double initialX, double initialY, double initialAngle) {
    DrawableEntity drawableEntity = new DrawableEntity(entityImageFile);
    drawableEntity.setLocationAndAngle(initialX, initialY, initialAngle);
    drawableEntitiesById.put(id, drawableEntity);
  }

  public void removeDrawableEntity(String id) {
    drawableEntitiesById.remove(id);
  }

  public void setDrawableEntityLocationAndAngle(String id, double x, double y, double angle) {
    drawableEntitiesById.get(id).setLocationAndAngle(x, y, angle);
  }

  public void addAnimation(AnimationResource animationResource, int frameDelay, double x, double y) {
    animations.add(new Animation(animationResource, frameDelay, x, y));
  }

  @Override
  public void paintComponent(Graphics g) {
    Graphics2D buffer = worldImage.createGraphics();
    buffer.setColor(Color.BLACK);
    buffer.fillRect(0, 0, SCREEN_DIMENSIONS.width, SCREEN_DIMENSIONS.height);

    for (DrawableEntity drawableEntity : drawableEntitiesById.values()) {
      buffer.drawImage(drawableEntity.getEntityImage(), drawableEntity.getAffineTransform(), null);
    }

    ListIterator<Animation> animationIterator = animations.listIterator();
    while (animationIterator.hasNext()) {
      Animation animation = animationIterator.next();
      Optional<BufferedImage> nextImage = animation.getImage();
      if (nextImage.isPresent()) {
        buffer.drawImage(nextImage.get(), (int) animation.getX(), (int) animation.getY(), null);
      } else {
        animationIterator.remove();
      }
    }

    g.drawImage(worldImage, 0, 0, null);
  }
}
