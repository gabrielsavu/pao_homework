public class CandyBox {

    private String flavor;

    private String origin;

    CandyBox() {
        super();
    }

    CandyBox(String flavor, String origin) {
        super();
        this.flavor = flavor;
        this.origin = origin;
    }

    public Float getVolume() {
        return 0f;
    }

    @Override
    public String toString() {
        return "The " + origin + " " + flavor;
    }

    /**
     * E de ajuns sa verificam daca origin si flavor sunt egale.
     * @param o obiectul verificat
     * @return true - sunt egale, false - nu sunt egale
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CandyBox)) return false; // merge si cu getClass()
        CandyBox candyBox = (CandyBox) o;
        return flavor.equals(candyBox.flavor) &&
                origin.equals(candyBox.origin);
    }
}
