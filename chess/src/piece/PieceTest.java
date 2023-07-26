package piece;

import game.Board;
import game.Color;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PieceTest {
    @Test
    void test() {
        Board board = new Board();
        Piece piece = board.getPlace("d1");

        // getLocation test
        assertEquals("D1", piece.getLocation());
        assertNotEquals("d1", piece.getLocation());

        try {
            board.move("d1", "d5", Color.white, false);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals("D5", piece.getLocation());

        // toString test
        assertEquals("Q", piece.toString());

        // canAttack of king test
        assertTrue(piece.canAttack("A2"));
        assertTrue(piece.canAttack("A8"));
        assertFalse(piece.canAttack("B1"));
        assertFalse(piece.canAttack("a2"));

        King king = board.findKing(Color.black);

        assertTrue(king.canAttack("E7"));
        assertTrue(king.canAttack("D7"));
        assertTrue(king.canAttack("D8"));

        // canMove of king test
        assertTrue(piece.canMove("G8", board));
        assertFalse(piece.canMove("c4", board));

        assertFalse(king.canMove("F7", board));  // is Echec
        assertTrue(king.canMove("F8", board));
    }
}
