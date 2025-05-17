import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SILab2Test {

    @Test
    public void testEveryStatement() {
        //lista allItems e null
        try {
            SILab2.checkCart(null, "1234567890123456");
            fail("Expected exception for null allItems");
        } catch (RuntimeException e) {
            assertEquals("allItems list can't be null!", e.getMessage());
        }
        //proizvod bez popust
        List<Item> items2 = new ArrayList<>();
        items2.add(new Item("Bread", 1, 100, 0.0));
        double result2 = SILab2.checkCart(items2, "1234567890123456");
        assertEquals(100.0, result2);

        //proizvod so popust
        List<Item> items3 = new ArrayList<>();
        items3.add(new Item("Laptop", 11, 400, 0.1));
        double expected = 400 * (1 - 0.1) * 11 - 30; // 3960 - 30 = 3930
        double result3 = SILab2.checkCart(items3, "1234567890123456");
        assertEquals(expected, result3);

        //ime na proizvod e null
        try {
            List<Item> items4 = new ArrayList<>();
            items4.add(new Item(null, 1, 100, 0.0));
            SILab2.checkCart(items4, "1234567890123456");
            fail("Expected exception for null item name");
        } catch (RuntimeException e) {
            assertEquals("Invalid item!", e.getMessage());
        }

        //nevalidna dolzina za broj na karticka
        try {
            List<Item> items5 = new ArrayList<>();
            items5.add(new Item("Apple", 1, 50, 0.0));
            SILab2.checkCart(items5, "12345");
            fail("Expected exception for invalid card number length");
        } catch (RuntimeException e) {
            assertEquals("Invalid card number!", e.getMessage());
        }

        //nevaliden karakter vo broj na karticka
        try {
            List<Item> items6 = new ArrayList<>();
            items6.add(new Item("Milk", 1, 100, 0.0));
            SILab2.checkCart(items6, "12345678901234X6");
            fail("Expected exception for non-digit in card number");
        } catch (RuntimeException e) {
            assertEquals("Invalid character in card number!", e.getMessage());
        }
    }

    @Test
    public void testMultipleCondition() {

        //T F F
        List<Item> list1 = new ArrayList<>();
        list1.add(new Item("Item1", 1, 301, 0.0));
        double res1 = SILab2.checkCart(list1, "1234567890123456");
        assertEquals(301 - 30, res1);

        //F T F
        List<Item> list2 = new ArrayList<>();
        list2.add(new Item("Item2", 1, 100, 0.1));
        double res2 = SILab2.checkCart(list2, "1234567890123456");
        assertEquals(100 * 0.9 - 30, res2);

        //F F T
        List<Item> list3 = new ArrayList<>();
        list3.add(new Item("Item3", 11, 100, 0.0));
        double res3 = SILab2.checkCart(list3, "1234567890123456");
        assertEquals(1100 - 30, res3);

        //site sostavni uslovi false,F F F
        List<Item> list4 = new ArrayList<>();
        list4.add(new Item("Item4", 1, 100, 0.0));
        double res4 = SILab2.checkCart(list4, "1234567890123456");
        assertEquals(100, res4);
    }
}
