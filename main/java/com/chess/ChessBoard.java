package com.chess;

import com.chess.utils.Vector2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class ChessBoard {

    private static final HashMap<Integer, ChessPiece> chessPieceMap = new HashMap<>(Chess.MAX_PIECE_COUNT * 2);
    public static void init() {
        for (int i = 0; i < Chess.MAX_PIECE_COUNT; i++) {
            chessPieceMap.put(Chess.getWhitePieces().get(i).getId(), Chess.getWhitePieces().get(i));
            chessPieceMap.put(Chess.getBlackPieces().get(i).getId(), Chess.getBlackPieces().get(i));
        }
    }

    public static void takeAction(boolean whitesTurn) {
        if (whitesTurn);
    }

    public static void removeDeadPieces(boolean whitesTurn) {
        Collection<ChessPiece> pieces = new ArrayList<>();
        chessPieceMap.values().forEach(chessPiece -> pieces.add(new ChessPiece(chessPiece)) );
        for (ChessPiece piece : pieces) {
            if (piece.getHealth() <= 0) {
                (whitesTurn ? Chess.getBlackPieces() : Chess.getWhitePieces()).remove(chessPieceMap.remove(piece.getId()));
            }
        }
    }

    public static void takePiece(boolean whitesTurn, int selectedIndex) {
        Vector2 piecePos = (whitesTurn ? Chess.getWhitePieces() : Chess.getBlackPieces()).get(selectedIndex).getPosition();
        for (ChessPiece piece : (whitesTurn ? Chess.getBlackPieces() : Chess.getWhitePieces())) {
            if (piece.getPosition().equals(piecePos)) {
                (whitesTurn ? Chess.getBlackPieces() : Chess.getWhitePieces()).remove(chessPieceMap.remove(piece.getId()));
                break;
            }
        }
    }

    public static Collection<ChessPiece> getChessPieces() {
        return chessPieceMap.values();
    }

    public static boolean isPositionValid(Vector2 position) {
        return (Math.max(Math.min(position.getX(), ChessPiece.getMaxPos().getX()), ChessPiece.getMinPos().getX()) == position.getX()) &&
                (Math.max(Math.min(position.getY(), ChessPiece.getMaxPos().getY()), ChessPiece.getMinPos().getY()) == position.getY());
    }

    public static boolean isPositionOccupied(Vector2 position) {
        if (isPositionValid(position)) {
            for (ChessPiece piece : chessPieceMap.values()) {
                if (piece.getPosition().equals(position)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public static boolean isPositionOccupied(Vector2 position, ChessPiece.Color color) {
        if (isPositionValid(position)) {
            for (ChessPiece piece : (color == ChessPiece.Color.WHITE ? Chess.getWhitePieces() : Chess.getBlackPieces())) {
                if (piece.getPosition().equals(position)) {
                    return true;
                }
            }
        }
        return false;
    }
}
