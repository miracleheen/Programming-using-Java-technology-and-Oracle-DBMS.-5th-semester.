import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Scanner;
import java.util.stream.Stream;

class ConsoleReader {
    private final Scanner sc = new Scanner(System.in);

    public String readFilePath() {
        System.out.print("Введите путь к файлу: ");
        return sc.nextLine();
    }

    public int[] readIntArray() {
        System.out.print("Введите элементы массива через пробел: ");
        String line = sc.nextLine().trim();
        return Stream.of(line.split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}

class ArrayService {
    public List<Integer> toList(int[] array) {
        return Arrays.stream(array)
                .boxed()
                .collect(Collectors.toList());
    }

    public List<Integer> filterEven(int[] array) {
        return Arrays.stream(array)
                .filter(v -> v % 2 == 0)
                .boxed()
                .collect(Collectors.toList());
    }

    public List<Integer> filterOdd(int[] array) {
        return Arrays.stream(array)
                .filter(v -> v % 2 != 0)
                .boxed()
                .collect(Collectors.toList());
    }

    public List<Integer> reverse(int[] array) {
        List<Integer> list = toList(array);
        Collections.reverse(list);
        return list;
    }
}


class FileWriterService implements AutoCloseable {
    private final PrintWriter writer;

    public FileWriterService(String filePath) throws IOException {
        this.writer = new PrintWriter(new FileWriter(filePath));
    }

    public void writeLine(List<Integer> data) {
        writer.println(data);
    }

    @Override
    public void close() {
        writer.close();
    }
}

public class ArrayToFile {
    public static void main(String[] args) {
        ConsoleReader cr = new ConsoleReader();
        ArrayService arrayService = new ArrayService();

        String path = cr.readFilePath();
        int[] nums = cr.readIntArray();

        List<Integer> all = arrayService.toList(nums);
        List<Integer> evens = arrayService.filterEven(nums);
        List<Integer> odds = arrayService.filterOdd(nums);
        List<Integer> reversed = arrayService.reverse(nums);

        try (FileWriterService fw = new FileWriterService(path)) {
            fw.writeLine(all);
            fw.writeLine(evens);
            fw.writeLine(odds);
            fw.writeLine(reversed);
            System.out.println("Данные записаны в файл");
        } catch (IOException e) {
            System.err.println("Ошибка записи файла: " + e.getMessage());
        }
    }
}
