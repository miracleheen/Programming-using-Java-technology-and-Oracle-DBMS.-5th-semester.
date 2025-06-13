import java.io.*;
import java.util.Scanner;

public class CompareWithTwoFiles {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean differencesFound = false;

        System.out.print("Введите путь к первому файлу: ");
        String path1 = sc.nextLine();
        System.out.print("Введите путь ко второму файлу: ");
        String path2 = sc.nextLine();

        try (
                BufferedReader br1 = new BufferedReader(new FileReader(path1));
                BufferedReader br2 = new BufferedReader(new FileReader(path2))
        ) {
            String firstLine;
            String secondLine;
            int lineNumber = 1;

            while (true) {
                firstLine = br1.readLine();
                secondLine = br2.readLine();

                if (firstLine == null && secondLine == null) {
                    break;
                }

                if (firstLine == null || !firstLine.equals(secondLine)) {
                    System.out.println("Различия на строке " + lineNumber + ":");
                    System.out.println("Файл 1: " + (firstLine != null ? firstLine : "нет строки"));
                    System.out.println("Файл 2: " + (secondLine != null ? secondLine : "нет строки"));
                }
                ++lineNumber;
            }
            if (!differencesFound) {
                System.out.println("Строки в файлах совпадают");
            }
        } catch (IOException e) {
            System.out.println("Произошла ошибка при работе с файлами: " + e.getMessage());
        }
        sc.close();
    }
}
