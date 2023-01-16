package com.chess;

import com.chess.utils.Vector2;
import com.crossly.gfx.Image;
import com.crossly.utils.Coordinate;

public class ChessPiece {
    private Image image;
    private Coordinate pos;
    private static Coordinate maxPos = new Coordinate();
    private static Coordinate minPos = new Coordinate();
    public ChessPiece(String imagePath, Coordinate position) {
        image = new Image(imagePath);
        pos = position;
    }

    public static void setMinPos(Coordinate pos) {
        minPos = pos;
    }

    public static void setMaxPos(Coordinate pos) {
        maxPos = pos;
    }

    public Coordinate getPos() {
        return pos;
    }

    public void setPos(Coordinate pos) {
        this.pos.setX(Math.max(Math.min(pos.getX(), maxPos.getX()), minPos.getX()));
        this.pos.setY(Math.max(Math.min(pos.getY(), maxPos.getY()), minPos.getY()));
    }

    public Image getImage() {
        return image;
    }

    public Vector2 getImageSize() {
        return new Vector2(image.getWidth(), image.getHeight());
    }
}
