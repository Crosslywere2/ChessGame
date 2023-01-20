package com.chess;

import com.chess.utils.Vector2;
import com.crossly.gfx.Image;
import com.crossly.utils.Coordinate;

import java.util.Objects;

public class ChessPiece implements Comparable<ChessPiece> {
    @Override
    public int compareTo(ChessPiece o) {
        return Integer.compare(type.ordinal, o.type.ordinal);
    }

    public enum Color {
        WHITE(-1), BLACK(1);
        private final int increment;
        Color(int increment) {
            this.increment = increment;
        }
        public int getIncrement() {
            return increment;
        }
    }

    public enum Type {
        PAWN(1, "/White/pawn.png", "/Black/pawn1.png", 4, 1,
                (int fromIndex, Color fromColor, int thisIndex, Color thisColor) -> {
                    ChessPiece piece = (thisColor == Color.WHITE ? Chess.getWhitePieces() : Chess.getBlackPieces()).get(thisIndex);
                    if (fromColor == thisColor) {
                        piece.health = Math.max(piece.health + 1, piece.type.health);
                    } else {
                        piece.health = Math.max(piece.health - 1, 0);
                    }
                },
                () -> {
                }),
        ROOK(2, "/White/rook.png", "/Black/rook1.png", 6, 4,
                (int fromIndex, Color fromColor, int thisIndex, Color thisColor) -> {
                    ChessPiece piece = (thisColor == Color.WHITE ? Chess.getWhitePieces() : Chess.getBlackPieces()).get(thisIndex);
                    if (fromColor == thisColor) {
                        piece.health = Math.max(piece.health + 1, piece.type.health);
                    } else {
                        piece.health = Math.max(piece.health - 1, 0);
                    }
                },
                () -> {
                }),
        KNIGHT(3, "/White/knight.png", "/Black/knight1.png", 6, 8,
                (int fromIndex, Color fromColor, int thisIndex, Color thisColor) -> {
                    ChessPiece piece = (thisColor == Color.WHITE ? Chess.getWhitePieces() : Chess.getBlackPieces()).get(thisIndex);
                    if (fromColor == thisColor) {
                        piece.health = Math.max(piece.health + 1, piece.type.health);
                    } else {
                        piece.health = Math.max(piece.health - 1, 0);
                    }
                },
                () -> {
                }),
        BISHOP(4, "/White/bishop.png", "/Black/bishop1.png", 6, 4,
                (int fromIndex, Color fromColor, int thisIndex, Color thisColor) -> {
                    ChessPiece piece = (thisColor == Color.WHITE ? Chess.getWhitePieces() : Chess.getBlackPieces()).get(thisIndex);
                    if (fromColor == thisColor) {
                        piece.health = Math.max(piece.health + 1, piece.type.health);
                    } else {
                        piece.health = Math.max(piece.health - 1, 0);
                    }
                },
                () -> {
                }),
        QUEEN(5, "/White/queen.png", "/Black/queen1.png", 7, 9,
                (int fromIndex, Color fromColor, int thisIndex, Color thisColor) -> {
                    ChessPiece piece = (thisColor == Color.WHITE ? Chess.getWhitePieces() : Chess.getBlackPieces()).get(thisIndex);
                    if (fromColor == thisColor) {
                        piece.health = Math.max(piece.health + 1, piece.type.health);
                    } else {
                        piece.health = Math.max(piece.health - 1, 0);
                    }
                },
                () -> {
                }),
        KING(6, "/White/king.png", "/Black/king1.png", 1, 0,
                (int fromIndex, Color fromColor, int thisIndex, Color thisColor) -> {
                    ChessPiece piece = (thisColor == Color.WHITE ? Chess.getWhitePieces() : Chess.getBlackPieces()).get(thisIndex);
                    if (fromColor == thisColor) {
                        piece.health = Math.max(piece.health + 1, piece.type.health);
                    } else {
                        piece.health = Math.max(piece.health - 1, 0);
                    }
                },
                () -> {
                });
        private interface InputFunc {
            void response(int fromIndex, Color fromColor, int thisIndex, Color thisColor);
        }
        private interface OutputFunc {
            void action();
        }
        private final String whiteImagePath;
        private final String blackImagePath;
        private final int health;
        private final int actionPoints;
        private final int ordinal;
        Type(int ordinal, String whiteImagePath, String blackImagePath, int health, int actionPoints, InputFunc input, OutputFunc output) {
            this.ordinal = ordinal;
            this.whiteImagePath = whiteImagePath;
            this.blackImagePath = blackImagePath;
            this.health = health;
            this.actionPoints = actionPoints;
            this.input = input;
            this.output = output;
        }
        public int getOrdinal() {
            return ordinal;
        }
        public final InputFunc input;
        public final OutputFunc output;
        public int getActionPoints() {
            return actionPoints;
        }
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
    private final int id;
    public ChessPiece(int id, Color color, Type type, Vector2 position) {
        this.id = id;
        this.color = color;
        this.type = type;
        this.position = position;
        image = new Image(color == Color.WHITE ? type.getWhiteImagePath() : type.getBlackImagePath());
        health = type.health;
    }

    public ChessPiece(ChessPiece other) {
        this.id = other.id;
        this.color = other.color;
        this.type = other.type;
        this.position = other.position;
        this.health = other.health;
    }

    public int getId() {
        return id;
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
        type.output.action();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessPiece piece = (ChessPiece) o;
        return getColor() == piece.getColor() && getType() == piece.getType() && Objects.equals(getPosition(), piece.getPosition());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getColor(), getType(), getPosition());
    }
}
