package com.ubs.display;

import com.ubs.data.GeneralPlayer;
import com.ubs.data.Move;
import com.ubs.exception.InvalidMoveException;
import com.ubs.exception.InvalidOperationException;

/**
 * Class to manager everything related to Game board
 */
public class GameBoard {

    private final String BOARDER = "|";
    private final String EMPTY = " ";
    private GeneralPlayer player1;
    private GeneralPlayer player2;

    private int [] [] board = null;
    private String [] displayBoard = null;
    private int columns;
    private int rows;

    public GameBoard(int columns, int rows, GeneralPlayer player1, GeneralPlayer player2)
    {
        // Add extra row to board for storing the next empty row in the column
        board = new int[columns][rows+1];
        displayBoard = new String[rows];
        this.columns = columns;
        this.rows = rows + 1;
        this.player1 = player1;
        this.player2 = player2;
        initializeBoard();
    }

    /**
     * Initialize game board to set every position to 0
     */
    public void initializeBoard()
    {
        for (int i = 0; i <columns; i++)
        {
            for (int j = 0; j < rows; j++)
            {
                board[i][j] = 0;
            }
        }

        for (int i = rows-2; i >= 0; i--)
        {
            displayBoard[i] = getRowDisplay(i);
        }
    }

    /**
     * Get current snapshot of a row
     * @param row
     * @return
     */
    private String getRowDisplay(int row)
    {
        StringBuilder sb = new StringBuilder();
        sb.append(BOARDER);
        for (int j = 0; j < columns; j++)
        {
            switch (board[j][row])
            {
                case 1:
                    sb.append(player1.getDisc()).append(BOARDER);
                    break;
                case 2:
                    sb.append(player2.getDisc()).append(BOARDER);
                    break;
                default:
                    sb.append(EMPTY).append(BOARDER);
                    break;
            }
        }
        return sb.toString();
    }

    /**
     * Place disc to game board and update the display board
     * @param currentMove
     * @return
     * @throws InvalidMoveException
     */
    public Move placeDisc(Move currentMove) throws InvalidMoveException
    {
        if (currentMove.getColumn() < 1 || currentMove.getColumn() > this.columns)
        {
            throw new InvalidMoveException("You can only place column between 1 to " + this.columns);
        }

        if (board[currentMove.getColumn()-1][rows-1] == rows-1)
        {
            throw new InvalidMoveException(String.format("The column %d is full.", currentMove.getColumn()));
        }

        int placeAtRow = board[currentMove.getColumn()-1][rows-1];
        board[currentMove.getColumn()-1][board[currentMove.getColumn()-1][rows-1]++] = currentMove.getPlayer().getPlayerNumber();
        String currentRowDisplay = displayBoard[placeAtRow];
        displayBoard[placeAtRow] = String.format("%s%s%s",
                currentRowDisplay.substring(0, ((currentMove.getColumn()-1)*2)+1),
                currentMove.getPlayer().getDisc(),
                currentRowDisplay.substring((currentMove.getColumn()*2), currentRowDisplay.length()));
        currentMove.setRow(placeAtRow+1); // +1 because row starts from 1
        return currentMove;
    }

    /**
     * Print board to console
     */
    public void printBoard()
    {
        for (int i = rows-2; i >= 0; i--)
        {
            System.out.println(displayBoard[i]);
        }
    }

    /**
     * Get player at column, row of the board
     * @param column
     * @param row
     * @return
     * @throws InvalidOperationException
     */
    public GeneralPlayer getDisc(int column, int row) throws InvalidOperationException
    {
        if (column < 1 || column > this.columns || row < 1 || row > this.rows-1)
        {
            throw new InvalidOperationException(String.format("Column must be between 1 to %d and Row must be between 1 to %d.", this.columns, this.rows-1));
        }

        switch (board[column-1][row-1])
        {
            case 1:
                return player1;
            case 2:
                return player2;
            default:
                return null;
        }
    }
}
