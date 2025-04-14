import java.util.*;
import java.util.stream.Collectors;

public class TaxInspectionDatabaseApp {
    public static void main(String[] args) {
        DatabaseManager db = new DatabaseManager();
        db.start();
    }
}

//класс отвечает за инициализацию бд,создает команды, опрос пользователя(консолька)
class DatabaseManager {
    private final TaxInspectionDatabase database;
    private final InputHandler inputHandler;
    private Map<String, Command> commands;

    public DatabaseManager() {
        database = new TaxInspectionDatabase();
        inputHandler = new InputHandler(new Scanner(System.in));
        loadTestData();
        initCommands();
    }

    //тестовые данные
    private void loadTestData() {
        PersonRecord person1 = new PersonRecord("ID001", "Иванов Иван", "Москва, ул. Ленина, д.1");
        person1.addFine(new Fine("Превышение скорости", "Москва"));
        person1.addFine(new Fine("Неправильная парковка", "Москва"));
        database.addPerson(person1);

        PersonRecord person2 = new PersonRecord("ID002", "Гончаров Пётр", "Санкт-Петербург, пр. Невский, д.10");
        person2.addFine(new Fine("Превышение скорости", "Санкт-Петербург"));
        database.addPerson(person2);

        PersonRecord person3 = new PersonRecord("ID003", "Казанова Дарья", "Краснодар, ул. Измаильская, д.1");
        person3.addFine(new Fine("Неправильная парковка", "Краснодар"));
        database.addPerson(person3);
    }

    // инициализация команд + их ключи
    private void initCommands() {
        commands = new LinkedHashMap<>();
        commands.put("1", new PrintDatabaseCommand(database));
        commands.put("2", new PrintByIdCommand(database, inputHandler));
        commands.put("3", new PrintByFineTypeCommand(database, inputHandler));
        commands.put("4", new PrintByCityCommand(database, inputHandler));
        commands.put("5", new AddPersonCommand(database, inputHandler));
        commands.put("6", new AddFinesCommand(database, inputHandler));
        commands.put("7", new DeleteFineCommand(database, inputHandler));
        commands.put("8", new UpdateRecordCommand(database, inputHandler));
    }

    public void start() {
        boolean exit = false;
        while (!exit) {
            printMenu();
            String option = inputHandler.getString();
            if (option.equals("0")) {
                exit = true;
                System.out.println("Выход из программы");
            } else {
                Command command = commands.get(option);
                if (command != null) {
                    command.execute();
                } else {
                    System.out.println("Некорректный выбор. Повторите попытку");
                }
            }
        }
    }

    // меню
    private void printMenu() {
        System.out.println("\n<---------------- Меню ---------------->");
        System.out.println("1. Распечатать полную базу данных");
        System.out.println("2. Распечатать данные по идентификационному коду");
        System.out.println("3. Распечатать данные по типу штрафа");
        System.out.println("4. Распечатать данные по городу");
        System.out.println("5. Добавить нового человека");
        System.out.println("6. Добавить новые штрафы для существующей записи");
        System.out.println("7. Удалить штраф из записи");
        System.out.println("8. Замена информации о человеке и его штрафах");
        System.out.println("0. Выход");
        System.out.print("Выберите действие: ");
    }
}


interface Command {
    void execute();
}

class PrintDatabaseCommand implements Command {
    private final TaxInspectionDatabase database;

    public PrintDatabaseCommand(TaxInspectionDatabase database) {
        this.database = database;
    }

    public void execute() {
        System.out.println("\nВся база данных:");
        System.out.println(database);
    }
}

//печать записи по Id
class PrintByIdCommand implements Command {
    private final TaxInspectionDatabase database;
    private final InputHandler inputHandler;

    public PrintByIdCommand(TaxInspectionDatabase database, InputHandler inputHandler) {
        this.database = database;
        this.inputHandler = inputHandler;
    }

    public void execute() {
        System.out.print("Введите идентификационный код: ");
        String id = inputHandler.getString();
        PersonRecord record = database.getRecordById(id);
        if (record != null) {
            System.out.println("\nДанные для кода " + id + ":");
            System.out.println(record);
        } else {
            System.out.println("Запись не была найдена");
        }
    }
}

// Печать записей по типу штрафа
class PrintByFineTypeCommand implements Command {
    private final TaxInspectionDatabase database;
    private final InputHandler inputHandler;

    public PrintByFineTypeCommand(TaxInspectionDatabase database, InputHandler inputHandler) {
        this.database = database;
        this.inputHandler = inputHandler;
    }

