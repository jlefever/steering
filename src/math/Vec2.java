package math;

public class Vec2 {
    public double x, y;

    public Vec2() {
        this.x = 0.0;
        this.y = 0.0;
    }

    public Vec2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static Vec2 polar(double ang, double len) {
        return new Vec2(len * Math.cos(ang), len * Math.sin(ang));
    }

    public double getX() {
        return this.x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return this.y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getAng() {
        return Math.atan2(this.y, this.x);
    }

    public void setAng(double ang) {
        double len = this.getLen();
        this.x = len * Math.cos(ang);
        this.y = len * Math.sin(ang);
    }

    public double getLen() {
        return Math.sqrt((this.x * this.x) + (this.y * this.y));
    }

    public void setLen(double len) {
        double ang = this.getAng();
        this.x = len * Math.cos(ang);
        this.y = len * Math.sin(ang);
    }

    public Vec2 clone() {
        return new Vec2(this.x, this.y);
    }

    public void addi(Vec2 v) {
        this.x += v.x;
        this.y += v.y;
    }

    public Vec2 add(Vec2 v) {
        Vec2 out = this.clone();
        out.addi(v);
        return out;
    }

    public void subi(Vec2 v) {
        this.x -= v.x;
        this.y -= v.y;
    }

    public Vec2 sub(Vec2 v) {
        Vec2 out = this.clone();
        out.subi(v);
        return out;
    }

    public double dot(Vec2 v) {
        return (this.x * v.x) + (this.y * v.y);
    }

    public double dist(Vec2 v) {
        Vec2 diff = this.sub(v);
        return Math.sqrt(diff.dot(diff));
    }

    public void rotatei(double ang) {
        double cos = Math.cos(ang);
        double sin = Math.sin(ang);
        double x = this.x;
        this.x = x * cos + this.y * sin;
        this.y = -x * sin + this.y * cos;
    }

    public Vec2 rotate(double ang) {
        Vec2 out = this.clone();
        out.rotatei(ang);
        return out;
    }

    public String toString() {
        return "[" + this.x + ", " + this.y + "]";
    }
}
