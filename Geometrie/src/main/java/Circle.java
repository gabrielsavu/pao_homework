import java.text.MessageFormat;
import java.util.Objects;

public class Circle extends Form {

    private Float radius;

    Circle() {
        super();
    }

    Circle(String color, Float radius) {
        super(color);
        this.radius = radius;
    }

    public void printCircleDimensions() {
        System.out.println(MessageFormat.format("radius={0}", this.radius));
    }

    @Override
    public Float getArea() {
        return Double.valueOf(Math.pow(this.radius, 2)).floatValue() * Double.valueOf(Math.PI).floatValue();
    }

    @Override
    public String toString() {
        return MessageFormat.format("{0}: {1} {2,number}", this.getClass().getSimpleName(), super.toString(), this.getArea());
    }

    /*
     * Daca nu e o instanta de Circle atunci nu discutam.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof Circle) {
            Circle circle = (Circle) o;
            return this.radius.equals(circle.radius) && this.getColor().equals(circle.getColor());
        }
        return false;
    }
}