    public void execute() {
        System.out.print("Введите тип штрафа: ");
        String fineType = inputHandler.getString();
        List<PersonRecord> records = database.getRecordsByFineType(fineType);
        if (records.isEmpty()) {
            System.out.println("Записей не было найдено");
        } else {
            System.out.println("\nЗаписи с типом штрафа {" + fineType + "}:");
            records.forEach(record -> {
                System.out.println(record);
                System.out.println("-----------------------------");
            });
        }
    }
}

// Печать записей по городу
class PrintByCityCommand implements Command {
    private final TaxInspectionDatabase database;
    private final InputHandler inputHandler;

    public PrintByCityCommand(TaxInspectionDatabase database, InputHandler inputHandler) {
        this.database = database;
        this.inputHandler = inputHandler;
    }

    public void execute() {
        System.out.print("Введите название города: ");
        String city = inputHandler.getString();
        List<PersonRecord> records = database.getRecordsByCity(city);
        if (records.isEmpty()) {
            System.out.println("Записей не было найдено");
        } else {
            System.out.println("\nЗаписи по городу {" + city + "}:");
            records.forEach(record -> {
                System.out.println(record);
                System.out.println("-----------------------------");
            });
        }
    }
}

class AddPersonCommand implements Command {
    private final TaxInspectionDatabase database;
    private final InputHandler inputHandler;

    public AddPersonCommand(TaxInspectionDatabase database, InputHandler inputHandler) {
        this.database = database;
        this.inputHandler = inputHandler;
    }

    public void execute() {
        System.out.println("Введите ID нового человека:");
        String id = inputHandler.getString();
        if (database.getRecordById(id) != null) {
            System.out.println("Такая запись уже существует");
            return;
        }
        System.out.println("Введите имя:");
        String name = inputHandler.getString();
        System.out.println("Введите адрес:");
        String address = inputHandler.getString();
        System.out.println("Введите штрафы (если есть) через точку с запятой. " +
                "Для каждого штрафа укажите тип, город через запятую.");
        System.out.println("Условно: Превышение скорости, Краснодар; Неправильная парковка, Краснодар");
        String finesLine = inputHandler.getString();
        List<Fine> fines = ParserUtils.parseFines(finesLine);
        PersonRecord newRecord = new PersonRecord(id, name, address);
        for (Fine fine : fines) {
            newRecord.addFine(fine);
        }
        database.addPerson(newRecord);
        System.out.println("Новый человек добавлен");
    }
}

//добавление новых штрафов к существующей записи
class AddFinesCommand implements Command {
    private final TaxInspectionDatabase database;
    private final InputHandler inputHandler;

    public AddFinesCommand(TaxInspectionDatabase database, InputHandler inputHandler) {
        this.database = database;
        this.inputHandler = inputHandler;
    }

    public void execute() {
        System.out.print("Введите ID записи для добавления штрафов: ");
        String id = inputHandler.getString();
        PersonRecord record = database.getRecordById(id);
        if (record == null) {
            System.out.println("Запись не была найдена");
            return;
        }
        System.out.println("Введите новые штрафы через точку с запятой (формат: тип, город).");
        String finesLine = inputHandler.getString();
        List<Fine> fines = ParserUtils.parseFines(finesLine);
        fines.forEach(record::addFine);
        System.out.println("Новые штрафы добавлены");
    }
}

// команда для удаления штрафа из записи бд
class DeleteFineCommand implements Command {
    private final TaxInspectionDatabase database;
    private final InputHandler inputHandler;

    public DeleteFineCommand(TaxInspectionDatabase database, InputHandler inputHandler) {
        this.database = database;
        this.inputHandler = inputHandler;
    }

    public void execute() {
        System.out.print("Введите ID записи для удаления штрафа: ");
        String id = inputHandler.getString();
        PersonRecord record = database.getRecordById(id);
        if (record == null) {
            System.out.println("Запись не была найдена");
            return;
        }
        List<Fine> fines = record.getFines();
        if (fines.isEmpty()) {
            System.out.println("У записи отсутствуют штрафы");
            return;
        }
        System.out.println("Список штрафов:");
        for (int i = 0; i < fines.size(); i++) {
            System.out.println((i + 1) + ". " + fines.get(i));
        }
        int index = inputHandler.getInt("Введите номер штрафа для удаления: ") - 1;
        if (index < 0 || index >= fines.size()) {
            System.out.println("Некорректный номер");
            return;
        }
        record.removeFineAtIndex(index);
        System.out.println("Штраф удален");
    }
}

