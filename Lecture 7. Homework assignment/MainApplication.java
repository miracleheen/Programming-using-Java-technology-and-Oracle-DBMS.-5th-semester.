import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Path;

class FileLoader {
    public List<String> loadLines(String filePath) throws IOException {
        return Files.readAllLines(Path.of(filePath));
    }
}

class LineParser {
    public int[] parse(String line) {
        String[] tokens = line.trim().split("\\s+");
        int[] nums = new int[tokens.length];
        for (int i = 0; i < tokens.length; i++) {
            nums[i] = Integer.parseInt(tokens[i]);
        }
        return nums;
    }
}

record ArrayStats(int[] array, int min, int max, long sum) {}

class GlobalStats {
    private final int globalMin;
    private final int globalMax;
    private final long totalSum;

    public GlobalStats(List<ArrayStats> statsList) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        long sum = 0;
        for (ArrayStats s : statsList) {
            if (s.min() < min) min = s.min();
            if (s.max() > max) max = s.max();
            sum += s.sum();
        }
        this.globalMin = min;
        this.globalMax = max;
        this.totalSum = sum;
    }

    public int getGlobalMin() {
        return globalMin;
    }

    public int getGlobalMax() {
        return globalMax;
    }

    public long getTotalSum() {
        return totalSum;
    }
}


class StatisticsCalculator {
    public List<ArrayStats> calculatePerArray(List<int[]> arrays) {
        List<ArrayStats> stats = new ArrayList<>();
        for (int[] arr : arrays) {
            int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
            long sum = 0;
            for (int v : arr) {
                if (v < min) min = v;
                if (v > max) max = v;
                sum += v;
            }
            stats.add(new ArrayStats(arr, min, max, sum));
        }
        return stats;
    }
}


class StatisticsPrinter {
    public void print(List<ArrayStats> statsList, GlobalStats global) {
        for (ArrayStats s : statsList) {
            System.out.print("Массив: ");
            for (int v : s.array()) System.out.print(v + " ");
            System.out.printf("%nМин: %d, Макс: %d, Сумма: %d%n",
                    s.min(), s.max(), s.sum());
        }
        System.out.println("Глобальный мин: " + global.getGlobalMin());
        System.out.println("Глобальная макс: " + global.getGlobalMax());
        System.out.println("Общая сумма: " + global.getTotalSum());
    }
}

public class MainApplication {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите путь к файлу: ");
        String path = sc.nextLine();

        FileLoader loader = new FileLoader();
        LineParser parser = new LineParser();
        StatisticsCalculator calculator = new StatisticsCalculator();
        StatisticsPrinter printer = new StatisticsPrinter();

        try {
            List<String> lines = loader.loadLines(path);
            List<int[]> arrays = lines.stream()
                    .map(parser::parse)
                    .toList();
            List<ArrayStats> perArrayStats = calculator.calculatePerArray(arrays);
            GlobalStats globalStats = new GlobalStats(perArrayStats);
            printer.print(perArrayStats, globalStats);
        } catch (IOException e) {
            System.err.println("Ошибка чтения файла: " + e.getMessage());
        }
    }
}
