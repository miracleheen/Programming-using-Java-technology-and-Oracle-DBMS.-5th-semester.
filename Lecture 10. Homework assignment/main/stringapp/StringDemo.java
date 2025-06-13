package main.stringapp;

public class StringDemo {
    public static void main(String[] args) {
        String exm = "А роза упала на лапу Азора";
        System.out.printf("Строка: '%s'%n", exm);
        System.out.println("isPalindrome: " + StringUtils.isPalindrome(exm));

        String exm2 = "Если Бога нет, то все позволено.\n{Братья Карамазовы}";
        System.out.printf("\nСтрока: '%s'%n", exm2);
        System.out.println("Гласных: " + StringUtils.countVowels(exm2));
        System.out.println("Согласных: " + StringUtils.countConsonants(exm2));

        String text = "Somebody SOmeone SoMEthing SOMEWHERE sOMetimes somehow";
        String word = "some";
        System.out.printf("\nТекст: '%s', слово: '%s', вхождений: %d%n",
                text, word, StringUtils.countOccurrences(text, word));

    }
}