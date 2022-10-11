package controllers;

import engine.Car;
import engine.Game;
import engine.GameObject;
import math.Vec2;

public class ArriveController extends Controller {
    private GameObject target;

    public ArriveController(GameObject target) {
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

        double desiredDisplacement = desiredPos.dist(currentPos);

        if (isFacing(currentPos, subject.getAngle(), desiredPos)) {
            double desiredSpeed = desiredDisplacement / delta_t;
            double throttle = OutputFiltering.toThrottle(subject.getSpeed(), desiredSpeed);
            controlVariables[VARIABLE_THROTTLE] = throttle;
        }

        if (desiredDisplacement < 10) {
            controlVariables[VARIABLE_THROTTLE] = 0.0;
        }
    }

    private static boolean isFacing(Vec2 pos, double angle, Vec2 target) {
        return target.sub(pos).dot(Vec2.polar(angle, 1.0)) > 0.0;
    }
}
