package model;

/**
 * interface is used by the Weapon, Room and Character classes to represent physical cards in the game
 */
public interface Card {
    /**
     * getName of card
     * @return
     */
    String getName();

    /**
     * gets a randomly generated card
     * @param clazz
     * @return
     */
    Enum<? extends Enum> getRandom(Class clazz);
}
