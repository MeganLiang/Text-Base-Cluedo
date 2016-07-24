package model;

public class Move {
    public enum Moves {
        U("U"),
        D("D"),
        L("L"),
        R("R");

        private String text;

        Moves(String text) {
            this.text = text;
        }

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
