package controllers;

import math.MathExt;

public class OutputFiltering {
    public static double toSteer(double angle, double desiredAngle) {
        return MathExt.clamp(MathExt.rad(desiredAngle - MathExt.rad(angle)), -1, +1);
    }

    public static double toThrottle(double speed, double desiredSpeed) {
        // This "0.99" comes from line 54 of Car.java
        return (speed / (1 - 0.99)) > desiredSpeed ? 0.0 : 1.0;
    }
}
