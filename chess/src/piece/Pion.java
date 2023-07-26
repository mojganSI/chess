package piece;

import game.Board;
import game.Color;

public class Pion extends Piece {
    private boolean isFirstAction;


    public Pion(String location, Color color, char lable) {
        super(location, color, lable);
        isFirstAction = true;
    }

    @Override
    public boolean canMove(String end, Board position) {
        if (isSame(end, position)) {
            return false;
        }
        String location = getLocation();
        int[] scale = position.findScale(location, end);
        int a = scale[0], b = scale[1];
        Color color = getColor();
        if (color.equals(Color.white) && a >= 0) {
            return false;
        }
        if (color.equals(Color.black) && a <= 0) {
            return false;
        }

        int[] startCordinates = position.convertToInt(location);
        int[] endCordinates = position.convertToInt(end);
        Piece targetPlace = position.getPlace(end);

        if (b == 0) {
            if (targetPlace != null) {
                return false;
            }
            if (Math.abs(startCordinates[0] - endCordinates[0]) > ((isFirstAction) ? 2 : 1)) {
                return false;
            }
            if (isFirstAction) {
                if (!checkPath(startCordinates[0], startCordinates[1], a, b, endCordinates[0], endCordinates[1], position)) {
                    return false;
                }
            }
        } else {
            if (targetPlace == null || targetPlace.getColor().equals(getColor())) {
                return false;
            }

            if (Math.abs(startCordinates[0] - endCordinates[0]) != 1 || Math.abs(startCordinates[1] - endCordinates[1]) != 1) {
                return false;
            }
        }

        boolean temp = isKingSafe( a, b, position);
        if (temp) {
            isFirstAction = false;
        }
        return temp;
    }


    @Override
    public boolean canAttack(String target) {
        String myLocation = getLocation();
        int myLocationCol = myLocation.charAt(0);
        int targetCol = target.charAt(0);
        int myLocationRow = myLocation.charAt(1);
        int targetRow = target.charAt(1);

        if (Math.abs(myLocationCol - targetCol) != 1) {
            return false;
        }

        if (getColor().equals(Color.white)) {
            if (myLocationRow - targetRow != 1)
                return false;
        } else {
            if (targetRow - myLocationRow != 1)
                return false;
        }

        return true;
    }
}
