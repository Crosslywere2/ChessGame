package com.chess.utils;

import com.crossly.utils.Coordinate;

public class Vector2 extends Coordinate implements Comparable<Vector2> {
    public Vector2() {
        super();
    }

    public Vector2(int x, int y) {
        super(x, y);
    }

    public Vector2(Coordinate c) {
        super(c);
    }

    public Vector2 add(int x, int y) {
        return new Vector2(this.getX() + x, this.getY() + y);
    }

    public Vector2 add(Vector2 that) {
        return add(that.getX(), that.getY());
    }

    public Vector2 sub(int x, int y) {
        return new Vector2(this.getX() - x, this.getY() - y);
    }

    public Vector2 sub(Vector2 that) {
        return sub(that.getX(), that.getY());
    }

    public Vector2 mul(double scalar) {
        return new Vector2((int)(this.getX() * scalar), (int)(this.getY() * scalar));
    }

    public Vector2 div(double scalar) {
        return mul(1 / scalar);
    }

    public Vector2 mod(int scalar) {
         return new Vector2(this.getX() % scalar, this.getY() % scalar);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public int compareTo(Vector2 o) {
        if (this.equals(o)) return 0;
        else {
            int thisMagSqr = getX() * getX() + getY() * getY();
            int thatMagSqr = o.getX() * o.getX() + o.getY() * o.getY();
            return (thisMagSqr > thatMagSqr ? 1 : -1);
        }
    }
}
