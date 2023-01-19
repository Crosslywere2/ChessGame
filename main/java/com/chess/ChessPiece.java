package com.chess;

import com.chess.utils.Vector2;
import com.crossly.gfx.Image;
import com.crossly.utils.Coordinate;

public class ChessPiece implements Comparable<ChessPiece> {
    @Override
    public int compareTo(ChessPiece o) {
        return this.type.getCompare().compareTo(o.type.getCompare());
    }

    public enum Color {
        WHITE(-1), BLACK(1);
        Color(int increment) {
            this.increment = increment;
        }
        private int increment = 0;
        public int getIncrement() {
            return increment;
        }
    }

    public enum Type {
        PAWN("a", "/White/pawn.png", "/Black/pawn1.png", 5, 3, null, null),
        ROOK("b", "/White/rook.png", "/Black/rook1.png", 6, 4, null, null),
        KNIGHT("c", "/White/knight.png", "/Black/knight1.png", 0, 0, null, null),
        BISHOP("d", "/White/bishop.png", "/Black/bishop1.png", 0, 0,null, null),
        QUEEN("e", "/White/queen.png", "/Black/queen1.png", 7, 9, null, null),
        KING("f", "/White/king.png", "/Black/king1.png", 1, 0, null, null);
        private interface InputFunc {
            void input(int fromIndex, Color fromColor, int thisIndex, Color thisColor);
        }
        private interface OutputFunc {
            void output();
        }
        private String whiteImagePath;
        private String blackImagePath;
        private int health;
        private float actionPoints;
        private String compare;
        Type(String compare, String whiteImagePath, String blackImagePath, int health, int actionPoints, InputFunc input, OutputFunc output) {
            this.compare = compare;
            this.whiteImagePath = whiteImagePath;
            this.blackImagePath = blackImagePath;
            this.health = health;
            this.actionPoints = actionPoints;
            this.input = input;
            this.output = output;
        }
        public String getCompare() {
            return compare;
        }
        public final InputFunc input;
        public final OutputFunc output;
        public String getWhiteImagePath() {
            return whiteImagePath;
        }
        public String getBlackImagePath() {
            return blackImagePath;
        }
    }

    private Color color;
    private Type type;
    private Vector2 position;
    private Image image;
    private int health;

    public ChessPiece(Color color, Type type, Vector2 position) {
        this.color = color;
        this.type = type;
        this.position = position;
        image = new Image(color == Color.WHITE ? type.getWhiteImagePath() : type.getBlackImagePath());
        health = type.health;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
        setType(type);
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
        image = new Image(color == Color.WHITE ? type.getWhiteImagePath() : type.getBlackImagePath());
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Coordinate position) {
        this.position.setX(Math.max(Math.min(position.getX(), maxPos.getX()), minPos.getX()));
        this.position.setY(Math.max(Math.min(position.getY(), maxPos.getY()), minPos.getY()));
    }

    public Image getImage() {
        return image;
    }

    public int getHealth() {
        return health;
    }

    public void outputTick() {
        type.output.output();
    }

    private static Coordinate minPos;
    public static Coordinate getMinPos() {
        return minPos;
    }
    public static void setMinPos(Coordinate coordinate) {
        minPos = coordinate;
    }
    private static Coordinate maxPos;
    public static Coordinate getMaxPos() {
        return maxPos;
    }
    public static void setMaxPos(Coordinate coordinate) {
        maxPos = coordinate;
    }

}
