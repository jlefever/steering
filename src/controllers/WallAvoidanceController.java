package controllers;

import engine.Car;
import engine.Game;
import engine.GameObject;
import engine.Obstacle;
import engine.RotatedRectangle;
import math.MathExt;
import math.Vec2;

public class WallAvoidanceController extends Controller {
    private Controller inner;

    public WallAvoidanceController(Controller inner) {
        this.inner = inner;
    }

    @Override
    public void update(Car subject, Game game, double delta_t, double[] controlVariables) {
        inner.update(subject, game, delta_t, controlVariables);
        double steering = controlVariables[VARIABLE_STEERING];

        // Wall Avoidance
        Vec2 currentPos = new Vec2(subject.getX(), subject.getY());
        double carWidth = subject.getCollisionBox().getWidth();
        double carHeight = subject.getCollisionBox().getHeight();

        // Send out three rays
        double angle = MathExt.rad(subject.getAngle());
        double frontCount = cast(game, currentPos, carWidth, carHeight, angle);
        double rightCount = cast(game, currentPos, carWidth, 5, angle + Math.PI / 4);
        double leftCount = cast(game, currentPos, carWidth, 5, angle - Math.PI / 4);

        final int MIN_DIST = 3;
        final int MAX_DIST = 6;
        double intensity = intensity(frontCount, MIN_DIST, MAX_DIST);
        double unit = leftCount <= rightCount ? 1 : -1;

        // Blend
        controlVariables[VARIABLE_STEERING] = ((1 - intensity) * steering) + (intensity * unit);
    }

    public int cast(Game game, Vec2 startingPos, double carWidth, double carHeight, double angle) {
        int count = 0;
        Vec2 pos = startingPos;

        while (true) {
            pos = pos.sub(Vec2.polar(angle, (carWidth * 2) + 1));
            RotatedRectangle rect = new RotatedRectangle(pos.getX(), pos.getY(), carWidth, carHeight, angle);
            GameObject collidedObj = game.collision(rect);

            if (collidedObj != null && collidedObj instanceof Obstacle) {
                return count;
            }

            count += 1;
        }
    }

    public double intensity(double value, int min, int max) {
        if (value < min)
            return 1.0;
        if (value > max)
            return 0.0;
        return 1 - ((value - min) / (max - min));
    }
}
