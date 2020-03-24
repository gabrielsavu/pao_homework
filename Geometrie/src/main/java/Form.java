public abstract class Form {

    private String color;

    Form() {
        super();
    }

    public Form(String color) {
        this.color = color;
    }

    public Float getArea() {
        return 0f;
    }

    @Override
    public String toString() {
        return color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
