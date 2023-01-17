package com.chess;

import com.chess.utils.Vector2;
import com.crossly.gfx.Image;
import com.crossly.utils.Coordinate;

public class ChessPiece {
    public enum Color {
        WHITE, BLACK;
    }
    public enum Type {
        PAWN, ROOK, KNIGHT, BISHOP, QUEEN, KING;
        private String whitePath;
        private String blackPath;
        public void setBlackPath(String blackPath) {
            this.blackPath = blackPath;
        }
        public String getBlackPath() {
            return blackPath;
        }
        public void setWhitePath(String whitePath) {
            this.whitePath = whitePath;
        }
        public String getWhitePath() {
            return whitePath;
        }
    }
    private Image image;
    private Coordinate pos;
    private Type type;
    private Color color;
    private static Coordinate maxPos = new Coordinate();
    private static Coordinate minPos = new Coordinate();
    public ChessPiece(String imagePath, Coordinate position, Type type, Color color) {
        image = new Image(imagePath);
        pos = position;
        if (color == Color.WHITE) type.setWhitePath(imagePath);
        else type.setBlackPath(imagePath);
        this.type = type;
        this.color = color;
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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
        image = new Image(color == Color.WHITE ? type.getWhitePath() : type.getBlackPath());
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
        setType(type);
    }
}
