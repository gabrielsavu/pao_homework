import java.text.MessageFormat;

public class Triangle extends Form {

    private Float height;

    private Float base;

    Triangle() {
        super();
    }

    Triangle(String color, Float height, Float base) {
        super(color);
        this.height = height;
        this.base = base;
    }

    public void printTriangleDimensions() {
        System.out.println(MessageFormat.format("height={0} base={1}", this.height, this.base));
    }

    @Override
    public Float getArea() {
        return height * base;
    }

    @Override
    public String toString() {
        return MessageFormat.format("{0}: {1} {2,number}", this.getClass().getSimpleName(), super.toString(), this.getArea());
    }

    /*
     * Daca nu e o instanta de Tiangle atunci nu discutam.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof Triangle) {
            Triangle circle = (Triangle) o;
            return this.height.equals(circle.height) && this.base.equals(circle.base) && this.getColor().equals(circle.getColor());
        }
        return false;
    }
}
