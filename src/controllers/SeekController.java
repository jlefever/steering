package controllers;

import engine.Car;
import engine.Game;
import engine.GameObject;
import math.Vec2;

public class SeekController extends Controller {
    private GameObject target;

    public SeekController(GameObject target) {
        this.target = target;
    }

    @Override
    public void update(Car subject, Game game, double delta_t, double[] controlVariables) {
        Vec2 currentPos = new Vec2(subject.getX(), subject.getY());
        Vec2 desiredPos = new Vec2(this.target.getX(), this.target.getY());
        double desiredAngle = desiredPos.sub(currentPos).getAng();

        controlVariables[VARIABLE_STEERING] = OutputFiltering.toSteer(subject.getAngle(), desiredAngle);
        controlVariables[VARIABLE_THROTTLE] = 1.0;
        controlVariables[VARIABLE_BRAKE] = 0;
    }
}
