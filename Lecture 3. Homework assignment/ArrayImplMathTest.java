import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ArrayImplMathTest {
    @Test
    public void testMax() {
        int[] arr = {14, 2, 18, 65, 11, 22};
        ArrayImpl arrayImpl = new ArrayImpl(arr);
        assertEquals("Максимум не соответствует значению", 22, arrayImpl.max());
    }

    @Test
    public void testMin() {
        int[] arr = {14, 2, 18, 65, 11, 22};
        ArrayImpl arrayImpl = new ArrayImpl(arr);
        assertEquals("Минимум не соответствует значению", 2, arrayImpl.min());
    }

    @Test
    public void testAvg() {
        int[] arr = {14, 2, 18, 65, 11, 22};
        ArrayImpl arrayImpl = new ArrayImpl(arr);
        assertEquals("Среднее арифметическое не соответствует значению", 22.0, arrayImpl.avg(), 0.001);
    }
}