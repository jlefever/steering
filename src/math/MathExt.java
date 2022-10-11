package math;

public class MathExt {
    public static double floorMod(double a, double n) {
        return a - (n * Math.floor(a / n));
    }

    public static double floorRad(double rad) {
        return MathExt.floorMod(rad, Math.PI * 2);
    }

    public static double rad(double rad) {
        return MathExt.floorRad(rad) - Math.PI;
    }

    public static double clamp(double value, double min, double max) {
        if (value > max) return max;
        if (value < min) return min;
        return value;
    }

    public static double log(double a, double b) {
        return Math.log(a) / Math.log(b);
    }
}
