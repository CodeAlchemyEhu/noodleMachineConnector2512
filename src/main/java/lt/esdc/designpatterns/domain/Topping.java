package lt.esdc.designpatterns.domain;

import java.util.Locale;
import java.util.Objects;

public enum Topping {
    SESAME,
    TOFU,
    CHILI;

    public static Topping from(String raw) {
        Objects.requireNonNull(raw, "topping");
        String v = raw.trim();
        if (v.isEmpty()) {
            throw new IllegalArgumentException("Topping cannot be empty");
        }
        return Topping.valueOf(v.toUpperCase(Locale.ROOT));
    }
}
