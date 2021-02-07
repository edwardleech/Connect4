package com.ubs.controller;

import com.ubs.data.GeneralPlayer;
import com.ubs.data.Move;
import com.ubs.display.GameBoard;
import com.ubs.exception.InvalidMoveException;
import com.ubs.exception.InvalidOperationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Controller of Connect5 Game
 */
@Component
public class GameController implements Runnable {

    @Value("${boardColumns}")
    private Integer boardColumns;

    @Value("${boardRows}")
    private Integer boardRows;

    @Value("${connectedDiscNumberToWin}")
    private Integer connectedDiscNumberToWin;

    private int round;
    private GameBoard board;
    private GeneralPlayer currentPlayer;
    private boolean playerWin = false;
    private Move currentMove = new Move();
    private GeneralPlayer player1;
    private GeneralPlayer player2;

    public GameController()
    {

    }

    public GameController(int boardColumns, int boardRows, int connectedDiscNumberToWin)
    {
        this.boardColumns = boardColumns;
        this.boardRows = boardRows;
        this.connectedDiscNumberToWin = connectedDiscNumberToWin;
    }

    /**
     * Initialize Game
     * @param player1
     * @param player2
     */
    public void initGame(GeneralPlayer player1, GeneralPlayer player2)
    {
        round = 1;
        this.player1 = player1;
        this.player2 = player2;
        board = new GameBoard(boardColumns, boardRows, player1, player2);
        currentPlayer = player1;
        playerWin = false;
    }

    /**
     * Start the game
     */
    public void gameStart()
    {
        while (round <= (boardColumns * boardRows) && !playerWin)
        {
            board.printBoard();
            System.out.print(String.format("Player %d [%s] - choose column (1-%d): ",
                    currentPlayer.getPlayerNumber(), currentPlayer.getColor(), boardColumns));
            try {
                currentMove.setPlayer(currentPlayer);
                currentMove.setColumn(currentPlayer.getUser().getInput());
                currentMove = board.placeDisc(currentMove);
                playerWin = checkPlayerWin(currentMove);
            } catch (InvalidMoveException ime)
            {
                System.out.println(String.format("**************** [ERROR] %s ****************",ime.getMessage()));
                continue;
            }
            if (!playerWin) {
                round++;
                currentPlayer = currentPlayer == player1 ? player2 : player1;
            }
        }

        board.printBoard();
        if (playerWin)
        {
            System.out.println(String.format("Player %d [%s] wins!", currentPlayer.getPlayerNumber(), currentPlayer.getColor()));
        }
        else
        {
            System.out.println("Draw Game!");
        }
    }

    /**
     * Check if the disc at column,row is same as player disc
     * @param column
     * @param row
     * @param checkPlayer
     * @return
     */
    private boolean isSameAsPlayerDisc(int column, int row, GeneralPlayer checkPlayer)
    {
        try {
            GeneralPlayer player = board.getDisc(column, row);
            if (player == checkPlayer)
            {
                return true;
            }
        } catch (InvalidOperationException ioe)
        {
            System.err.println(ioe.getMessage());
        }
        return false;
    }

    /**
     * Check if the last move from player win the game
     * Win if disc connected in horizontal, vertical or diagonals
     * @param currentMove
     * @return
     */
    private boolean checkPlayerWin(Move currentMove) {

        // Check horizontal
        int connectedDisc = 0;
        // Move left
        for (int c=currentMove.getColumn(); c>0; c--)
        {
            if (isSameAsPlayerDisc(c, currentMove.getRow(), currentMove.getPlayer()))
            {
                connectedDisc++;
                if (connectedDisc >= connectedDiscNumberToWin)
                {
                    return true;
                }
            }
            else
                break;
        }
        // Move right
        for (int c=currentMove.getColumn()+1; c<=boardColumns; c++)
        {
            if (isSameAsPlayerDisc(c, currentMove.getRow(), currentMove.getPlayer()))
            {
                connectedDisc++;
                if (connectedDisc >= connectedDiscNumberToWin)
                {
                    return true;
                }
            }
            else
                break;
        }
        // Check Vertical
        connectedDisc = 0;
        // Move downward
        for (int r=currentMove.getRow(); r>0; r--)
        {
            if (isSameAsPlayerDisc(currentMove.getColumn(), r, currentMove.getPlayer()))
            {
                connectedDisc++;
                if (connectedDisc >= connectedDiscNumberToWin)
                {
                    return true;
                }
            }
            else
                break;
        }
        // Move upward
        for (int r=currentMove.getRow()+1; r<=boardRows; r++)
        {
            if (isSameAsPlayerDisc(currentMove.getColumn(), r, currentMove.getPlayer()))
            {
                connectedDisc++;
                if (connectedDisc >= connectedDiscNumberToWin)
                {
                    return true;
                }
            }
            else
                break;
        }
        // Check Diagonals "\"
        connectedDisc = 0;
        int c = currentMove.getColumn();
        int r = currentMove.getRow();
        // Move toward upper left corner
        while (c > 0 && r <= boardRows)
        {
            if (isSameAsPlayerDisc(c, r, currentMove.getPlayer()))
            {
                connectedDisc++;
                if (connectedDisc >= connectedDiscNumberToWin)
                {
                    return true;
                }
            }
            else {
                break;
            }
            c--;
            r++;
        }
        c = currentMove.getColumn()+1;
        r = currentMove.getRow()-1;
        // Move toward lower right corner
        while (c <= boardColumns && r > 0)
        {
            if (isSameAsPlayerDisc(c, r, currentMove.getPlayer()))
            {
                connectedDisc++;
                if (connectedDisc >= connectedDiscNumberToWin)
                {
                    return true;
                }
            }
            else {
                break;
            }
            c++;
            r--;
        }

        // Check Diagonals "/"
        connectedDisc = 0;
        c = currentMove.getColumn();
        r = currentMove.getRow();
        // Move toward lower left corner
        while (c > 0 && r > 0)
        {
            if (isSameAsPlayerDisc(c, r, currentMove.getPlayer()))
            {
                connectedDisc++;
                if (connectedDisc >= connectedDiscNumberToWin)
                {
                    return true;
                }
            }
            else {
                break;
            }
            c--;
            r--;
        }
        c = currentMove.getColumn()+1;
        r = currentMove.getRow()+1;
        // Move toward upper right corner
        while (c <= boardColumns && r <= boardRows)
        {
            if (isSameAsPlayerDisc(c, r, currentMove.getPlayer()))
            {
                connectedDisc++;
                if (connectedDisc >= connectedDiscNumberToWin)
                {
                    return true;
                }
            }
            else {
                break;
            }
            c++;
            r++;
        }

        return false;
    }

    @Override
    public void run() {
        gameStart();
    }

    public boolean isPlayerWin() {
        return playerWin;
    }
}
