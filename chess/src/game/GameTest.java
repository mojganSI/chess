package game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {
    @Test
    void test() throws Exception {
        Game game = new Game();
        Board board = game.getBoard();
        System.out.println(board.toString());

        board.move("d1", "d7", Color.white, false);
        System.out.println(board.toString());

        board.move("e8", "f8", Color.black, true);
        System.out.println(board.toString());

        board.move("e1", "f2", Color.white, false);
        System.out.println(board.toString());

        board.move("f8", "g8", Color.black, false);
        System.out.println(board.toString());

        board.move("f2", "g3", Color.white, false);
        System.out.println(board.toString());

        board.move("g8", "h8", Color.black, false);
        System.out.println(board.toString());

        board.move("g3", "g4", Color.white, false);
        System.out.println(board.toString());

        board.move("h8", "g8", Color.black, false);
        System.out.println(board.toString());

        board.move("g4", "h5", Color.white, false);
        System.out.println(board.toString());



        board.move("g8", "h8", Color.black, false);
        System.out.println(board.toString());

        board.move("h5", "g6", Color.white, false);
        System.out.println(board.toString());

        board.move("h8", "g8", Color.black, false);
        System.out.println(board.toString());

        board.move("d7", "d8", Color.white, false);
        System.out.println(board.toString());

        board.move("g1", "h3", Color.black, true);
        System.out.println(board.toString());



        assertFalse(game.canKingMove(board.findKing(Color.black)));

    }
}