package model;

/**
 * Valid Moves the player can enter
 */
public class Move {
    public enum Moves {
        W("W"),
        A("A"),
        S("S"),
        D("D"),
        w("w"),
        a("a"),
        s("s"),
        d("d");

        private String text;

        Moves(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }

        /**
         * Gets the String associations of the enums
         * @param text
         * @return
         */
        public static Moves fromString(String text) {
            if (text != null) {
                for (Moves b : Moves.values()) {
                    if (text.equalsIgnoreCase(b.text)) {
                        return b;
                    }
                }
            }
            return null;
        }
    }
}
