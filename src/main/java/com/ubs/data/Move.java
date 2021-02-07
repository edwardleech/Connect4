package com.ubs.data;

/**
 * Data class for store column, row and player of the move
 */
public class Move {

    private int column;
    private int row;
    private GeneralPlayer player;

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public GeneralPlayer getPlayer() {
        return player;
    }

    public void setPlayer(GeneralPlayer player) {
        this.player = player;
    }
}
