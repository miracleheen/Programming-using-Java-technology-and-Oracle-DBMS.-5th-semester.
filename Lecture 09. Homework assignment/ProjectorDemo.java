import java.time.Year;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

record Projector(String name, int year, double price, String manufacturer) {
    @Override
    public String toString() {
        return String.format("%s [%d, %.2f rub, %s]", name, year, price, manufacturer);
    }
}

public class ProjectorDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int currentYear = Year.now().getValue();
        List<Projector> projectors = Arrays.asList(
                new Projector("Epson EH-TW7000", 2022, 59990, "Epson"),
                new Projector("BenQ W2700",       2021, 74990, "BenQ"),
                new Projector("Hisense C2 Ultra",    2025, 239999, "Hisense"),
                new Projector("Sony VPL-VW290ES", 2024, 549990, "Sony"),
                new Projector("ViewSonic PX747-4K",2023, 49990, "ViewSonic"),
                new Projector("LG HU70LA",        2020, 66000, "LG"),
                new Projector("BenQ TK800M",      2022, 52000, "BenQ")
        );


        System.err.println("<---Все проекторы--->");
        projectors.forEach(System.out::println);

        System.out.print("\nВведите производителя: ");
        String manu = sc.nextLine().trim();
        System.out.printf("Проекторы производителя '%s':%n", manu);
        projectors.stream()
                .filter(p -> p.manufacturer().equalsIgnoreCase(manu))
                .forEach(System.out::println);

        System.out.printf("%nПроекторы %d года выпуска:%n", currentYear);
        projectors.stream()
                .filter(p -> p.year() == currentYear)
                .forEach(System.out::println);

        System.out.print("\nВведите минимальную цену (rub): ");
        double minPrice = Double.parseDouble(sc.nextLine().trim());
        System.out.printf("Проекторы дороже %.2f rub:%n", minPrice);
        projectors.stream()
                .filter(p -> p.price() > minPrice)
                .forEach(System.out::println);

        System.out.println("\nПроекторы по возрастанию цены:");
        projectors.stream()
                .sorted(Comparator.comparingDouble(Projector::price))
                .forEach(System.out::println);

        System.out.println("\nПроекторы по убыванию цены:");
        projectors.stream()
                .sorted((a, b) -> Double.compare(b.price(), a.price()))
                .forEach(System.out::println);

        sc.close();
    }
}

