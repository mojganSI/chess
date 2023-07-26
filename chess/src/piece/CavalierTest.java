package piece;

import game.Board;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CavalierTest {
    @Test
    void test() {
        Board board = new Board();
        Cavalier cavalier = (Cavalier) board.getPlace("b1");
        System.out.println(board.toString());

        assertTrue(cavalier.canMove("C3", board));
        assertTrue(cavalier.checkPath(3, 5, -1, 1, 1, 7, board));
        assertFalse(cavalier.checkPath(2, 2, -1, 1, 1, 4, board));
        assertFalse(cavalier.checkPath(0, 0, 1, 1, 0, 7, board));
        assertFalse(cavalier.checkPath(2, 5, 1, -1, 0, 4, board));
    }
}
