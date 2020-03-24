import java.util.ArrayList;
import java.util.List;

public class CandyBag {


    public static void main(String[] args) {


        List<CandyBox> boxes = new ArrayList<CandyBox>();

        Lindt lindt1 = new Lindt("test1", "test1");
        Lindt lindt2 = new Lindt("test2", "test2");
        Milka milka1 = new Milka("test1", "test1");
        Milka milka2 = new Milka("test4", "test2");
        Heidi heidi1 = new Heidi("test1", "test1");
        Heidi heidi2 = new Heidi("test2", "test2");

        boxes.add(lindt1);
        boxes.add(lindt2);
        boxes.add(milka1);
        boxes.add(milka2);
        boxes.add(heidi1);
        boxes.add(heidi2);

        System.out.println(lindt1.equals(lindt2));

    }
}
