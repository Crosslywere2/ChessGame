package com.chess;

import com.crossly.GameContainer;
import com.crossly.GameManager;
import com.crossly.utils.Coordinate;
import com.chess.utils.Vector2;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Chess extends GameManager {

    private static final int grid = 48;
    private static final String title = "Super Chess";
    private static final int width = grid * 8;
    private static final int height = grid * 8;
    private static final int scale = 1;

    public static final int MAX_PIECE_COUNT = 16;
    private static final ArrayList<ChessPiece> whitePieces = new ArrayList<>(MAX_PIECE_COUNT);
    private static final ArrayList<ChessPiece> blackPieces = new ArrayList<>(MAX_PIECE_COUNT);

    private static int playCount = 0;
    private static int selectedIndex = -1;

    public Chess(GameContainer container) {
        super(container);
        ChessPiece.setMinPos(new Coordinate(0, 0));
        ChessPiece.setMaxPos(new Vector2(grid, grid).mul(7));
        for (int i = 0; i < MAX_PIECE_COUNT; i++) {
            if (i < 8) {
                whitePieces.add(new ChessPiece(i, ChessPiece.Color.WHITE, ChessPiece.Type.PAWN, new Vector2(i * grid, 6 * grid)));
                blackPieces.add(new ChessPiece(i + 16, ChessPiece.Color.BLACK, ChessPiece.Type.PAWN, new Vector2(i * grid, grid)));
            } else if (i == 8 || i == 15) {
                whitePieces.add(new ChessPiece(i, ChessPiece.Color.WHITE, ChessPiece.Type.ROOK, new Vector2((i - 8) * grid, 7 * grid)));
                blackPieces.add(new ChessPiece(i + 16, ChessPiece.Color.BLACK, ChessPiece.Type.ROOK, new Vector2((i - 8) * grid, 0)));
            } else if (i == 9 || i == 14) {
                whitePieces.add(new ChessPiece(i, ChessPiece.Color.WHITE, ChessPiece.Type.KNIGHT, new Vector2((i - 8) * grid, 7 * grid)));
                blackPieces.add(new ChessPiece(i + 16, ChessPiece.Color.BLACK, ChessPiece.Type.KNIGHT, new Vector2((i - 8) * grid, 0)));
            } else if (i == 10 || i == 13) {
                whitePieces.add(new ChessPiece(i, ChessPiece.Color.WHITE, ChessPiece.Type.BISHOP, new Vector2((i - 8) * grid, 7 * grid)));
                blackPieces.add(new ChessPiece(i + 16, ChessPiece.Color.BLACK, ChessPiece.Type.BISHOP, new Vector2((i - 8) * grid, 0)));
            } else if (i == 11) {
                whitePieces.add(new ChessPiece(i, ChessPiece.Color.WHITE, ChessPiece.Type.QUEEN, new Vector2((i - 8) * grid, 7 * grid)));
                blackPieces.add(new ChessPiece(i + 16, ChessPiece.Color.BLACK, ChessPiece.Type.QUEEN, new Vector2((i - 8) * grid, 0)));
            } else {
                whitePieces.add(new ChessPiece(i, ChessPiece.Color.WHITE, ChessPiece.Type.KING, new Vector2((i - 8) * grid, 7 * grid)));
                blackPieces.add(new ChessPiece(i + 16, ChessPiece.Color.BLACK, ChessPiece.Type.KING, new Vector2((i - 8) * grid, 0)));
            }
        }
    }

    public void onCreate() {
        whitePieces.sort(ChessPiece::compareTo);
        blackPieces.sort(ChessPiece::compareTo);
        ChessBoard.init();
    }

    public void onUpdate(double delta) {
        Vector2 mouse = new Vector2(input.getMousePos());
        mouse = mouse.sub(mouse.mod(grid));
        if (input.isButtonPressed(1)) {
            boolean turn = playCount % 2 == 0;
            if (selectedIndex < 0) {
                for (int i = 0; i < (turn ? whitePieces : blackPieces).size(); i++) {
                    if ((turn ? whitePieces : blackPieces).get(i).getPosition().equals(mouse)) {
                        selectedIndex = i;
                        break;
                    }
                }
            } else {
                if (!(turn ? whitePieces : blackPieces).get(selectedIndex).getPosition().equals(mouse)) {
                    (turn ? whitePieces : blackPieces).get(selectedIndex).setPosition(mouse);
                    ChessBoard.takePiece(turn, selectedIndex);
                    ChessBoard.removeDeadPieces(turn, selectedIndex);
                    selectedIndex = -1;
                    playCount++;
                } else {
                    selectedIndex = -1;
                }
            }
        }
        if (input.isKeyPressed(KeyEvent.VK_SPACE)) {
            for (ChessPiece piece : whitePieces)
                System.out.println(piece.getColor() + ": " + piece.getType());
            for (ChessPiece piece : blackPieces)
                System.out.println(piece.getColor() + ": " + piece.getType());
        }
        if (input.isKeyPressed(KeyEvent.VK_ESCAPE)) quit();
    }

    public void onRender() {
        drawChessBoard();
        if (selectedIndex >= 0) {
            if (playCount % 2 == 0) {
                renderer.fillRectangle(whitePieces.get(selectedIndex).getPosition(), grid, grid, 0xffff4040);
            } else {
                renderer.fillRectangle(blackPieces.get(selectedIndex).getPosition(), grid, grid, 0xff0080ff);
            }
        }
        for (ChessPiece piece : ChessBoard.getChessPieces()) {
            renderer.drawImage(piece.getImage(), piece.getPosition());
        }
    }

    public void dispose() {
        System.exit(0);
    }

    public static int getPlayCount() {
        return playCount;
    }

    public static ArrayList<ChessPiece> getWhitePieces() {
        return whitePieces;
    }

    public static ArrayList<ChessPiece> getBlackPieces() {
        return blackPieces;
    }

    public static void main(String[] args) {
        new Chess(new GameContainer(title, width, height, scale)).play();
    }

    private void drawChessBoard() {
        boolean swap = false;
        for (int i = 0; i < 8; i++) {
            swap = !swap;
            for (int j = 0; j < 8; j++) {
                swap = !swap;
                renderer.fillRectangle(i * grid, j * grid, grid, grid, swap ? 0xff6d3e17 : 0xfff0c380);
            }
        }
    }
}

