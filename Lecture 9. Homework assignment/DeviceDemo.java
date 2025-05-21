import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

record Device(String name, int year, double price, String color, String type) {
    @Override
    public String toString() {
        return String.format("%s [%s, %d, %.2f rub, %s]",
                name, type, year, price, color);
    }
}

public class DeviceDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Device> devices = Arrays.asList(
                new Device("Asus Zenbook 13", 2022, 110000, "Gray", "Нетбук"),
                new Device("iPhone 16", 2024, 80000, "Black", "Смартфон"),
                new Device("MacBook Air", 2019, 99990, "Silver", "Ноутбук"),
                new Device("iPad Pro", 2021, 81990, "Gray", "Планшет"),
                new Device("GalaxyTab S9", 2023, 85000, "White", "Планшет"),
                new Device("Marshall Major 4", 2020, 11000, "Black", "Наушники"),
                new Device("Galaxy S25 Ultra", 2025, 114990, "Black", "Смартфон"),
                new Device("AirPods Pro", 2019, 19990, "White", "Наушники"),
                new Device("Kindle Paperwhite", 2018, 12990, "Black", "Читалка")
        );

        System.err.println("<--- Все устройства --->");
        devices.forEach(System.out::println);

        System.out.print("\nВведите цвет для фильтрации: ");
        String color = sc.nextLine().trim();
        System.out.printf("Устройства цвета '%s':%n", color);
        devices.stream()
                .filter(d -> d.color().equalsIgnoreCase(color))
                .forEach(System.out::println);

        System.out.print("\nВведите год выпуска для фильтрации: ");
        int year = Integer.parseInt(sc.nextLine().trim());
        System.out.printf("Устройства %d года выпуска:%n", year);
        devices.stream()
                .filter(d -> d.year() == year)
                .forEach(System.out::println);

        System.out.print("\nВведите минимальную цену (rub): ");
        double minPrice = Double.parseDouble(sc.nextLine().trim());
        System.out.printf("Устройства дороже %.2f rub:%n", minPrice);
        devices.stream()
                .filter(d -> d.price() > minPrice)
                .forEach(System.out::println);

        System.out.print("\nВведите тип устройства (например, Ноутбук): ");
        String type = sc.nextLine().trim();
        System.out.printf("Устройства типа '%s':%n", type);
        devices.stream()
                .filter(d -> d.type().equalsIgnoreCase(type))
                .forEach(System.out::println);

        System.out.print("\nВведите год начала диапазона: ");
        int startYear = Integer.parseInt(sc.nextLine().trim());
        System.out.print("Введите год конца диапазона: ");
        int endYear = Integer.parseInt(sc.nextLine().trim());
        System.out.printf("Устройства с %d по %d год выпуска:%n", startYear, endYear);
        devices.stream()
                .filter(d -> d.year() >= startYear && d.year() <= endYear)
                .forEach(System.out::println);

        sc.close();
    }
}






















/*class Device {
    private final String name;
    private final int year;
    private final double price;
    private final String color;
    private final String type;

    public Device(String name, int year, double price, String color, String type) {
        this.name = name;
        this.year = year;
        this.price = price;
        this.color = color;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public double getPrice() {
        return price;
    }

    public String getColor() {
        return color;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return String.format("%s [%s, %d, %.2frub, %s]",
                name, type, year, price, color);
    }
}

public class DeviceDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        System.out.println("Все устройства.");
        devices.forEach(System.out::println);

        System.out.print("\nВведите цвет: ");
        String colorChoice = sc.nextLine().trim();
        devices.stream()
                .filter(device -> device.getColor().equalsIgnoreCase(colorChoice))
                .forEach(System.out::println);

        System.out.print("\nВведите год выпуска устройства: ");
        String yearChoice = sc.nextLine().trim();
        devices.stream()
                .filter(device -> device.getYear() == Integer.parseInt(yearChoice))
                .forEach(System.out::println);

        System.out.print("\nВведите цену: ");
        int priceChoice = Integer.parseInt(sc.nextLine().trim());
        devices.stream()
                .filter(device -> device.getPrice() > priceChoice)
                .forEach(System.out::println);

        System.out.print("\nВведите тип устройства: ");
        String typeChoice = sc.nextLine().trim();
        devices.stream()
                .filter(device -> device.getType().equalsIgnoreCase(typeChoice))
                .forEach(System.out::println);

        System.out.print("\nВведите диапазон года выпуска [от и до]\nОт: ");
        int from = Integer.parseInt(sc.nextLine().trim());
        System.out.print("\nДо: ");
        int to = Integer.parseInt(sc.nextLine().trim());
        devices.stream()
                .filter(device -> device.getYear() >= from && device.getYear() <= to)
                .forEach(System.out::println);



        sc.close();
    }
}*/
