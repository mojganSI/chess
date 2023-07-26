package piece;

import game.Board;
import game.Color;

public class King extends Piece {

    public King(String location, Color color, char label) {
        super(location, color, label);
    }

    @Override
    public boolean canMove(String end, Board position) {
        int[] startArray = position.convertToInt(getLocation());
        int[] endArray = position.convertToInt(end);
        if (isNear(startArray, endArray)) {
            if (isSame(end, position)) {
                return false;
            }
            if (isEchec(end, position) != null) {
                return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean canAttack(String target) {
        String myLocation = getLocation();
        int myLocationCol = myLocation.charAt(0);
        int targetCol = target.charAt(0);
        if (Math.abs(myLocationCol - targetCol) > 1)
            return false;
        int myLocationRow = myLocation.charAt(1);
        int targetRow = target.charAt(1);

        if (Math.abs(myLocationRow - targetRow) > 1)
            return false;
        return true;
    }

    private Piece isEchecByCavalier(String location, Board position) {
        // x   y
        // x-2 y-1
        // x-2 y+1
        // x-1 y-2

        // x-1 y+2
        // x+1 y-2
        // x+1 y+2
        // x+2 y-1
        // x+2 y+1
        for (int i = -2; i <= 2; i++) {
            if (i == 0)
                continue;
            int j = 3 - Math.abs(i);
            for (int k = 0; k < 2; k++) {
                j = j * -1;
                String enemyLoc = String.valueOf((char) (location.charAt(0) + i)) + (char) (location.charAt(1) + j);
                Piece piece = null;
                try {
                    piece = position.getPlace(enemyLoc);
                } catch (Exception e) {
                }
                if (piece instanceof Cavalier && !piece.getColor().equals(getColor())) {
                    return piece;
                }
            }
        }
        return null;
    }

    private Piece isEchecByOther(String location, Board position) {
        int a = -1, b = -1;
        for (int i = 0; i < 9; i++) {
            if (a != 0 || b != 0) {
                try {
                    int j = 1;
                    String enemyLoc = String.valueOf((char) (location.charAt(0) + (j * a))) + (char) (location.charAt(1) + (j * b));
                    Piece piece = position.getPlace(enemyLoc);
                    while (piece == null || piece.equals(this)) {
                        j++;
                        enemyLoc = String.valueOf((char) (location.charAt(0) + (j * a))) + (char) (location.charAt(1) + (j * b));
                        piece = position.getPlace(enemyLoc);
                    }
                    if (!piece.getColor().equals(getColor())) {
                        if (piece.canAttack(location)) {
                            return piece;
                        }
                    }
                } catch (Exception e) {
                }
            }
            b++;
            if (b == 2) {
                a++;
                b = -1;
            }
        }
        return null;
    }

    public Piece isEchec(String location, Board position) {
        Piece enemy = isEchecByCavalier(location, position);
        if (enemy != null) {
            return enemy;
        }
        return isEchecByOther(location, position);
    }

    private boolean isNear(int[] location1, int[] location2) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                if (location1[0] + i == location2[0] && location1[1] + j == location2[1]) {
                    return true;
                }
            }
        }
        return false;
        // i=-1 j=-1
        // i=-1 j=0
        // i=-1 j=1
        // i=0 j=-1
        // i=0 j=1
        // i=1 j=-1
        // i=1 j=0
        // i=1 j=1
    }
}