//класс для обновлении инфы о человеке и его штрафах
class UpdateRecordCommand implements Command {
    private final TaxInspectionDatabase database;
    private final InputHandler inputHandler;

    public UpdateRecordCommand(TaxInspectionDatabase database, InputHandler inputHandler) {
        this.database = database;
        this.inputHandler = inputHandler;
    }

    public void execute() {
        System.out.print("Введите ID записи для обновления информации: ");
        String id = inputHandler.getString();
        PersonRecord record = database.getRecordById(id);
        if (record == null) {
            System.out.println("Запись не была найдена");
            return;
        }
        System.out.println("Введите новую информацию");
        System.out.print("Новое имя: ");
        String newName = inputHandler.getString();
        System.out.print("Новый адрес: ");
        String newAddress = inputHandler.getString();
        System.out.println("Введите новые штрафы (формат: тип, город) через точку с запятой:");
        String finesLine = inputHandler.getString();
        List<Fine> newFines = ParserUtils.parseFines(finesLine);
        record.updateRecord(newName, newAddress, newFines);
        System.out.println("Информация обновлена");
    }
}

class TaxInspectionDatabase {
    private final Map<String, PersonRecord> records;

    public TaxInspectionDatabase() {
        records = new HashMap<>();
    }

    public void addPerson(PersonRecord record) {
        records.put(record.getId(), record);
    }

    public PersonRecord getRecordById(String id) {
        return records.get(id);
    }

    public List<PersonRecord> getRecordsByFineType(String fineType) {
        return records.values().stream()
                .filter(record -> record.containsFineType(fineType))
                .collect(Collectors.toList());
    }

    public List<PersonRecord> getRecordsByCity(String city) {
        return records.values().stream()
                .filter(record -> record.containsCity(city))
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        if (records.isEmpty()) {
            return "База данных пуста";
        }
        StringBuilder sb = new StringBuilder();
        records.values().forEach(record -> {
            sb.append(record).append("\n---------------------\n");
        });
        return sb.toString();
    }
}

//предтавляет инфу о человеке
class PersonRecord {
    private final String id;
    private String name;
    private String address;
    private List<Fine> fines;

    public PersonRecord(String id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.fines = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public List<Fine> getFines() {
        return fines;
    }

    public void addFine(Fine fine) {
        fines.add(fine);
    }

    public void removeFineAtIndex(int index) {
        if (index >= 0 && index < fines.size()) {
            fines.remove(index);
        }
    }

    public void updateRecord(String newName, String newAddress, List<Fine> newFines) {
        this.name = newName;
        this.address = newAddress;
        this.fines = new ArrayList<>(newFines);
    }

    public boolean containsFineType(String fineType) {
        return fines.stream().anyMatch(f -> f.type().equalsIgnoreCase(fineType));
    }

    public boolean containsCity(String city) {
        return fines.stream().anyMatch(f -> f.city().equalsIgnoreCase(city));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID: ").append(id)
                .append("\nИмя: ").append(name)
                .append("\nАдрес: ").append(address)
                .append("\nШтрафы:");
        if (fines.isEmpty()) {
            sb.append(" Нет штрафов.");
        } else {
            fines.forEach(fine -> sb.append("\n  - ").append(fine));
        }
        return sb.toString();
    }
}

//представляет штраф (тип + город)
record Fine(String type, String city) {
    @Override
    public String toString() {
        return "Тип: " + type + ", Город: " + city;
    }
}

class InputHandler {
    private final Scanner scanner;

    public InputHandler(Scanner scanner) {
        this.scanner = scanner;
    }

    public String getString() {
        return scanner.nextLine().trim();
    }

    public int getInt(String prompt) {
        System.out.print(prompt);
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Неверный ввод " + prompt);
            }
        }
    }
}

//во избежания дублирования написал утилитный метод для парсинга строк
class ParserUtils {
    public static List<Fine> parseFines(String line) {
        List<Fine> fines = new ArrayList<>();
        if (line == null || line.isEmpty()) {
            return fines;
        }
        String[] parts = line.split(";");
        for (String part : parts) {
            String[] info = part.split(",");
            if (info.length >= 2) {
                String type = info[0].trim();
                String city = info[1].trim();
                if (!type.isEmpty() && !city.isEmpty()) {
                    fines.add(new Fine(type, city));
                }
            }
        }
        return fines;
    }
}
