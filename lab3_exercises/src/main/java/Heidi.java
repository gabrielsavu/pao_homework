import java.text.MessageFormat;

public class Heidi extends CandyBox {

    private Float l = 2.0f;

    public Heidi() {
        super();
    }

    public Heidi(String flavor, String origin) {
        super(flavor, origin);
    }

    @Override
    public Float getVolume() {
        return Double.valueOf(Math.pow(l, 3)).floatValue();
    }

    @Override
    public String toString() {
        return super.toString() + " has volume " + getVolume();
    }

    public void printHeidiDim() {
        System.out.println(MessageFormat.format("l={1}", this.l));
    }
}
