import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class FilteredMergeTaskManager {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.print("Путь к исходной директории: ");
        Path sourceDir = Paths.get(sc.nextLine().trim());

        if (!Files.isDirectory(sourceDir)) {
            System.err.println("Не является директорией");
            return;
        }

        System.out.print("Слово для поиска (вхождений): ");
        String searchWord = sc.nextLine().trim();

        System.out.print("Путь к файлу со списком запрещённых слов: ");
        Path forbiddenList = Paths.get(sc.nextLine().trim());
        List<String> forbidden = Files.readAllLines(forbiddenList);

        Path mergedFile = sourceDir.resolve("merged.txt");
        Path cleanedFile = sourceDir.resolve("cleaned.txt");

        FirstTask first = new FirstTask(sourceDir, searchWord, mergedFile);
        Thread t1 = new Thread(first);
        t1.start();
        t1.join();

        SecondTask second = new SecondTask(mergedFile, cleanedFile, forbidden);
        Thread t2 = new Thread(second);
        t2.start();
        t2.join();

        System.out.println("\n<--- Статистика --->");
        System.out.printf("Найдено и слито файлов: %d%n", first.getFilesProcessed());
        System.out.printf("Всего строк после слияния: %d%n", first.getLinesMerged());
        System.out.printf("Удалено слов из списка: %d%n", second.getRemovedCount());
        System.out.printf("Строк в чистом файле: %d%n", second.getLinesWritten());
    }
}

class FirstTask implements Runnable {
    private final Path sourceDir;
    private final String searchWord;
    private final Path mergedFile;
    private int filesProcessed = 0;
    private int linesMerged = 0;

    public FirstTask(Path sourceDir, String searchWord, Path mergedFile) {
        this.sourceDir = sourceDir;
        this.searchWord = searchWord;
        this.mergedFile = mergedFile;
    }

    @Override
    public void run() {
        try (BufferedWriter writer = Files.newBufferedWriter(mergedFile)) {
            Files.walk(sourceDir)
                    .filter(p -> Files.isRegularFile(p) && p.toString().endsWith(".txt"))
                    .forEach(p -> {
                        try {
                            List<String> lines = Files.readAllLines(p);
                            boolean found = lines.stream().anyMatch(l -> l.contains(searchWord));
                            if (found) {
                                filesProcessed++;
                                for (String line : lines) {
                                    writer.write(line);
                                    writer.newLine();
                                    linesMerged++;
                                }
                            }
                        } catch (IOException e) {
                            throw new UncheckedIOException(e);
                        }
                    });
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public int getFilesProcessed() {
        return filesProcessed;
    }

    public int getLinesMerged() {
        return linesMerged;
    }
}

class SecondTask implements Runnable {
    private final Path mergedFile;
    private final Path cleanedFile;
    private final List<String> forbidden;
    private int removedCount = 0;
    private int linesWritten = 0;

    public SecondTask(Path mergedFile, Path cleanedFile, List<String> forbidden) {
        this.mergedFile = mergedFile;
        this.cleanedFile = cleanedFile;
        this.forbidden = forbidden;
    }

    @Override
    public void run() {
        try (BufferedReader reader = Files.newBufferedReader(mergedFile);
             BufferedWriter writer = Files.newBufferedWriter(cleanedFile)) {

            String line;
            while ((line = reader.readLine()) != null) {
                String cleaned = line;
                for (String bad : forbidden) {
                    int before = countOccurrences(cleaned, bad);
                    if (before > 0) {
                        cleaned = cleaned.replace(bad, "");
                        removedCount += before;
                    }
                }
                writer.write(cleaned);
                writer.newLine();
                linesWritten++;
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private int countOccurrences(String text, String sub) {
        int count = 0, idx = 0;
        while ((idx = text.indexOf(sub, idx)) != -1) {
            count++;
            idx += sub.length();
        }
        return count;
    }

    public int getRemovedCount() {
        return removedCount;
    }

    public int getLinesWritten() {
        return linesWritten;
    }
}

