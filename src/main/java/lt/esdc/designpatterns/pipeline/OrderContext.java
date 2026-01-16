package lt.esdc.designpatterns.pipeline;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class OrderContext {
    private final String rawInput;

    private List<String> tokens = new ArrayList<>();
    private String discountToken;
    private String noodleToken;
    private List<String> toppingTokens = new ArrayList<>();

    public OrderContext(String rawInput) {
        this.rawInput = rawInput;
    }

    public String rawInput() {
        return rawInput;
    }

    public List<String> tokens() {
        return Collections.unmodifiableList(tokens);
    }

    public void tokens(List<String> tokens) {
        this.tokens = new ArrayList<>(tokens);
    }

    public String discountToken() {
        return discountToken;
    }

    public void discountToken(String discountToken) {
        this.discountToken = discountToken;
    }

    public String noodleToken() {
        return noodleToken;
    }

    public void noodleToken(String noodleToken) {
        this.noodleToken = noodleToken;
    }

    public List<String> toppingTokens() {
        return Collections.unmodifiableList(toppingTokens);
    }

    public void toppingTokens(List<String> toppingTokens) {
        this.toppingTokens = new ArrayList<>(toppingTokens);
    }
}
