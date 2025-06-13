package tests;

import main.stringapp.StringUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StringUtilsTest {
    @Test
    void testIsPalindrome() {
        assertTrue(StringUtils.isPalindrome("madam"));
        assertTrue(StringUtils.isPalindrome("RaceCar"));
        assertFalse(StringUtils.isPalindrome("hello"));
    }

    @Test
    void testIsPalindromeWithPunctuation() {
        assertTrue(StringUtils.isPalindrome("A man, a plan, a canal: Panama"));
        assertTrue(StringUtils.isPalindrome("А роза упала на лапу Азора"));
        assertFalse(StringUtils.isPalindrome("This is not palindrome"));
    }

    @Test
    void testIsPalindromeNull() {
        assertThrows(IllegalArgumentException.class, () -> StringUtils.isPalindrome(null));
    }

    @Test
    void testCountVowels() {
        assertEquals(2, StringUtils.countVowels("hello"));
        assertEquals(5, StringUtils.countVowels("что есть истина?"));
    }

    @Test
    void testCountVowelsEmptyOrNull() {
        assertEquals(0, StringUtils.countVowels(""));
        assertThrows(IllegalArgumentException.class, () -> StringUtils.countVowels(null));
    }

    @Test
    void testCountConsonants() {
        assertEquals(3, StringUtils.countConsonants("hello"));
        assertEquals(6, StringUtils.countConsonants("Barbarossa"));
    }

    @Test
    void testCountConsonantsEmptyOrNull() {
        assertEquals(0, StringUtils.countConsonants(""));
        assertThrows(IllegalArgumentException.class, () -> StringUtils.countConsonants(null));
    }

    @Test
    void testCountOccurrences() {
        assertEquals(2, StringUtils.countOccurrences("Summer Summation", "sum"));
        assertEquals(0, StringUtils.countOccurrences("Check?", "test"));
    }

    @Test
    void testCountOccurrencesCaseInsensitive() {
        assertEquals(2, StringUtils.countOccurrences("Hello heLLo", "hello"));
    }

    @Test
    void testCountOccurrencesOverlapping() {
        assertEquals(3, StringUtils.countOccurrences("Анаконда", "а"));
    }

    @Test
    void testCountOccurrencesInvalid() {
        assertThrows(IllegalArgumentException.class, () ->
                StringUtils.countOccurrences(null, "test"));
        assertThrows(IllegalArgumentException.class, () ->
                StringUtils.countOccurrences("text", null));
        assertThrows(IllegalArgumentException.class, () ->
                StringUtils.countOccurrences("text", ""));
    }
}