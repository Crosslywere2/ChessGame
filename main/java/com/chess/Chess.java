package com.chess;

import com.chess.utils.Vector2;
import com.crossly.GameContainer;
import com.crossly.GameManager;
import com.crossly.utils.Coordinate;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Chess extends GameManager {

    private static final int gridSize = 48;

    private static final int width = gridSize * 11;
    private static final int height = gridSize * 8;
    private static final int scale = 1;

    private static ArrayList<ChessPiece> white = new ArrayList<>(16);
    private static ArrayList<ChessPiece> black = new ArrayList<>(16);

    public Chess(GameContainer gc) {
        super(gc);
        ChessPiece.setMinPos(new Vector2());
        ChessPiece.setMaxPos(new Vector2(7, 7).mul(gridSize));
        for (int i = 0; i < 16; i++) {
            if (i < 8) {
                white.add(i, new ChessPiece("/White/pawn.png",
                        new Coordinate(i * gridSize, 6 * gridSize),
                        ChessPiece.Type.PAWN, ChessPiece.Color.WHITE));
                black.add(i, new ChessPiece("/Black/pawn1.png",
                        new Coordinate(i * gridSize, gridSize),
                        ChessPiece.Type.PAWN, ChessPiece.Color.BLACK));
            } else if (i == 8 || i == 15) {
                white.add(i, new ChessPiece("/White/rook.png",
                        new Coordinate((i - 8) * gridSize, 7 * gridSize),
                        ChessPiece.Type.ROOK, ChessPiece.Color.WHITE));
                black.add(i, new ChessPiece("/Black/rook1.png",
                        new Coordinate((i - 8) * gridSize, 0),
                        ChessPiece.Type.ROOK, ChessPiece.Color.BLACK));
            } else if (i == 9 || i == 14) {
                white.add(i, new ChessPiece("/White/knight.png",
                        new Coordinate((i - 8) * gridSize, 7 * gridSize),
                        ChessPiece.Type.KNIGHT, ChessPiece.Color.WHITE));
                black.add(i, new ChessPiece("/Black/knight1.png",
                        new Coordinate((i - 8) * gridSize, 0),
                        ChessPiece.Type.KNIGHT, ChessPiece.Color.BLACK));
            } else if (i == 10 || i == 13) {
                white.add(i, new ChessPiece("/White/bishop.png",
                        new Coordinate((i - 8) * gridSize, 7 * gridSize),
                        ChessPiece.Type.BISHOP, ChessPiece.Color.WHITE));
                black.add(i, new ChessPiece("/Black/bishop1.png",
                        new Coordinate((i - 8) * gridSize, 0),
                        ChessPiece.Type.BISHOP, ChessPiece.Color.BLACK));
            } else if (i == 11) {
                white.add(i, new ChessPiece("/White/queen.png",
                        new Coordinate((i - 8) * gridSize, 7 * gridSize),
                        ChessPiece.Type.QUEEN, ChessPiece.Color.WHITE));
                black.add(i, new ChessPiece("/Black/queen1.png",
                        new Coordinate((i - 8) * gridSize, 0),
                        ChessPiece.Type.QUEEN, ChessPiece.Color.BLACK));
            } else {
                white.add(i, new ChessPiece("/White/king.png",
                        new Coordinate((i - 8) * gridSize, 7 * gridSize),
                        ChessPiece.Type.KING, ChessPiece.Color.WHITE));
                black.add(i, new ChessPiece("/Black/king1.png",
                        new Coordinate((i - 8) * gridSize, 0),
                        ChessPiece.Type.KING, ChessPiece.Color.BLACK));
            }
        }
    }

    private int selectedIndex = -1;
    private int plays = 0;
    private Vector2 selectedPos;

    public void onUpdate(double d) {
        Vector2 mouse = new Vector2(input.getMousePos());
        if (input.isButtonPressed(1) && selectedIndex == -1) {
            mouse = mouse.sub(mouse.mod(gridSize));
            for (int i = 0; i < (plays % 2 == 0 ? white : black).size(); i++) {
                if (new Vector2((plays % 2 == 0 ? white : black).get(i).getPos()).equals(mouse)) {
                    selectedIndex = i;
                    selectedPos = new Vector2((plays % 2 == 0 ? white : black).get(i).getPos());
                    break;
                }
            }
        } else if (input.isButtonPressed(1) && selectedIndex >= 0) {
            if (isWithinBoard(mouse)) {
                mouse = mouse.sub(mouse.mod(gridSize));
                (plays % 2 == 0 ? white : black).get(selectedIndex).setPos(mouse);
                if (!selectedPos.equals(mouse)) {
                    plays++;
                    selectedPos = null;
                }
                selectedIndex = -1;
            }
        }
        if (selectedIndex >= 0) {
            (plays % 2 == 0 ? white : black).get(selectedIndex).setPos(mouse.sub(gridSize / 2, gridSize / 2));
        }
        if (input.isKeyPressed(KeyEvent.VK_ESCAPE)) {
            quit();
            System.exit(0);
        }
    }

    public void onRender() {
        drawBoard(0, 0, 8, 8, gridSize, 0xff6d3e17, 0xfff0c380);
        if (selectedIndex >= 0 && selectedPos != null) {
            renderer.fillRectangle(selectedPos.getX(), selectedPos.getY(), gridSize, gridSize, plays % 2 == 0 ? 0xffff4040 : 0xff0080ff);
        }
        for (ChessPiece piece : white) renderer.drawImage(piece.getImage(), piece.getPos().getX(), piece.getPos().getY());
        for (ChessPiece piece : black) renderer.drawImage(piece.getImage(), piece.getPos().getX(), piece.getPos().getY());
    }

    public static void main(String[] args) {
        new Chess(new GameContainer("Chess++", width, height, scale)).play();
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

    private boolean isWithinBoard(Coordinate pos) {
        return (pos.getX() >= ChessPiece.getMinPos().getX() &&
                pos.getX() < ChessPiece.getMaxPos().getX() + gridSize &&
                pos.getY() >= ChessPiece.getMinPos().getY() &&
                pos.getY() < ChessPiece.getMaxPos().getY() + gridSize);
    }
}
