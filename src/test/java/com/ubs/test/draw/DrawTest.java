package com.ubs.test.draw;

import com.ubs.controller.GameController;
import com.ubs.data.TestPlayer;
import com.ubs.test.Connect4Tests;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class DrawTest extends Connect4Tests {

    @BeforeAll
    public static void init() throws Exception {
    }

    @Test
    public void horizontalWinTest() throws Exception {
        GameController gameController = new GameController(7 , 6 , 4);
        gameController.initGame(TestPlayer.TESTDRAWPLAYER1, TestPlayer.TESTDRAWPLAYER2);
        gameController.gameStart();
        assert(gameController.isPlayerWin() == false);
    }
}