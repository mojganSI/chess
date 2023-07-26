package game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Test1 {
    @Test
    void test() throws Exception {
        Game game = new Game();
        Board board = game.getBoard();
        System.out.println(board.toString());
        board.move("d1", "d7", Color.white, false);
        System.out.println(board.toString());

        board.move("e8", "d7", Color.black, true);
        System.out.println(board.toString());

        board.move("e1", "f2", Color.white, false);
        System.out.println(board.toString());

        board.move("f8", "g7", Color.black, false);
        System.out.println(board.toString());

        board.move("f2", "g3", Color.white, false);
        System.out.println(board.toString());

        board.move("h8", "h1", Color.black, false);
        System.out.println(board.toString());

        board.move("g3", "g4", Color.white, false);
        System.out.println(board.toString());

        board.move("a8", "a1", Color.black, false);
        System.out.println(board.toString());

        board.move("c1", "g5", Color.white, false);
        System.out.println(board.toString());


        board.move("h1", "f1", Color.black, false);
        System.out.println(board.toString());

        board.move("g5", "c1", Color.white, false);
        System.out.println(board.toString());

        board.move("f1", "g1", Color.black, false);
        System.out.println(board.toString());

        board.move("g4", "f5", Color.white, false);
        System.out.println(board.toString());

        board.move("d8", "f6", Color.black, false);
        System.out.println(board.toString());

        board.move("c1", "a3", Color.white, false);
        System.out.println(board.toString());

        board.move("a1", "e1", Color.black, false);
        System.out.println(board.toString());

        board.move("a3", "f8", Color.white, false);
        System.out.println(board.toString());

        board.move("g7", "h6", Color.black, false);
        System.out.println(board.toString());

        board.move("f8", "h6", Color.white, false);
        System.out.println(board.toString());


        assertTrue(game.canKingMove(board.findKing(Color.black)));
        assertTrue(game.canKingMove(board.findKing(Color.white)));

    }
}