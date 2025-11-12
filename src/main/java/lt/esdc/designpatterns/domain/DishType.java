package lt.esdc.designpatterns.domain;

public enum DishType {
    RAMEN, SPAGHETTI, CHOWMEIN;

    public static DishType from(String s) {
        return switch (s.trim().toLowerCase()) {
            case "ramen" -> RAMEN;
            case "spaghetti" -> SPAGHETTI;
            case "chowmein", "chow_mein", "chow-mein" -> CHOWMEIN;
            default -> throw new IllegalArgumentException("Unknown dish: " + s);
        };
    }
}
