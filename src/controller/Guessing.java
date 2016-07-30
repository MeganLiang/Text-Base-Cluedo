package controller;

import model.*;
import model.Character;
import static controller.Game.getTextBaseCluedo;
import static controller.Setup.getAvailableCharacters;
import static controller.Setup.getAvailableRooms;

public class Guessing {
    /**
     * calls textBaseCluedo to ask for player input for accusation
     * @param player player
     */
    public static void chooseAccusation(Player player) {
        System.out.println(player.getName() + "'s turn");
        String choice = getTextBaseCluedo().accusationOption();
        if(choice.equals("Yes") || choice.equals("yes") || choice.equals("y")) {
            player.setMadeAccusation(true);
            accusation();
        }

    }
    /**
     * player can make accusations to win the game
     * @return Accusation
     */
    public static Accusation accusation() {
        String s = getTextBaseCluedo().accuse(); //weapon room character
        String[] splitS = s.trim().split("\\s+");

        while(!Setup.getAvailableWeapons().contains(Weapon.Weapons.fromString(splitS[0]))
                || !getAvailableRooms().contains(Room.Rooms.fromString(splitS[1]))
                || !getAvailableCharacters().contains(Character.Characters.fromString(splitS[2]))) { //invalid input, can be duplicate character or not a token
            System.out.println("Unexpected input, try again");
            s = getTextBaseCluedo().accuse();
            splitS = s.trim().split("\\s+");
        }
        Weapon w = new Weapon(Weapon.Weapons.valueOf(splitS[0]));
        Room r = new Room(Room.Rooms.valueOf(splitS[1]));
        Character c = new Character(Character.Characters.valueOf(splitS[2]));
        return new Accusation(w,r,c);

    }

    /**
     * players can make suggestion to gather intelligence for their accusation
     *
     */
    public static void suggestion() {
        for(Player player: Game.getPlayerList()) {
            if (player.isInRoom(player, Game.getBoard())) {
                System.out.println(player.getName() + ", you are in the " + player.whatRoom(player, Game.getBoard()).getName());
                String s = getTextBaseCluedo().suggest(); //weapon room character
                String[] splitS = s.trim().split("\\s+");

                while (splitS.length != 3
                        || !Setup.getAvailableWeapons().contains(Weapon.Weapons.fromString(splitS[0]))
                        || !getAvailableRooms().contains(Room.Rooms.fromString(splitS[1]))
                        || !getAvailableCharacters().contains(Character.Characters.fromString(splitS[2]))
                        || !player.whatRoom(player, Game.getBoard()).getName().equals(new Room(Room.Rooms.valueOf(splitS[1])).getName())) { //invalid input, can be duplicate character or not a token
                    System.out.println("Unexpected input, try again. You can only make suggestions about the room");
                    s = getTextBaseCluedo().suggest();
                    splitS = s.trim().split("\\s+");
                }

                Weapon w = new Weapon(Weapon.Weapons.valueOf(splitS[0]));
                Room r = new Room(Room.Rooms.valueOf(splitS[1]));
                Character c = new Character(Character.Characters.valueOf(splitS[2]));
                Suggestion suggestion = new Suggestion(w, r, c);
                //return new Suggestion(w, r, c);
                proveSuggestions(suggestion,player);
            }
        }
        //return null;
    }

    /**
     * players show their hand if they have a mentioned suggestion. If more the one card is mentioned
     * the first card found in the list Hand is printed
     * @param suggestion player's suggestion
     * @param excludePlayer player
     */
    private static void proveSuggestions(Suggestion suggestion, Player excludePlayer) {
        for(Player player: Game.getPlayerList()) {
            if(player != excludePlayer) {
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

}
