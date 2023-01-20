package com.chess;

import com.chess.utils.Vector2;

import java.util.ArrayList;

public class MoveGenerator {
    public static ArrayList<Vector2> generateMoves(ChessPiece piece) {
        ArrayList<Vector2> moves = null;
        switch (piece.getType()) {
            case PAWN -> moves = generatePawnMoves(piece);
            case ROOK -> moves = generateRookMoves(piece);
            case KNIGHT -> moves = generateKnightMoves(piece);
            case BISHOP -> moves = generateBishopMoves(piece);
            case QUEEN -> moves = generateQueenMoves(piece);
            case KING -> moves = generateKingMoves(piece);
        }
        return moves;
    }

    public static ArrayList<Vector2> generatePawnMoves(ChessPiece pawn) {
        ArrayList<Vector2> moves = new ArrayList<>(4);
        Vector2 up = pawn.getPosition().add(0, pawn.getColor().getIncrement());
        if (!ChessBoard.isPositionOccupied(up)) {
            moves.add(up);
            up = up.add(0, pawn.getColor().getIncrement());
            if (!pawn.isMoved() && !ChessBoard.isPositionOccupied(up)) {
                moves.add(up);
            }
        }
        Vector2 take = pawn.getPosition().add(pawn.getColor().getIncrement(), pawn.getColor().getIncrement());
        if (ChessBoard.isPositionOccupied(take, pawn.getColor() == ChessPiece.Color.WHITE ? ChessPiece.Color.BLACK : ChessPiece.Color.WHITE)) {
            moves.add(take);
        }
        take = pawn.getPosition().add(-pawn.getColor().getIncrement(), pawn.getColor().getIncrement());
        if (ChessBoard.isPositionOccupied(take, pawn.getColor() == ChessPiece.Color.WHITE ? ChessPiece.Color.BLACK : ChessPiece.Color.WHITE)) {
            moves.add(take);
        }
        moves.trimToSize();
        return moves;
    }

    public static ArrayList<Vector2> generateRookMoves(ChessPiece rook) {
        ArrayList<Vector2> moves = new ArrayList<>(14);
        Vector2 up = new Vector2(0, rook.getColor().getIncrement());
        Vector2 left = new Vector2(rook.getColor().getIncrement(), 0);
        Vector2 check = rook.getPosition().add(up);
        ChessPiece.Color opposite = rook.getColor().getOpposite();
        for (int i = 1; i < 7; i++) {
            if (ChessBoard.isPositionOccupied(check, opposite)) {
                moves.add(check);
                break;
            } else if (ChessBoard.isPositionOccupied(check)) {
                break;
            }
            moves.add(check);
            check = check.add(up);
        }
        check = rook.getPosition().sub(up);
        for (int i = 1; i < 7; i++) {
            if (ChessBoard.isPositionOccupied(check, opposite)) {
                moves.add(check);
                break;
            } else if (ChessBoard.isPositionOccupied(check)) {
                break;
            }
            moves.add(check);
            check = check.sub(up);
        }
        check = rook.getPosition().add(left);
        for (int i = 1; i < 7; i++) {
            if (ChessBoard.isPositionOccupied(check, opposite)) {
                moves.add(check);
                break;
            } else if (ChessBoard.isPositionOccupied(check)) {
                break;
            }
            moves.add(check);
            check = check.add(left);
        }
        check = rook.getPosition().sub(left);
        for (int i = 1; i < 7; i++) {
            if (ChessBoard.isPositionOccupied(check, opposite)) {
                moves.add(check);
                break;
            } else if (ChessBoard.isPositionOccupied(check)) {
                break;
            }
            moves.add(check);
            check = check.sub(left);
        }
        moves.trimToSize();
        return moves;
    }

