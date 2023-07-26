package piece;

import game.Board;
import game.Color;

public abstract class Piece implements IPiece {
    private String location;
    private Color color;
    private char label;

    public Piece(String location, Color color, char label) {
        this.location = location;
        this.color = color;
        this.label = label;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public boolean isSame(String end, Board position) {
        try {
            Piece piece = position.getPlace(end);
            if (piece != null && piece.getColor().equals(getColor()))
                return true;
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    @Override
    public boolean checkPath(int row, int col, int a, int b, int endRow, int endCol, Board board) {
        if (row + a == endRow && col + b == endCol)
            return true;
        Piece piece;
        try {
            piece = board.getPlace(row + a, col + b);
        } catch (Exception e) {
            return false;
        }
        if (piece != null) {
            return false;
        }
        return checkPath(row + a, col + b, a, b, endRow, endCol, board);
    }

    public boolean isKingSafe(int a, int b, Board board) {
        King king = board.findKing(getColor());
        int[] kingLocation = board.convertToInt(king.getLocation());
        int kingRow = kingLocation[0], kingCol = kingLocation[1];

        int[] scale = board.findScale(king, this);
        int c = scale[0];
        int d = scale[1];

        if (a == c && a != 0) {
            if (b == d * -1) {
                return true;
            }
        }

        if (b == d && b != 0) {
            if (a == c * -1) {
                return true;
            }
        }
        return !canNextPieceAttack(kingRow, kingCol, kingRow + c, kingCol - d, c, d * -1, false, board);
    }

    private boolean canNextPieceAttack(int kingRow, int kingCol, int checkRow, int checkCol, int c, int d, boolean thisPieceFlag, Board board) {
        if (checkRow > 7 || checkRow < 0)
            return false;
        if (checkCol > 7 || checkCol < 0)
            return false;

        Piece piece = null;
        try {
            piece = board.getPlace(board.convertToStr(checkRow, checkCol));
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (piece == null) {
            return canNextPieceAttack(kingRow, kingCol, checkRow + c, checkCol + d, c, d, thisPieceFlag, board);
        }

        if (piece.equals(this)) {
            thisPieceFlag = true;
            return canNextPieceAttack(kingRow, kingCol, checkRow + c, checkCol + d, c, d, thisPieceFlag, board);
        }

        if (piece.getColor().equals(getColor())) {
            return false;
        }


        if (piece.canAttack(board.convertToStr(kingRow, kingCol)) && thisPieceFlag) {
            return true;
        }
        return false;
    }

    public char getLabel() {
        return label;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public abstract boolean canMove(String end, Board position);

    public abstract boolean canAttack(String target);

    @Override
    public String toString() {
        return String.valueOf(label);
    }
}