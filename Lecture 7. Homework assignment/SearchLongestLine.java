import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.Optional;
import java.util.Scanner;

public class SearchLongestLine {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите путь к файлу: ");
        String filePath = sc.nextLine();

        try {
            Optional<String> longestLine = Files
                    .lines(Path.of(filePath))
                    .max(Comparator.comparingInt(String::length));

            if (longestLine.isPresent()) {
                String line = longestLine.get();
                System.out.println("Длина самой длинной строки: " + line.length());
                System.out.println("Сама строка: " + line);
            } else {
                System.out.println("Файл пуст");
            }
        } catch (IOException e) {
            System.err.println("Не получилось прочитать файл: " + e.getMessage() + "\n" + e.getCause());
            //e.printStackTrace();
        }
    }
}
