import arrays.MyArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

public class MyArrayListTest {

    @Test
    public void multiplicationOfZeroIntegersShouldReturnZero() {
        MyArrayList myArrayList = new MyArrayList();

        myArrayList.add(1);
        myArrayList.add(4);
        myArrayList.add(2);

        assertEquals(1, myArrayList.get(0), 0.0001);
        assertEquals(4, myArrayList.get(1), 0.0001);
        assertEquals(2, myArrayList.get(2), 0.0001);

        MyArrayList myArrayList2 = new MyArrayList(5);
        for (int i = 0; i < 10; i ++) {
            myArrayList2.add(i);
        }

        assertEquals(10, myArrayList2.size());
        for (int i = 0; i < 5; i ++) {
            assertTrue(myArrayList2.contains(i));
        }

        myArrayList2.remove(3);
        assertFalse(myArrayList2.contains(3));
        myArrayList2.remove(3);
        assertFalse(myArrayList2.contains(4));
        myArrayList2.remove(1);
        assertFalse(myArrayList2.contains(1));
        myArrayList2.remove(6);
        assertFalse(myArrayList2.contains(4));



    }

}
