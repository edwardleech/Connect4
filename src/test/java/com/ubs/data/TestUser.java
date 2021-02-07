package com.ubs.data;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Test User for running test by executing move from file
 */
public class TestUser implements GeneralUser {

    private String movesFile;
    private InputStream is = null;
    private List<Integer> moves = null;
    private int moveIndex;

    public TestUser(String movesFile)
    {
        this.movesFile = movesFile;
    }

    @Override
    public int getInput() {
        if (moves == null)
        {
            ClassLoader classLoader = TestUser.class.getClassLoader();
            try (InputStream in = classLoader.getResourceAsStream(movesFile)) {
                moves = new ArrayList<Integer>();
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(in)))
                {
                    String line;
                    while ((line = reader.readLine()) != null)
                    {
                        moves.add(Integer.parseInt(line));
                    }
                }
            } catch (Exception e) {
                System.err.println("Failed to load moves for test user from file " + movesFile + ". " + e.getMessage());
            }
            moveIndex = 0;
        }

        while (true) {
            if (moveIndex >= moves.size())
            {
                moveIndex = 0;
            }
            int move = moves.get(moveIndex++);
            return move;
        }
    }
}
