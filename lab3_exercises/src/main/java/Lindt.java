import java.text.MessageFormat;

public class Lindt extends CandyBox{

    private Float L = 3.0f;

    private Float l = 2.3f;

    private Float h = 4.0f;

    public Lindt() {
        super();
    }

    public Lindt(String flavor, String origin) {
        super(flavor, origin);
    }

    @Override
    public Float getVolume() {
        return super.getVolume();
    }

    @Override
    public String toString() {
        return super.toString() + " has volume " + getVolume();
    }

    public void printLindtDim() {
        System.out.println(MessageFormat.format("L={0} l={1} h={2}", this.L, this.l, this.h));
    }


}
