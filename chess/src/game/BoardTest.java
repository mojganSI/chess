package game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    @Test
    void test() {
        Board board = new Board();
        // getPiece test
        assertEquals('Q', board.getPlace(7, 3).getLabel());
        assertEquals('k', board.getPlace(0, 4).getLabel());
        assertEquals('K', board.getPlace("e1").getLabel());
        assertEquals('q', board.getPlace("D8").getLabel());
        assertNull(board.getPlace(2, 3));
        assertThrows(IndexOutOfBoundsException.class, () -> {
            board.getPlace(8, 5);
        });
        assertThrows(IndexOutOfBoundsException.class, () -> {
            board.getPlace(2, -2);
        });
        assertThrows(IndexOutOfBoundsException.class, () -> {
            board.getPlace("R3");
        });

        // findKing test
        assertEquals(board.getPlace("e1"), board.findKing(Color.white));
        assertEquals(board.getPlace("E8"), board.findKing(Color.black));
        assertNotEquals(board.getPlace("d1"), board.findKing(Color.white));

        // convertToStr test
        assertEquals("A8", board.convertToStr(0, 0));
        assertEquals("H1", board.convertToStr(7, 7));
        assertEquals("D3", board.convertToStr(5, 3));
        assertNotEquals("f3", board.convertToStr(5, 5));

        // convertToInt test
        assertArrayEquals(new int[]{0, 0}, board.convertToInt("A8"));
        assertArrayEquals(new int[]{7, 7}, board.convertToInt("H1"));
        assertArrayEquals(new int[]{5, 3}, board.convertToInt("d3"));

        // move test

        assertThrows(Exception.class, () -> {
            board.move("start", "end", Color.white, false);
        }, "Your entered locations is not valid!!");

        assertThrows(Exception.class, () -> {
            board.move("D12", "A5", Color.black, false);
        }, "Your entered locations is not valid!!");

        assertThrows(Exception.class, () -> {
            board.move("A1", "c5", Color.black, true);
        }, "Your selected place is empty!!!");

        assertThrows(Exception.class, () -> {
            board.move("d1", "a7", Color.black, true);
        }, "Your selected piece is not yours!!");

        assertThrows(Exception.class, () -> {
            board.move("E8", "a7", Color.white, false);
        }, "Your selected piece is not yours!!");

        assertThrows(Exception.class, () -> {
            board.move("d1", "a7", Color.white, false);
        }, "You cant move this piece to this place!!");

        assertThrows(Exception.class, () -> {
            board.move("d1", "f1", Color.white, false);
        }, "You cant move this piece to this place!!");

        assertThrows(Exception.class, () -> {
            board.move("e1", "e3", Color.white, false);
        }, "You cant move this piece to this place!!");

        assertThrows(Exception.class, () -> {
            board.move("E8", "d8", Color.black, false);
        }, "You cant move this piece to this place!!");
    }
}