    public static ArrayList<Vector2> generateKnightMoves(ChessPiece knight) {
        ArrayList<Vector2> moves = new ArrayList<>(8);
        ChessPiece.Color opposite = knight.getColor().getOpposite();
        Vector2 check = knight.getPosition().add(Chess.grid, Chess.grid * 2);
        if (!ChessBoard.isPositionOccupied(check) || ChessBoard.isPositionOccupied(check, opposite)) {
            moves.add(check);
        }
        check = knight.getPosition().add(-Chess.grid, Chess.grid * 2);
        if (!ChessBoard.isPositionOccupied(check) || ChessBoard.isPositionOccupied(check, opposite)) {
            moves.add(check);
        }
        check = knight.getPosition().sub(Chess.grid, Chess.grid * 2);
        if (!ChessBoard.isPositionOccupied(check) || ChessBoard.isPositionOccupied(check, opposite)) {
            moves.add(check);
        }
        check = knight.getPosition().sub(-Chess.grid, Chess.grid * 2);
        if (!ChessBoard.isPositionOccupied(check) || ChessBoard.isPositionOccupied(check, opposite)) {
            moves.add(check);
        }
        check = knight.getPosition().add(Chess.grid * 2, Chess.grid);
        if (!ChessBoard.isPositionOccupied(check) || ChessBoard.isPositionOccupied(check, opposite)) {
            moves.add(check);
        }
        check = knight.getPosition().add(-Chess.grid * 2, Chess.grid);
        if (!ChessBoard.isPositionOccupied(check) || ChessBoard.isPositionOccupied(check, opposite)) {
            moves.add(check);
        }
        check = knight.getPosition().sub(Chess.grid * 2, Chess.grid);
        if (!ChessBoard.isPositionOccupied(check) || ChessBoard.isPositionOccupied(check, opposite)) {
            moves.add(check);
        }
        check = knight.getPosition().sub(-Chess.grid * 2, Chess.grid);
        if (!ChessBoard.isPositionOccupied(check) || ChessBoard.isPositionOccupied(check, opposite)) {
            moves.add(check);
        }
        moves.trimToSize();
        return moves;
    }

    public static ArrayList<Vector2> generateBishopMoves(ChessPiece bishop) {
        ArrayList<Vector2> moves = new ArrayList<>(14);
        ChessPiece.Color opposite = bishop.getColor().getOpposite();
        Vector2 up_left = new Vector2(bishop.getColor().getIncrement(), bishop.getColor().getIncrement());
        Vector2 down_left = new Vector2(bishop.getColor().getIncrement(), -bishop.getColor().getIncrement());
        Vector2 check = bishop.getPosition().add(up_left);
        for (int i = 1; i < 7; i++) {
            if (ChessBoard.isPositionOccupied(check, opposite)) {
                moves.add(check);
                break;
            } else if (ChessBoard.isPositionOccupied(check)) {
                break;
            }
            moves.add(check);
            check = check.add(up_left);
        }
        check = bishop.getPosition().sub(up_left);
        for (int i = 1; i < 7; i++) {
            if (ChessBoard.isPositionOccupied(check, opposite)) {
                moves.add(check);
                break;
            } else if (ChessBoard.isPositionOccupied(check)) {
                break;
            }
            moves.add(check);
            check = check.sub(up_left);
        }
        check = bishop.getPosition().add(down_left);
        for (int i = 1; i < 7; i++) {
            if (ChessBoard.isPositionOccupied(check, opposite)) {
                moves.add(check);
                break;
            } else if (ChessBoard.isPositionOccupied(check)) {
                break;
            }
            moves.add(check);
            check = check.add(down_left);
        }
        check = bishop.getPosition().sub(down_left);
        for (int i = 1; i < 7; i++) {
            if (ChessBoard.isPositionOccupied(check, opposite)) {
                moves.add(check);
                break;
            } else if (ChessBoard.isPositionOccupied(check)) {
                break;
            }
            moves.add(check);
            check = check.sub(down_left);
        }
        moves.trimToSize();
        return moves;
    }

    public static ArrayList<Vector2> generateQueenMoves(ChessPiece queen) {
        ArrayList<Vector2> moves = new ArrayList<>(28);
        moves.addAll(generateRookMoves(queen));
        moves.addAll(generateBishopMoves(queen));
        moves.trimToSize();
        return moves;
    }

    public static ArrayList<Vector2> generateKingMoves(ChessPiece king) {
        ArrayList<Vector2> moves = new ArrayList<>(11);
        Vector2 check;
        moves.trimToSize();
        return moves;
    }
}
