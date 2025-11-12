package lt.esdc.designpatterns.domain;

public enum Region {
    ITALY, INDIA;

    public static Region from(String s) {
        return switch (s.trim().toLowerCase()) {
            case "italy", "it" -> ITALY;
            case "india", "in" -> INDIA;
            default -> throw new IllegalArgumentException("Unknown region: " + s);
        };
    }
}
