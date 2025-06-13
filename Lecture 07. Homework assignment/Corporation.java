import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Scanner;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

class Employee {
    private int id;
    private String firstName;
    private String lastName;
    private int age;

    public Employee(int id, String firstName, String lastName, int age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

class EmployeeRepository {
    private final Map<Integer, Employee> store = new HashMap<>();
    private int nextId = 1;

    public EmployeeRepository() {
    }

    public Employee save(Employee emp) {
        if (emp.getId() == 0) {
            emp.setId(nextId++);
        }
        store.put(emp.getId(), emp);
        return emp;
    }

    public Optional<Employee> findById(int id) {
        return Optional.ofNullable(store.get(id));
    }

    public List<Employee> findAll() {
        return new ArrayList<>(store.values());
    }

    public boolean delete(int id) {
        return store.remove(id) != null;
    }

    public void loadFromFile(String filePath) throws IOException {
        var lines = Files.readAllLines(Path.of(filePath));
        for (String line : lines) {
            if (line.isBlank()) continue;
            String[] parts = line.split(",");
            int id = Integer.parseInt(parts[0]);
            String fn = parts[1];
            String ln = parts[2];
            int age = Integer.parseInt(parts[3]);
            store.put(id, new Employee(id, fn, ln, age));
            nextId = Math.max(nextId, id + 1);
        }
    }

    public void saveToFile(String filePath) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (Employee e : store.values()) {
                writer.printf("%d,%s,%s,%d%n",
                        e.getId(),
                        e.getFirstName(),
                        e.getLastName(),
                        e.getAge());
            }
        }
    }
}


class EmployeeService {
    private final EmployeeRepository repo = new EmployeeRepository();

    public void load(String path) throws IOException {
        repo.loadFromFile(path);
    }

    public void saveAll(String path) throws IOException {
        repo.saveToFile(path);
    }

    public void saveList(List<Employee> list, String path) throws IOException {
        try (PrintWriter w = new PrintWriter(new FileWriter(path))) {
            for (Employee e : list) {
                w.printf("%d,%s,%s,%d%n",
                        e.getId(), e.getFirstName(),
                        e.getLastName(), e.getAge());
            }
        }
    }

    public Employee addEmployee(String fn, String ln, int age) {
        return repo.save(new Employee(0, fn, ln, age));
    }

    public boolean editEmployee(int id, String fn, String ln, int age) {
        Optional<Employee> o = repo.findById(id);
        if (o.isEmpty()) return false;
        Employee e = o.get();
        e.setFirstName(fn);
        e.setLastName(ln);
        e.setAge(age);
        repo.save(e);
        return true;
    }

    public boolean removeEmployee(int id) {
        return repo.delete(id);
    }

    // Поиск и фильтрация
    public List<Employee> findByLastName(String lastName) {
        return repo.findAll().stream()
                .filter(e -> e.getLastName().equalsIgnoreCase(lastName))
                .collect(Collectors.toList());
    }

    public List<Employee> filterByAge(int age) {
        return repo.findAll().stream()
                .filter(e -> e.getAge() == age)
                .collect(Collectors.toList());
    }

    public List<Employee> filterByLastInitial(char initial) {
        return repo.findAll().stream()
                .filter(e -> Character.toUpperCase(e.getLastName().charAt(0))
                        == Character.toUpperCase(initial))
                .collect(Collectors.toList());
    }

    public List<Employee> listAll() {
        return repo.findAll();
    }
}

class EmployeeConsoleUI {
    private final EmployeeService service = new EmployeeService();
    private final Scanner sc = new Scanner(System.in);
    private String dataFilePath;
    private List<Employee> lastSearchResults;

    public void start() {
        System.out.print("Введите путь к файлу сотрудников: ");
        dataFilePath = sc.nextLine().trim();
        try {
            service.load(dataFilePath);
            System.out.println("Данные загружены из " + dataFilePath);
        } catch (IOException e) {
            System.err.println("Ошибка загрузки: " + e.getMessage());
        }

        while (true) {
            printMenu();
            String choice = sc.nextLine().trim();
            switch (choice) {
                case "1" -> add();
                case "2" -> edit();
                case "3" -> remove();
                case "4" -> searchByLastName();
                case "5" -> filterByAgeOrInitial();
                case "6" -> listAll();
                case "7" -> saveAll();
                case "8" -> saveLastSearch();
                case "0" -> {
                    exit();
                    return;
                }
                default -> System.out.println("Неверный выбор");
            }
        }
    }

