import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TicTacToeSpec {

    private TicTacToe ticTacToe;

    @BeforeEach
    public final void before() {
        ticTacToe = new TicTacToe();
    }

    @Test
    public void whenXOutsideBoardThenRuntimeException() {
        assertThrows(RuntimeException.class, () -> {
            ticTacToe.play(5, 2);
        }, "X is outside board");
    }

    @Test
    public void whenYOutsideBoardThenRuntimeException() {
        assertThrows(RuntimeException.class, () -> {
            ticTacToe.play(2, 5);
        }, "Y is outside board");
    }

    @Test
    public void whenOccupiedThenRuntimeException() {
        ticTacToe.play(2, 1);
        assertThrows(RuntimeException.class, () -> {
            ticTacToe.play(2, 1);
        }, "Box is occupied");
    }

    @Test
    public void givenFirstTurnWhenNextPlayerThenX() {
        assertEquals("X", ticTacToe.nextPlayer());
    }

    @Test
    public void givenLastTurnWasXWhenNextPlayerThenO() {
        ticTacToe.play(1, 1);
        assertEquals("O", ticTacToe.nextPlayer());
    }

    @Test
    public void whenPlayThenNoWinner() {
        String actual = ticTacToe.play(1, 1);
        assertEquals("No winner", actual);
    }

    @Test
    public void whenPlayXAndWholeHorizontalLineThenWinnerIsX() {
        ticTacToe.play(1, 1);
        ticTacToe.play(1, 2);
        ticTacToe.play(2, 1);
        ticTacToe.play(2, 2);
        String actual = ticTacToe.play(3, 1);
        assertEquals("X is the winner", actual);
    }

    @Test
    public void whenPlayOAndWholeHorizontalLineThenWinnerIsO() {
        ticTacToe.play(3, 3);
        ticTacToe.play(1, 1);
        ticTacToe.play(1, 2);
        ticTacToe.play(2, 1);
        ticTacToe.play(2, 2);
        String actual = ticTacToe.play(3, 1);
        assertEquals("O is the winner", actual);
    }

    @Test
    public void whenPlayXAndWholeVerticalLineThenWinner() {
        ticTacToe.play(2, 1);
        ticTacToe.play(1, 1);
        ticTacToe.play(3, 1);
        ticTacToe.play(1, 2);
        ticTacToe.play(2, 2);
        String actual = ticTacToe.play(1, 3);
        assertEquals("O is the winner", actual);
    }

    @Test
    public void whenPlayAndTopBottomDiagonalLineThenWinner() {
        ticTacToe.play(1, 1);
        ticTacToe.play(1, 2);
        ticTacToe.play(2, 2);
        ticTacToe.play(1, 3);
        String actual = ticTacToe.play(3, 3);
        assertEquals("X is the winner", actual);
    }

    @Test
    public void whenPlayAndBottomTopDiagonalLineThenWinner() {
        ticTacToe.play(1, 3);
        ticTacToe.play(1, 2);
        ticTacToe.play(2, 2);
        ticTacToe.play(3, 3);
        String actual = ticTacToe.play(3, 1);
        assertEquals("X is the winner", actual);
    }

    @Test
    public void whenAllBoxesAreFilledThenDraw() {
        ticTacToe.play(1, 1);
        ticTacToe.play(1, 2);
        ticTacToe.play(1, 3);
        ticTacToe.play(2, 1);
        ticTacToe.play(2, 3);
        ticTacToe.play(2, 2);
        ticTacToe.play(3, 1);
        ticTacToe.play(3, 3);
        String actual = ticTacToe.play(3, 2);
        assertEquals("The result is draw", actual);
    }
}
