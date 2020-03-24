import java.text.MessageFormat;

public class Milka extends CandyBox {

    private Float r = 6.0f;

    private Float h = 4.0f;

    public Milka() {
        super();
    }

    public Milka(String flavor, String origin) {
        super(flavor, origin);
    }

    @Override
    public Float getVolume() {
        return Double.valueOf(Math.pow(r, 2)).floatValue() * h * Double.valueOf(Math.PI).floatValue() ;
    }

    @Override
    public String toString() {
        return super.toString() + " has volume " + getVolume();
    }

    public void printMilkaDim() {
        System.out.println(MessageFormat.format("r={1} h={2}", this.r, this.h));
    }
}
