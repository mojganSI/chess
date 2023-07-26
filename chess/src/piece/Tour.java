package piece;

import game.Board;
import game.Color;

public class Tour extends Piece {

    private boolean moveFlag;

    public Tour(String location, Color color, char label) {
        super(location, color, label);
        moveFlag = false;
    }

    @Override
    public boolean canMove(String end, Board position) {
        if (isSame(end, position)) {
            return false;
        }
        String location = getLocation();
        int[] scale = position.findScale(location, end);
        int a = scale[0], b = scale[1];

        if (a != 0 && b != 0) {
            return false;
        }

        int[] startCordinates = position.convertToInt(location);
        int[] endCordinates = position.convertToInt(end);

        boolean flag = checkPath(startCordinates[0], startCordinates[1], a, b, endCordinates[0], endCordinates[1], position);
        if (!flag) {
            return false;
        }

        return isKingSafe(a, b, position);
    }

    @Override
    public boolean canAttack(String target) {
        String myLocation = getLocation();
        int myLocationCol = myLocation.charAt(0);
        int targetCol = target.charAt(0);
        if (myLocationCol - targetCol == 0)
            return true;
        int myLocationRow = myLocation.charAt(1);
        int targetRow = target.charAt(1);

        if (myLocationRow - targetRow == 0)
            return true;
        return false;
    }

    public void setMoveFlag(boolean moveFlag) {
        this.moveFlag = moveFlag;
    }

    public boolean isMoveFlag() {
        return moveFlag;
    }
}
