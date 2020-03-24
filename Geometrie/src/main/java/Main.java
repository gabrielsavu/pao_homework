import javax.swing.plaf.basic.BasicTreeUI;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Form> formList = new ArrayList<>();

        formList.add(new Triangle("negru", 4f, 5f));
        formList.add(new Triangle("maro", 3f, 1f));
        formList.add(new Triangle("albastru", 1f, 8f));
        formList.add(new Circle("alb", 2f));
        formList.add(new Circle("verde", 5f));

        /*
         Output:
            Triangle: negru 20
            Triangle: maro 3
            Triangle: albastru 8
            Circle: alb 12.566
            Circle: verde 78.54
         */
        for (Form form: formList) {
            System.out.println(form);
        }

        /*
        Output:
            height=4 base=5
            height=3 base=1
            height=1 base=8
            radius=2
            radius=5
         */
        for (Form form: formList) {
            if (form instanceof Triangle) {
                ((Triangle) form).printTriangleDimensions();
            }
            else if (form instanceof  Circle) {
                ((Circle) form).printCircleDimensions();
            }
        }

        /*
        Output:
            height=4 base=5
            height=3 base=1
            height=1 base=8
            radius=2
            radius=5
         */
        for (Form form: formList) {
            if (form.getClass() == Triangle.class) {
                ((Triangle) form).printTriangleDimensions();
            }
            else if (form.getClass() ==  Circle.class) {
                ((Circle) form).printCircleDimensions();
            }
        }
    }
}
