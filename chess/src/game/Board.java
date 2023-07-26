package game;

import piece.*;

import java.util.ArrayList;

public class Board {
    private Piece[][] place;
    private final static int LIG = 8;
    private final static int COL = 8;


    public Board() {
        initPieces();
    }

    private void initPieces() {
        place = new Piece[LIG][COL];
        for (int i = 2; i < LIG - 2; i++) {
            for (int j = 0; j < COL; j++) {
                place[i][j] = null;
            }
        }

        place[0][0] = new Tour("A8", Color.black, 't');
        place[0][7] = new Tour("H8", Color.black, 't');
        place[0][1] = new Cavalier("B8", Color.black, 'c');
        place[0][6] = new Cavalier("G8", Color.black, 'c');
        place[0][2] = new Bishop("C8", Color.black, 'b');
        place[0][5] = new Bishop("F8", Color.black, 'b');
        place[0][3] = new Queen("D8", Color.black, 'q');
        place[0][4] = new King("E8", Color.black, 'k');

        place[7][0] = new Tour("A1", Color.white, 'T');
        place[7][7] = new Tour("H1", Color.white, 'T');
        place[7][1] = new Cavalier("B1", Color.white, 'C');
        place[7][6] = new Cavalier("G1", Color.white, 'C');
        place[7][2] = new Bishop("C1", Color.white, 'B');
        place[7][5] = new Bishop("F1", Color.white, 'B');
        place[7][3] = new Queen("D1", Color.white, 'Q');
        place[7][4] = new King("E1", Color.white, 'K');

        char col = 'A';
        for (int i = 0; i <= 7; i++) {
            place[1][i] = new Pion(col + "7", Color.black, 'p');
            col++;
        }

        col = 'A';
        for (int i = 0; i <= 7; i++) {
            place[6][i] = new Pion(col + "2", Color.white, 'P');
            col++;
        }
    }

    public ArrayList<Piece> getTeamPieces(Color color) {
        ArrayList<Piece> pieces = new ArrayList<>();
        for (int i = 0; i < place.length; i++) {
            for (int j = 0; j < place[i].length; j++) {
                Piece piece = place[i][j];
                if (piece != null && piece.getColor().equals(color))
                    pieces.add(place[i][j]);
            }
        }
        return pieces;
    }

    public int[] findScale(Piece p1, Piece p2) {
        String location1 = p1.getLocation();
        String location2 = p2.getLocation();
        return findScale(location1, location2);
    }

    public int[] findScale(String l1, String l2) {
        int a, b;
        int colDifferent = l1.charAt(0) - l2.charAt(0);
        int rowDifferent = l1.charAt(1) - l2.charAt(1);

        if (colDifferent > 0) {
            b = -1;
        } else if (colDifferent == 0) {
            b = 0;
        } else {
            b = 1;
        }

        if (rowDifferent > 0) {
            a = 1;
        } else if (rowDifferent == 0) {
            a = 0;
        } else {
            a = -1;
        }
        return new int[]{a, b};
    }

    public Piece getPlace(String location) {
        int[] intLoc = convertToInt(location);
        return getPlace(intLoc[0], intLoc[1]);
    }

    public Piece getPlace(int row, int col) {
        return place[row][col];
    }

    public King findKing(Color color) {
        for (int i = 0; i < place.length; i++) {
            for (int j = 0; j < place[i].length; j++) {
                Piece piece = place[i][j];
                if (piece instanceof King && piece.getColor().equals(color)) {
                    return (King) piece;
                }
            }
        }
        throw new IllegalStateException();
    }

    public int[] convertToInt(String location) {
        location = location.toUpperCase();
        char ch = location.charAt(0);
        int row = 8 - Integer.parseInt(String.valueOf(location.charAt(1)));
        int col = Integer.parseInt(String.valueOf(ch - 'A'));
        return new int[]{row, col};
    }

    public String convertToStr(int row, int col) {
        return String.valueOf((char) ('A' + col)) + (8 - row);
    }

    public boolean isLocationValid(String location) {
        if (location.length() != 2)
            return false;
        char col = location.charAt(0);
        if (!(col >= 65 && col <= 72) && !(col >= 97 && col <= 104))
            return false;
        int row = Integer.parseInt(String.valueOf(location.charAt(1)));
        if (row < 1 || row > 8)
            return false;
        return true;
    }

    public void move(String start, String end, Color turn, boolean isEchec) throws Exception {
        start = start.toUpperCase();
        end = end.toUpperCase();
        boolean isValid = isLocationValid(start) && isLocationValid(end);
        if (!isValid) {
            throw new Exception("Your entered locations is not valid!!");
        }

        if (start.equals(end)) {
            throw new Exception("You cant move to your current location!!");
        }

        int[] startArray = convertToInt(start);
        int[] endArray = convertToInt(end);

        int rowStart = startArray[0];
        int colStart = startArray[1];

        int rowEnd = endArray[0];
        int colEnd = endArray[1];

        Piece piece = place[rowStart][colStart];
        if (piece == null) {
            throw new Exception("Your selected place is empty!!!");
        }

        if (!piece.getColor().equals(turn)) {
            throw new Exception("Your selected piece is not yours!!");
        }

        if (!piece.canMove(end, this)) {
            throw new Exception("You cant move this piece to this place!!");
        }

        if (piece instanceof Tour) {
            ((Tour) piece).setMoveFlag(true);
        }


        Piece temp = place[rowEnd][colEnd];
        place[rowEnd][colEnd] = place[rowStart][colStart];
        place[rowStart][colStart] = null;
        piece.setLocation(end);

        King king = findKing(turn);
        if (isEchec && king.isEchec(king.getLocation(), this) != null) {
            piece.setLocation(start);
            place[rowStart][colStart] = place[rowEnd][colEnd];
            place[rowEnd][colEnd] = temp;
            throw new Exception("You first should be unchecked!");
        }
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        char columnLabel = 'A';
        for (int i = 0; i <= 7; i++) {
            String result = (i == 0) ? "    " + columnLabel : "   " + columnLabel;
            str.append(result);
            columnLabel++;
        }
        str.append("\n");   // for new line

        for (int i = 8; i >= 0; i--) {
            for (int k = 0; k <= 7; k++) {
                String result = (k == 0) ? "   ---" : " ---";
                str.append(result);
            }
            str.append("\n");   // for new line
            if (i == 0) {
                break;
            }
            str.append(i);
            for (int j = 0; j <= 8; j++) {
                if (j == 8) {
                    str.append(" |");
                } else {
                    str.append(" | ").append((place[8 - i][j] == null) ? ' ' : place[8 - i][j]);
                }
            }
            str.append(" ").append(i).append("\n");
        }
        columnLabel = 'A';
        for (int i = 0; i <= 7; i++) {
            String result = (i == 0) ? "    " + columnLabel : "   " + columnLabel;
            str.append(result);
            columnLabel++;
        }
        str.append("\n");
        return str.toString();
    }
}