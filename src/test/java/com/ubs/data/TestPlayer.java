package com.ubs.data;

/**
 * Test player ENUM for testing
 */
public enum TestPlayer implements GeneralPlayer {

    TESTHORIZONTALWINPLAYER1("RED", "R", 1, new TestUser("TestPlayer1HorizontalMoves.txt")),
    TESTHORIZONTALWINPLAYER2("GREEN", "G", 2, new TestUser("TestPlayer2HorizontalMoves.txt")),
    TESTVERTICALWINPLAYER1("RED", "R", 1, new TestUser("TestPlayer1VerticalMoves.txt")),
    TESTVERTICALWINPLAYER2("GREEN", "G", 2, new TestUser("TestPlayer2VerticalMoves.txt")),
    TESTDIAGONALFWINPLAYER1("RED", "R", 1, new TestUser("TestPlayer1DiagonalForwardMoves.txt")),
    TESTDIAGONALFWINPLAYER2("GREEN", "G", 2, new TestUser("TestPlayer2DiagonalForwardMoves.txt")),
    TESTDIAGONALBWINPLAYER1("RED", "R", 1, new TestUser("TestPlayer1DiagonalBackwardMoves.txt")),
    TESTDIAGONALBWINPLAYER2("GREEN", "G", 2, new TestUser("TestPlayer2DiagonalBackwardMoves.txt")),
    TESTDRAWPLAYER1("RED", "R", 1, new TestUser("TestPlayer1DrawMoves.txt")),
    TESTDRAWPLAYER2("GREEN", "G", 2, new TestUser("TestPlayer2DrawMoves.txt"));

    private String color;
    private String disc;
    private int playerNumber;
    private GeneralUser user;

    TestPlayer(String colorArg, String discArg, int playerNumberArg, GeneralUser userArg){
        color = colorArg;
        disc = discArg;
        playerNumber = playerNumberArg;
        user = userArg;
    }

    public String getColor()
    {
        return color;
    }

    public String getDisc()
    {
        return disc;
    }

    public int getPlayerNumber()
    {
        return playerNumber;
    }

    public GeneralUser getUser()
    {
        return user;
    }
}
