package com.chess.utils;

import com.crossly.utils.Coordinate;

public class Vector2 extends Coordinate {
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

    public boolean equals(Object o) {
        if (!o.getClass().equals(this.getClass())) return false;
        Vector2 that = (Vector2)o;
        return this.getX() == that.getX() && this.getY() == that.getY();
    }
}
