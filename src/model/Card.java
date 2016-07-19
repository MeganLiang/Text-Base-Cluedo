package model;

/**
 * Created by megan on 17/07/16.
 * interface is used by the Weapon, Room and Character classes to represent physical cards in the game
 */
public interface Card {
    String getName();

    /**
     * gets a randomly generated card
     * @param clazz
     * @return
     */
    static Enum<? extends Enum> getRandom(Class clazz) {
        return null;
    }
}
