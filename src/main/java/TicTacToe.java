import java.util.HashMap;
import java.util.Map;

public class TicTacToe {

    private Map<String, String> board = new HashMap<>();
    private String lastPlayer = "O";
    private static final int maxBoardSize = 9;

    public String play(int x, int y) {
        checkAxis(x, y);
        lastPlayer = nextPlayer();
        setBox(x, y, lastPlayer);
        if (checkHorizontalWinner() || checkVerticalWinner() || checkDiagonalWineWinner())
            return lastPlayer + " is the winner";

        if (board.size() == maxBoardSize) {
            return "The result is draw";
        }
        return "No winner";
    }

    private boolean checkDiagonalWineWinner() {
        final String plotTopLeft = board.get(1 + "" + 1);
        final String plotCenter = board.get(2 + "" + 2);
        final String plotBottomRight = board.get(3 + "" + 3);
        final String plotTopRight = board.get(3 + "" + 1);
        final String plotBottomLeft = board.get(1 + "" + 3);
        return ("X".equals(plotTopLeft) && "X".equals(plotCenter) && "X".equals(plotBottomRight)) ||
                ("X".equals(plotBottomLeft) && "X".equals(plotCenter) && "X".equals(plotTopRight)) ||
                ("O".equals(plotTopLeft) && "O".equals(plotCenter) && "O".equals(plotBottomRight)) ||
                ("O".equals(plotBottomLeft) && "O".equals(plotCenter) && "O".equals(plotTopRight));
    }

    private boolean checkHorizontalWinner() {
        for (int plotY = 1; plotY <= 3; plotY++) {
            final String plotX1 = board.get(1 + "" + plotY);
            final String plotX2 = board.get(2 + "" + plotY);
            final String plotX3 = board.get(3 + "" + plotY);
            if (("X".equals(plotX1) && "X".equals(plotX2) && "X".equals(plotX3)) ||
                    "O".equals(plotX1) && "O".equals(plotX2) && "O".equals(plotX3)) {
                System.out.println("horizontal win");
                return true;
            }
        }
        return false;
    }

    private boolean checkVerticalWinner() {
        for (int plotX = 1; plotX <= 3; plotX++) {
            final String ployY1 = board.get(plotX + "" + 1);
            final String plotY2 = board.get(plotX + "" + 2);
            final String plotY3 = board.get(plotX + "" + 3);
            if (("X".equals(ployY1) && "X".equals(plotY2) && "X".equals(plotY3)) ||
                    "O".equals(ployY1) && "O".equals(plotY2) && "O".equals(plotY3)) {
                System.out.println("vertical win");
                return true;
            }
        }
        return false;
    }

    private void setBox(int x, int y, String lastPlayer) {
        String select = x + "" + y;
        if (checkOccupy(select)) {
            throw new RuntimeException("Box is occupied");
        } else {
            board.put(select, lastPlayer);
        }
    }

    private boolean checkOccupy(String select) {
        return board.get(select) != null;
    }

    private void checkAxis(int x, int y) {
        if (x < 1 || x > 3) {
            throw new RuntimeException("X is outside board");
        } else if (y < 1 || y > 3) {
            throw new RuntimeException("Y is outside board");
        }
    }

    public String nextPlayer() {
        if ("X".equals(lastPlayer)) {
            return "O";
        }
        return "X";
    }
}