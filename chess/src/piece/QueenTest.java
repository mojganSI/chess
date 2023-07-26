package piece;

import game.Board;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class QueenTest {
    @Test
    void test() {
        Board board = new Board();
        Queen queen = (Queen) board.getPlace("d1");

        assertTrue(queen.checkPath(7, 3, 1, 0, 5, 3, board));
        assertTrue(queen.checkPath(3, 5, -1, 1, 1, 7, board));
        assertFalse(queen.checkPath(0, 0, -1, 1, 0, 7, board));
        assertFalse(queen.checkPath(0, 0, 1, 1, 0, 7, board));
        assertFalse(queen.checkPath(0, 0, 0, 1, 0, 7, board));
    }
}