package com.test;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Game game = new Game();
        game.initialize();
        int player = 0;
        String gameResult;
        while ((gameResult = game.getGameResult()) == null) {
            player %= 2;
            printOptionsForPlayer(player+1);
            int input = in.nextInt();
            try {
                game.play(player, input);
            } catch (Exception e) {
                System.out.println("\nYou have selected wrong option. " + e.getMessage()
                        + ". \nPlease Try Again.\n");
                continue;
            }
            player++;
        }
        System.out.println(gameResult);
    }

    private static void printOptionsForPlayer(int player) {
        System.out.println("\nPlayer " + player + ": Choose an outcome from the list below\n" +
                "1. Strike\n" +
                "2. Multistrike\n" +
                "3. Red strike\n" +
                "4. Striker strike\n" +
                "5. Defunct Red coin\n" +
                "6. Defunct Black coin\n" +
                "7. None");
    }
}
