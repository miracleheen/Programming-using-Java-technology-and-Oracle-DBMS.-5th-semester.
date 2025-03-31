import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class ArrayImplTest {
    @Test
    public void testSortAsc() {
        int[] originalArray = {5, 2, 8, 99, 1};
        ArrayImpl arrayImpl = new ArrayImpl(originalArray);
        arrayImpl.sortAsc();
        int[] expected = {1, 2, 3, 5, 8};
        assertArrayEquals("Сортировка по возрастанию не прошла", expected, arrayImpl.array());
    }

    @Test
    public void testSortDesc() {
        int[] originalArray = {5, 2, 8, 3, 1};
        ArrayImpl arrayImpl = new ArrayImpl(originalArray);
        arrayImpl.sortDesc();
        int[] expected = {8, 5, 3, 2, 1};
        assertArrayEquals("Сортировка по убыванию не прошла", expected, arrayImpl.array());
    }
}