    private void printMenu() {
        System.out.println("\nМеню:");
        System.out.println("1 – Добавить сотрудника");
        System.out.println("2 – Редактировать сотрудника");
        System.out.println("3 – Удалить сотрудника");
        System.out.println("4 – Поиск по фамилии");
        System.out.println("5 – Фильтрация по возрасту/букве фамилии");
        System.out.println("6 – Показать всех сотрудников");
        System.out.println("7 – Сохранить всех в файл");
        System.out.println("8 – Сохранить результаты поиска");
        System.out.println("0 – Выход");
        System.out.print("Выберите действие: ");
    }

    private void add() {
        System.out.print("Имя: ");
        var fn = sc.nextLine().trim();
        System.out.print("Фамилия: ");
        var ln = sc.nextLine().trim();
        System.out.print("Возраст: ");
        int age = Integer.parseInt(sc.nextLine().trim());
        Employee e = service.addEmployee(fn, ln, age);
        System.out.printf("Сотрудник добавлен (ID=%d)%n", e.getId());
    }

    private void edit() {
        System.out.print("ID сотрудника для редактирования: ");
        int id = Integer.parseInt(sc.nextLine().trim());
        System.out.print("Новое имя: ");
        var fn = sc.nextLine().trim();
        System.out.print("Новая фамилия: ");
        var ln = sc.nextLine().trim();
        System.out.print("Новый возраст: ");
        int age = Integer.parseInt(sc.nextLine().trim());
        if (service.editEmployee(id, fn, ln, age)) {
            System.out.println("Сотрудник обновлён.");
        } else {
            System.out.println("Сотрудник с таким ID не найден.");
        }
    }

    private void remove() {
        System.out.print("ID сотрудника для удаления: ");
        int id = Integer.parseInt(sc.nextLine().trim());
        if (service.removeEmployee(id)) {
            System.out.println("Сотрудник удалён");
        } else {
            System.out.println("Сотрудник с таким ID не найден");
        }
    }

    private void searchByLastName() {
        System.out.print("Введите фамилию для поиска: ");
        String ln = sc.nextLine().trim();
        lastSearchResults = service.findByLastName(ln);
        printList(lastSearchResults);
    }

    private void filterByAgeOrInitial() {
        System.out.print("1 - по возрасту, 2 – по букве фамилии: ");
        String opt = sc.nextLine().trim();
        if ("1".equals(opt)) {
            System.out.print("Укажите возраст: ");
            int age = Integer.parseInt(sc.nextLine().trim());
            lastSearchResults = service.filterByAge(age);
        } else {
            System.out.print("Укажите первую букву фамилии: ");
            char c = sc.nextLine().trim().charAt(0);
            lastSearchResults = service.filterByLastInitial(c);
        }
        printList(lastSearchResults);
    }

    private void listAll() {
        lastSearchResults = service.listAll();
        printList(lastSearchResults);
    }

    private void saveAll() {
        try {
            service.saveAll(dataFilePath);
            System.out.println("Все данные сохранены в " + dataFilePath);
        } catch (IOException e) {
            System.err.println("Ошибка сохранения: " + e.getMessage());
        }
    }

    private void saveLastSearch() {
        if (lastSearchResults == null) {
            System.out.println("Нет результатов для сохранения.Выполните поиск или вывод всех");
            return;
        }
        System.out.print("Путь для сохранения результатов: ");
        String path = sc.nextLine().trim();
        try {
            service.saveList(lastSearchResults, path);
            System.out.println("Результаты сохранены в " + path);
        } catch (IOException e) {
            System.err.println("Ошибка сохранения: " + e.getMessage());
        }
    }

    private void exit() {
        try {
            service.saveAll(dataFilePath);
            System.out.println("Данные автоматически сохранены при выходе");
        } catch (IOException e) {
            System.err.println("Ошибка автосохранения: " + e.getMessage());
        }
        System.out.println("Завершение работы.");
    }

    private void printList(List<Employee> list) {
        if (list.isEmpty()) {
            System.out.println("[список пуст]");
            return;
        }
        System.out.println("Результаты:");
        for (Employee e : list) {
            System.out.printf("%d: %s %s, %d лет%n",
                    e.getId(), e.getFirstName(),
                    e.getLastName(), e.getAge());
        }
    }
}

public class Corporation {
    public static void main(String[] args) {
        new EmployeeConsoleUI().start();
    }
}
