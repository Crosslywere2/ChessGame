package com.chess;

import com.chess.utils.Vector2;
import com.crossly.GameContainer;
import com.crossly.GameManager;
import com.crossly.utils.Coordinate;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Chess extends GameManager {

    private static final int gridSize = 42;

    private static final int width = gridSize * 12;
    private static final int height = gridSize * 9;
    private static final int scale = 2;

    private static ArrayList<ChessPiece> white = new ArrayList<>(16);
    private static ArrayList<ChessPiece> black = new ArrayList<>(16);

    public Chess(GameContainer gc) {
        super(gc);
        ChessPiece.setMaxPos(new Vector2(7, 7).mul(gridSize));
        for (int i = 0; i < 16; i++) {
            if (i < 8) {
                white.add(i, new ChessPiece("/White/pawn.png", new Coordinate(i * gridSize, 6 * gridSize)));
                black.add(i, new ChessPiece("/Black/pawn1.png", new Coordinate(i * gridSize, gridSize)));
            } else if (i == 8 || i == 15) {
                white.add(i, new ChessPiece("/White/rook.png", new Coordinate((i - 8) * gridSize, 7 * gridSize)));
                black.add(i, new ChessPiece("/Black/rook1.png", new Coordinate((i - 8) * gridSize, 0)));
            } else if (i == 9 || i == 14) {
                white.add(i, new ChessPiece("/White/knight.png", new Coordinate((i - 8) * gridSize, 7 * gridSize)));
                black.add(i, new ChessPiece("/Black/knight1.png", new Coordinate((i - 8) * gridSize, 0)));
            } else if (i == 10 || i == 13) {
                white.add(i, new ChessPiece("/White/bishop.png", new Coordinate((i - 8) * gridSize, 7 * gridSize)));
                black.add(i, new ChessPiece("/Black/bishop1.png", new Coordinate((i - 8) * gridSize, 0)));
            } else if (i == 11) {
                white.add(i, new ChessPiece("/White/queen.png", new Coordinate((i - 8) * gridSize, 7 * gridSize)));
                black.add(i, new ChessPiece("/Black/queen1.png", new Coordinate((i - 8) * gridSize, 0)));
            } else {
                white.add(i, new ChessPiece("/White/king.png", new Coordinate((i - 8) * gridSize, 7 * gridSize)));
                black.add(i, new ChessPiece("/Black/king1.png", new Coordinate((i - 8) * gridSize, 0)));
            }
        }
    }

    public void onUpdate(double d) {
        Vector2 mouse = new Vector2(input.getMousePos());
        if (input.isKeyPressed(KeyEvent.VK_ESCAPE)) {
            quit();
        }
    }

    public void onRender() {
        drawBoard(0, 0, 8, 8, gridSize, 0xff6d3e17, 0xfff0c380);
        for (ChessPiece piece : white) {
            renderer.drawImage(piece.getImage(), piece.getPos().getX(), piece.getPos().getY());
        }
        for (ChessPiece piece : black) {
            renderer.drawImage(piece.getImage(), piece.getPos().getX(), piece.getPos().getY());
        }
    }

    public static void main(String[] args) {
        new Chess(new GameContainer("Chess", width, height, scale)).play();
    }

    private void drawBoard(int posX, int posY, int countX, int countY, int size, int color1, int color2) {
        boolean swap = false;
        int width = size * countX;
        int height = size * countY;
        for (int y = 0; y < height; y += size) {
            swap = !swap;
            for (int x = 0; x < width; x += size) {
                swap = !swap;
                renderer.fillRectangle(x + posX, y + posY, size, size, swap ? color1 : color2);
            }
        }
    }
}
