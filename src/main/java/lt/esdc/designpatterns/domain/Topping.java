package lt.esdc.designpatterns.domain;

public enum Topping {
    SESAME, TOFU, CHILI;

    public static Topping from(String s) {
        return switch (s.trim().toLowerCase()) {
            case "sesame" -> SESAME;
            case "tofu" -> TOFU;
            case "chili" -> CHILI;
            default -> throw new IllegalArgumentException("Unknown topping: " + s);
        };
    }

    public String token() {
        return name().toLowerCase();
    }
}
