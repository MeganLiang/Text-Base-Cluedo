package controller;

import model.Move;
import model.Player;
import model.Suggestion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class Moving {
    private static Game game = new Game();

    public static void movePlayer() {
        for(Player player : game.getPlayerList()) {
            System.out.println("Hi " + player.getName());
            Random random = new Random();
            int diceRoll = random.nextInt(12 - 1 + 1) + 1;
            System.out.println("You rolled a " + diceRoll);
            String commands = "";
            List<Move.Moves> moves = new ArrayList<>(Arrays.asList(Move.Moves.values()));
            if(player.isInRoom()) {
                System.out.println("You are in a room");
                commands = game.textBaseCluedo.movingInRoom();
                String[] commandArray = commands.trim().split("\\s+");
                String exitRoute = commandArray[0];
                for(int i=1; i<commandArray.length; i++) {
                    while(!moves.contains(Move.Moves.fromString(commandArray[i]))) {
                        commands = game.textBaseCluedo.invalidArrayInput();
                        commandArray = commands.trim().split("\\s+");
                    }
                }
                while(commandArray.length != diceRoll+1) {
                    commands = game.textBaseCluedo.invalidArrayInput();
                    commandArray = commands.trim().split("\\s+");
                }
            }else {
                commands = game.textBaseCluedo.moving(); //not in room
                String[] commandArray = commands.trim().split("\\s+");

                for(int i=0; i<commandArray.length; i++) {
                    while(!moves.contains(Move.Moves.fromString(commandArray[i]))) {
                        commands = game.textBaseCluedo.invalidArrayInput();
                        commandArray = commands.trim().split("\\s+");
                    }
                }
                while(commandArray.length != diceRoll) {
                    commands = game.textBaseCluedo.invalidArrayInput();
                    commandArray = commands.trim().split("\\s+");
                }
            }



        }
    }


}
