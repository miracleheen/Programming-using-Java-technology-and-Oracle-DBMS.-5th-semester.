import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.function.IntPredicate;

public class FirstHomework {
    private static double readValidatedDouble(Scanner sc, String message) {
        while (true) {
            System.out.println(message);
            try {
                return sc.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Ошибка, введите корректное число");
            } finally {
                sc.nextLine();
            }
        }
    }

    private static int readValidDigit(Scanner sc, String message) {
        while (true) {
            System.out.print(message);
            try {
                int number = sc.nextInt();
                if (number >= 0 && number <= 9) {
                    return number;
                } else {
                    System.out.println("Ошибка, введите цифру от 0 до 9");
                }
            } catch (InputMismatchException e) {
                System.out.println("Ошибка, введите целое число");
            } finally {
                sc.nextLine();
            }
        }
    }

    public static void swapDigitsInNumber() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Введите шестизначное число: ");
            String input = sc.nextLine().trim();

            if (input.matches("\\d{6}")) {
                char[] digits = input.toCharArray();

                char temp = digits[0];
                digits[0] = digits[5];
                digits[5] = temp;

                temp = digits[1];
                digits[1] = digits[4];
                digits[4] = temp;

                String result = new String(digits);
                System.out.println("Результат после обмена: " + result);
                break;
            }
            System.out.println("Ошибка, необходимо ввести ровно шестизначное число, состоящее только из цифр");
        }
    }

    private static int getMonthNumber(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (sc.hasNextInt()) {
                int number = sc.nextInt();
                sc.nextLine();
                if (number >= 1 && number <= 12) {
                    return number;
                } else {
                    System.out.println("Ошибка, введите цифру от 1 до 12");
                }
            } else {
                System.out.println("Ошибка, введите целое число");
                sc.next();
            }
        }
    }

    private static String getSeason(int month) {
        return switch (month) {
            case 12, 1, 2 -> "Winter";
            case 3, 4, 5 -> "Spring";
            case 6, 7, 8 -> "Summer";
            case 9, 10, 11 -> "Autumn";
            default -> "Something went wrong";
        };
    }

    private static void convertedChoice() {
        Scanner sc = new Scanner(System.in);

        double meters = readDouble(sc, "Введите количество метров: ");
        int choice = readChoice(sc);

        String result = convertAndFormat(meters, choice);
        System.out.println(result);
    }

    private static double readDouble(Scanner sc, String prompt) {
        double value;
        while (true) {
            System.out.print(prompt);
            if (sc.hasNextDouble()) {
                value = sc.nextDouble();
                break;
            } else {
                System.out.println("Пожалуйста, введите корректное числовое значение");
                sc.next();
            }
        }
        return value;
    }

    private static int readChoice(Scanner sc) {
        int choice;
        while (true) {
            System.out.println("Выберите единицу для перевода: ");
            System.out.println("1. Мили");
            System.out.println("2. Дюймы");
            System.out.println("3. Ярды");
            System.out.print("Введите номер опции: ");
            if (sc.hasNextInt()) {
                choice = sc.nextInt();
                if (choice >= 1 && choice <= 3) {
                    break;
                } else {
                    System.out.println("Пожалуйста, выберите число от 1 до 3");
                }
            } else {
                System.out.println("Пожалуйста, введите корректное числовое значение");
                sc.next();
            }
        }
        return choice;
    }

    private static String convertAndFormat(double meters, int option) {
        double result = switch (option) {
            case 1 -> meters * 0.000621371;
            case 2 -> meters * 39.3701;
            case 3 -> meters * 1.09361;
            default -> -1;
        };

        return switch (option) {
            case 1 -> String.format("%.2f метров = %.2f миль", meters, result);
            case 2 -> String.format("%.2f метров = %.2f дюймов", meters, result);
            case 3 -> String.format("%.2f метров = %.2f ярдов", meters, result);
            default -> "Неверный выбор единицы измерения";
        };
    }

    private static int readIntFromUser(String prompt) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println(prompt);
            String input = sc.nextLine();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка, ведите целое число, а не текст.");
            }
        }
    }

    private static void normalizationOfBoarders(int firstBoundary, int secondBoundary) {
        if (secondBoundary < firstBoundary) {
            firstBoundary = firstBoundary ^ secondBoundary;
            secondBoundary = firstBoundary ^ secondBoundary;
            firstBoundary = firstBoundary ^ secondBoundary;
        }

        for (int i = firstBoundary; i <= secondBoundary; i++) {
            System.out.println("index {" + i + "}");
        }
    }

    private static int readNumber(String prompt) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка, введите целое число");
            }
        }
    }

    private static void printMultiplicationTable(int start, int end) {
        System.out.println("\nТаблица умножения от " + start + " до " + end + ":\n");

        for (int number = start; number <= end; number++) {
            System.out.println("Таблица для " + number + ":");
            for (int multiplier = 1; multiplier <= 10; multiplier++) {
                System.out.printf("%2d x %2d = %3d\n", number, multiplier, number * multiplier);
            }
            if (number != end) {
                System.out.println("───────────────────────────");
            }
        }
    }

    private static int[] generateArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = (int) (Math.random() * 41) - 20;
        }
        return array;
    }

    private static void analyzeArray(int[] array) {
        int min = array[0];
        int max = array[0];
        int countNegative = 0;
        int countPositive = 0;
        int countZero = 0;

        for (int num : array) {
            if (num < min) {
                min = num;
            }
            if (num > max) {
                max = num;
            }
            if (num < 0) {
                countNegative++;
            } else if (num > 0) {
                countPositive++;
            } else {
                countZero++;
            }
        }

        System.out.println("Минимальное значение: " + min);
        System.out.println("Максимальное значение: " + max);
        System.out.println("Количество отрицательных чисел: " + countNegative);
        System.out.println("Количество положительных чисел: " + countPositive);
        System.out.println("Количество нулей: " + countZero);
    }

    public static int[] filterArray(int[] array, IntPredicate condition) {
        int count = 0;
        for (int num : array) {
            if (condition.test(num)) {
                count++;
            }
        }
        int[] result = new int[count];
        int index = 0;
        for (int num : array) {
            if (condition.test(num)) {
                result[index++] = num;
            }
        }
        return result;
    }

    private static void sortArray(int[] array, boolean ascending) {
        if (array == null || array.length <= 1) {
            return;
        }

        Arrays.sort(array);

        if (!ascending) {
            reverseArray(array);
        }
    }

    private static void reverseArray(int[] array) {
        int left = 0;
        int right = array.length - 1;

        while (left < right) {
            int temp = array[left];
            array[left] = array[right];
            array[right] = temp;
            left++;
            right--;
        }
    }

    private static boolean getUserChoice() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Выберите тип сортировки:");
        System.out.println("1 => По возрастанию");
        System.out.println("2 => По убыванию");

        while (true) {
            System.out.print("Ваш выбор: ");
            String input = sc.nextLine();

            switch (input) {
                case "1":
                    return true;
                case "2":
                    return false;
                default:
                    System.out.println("Некорректный ввод. Введите 1 или 2");
            }
        }
    }

    private static void drawLine(int length, String direction, char symbol) {
        if (length < 1) {
            throw new IllegalArgumentException("Длина должна быть положительной");
        }

        if (!direction.equalsIgnoreCase("horizontal")
                && !direction.equalsIgnoreCase("vertical")) {
            throw new IllegalArgumentException("Допустимо horizontal, либо vertical");
        }

        if (direction.equalsIgnoreCase("horizontal")) {
            drawHorizontalLine(length, symbol);
        } else {
            drawVerticalLine(length, symbol);
        }
    }

    private static void drawHorizontalLine(int length, char symbol) {
        for (int i = 0; i < length; i++) {
            System.out.print(symbol);
        }
        System.out.println();
    }

    private static void drawVerticalLine(int length, char symbol) {
        for (int i = 0; i < length; i++) {
            System.out.println(symbol);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        //first
        Scanner sc = new Scanner(System.in);
        String str = """
                "Your time is limited,
                \tso don't waste it
                \t\tliving someone else's life"
                """;
        System.out.println(str + "\n");

        //second
        System.out.println("Введите два числа, чтобы найти остаток.");
        double number = readValidatedDouble(sc, "Введите число: ");
        double percent = readValidatedDouble(sc, "Введите процент: ");
        double result = (number * percent) / 100;
        System.out.println("Результат: " + result + "\n");

        //third
        System.out.println("Введите 3 цифры.");
        int x = readValidDigit(sc, "Введите первую цифру от 0 до 9: ");
        int y = readValidDigit(sc, "Введите вторую цифру от 0 до 9: ");
        int z = readValidDigit(sc, "Введите третью цифру от 0 до 9: ");

        int concatenationNumber = (x * 100) + (y * 10) + z;
        System.out.println("Результат: " + concatenationNumber + "\n");

        //fourth
        swapDigitsInNumber();

        //fifth
        int monthNumber = getMonthNumber(sc, "Введите номер месяца (1-12): ");
        String season = getSeason(monthNumber);
        System.out.println("Сезон: " + season);

        //sixth
        convertedChoice();

        //seventh
        System.out.println("\nВыберите границу двух чисел");
        int firstBoundary = readIntFromUser("Введите первое число");
        int secondBoundary = readIntFromUser("Введите второе число");
        normalizationOfBoarders(firstBoundary, secondBoundary);

        //eighth
        System.out.println("\nТаблица умножения в диапазоне.");
        int num1 = readNumber("Введите первое число: ");
        int num2 = readNumber("Введите второе число: ");

        if (num2 < num1) {
            num1 = num1 ^ num2;
            num2 = num1 ^ num2;
            num1 = num1 ^ num2;
        }

        printMultiplicationTable(num1, num2);

        //ninth
        final int SIZE = 20;
        int[] arr = generateArray(SIZE);
        System.out.println("Исходный массив: " + Arrays.toString(arr));
        analyzeArray(arr);

        //tenth
        int[] originalArray = generateArray(SIZE);
        System.out.println("Исходный массив: " + Arrays.toString(originalArray));

        int[] evenNumbers = filterArray(originalArray, t -> t % 2 == 0);
        int[] oddNumbers = filterArray(originalArray, t -> t % 2 != 0);
        int[] negativeNumbers = filterArray(originalArray, t -> t < 0);
        int[] positiveNumbers = filterArray(originalArray, t -> t > 0);

        System.out.println("Четные числа: " + Arrays.toString(evenNumbers));
        System.out.println("Нечетные числа: " + Arrays.toString(oddNumbers));
        System.out.println("Отрицательные числа: " + Arrays.toString(negativeNumbers));
        System.out.println("Положительные числа: " + Arrays.toString(positiveNumbers));

        //eleventh
        drawLine(5, "horizontal", '-');
        drawLine(3, "vertical", '|');

        //twelfth
        int[] array = {5, 2, 9, 1, 5, 6, 18, 4, 11, 3};
        System.out.println("Исходный массив: [" + Arrays.toString(array) + "]");
        boolean ascending = getUserChoice();

        sortArray(array, ascending);
        System.out.println("Результат сортировки: " + Arrays.toString(array));
    }
}
