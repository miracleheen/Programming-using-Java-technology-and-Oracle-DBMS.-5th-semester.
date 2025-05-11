import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Scanner;

public class DirectoryCopyCoordinator {
    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);

        System.out.print("Введите путь к исходной директории: ");
        Path sourceDir = Paths.get(sc.nextLine().trim());

        System.out.print("Введите путь к целевой директории: ");
        Path targetDir = Paths.get(sc.nextLine().trim());

        DirectoryCopyTask task = new DirectoryCopyTask(sourceDir, targetDir);
        Thread copyThread = new Thread(task);

        copyThread.start();
        copyThread.join();

        CopyStats stats = task.getStats();
        System.out.println("\n<--- Статистика --->");
        System.out.printf("Скопировано директорий: %d%n", stats.getDirsCopied());
        System.out.printf("Скопировано файлов: %d%n", stats.getFilesCopied());
        System.out.printf("Суммарный объём (байт): %d%n", stats.getBytesCopied());
    }
}

class DirectoryCopyTask implements Runnable {
    private final Path source;
    private final Path target;
    private final CopyStats stats = new CopyStats();

    public DirectoryCopyTask(Path source, Path target) {
        this.source = source;
        this.target = target;
    }

    @Override
    public void run() {
        try {
            Files.walkFileTree(source, new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
                        throws IOException {
                    Path relative = source.relativize(dir);
                    Path targetDir = target.resolve(relative);
                    Files.createDirectories(targetDir);
                    stats.incrementDirs();
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
                        throws IOException {
                    Path relative   = source.relativize(file);
                    Path targetFile = target.resolve(relative);

                    Files.copy(file, targetFile, StandardCopyOption.REPLACE_EXISTING);
                    long bytesCopied = Files.size(file);

                    stats.incrementFiles();
                    stats.addBytes(bytesCopied);

                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            throw new RuntimeException("Ошибки при копировании директоирии", e);
        }
    }

    public CopyStats getStats() {
        return stats;
    }
}

class CopyStats {
    private long dirsCopied = 0;
    private long filesCopied = 0;
    private long bytesCopied = 0;

    public void incrementDirs()   { dirsCopied++; }
    public void incrementFiles()  { filesCopied++; }
    public void addBytes(long b)  { bytesCopied += b; }

    public long getDirsCopied()   { return dirsCopied; }
    public long getFilesCopied()  { return filesCopied; }
    public long getBytesCopied()  { return bytesCopied; }
}
