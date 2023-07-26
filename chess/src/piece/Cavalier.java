package piece;

import game.Board;
import game.Color;

public class Cavalier extends Piece {

    public Cavalier(String location, Color color, char lable) {
        super(location, color, lable);
    }

    @Override
    public boolean canMove(String end, Board position) {
        if (isSame(end, position)) {
            return false;
        }
        String location = getLocation();
        int[] startLoc = position.convertToInt(location);
        int[] endLoc = position.convertToInt(end);
        int[] scale = position.findScale(location, end);
        int diffR = Math.abs(startLoc[0] - endLoc[0]);
        int diffC = Math.abs(startLoc[1] - endLoc[1]);


        if ((diffR == 1 && diffC == 2) || (diffR == 2 && diffC == 1)) {
            return isKingSafe(scale[0], scale[1], position);
        }
        return false;
    }

    @Override
    public boolean canAttack(String target) {
        String myLocation = getLocation();
        int myLocationCol = myLocation.charAt(0);
        int targetCol = target.charAt(0);

        int myLocationRow = myLocation.charAt(1);
        int targetRow = target.charAt(1);

        if (Math.abs(myLocationRow - targetRow) == 2 && Math.abs(myLocationCol - targetCol) == 1)
            return true;

        if (Math.abs(myLocationRow - targetRow) == 1 && Math.abs(myLocationCol - targetCol) == 2)
            return true;

        return false;
    }
}
