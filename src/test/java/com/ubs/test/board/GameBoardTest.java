package com.ubs.test.board;

import com.ubs.data.Move;
import com.ubs.data.TestPlayer;
import com.ubs.display.GameBoard;
import com.ubs.exception.InvalidMoveException;
import com.ubs.exception.InvalidOperationException;
import com.ubs.test.Connect4Tests;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameBoardTest extends Connect4Tests {

    @BeforeAll
    public static void init() throws Exception {
    }

    @Test
    public void placeDiscInvalidColumnTest() throws Exception {
        Exception exception = assertThrows(InvalidMoveException.class, () -> {
            GameBoard gameBoard = new GameBoard(7, 6, TestPlayer.TESTHORIZONTALWINPLAYER1, TestPlayer.TESTHORIZONTALWINPLAYER2);
            Move move = new Move();
            move.setColumn(0);
            gameBoard.placeDisc(move);
        });

        String expectedMessage = "You can only place column between 1 to 7";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void placeDiscColumnFullTest() throws Exception {
        Exception exception = assertThrows(InvalidMoveException.class, () -> {
            GameBoard gameBoard = new GameBoard(7, 6, TestPlayer.TESTHORIZONTALWINPLAYER1, TestPlayer.TESTHORIZONTALWINPLAYER2);
            Move move = new Move();
            move.setColumn(1);
            move.setPlayer(TestPlayer.TESTHORIZONTALWINPLAYER1);
            gameBoard.placeDisc(move);
            move.setColumn(1);
            move.setPlayer(TestPlayer.TESTHORIZONTALWINPLAYER2);
            gameBoard.placeDisc(move);
            move.setColumn(1);
            move.setPlayer(TestPlayer.TESTHORIZONTALWINPLAYER1);
            gameBoard.placeDisc(move);
            move.setColumn(1);
            move.setPlayer(TestPlayer.TESTHORIZONTALWINPLAYER2);
            gameBoard.placeDisc(move);
            move.setColumn(1);
            move.setPlayer(TestPlayer.TESTHORIZONTALWINPLAYER1);
            gameBoard.placeDisc(move);
            move.setColumn(1);
            move.setPlayer(TestPlayer.TESTHORIZONTALWINPLAYER2);
            gameBoard.placeDisc(move);
            move.setColumn(1);
            move.setPlayer(TestPlayer.TESTHORIZONTALWINPLAYER1);
            gameBoard.placeDisc(move);
        });

        String expectedMessage = "The column 1 is full.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void getInvalidDiscTest() throws Exception {
        Exception exception = assertThrows(InvalidOperationException.class, () -> {
            GameBoard gameBoard = new GameBoard(7, 6, TestPlayer.TESTHORIZONTALWINPLAYER1, TestPlayer.TESTHORIZONTALWINPLAYER2);
            gameBoard.getDisc(0,9);
        });

        String expectedMessage = "Column must be between 1 to 7 and Row must be between 1 to 6.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}