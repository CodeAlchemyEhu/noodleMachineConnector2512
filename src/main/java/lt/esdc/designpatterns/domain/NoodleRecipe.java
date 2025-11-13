package lt.esdc.designpatterns.domain;

public class NoodleRecipe {
    private final int noodleGrams;
    private final int waterMl;
    private final int brothMl;
    private final int vegetableGrams;

    private NoodleRecipe(int noodleGrams, int waterMl, int brothMl, int vegetableGrams) {
        this.noodleGrams = noodleGrams;
        this.waterMl = waterMl;
        this.brothMl = brothMl;
        this.vegetableGrams = vegetableGrams;
    }

    public NoodleRecipe(NoodleRecipe other) {
        this.noodleGrams = other.noodleGrams;
        this.waterMl = other.waterMl;
        this.brothMl = other.brothMl;
        this.vegetableGrams = other.vegetableGrams;
    }

    public String toCommand() {
        return noodleGrams + "g " + waterMl + "ml " + brothMl + "ml " + vegetableGrams + "g";
    }

    @Override
    public NoodleRecipe clone() {
        try {
            return (NoodleRecipe) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public static class Builder {
        private int noodleGrams;
        private int waterMl;
        private int brothMl;
        private int vegetableGrams;

        public Builder noodle(int grams) {
            this.noodleGrams = grams;
            return this;
        }

        public Builder water(int ml) {
            this.waterMl = ml;
            return this;
        }

        public Builder broth(int ml) {
            this.brothMl = ml;
            return this;
        }

        public Builder vegetables(int grams) {
            this.vegetableGrams = grams;
            return this;
        }

        public NoodleRecipe build() {
            return new NoodleRecipe(noodleGrams, waterMl, brothMl, vegetableGrams);
        }
    }
}
