package model;

import java.security.SecureRandom;

public class Room implements Card  {

    private Rooms roomType; //enum which has possible tokens for rooms
    private static final SecureRandom random = new SecureRandom();
    private boolean hasWeapon;

    public Room(Rooms roomType) {
        this.roomType = roomType;
    }

    public enum Rooms { // enum which represents 9 rooms in Cluedo game
        Kitchen("Kitchen"),
        BallRoom("BallRoom"),
        Conservatory("Conservatory"),
        DiningRoom("DiningRoom"),
        BilliardRoom("BilliardRoom"),
        Library("Library"),
        Lounge("Lounge"),
        Hall("Hall"),
        Study("Study");

        private String text;

        Rooms(String text) {
            this.text = text;
        }

        public static Rooms fromString(String text) {
            if (text != null) {
                for (Rooms b: Rooms.values()) {
                    if (text.equalsIgnoreCase(b.text)) {
                        return b;
                    }
                }
            }
            return null;
        }

        }

    /**
     * randomly generates a room
     * @return a enum Rooms
     */
    @Override
    public Enum<? extends Enum> getRandom(Class clazz) {
        int x = random.nextInt(clazz.getEnumConstants().length);
        return (Rooms) clazz.getEnumConstants()[x];
    }


    public void setRoomName(Rooms roomType) {
        this.roomType = roomType;
    }


    @Override
    public String getName() {
        return roomType.toString();
    }

    public Rooms getEnum() {
        return roomType;
    }

    public boolean hasWeapon() {
        return hasWeapon;
    }

    public void setHasWeapon(boolean hasWeapon) {
        this.hasWeapon = hasWeapon;
    }


    public static void main(String[] args) {
        //getRandom(Rooms.class);
    }
}
