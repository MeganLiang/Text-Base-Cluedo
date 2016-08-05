package controller;

import model.*;
import model.Character;

/**
 * Controller for Moving the players around the board
 */
public class Guessing {
    private boolean gameWon;

    /**
     * calls textBaseCluedo to ask for player input for accusation
     *
     * @param player player
     */
    public void chooseAccusation(Player player, Game cluedo) {
        String choice = cluedo.getTextBaseCluedo().accusationOption();
        if (choice.equals("Yes") || choice.equals("yes") || choice.equals("y")) {
            player.setMadeAccusation(true);
            Accusation accusation = accusation(cluedo);
            if (accusation.equals(cluedo.getSetup().getGameSolution())) { //winning or losing determined
                System.out.println(player.getName() + ", you have won Cluedo!");
                System.out.println("Solution: ");
                accusation.printAccusation();
                gameWon = true;
            } else {//player has lost
                System.out.println("Your accusation is wrong! You can not move anymore but your cards will still be used to refute any suggestions");
            }
        }

    }

    /**
     * player can make accusations to win the game
     *
     * @return Accusation
     */
    public Accusation accusation(Game cluedo) {
        String[] splitS = cluedo.getTextBaseCluedo().accuse(); //weapon room character

        while (!cluedo.getSetup().getAvailableWeapons().contains(Weapon.Weapons.fromString(splitS[0]))
                || !cluedo.getSetup().getAvailableRooms().contains(Room.Rooms.fromString(splitS[1]))
                || !cluedo.getSetup().getAvailableCharacters().contains(Character.Characters.fromString(splitS[2]))) { //invalid input, can be duplicate character or not a token
            System.out.println("Unexpected input, try again");
            splitS = cluedo.getTextBaseCluedo().accuse();
        }
        Weapon w = new Weapon(Weapon.Weapons.valueOf(splitS[0]));
        Room r = new Room(Room.Rooms.valueOf(splitS[1]));
        Character c = new Character(Character.Characters.valueOf(splitS[2]));
        return new Accusation(w, r, c);

    }

    /**
     * players can make suggestion to gather intelligence for their accusation
     */
    public void suggestion(Game cluedo, Player player) {
        if (player.isInRoom(player, cluedo.getBoard(), cluedo)) {
            System.out.println(player.getName() + ", you are in the " + player.findRoom(player, cluedo.getBoard(), cluedo).getName());
            String[] splitS = cluedo.getTextBaseCluedo().suggest(); //weapon room character


            while (splitS.length != 3
                    || !cluedo.getSetup().getAvailableWeapons().contains(Weapon.Weapons.fromString(splitS[0]))
                    || !cluedo.getSetup().getAvailableRooms().contains(Room.Rooms.fromString(splitS[1]))
                    || !cluedo.getSetup().getAvailableCharacters().contains(Character.Characters.fromString(splitS[2]))
                    || !player.findRoom(player, cluedo.getBoard(), cluedo).getName().equals(new Room(Room.Rooms.valueOf(splitS[1])).getName())) { //invalid input, can be duplicate character or not a token
                System.out.println("Unexpected input, try again. You can only make suggestions about the room");
                splitS = cluedo.getTextBaseCluedo().suggest();
            }

            Weapon w = new Weapon(Weapon.Weapons.valueOf(splitS[0]));
            Room r = new Room(Room.Rooms.valueOf(splitS[1]));
            Character c = new Character(Character.Characters.valueOf(splitS[2]));
            Suggestion suggestion = new Suggestion(w, r, c);
            player.setJustEnteredRoom(false);

            for (Room.Rooms roomEnum : cluedo.getSetup().getAvailableRooms()) { //teleported the suggested weapon to the room
                Room oldRoom = new Room(roomEnum);
                if (oldRoom.getWeaponList().contains(w)) {
                    oldRoom.getWeaponList().remove(w); //remove the weapon from previous position
                }
            }
            r.addWeapon(w);
            System.out.println(w.getName() + " teleported to " + r.getName());
            w.setInRoom(r);

            System.out.println("Refuting the suggestion: ");
            proveSuggestions(suggestion, player, cluedo);
        }
    }


    /**
     * players show their hand if they have a mentioned suggestion. If more the one card is mentioned
     * the first card found in the list Hand is printed
     *
     * @param suggestion    player's suggestion
     * @param excludePlayer player
     */
    public static void proveSuggestions(Suggestion suggestion, Player excludePlayer, Game cluedo) {
        for (Player player : cluedo.getPlayerList()) {
            if (player != excludePlayer) {
                System.out.print(player.getName());
                System.out.println("============================");
                for (Card c : player.getHand().getCards()) {
                    if (c instanceof Weapon) {
                        Weapon weapon = (Weapon) c;
                        if (weapon.getName().equals(suggestion.getWeapon().getName())) {
                            System.out.println(weapon.getName());
                            return;
                        }
                    } else if (c instanceof Room) {
                        Room room = (Room) c;
                        if (room.getName().equals(suggestion.getRoom().getName())) {
                            System.out.println(room.getName());
                            return;
                        }
                    } else if (c instanceof Character) {
                        Character character = (Character) c;
                        if (character.getName().equals(suggestion.getCharacter().getName())) {
                            System.out.println(character.getName());
                            return;
                        }
                    }
                }
            }
        }
    }

    /**
     * Boolean for game to be won
     *
     * @return game won
     */
    public boolean isGameWon() {
        return gameWon;
    }

    /**
     * For testing, setting the boolean for game to be won
     *
     * @param gameWon
     */
    public void setGameWon(boolean gameWon) {
        this.gameWon = gameWon;
    }


}
