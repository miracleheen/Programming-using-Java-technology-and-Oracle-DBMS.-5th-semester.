import java.io.BufferedWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class NumberProcessingOrchestrator {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int queueCapacity = 100;
        int totalNumbers = 200;
        int maxRandom = 300;

        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(queueCapacity);
        int blockPill = Integer.MIN_VALUE;

        System.out.print("Введите путь к файлу: ");
        Path dir = Path.of(sc.nextLine());

        Path primesFile = dir.resolve("primes.txt");
        Path factorialsFile = dir.resolve("factorials.txt");

        Thread producer = new Thread(new NumberProducer(queue, totalNumbers, maxRandom, blockPill));

        Thread primeConsumer = new Thread(
                new PrimeConsumer(queue, primesFile, blockPill));
        Thread factorialConsumer = new Thread(
                new FactorialConsumer(queue, factorialsFile, blockPill)
        );

        producer.start();
        primeConsumer.start();
        factorialConsumer.start();

        producer.join();
        primeConsumer.join();
        factorialConsumer.join();

        long primesCount = Files.lines(primesFile).count();
        long factsCount = Files.lines(factorialsFile).count();
        System.out.println("Найдено простых чисел: " + primesCount);
        System.out.println("Вычислено факториалов: " + factsCount);
    }
}

class NumberProducer implements Runnable {
    private final BlockingQueue<Integer> queue;
    private final int total;
    private final int max;
    private final int poisonPill;

    public NumberProducer(BlockingQueue<Integer> queue, int total, int max, int poisonPill) {
        this.queue = queue;
        this.total = total;
        this.max = max;
        this.poisonPill = poisonPill;
    }

    @Override
    public void run() {
        Random rand = new Random();
        try {
            for (int i = 0; i < total; i++) {
                queue.put(rand.nextInt(max) + 1);
            }
            queue.put(poisonPill);
            queue.put(poisonPill);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }
}

class PrimeConsumer implements Runnable {
    private final BlockingQueue<Integer> queue;
    private final Path output;
    private final int poisonPill;

    public PrimeConsumer(BlockingQueue<Integer> queue, Path output, int poisonPill) {
        this.queue = queue;
        this.output = output;
        this.poisonPill = poisonPill;
    }

    @Override
    public void run() {
        try (BufferedWriter writer = Files.newBufferedWriter(output)) {
            while (true) {
                int num = queue.take();
                if (num == poisonPill) break;
                if (isPrime(num)) {
                    writer.write(Integer.toString(num));
                    writer.newLine();
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isPrime(int n) {
        if (n < 2)
            return false;
        int limit = (int) Math.sqrt(n);
        for (int i = 2; i <= limit; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}

class FactorialConsumer implements Runnable {
    private final BlockingQueue<Integer> queue;
    private final Path output;
    private final int poisonPill;

    public FactorialConsumer(BlockingQueue<Integer> queue, Path output, int poisonPill) {
        this.queue = queue;
        this.output = output;
        this.poisonPill = poisonPill;
    }

    @Override
    public void run() {
        try (BufferedWriter writer = Files.newBufferedWriter(output)) {
            while (true) {
                int num = queue.take();
                if (num == poisonPill) break;
                writer.write(factorial(num).toString());
                writer.newLine();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private BigInteger factorial(int n) {
        BigInteger res = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            res = res.multiply(BigInteger.valueOf(i));
        }
        return res;
    }
}
