package homeworkForTop;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;
import java.util.function.Supplier;

public class ArrayStatisticsCoordinator {
    private static final int DEFAULT_ARRAY_SIZE = 20;
    private static final int THREAD_POOL_SIZE = 3;

    public static void main(String[] args) {
        new ArrayStatisticsCoordinator().run();
    }

    private void run() {
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

        try {
            CompletableFuture<int[]> arrayFuture =
                    CompletableFuture.supplyAsync(new RandomArrayGenerator(ArrayStatisticsCoordinator.DEFAULT_ARRAY_SIZE), executor);

            CompletableFuture<Integer> sumFuture =
                    arrayFuture.thenApplyAsync(new SumCalculator(), executor);

            CompletableFuture<Double> averageFuture =
                    arrayFuture.thenApplyAsync(new AverageCalculator(), executor);

            int[] resultArray = arrayFuture.join();
            int sum = sumFuture.join();
            double average = averageFuture.join();

            displayResult(resultArray, sum, average);

        } finally {
            executor.shutdown();
        }
    }

    private void displayResult(int[] array, int sum, double average) {
        System.out.println("Сгенерированный массив: " + Arrays.toString(array));
        System.out.println("Сумма элементов: " + sum);
        System.out.println("Среднее арифметическое: " + average);
    }
}

class RandomArrayGenerator implements Supplier<int[]> {
    private static final int MAX_RANDOM_VALUE = 100;
    private final int size;
    private final Random random;

    public RandomArrayGenerator(int size) {
        this.size = size;
        this.random = new Random();
    }

    @Override
    public int[] get() {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(MAX_RANDOM_VALUE);
        }
        return array;
    }
}

class SumCalculator implements Function<int[], Integer> {
    @Override
    public Integer apply(int[] array) {
        return Arrays.stream(array).sum();
    }
}

class AverageCalculator implements Function<int[], Double> {
    @Override
    public Double apply(int[] array) {
        return Arrays.stream(array)
                .average()
                .orElseThrow(() -> new IllegalArgumentException("Empty Array"));
    }
}


