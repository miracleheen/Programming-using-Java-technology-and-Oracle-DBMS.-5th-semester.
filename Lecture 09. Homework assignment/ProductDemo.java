import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

record Product(String name, String category) {
    @Override
    public String toString() {
        return name + " [" + category + "]";
    }
}

public class ProductDemo {
    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
                new Product("Молоко", "Молоко"),
                new Product("Кефир", "Молоко"),
                new Product("Хлеб", "Выпечка"),
                new Product("Масло", "Молоко"),
                new Product("Яблоко", "Фрукты"),
                new Product("Яйцо", "Мясо"),
                new Product("Сыр", "Молоко"),
                new Product("Банан", "Фрукты"),
                new Product("Молоко", "Молоко"),
                new Product("Маракуйя", "Фрукты")
        );

        Scanner sc = new Scanner(System.in);

        System.out.println("Все продукты:");
        products.forEach(System.out::println);

        System.out.println("\nПродукты с именем < 5 символов:");
        products.stream()
                .filter(product -> product.name().length() < 5)
                .distinct()
                .forEach(System.out::println);

        System.out.print("\nВведите название продукта для поиска: ");
        String query = sc.nextLine().trim();
        long count = products.stream()
                .filter(p -> p.name().equalsIgnoreCase(query))
                .count();
        System.out.printf("Продукт '%s' встречается %d раз(а)%n", query, count);

        System.out.print("\nВведите букву для фильтрации: ");
        char letter = sc.nextLine().trim().toUpperCase().charAt(0);
        System.out.printf("Продукты, начинающиеся на %c: %n", letter);
        products.stream()
                .map(Product::name)
                .filter(name -> !name.isEmpty() && Character.toUpperCase(name.charAt(0)) == letter)
                .distinct()
                .forEach(System.out::println);

        System.out.println("\nПродукты категории 'Молоко':");
        products.stream()
                .filter(p -> "Молоко".equalsIgnoreCase(p.category()))
                .forEach(System.out::println);

        sc.close();
    }
}
