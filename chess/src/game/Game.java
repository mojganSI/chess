package game;

import piece.King;
import piece.Cavalier;
import piece.Piece;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private Board board;
    private Color turn;
    private boolean isEchec;
    private boolean isGameRun;

    public Game() {
        board = new Board();
        turn = Color.white;
        isEchec = false;
        isGameRun = false;
    }

    public void start() {
        isGameRun = true;
        while (isGameRun) {
            System.out.println(board);
            showTurn();
            if (isEchec) {
                System.out.println("You are in echec state.");
            }
            String departure = getLocation("Enter the piece departure: ");
            String destination = getLocation("Enter the piece destination: ");

            try {
                board.move(departure, destination, turn, isEchec);
                changeTurn();
                checkGameState();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        endOfGame();
    }

    private void checkGameState() {
        King king = board.findKing(turn);
        Piece enemy = king.isEchec(king.getLocation(), board);
        if (enemy != null) {
            isEchec = true;
        }
        if (!isEchec) {
            return;
        }

        if (canKingMove(king)) {
            return;
        }

        if (canAttackToEnemy(enemy)) {
            return;
        }

        if (!(enemy instanceof Cavalier) && canBlockPath(king, enemy)) {
            return;
        }

        isGameRun = false;
    }

    private boolean canAttackToEnemy(Piece enemy) {
        ArrayList<Piece> pieces = board.getTeamPieces(turn);
        for (Piece piece : pieces) {
            if (piece.canMove(enemy.getLocation(), board)) {
                return true;
            }
        }
        return false;
    }

    private boolean canBlockPath(King king, Piece enemy) {
        int[] scale = board.findScale(king, enemy);
        int[] kingLocation = board.convertToInt(king.getLocation());
        int[] enemyLocation = board.convertToInt(enemy.getLocation());
        ArrayList<Piece> pieces = board.getTeamPieces(king.getColor());
        int a = kingLocation[0] + scale[0];
        int b = kingLocation[1] + scale[1];
        while (a != enemyLocation[0] || b != enemyLocation[1]) {
            for (Piece piece : pieces) {
                if (piece instanceof King) {
                    continue;
                }
                if (piece.canMove(board.convertToStr(a, b), board)) {
                    return true;
                }
            }
            a += scale[0];
            b += scale[1];
        }
        return false;
    }

    public boolean canKingMove(King king) {
        int[] kingLocation = board.convertToInt(king.getLocation());
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int row = kingLocation[0] + i;
                int col = kingLocation[1] + j;
                if (i == 0 && j == 0) {
                    continue;
                }
                if (row < 0 || row > 7 || col < 0 || col > 7) {
                    continue;
                }
                if (king.isEchec(board.convertToStr(row, col), board) == null) {
                    return true;
                }
            }
        }
        return false;
    }

    private String getLocation(String message) {
        Scanner input = new Scanner(System.in);
        System.out.print(message);
        return input.next();
    }

    private void showTurn() {
        System.out.println("Turn player : " + turn);
    }

    private void changeTurn() {
        isEchec = false;
        if (turn.equals(Color.black)) {
            turn = Color.white;
        } else {
            turn = Color.black;
        }
    }

    private void endOfGame() {
        System.out.println(board);
        System.out.println("Player " + turn + " is check mate.");
        System.out.println("Player " + (turn.equals(Color.black) ? Color.white : Color.black) + " wins the game.");
    }

    public Board getBoard() {
        return board;
    }
}