package com.ubs.data;

/**
 * Human player ENUM
 */
public enum HumanPlayer implements GeneralPlayer {

    PLAYER1("RED", "R", 1, new HumanUser()),
    PLAYER2("GREEN", "G", 2, new HumanUser());

    private String color;
    private String disc;
    private int playerNumber;
    private GeneralUser user;

    HumanPlayer(String colorArg, String discArg, int playerNumberArg, GeneralUser userArg){
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
