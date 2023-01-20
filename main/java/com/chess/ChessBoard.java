package com.chess;

import com.chess.utils.Vector2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class ChessBoard {

    private static HashMap<Integer, ChessPiece> chessPieceMap = new HashMap<>(Chess.MAX_PIECE_COUNT * 2);
    public static void init() {
        for (int i = 0; i < Chess.MAX_PIECE_COUNT; i++) {
            chessPieceMap.put(Chess.getWhitePieces().get(i).getId(), Chess.getWhitePieces().get(i));
            chessPieceMap.put(Chess.getBlackPieces().get(i).getId(), Chess.getBlackPieces().get(i));
        }
    }

    public static void updatePieces(boolean whitesTurn, int selectedIndex) {
        ArrayList<ChessPiece> pieces = new ArrayList<>(chessPieceMap.size() - 2);
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
}
