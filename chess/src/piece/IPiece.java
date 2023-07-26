package piece;

import game.Board;

public interface IPiece {
    boolean isSame(String end, Board position);

    boolean checkPath(int row, int col, int a, int b, int endRow, int endCol, Board board);

    boolean isKingSafe(int a, int b, Board board);

    boolean canMove(String end, Board position);

    boolean canAttack(String target);

    String toString();
}