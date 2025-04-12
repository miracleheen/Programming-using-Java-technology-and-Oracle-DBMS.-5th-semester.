import java.util.*;
import java.util.stream.Collectors;


public class DictionaryApp {
    public static void main(String[] args) {
        DictionaryManager manager = new DictionaryManager();
        manager.start();
    }
}

//Отвечает за инициализацию и ввод + меню
class DictionaryManager {
    private final Dictionary dictionary;
    private final InputHandler inputHandler;

    public DictionaryManager() {
        dictionary = new Dictionary();
        inputHandler = new InputHandler(new Scanner(System.in));
    }

    public void start() {
        loadInitialData();
        boolean exit = false;
        while (!exit) {
            printMenu();
            String choice = inputHandler.getString();
            switch (choice) {
                case "1":
                    displayWord();
                    break;
                case "2":
                    addNewWord();
                    break;
                case "3":
                    appendTranslations();
                    break;
                case "4":
                    replaceTranslations();
                    break;
                case "5":
                    deleteWord();
                    break;
                case "6":
                    showEntireDictionary();
                    break;
                case "7":
                    showTopPopular();
                    break;
                case "8":
                    showTopUnpopular();
                    break;
                case "0":
                    exit = true;
                    System.out.println("Выход из программы\uD83D\uDC4B");
                    break;
                default:
                    System.out.println("Ввод неверен. Повторите попытку");
            }
        }
    }

    private void loadInitialData() {
        System.out.println("Введите количество слов для начального заполнения:");
        int count = inputHandler.getInt("Количество: ");
        for (int i = 0; i < count; i++) {
            System.out.println("\nВведите английское слово:");
            String word = inputHandler.getString();
            System.out.println("Введите переводы (через запятую):");
            String line = inputHandler.getString();
            List<String> translations = Dictionary.parseTranslations(line);
            dictionary.addWord(word, translations);
        }
    }

    // меню
    private void printMenu() {
        System.out.println("\n<---------------- Меню ---------------->");
        System.out.println("1. Отобразить слово и его перевод");
        System.out.println("2. Добавить новое слово и его переводы");
        System.out.println("3. Добавить переводы к существующему слову");
        System.out.println("4. Заменить переводы слова");
        System.out.println("5. Удалить слово");
        System.out.println("6. Показать весь словарь");
        System.out.println("7. Топ-10 популярных слов");
        System.out.println("8. Топ-10 непопулярных слов");
        System.out.println("0. Выход");
        System.out.print("Выберите действие: ");
    }

    // Команды меню:
    private void displayWord() {
        System.out.print("Введите слово для поиска: ");
        String word = inputHandler.getString();
        List<String> translations = dictionary.getTranslations(word);
        if (translations != null) {
            System.out.println(word + " : " + String.join(", ", translations));
        } else {
            System.out.println("Слово было не найдено");
        }
    }

    private void addNewWord() {
        System.out.print("Введите новое английское слово: ");
        String word = inputHandler.getString();
        System.out.print("Введите переводы (через запятую): ");
        String line = inputHandler.getString();
        List<String> translations = Dictionary.parseTranslations(line);
        if (dictionary.addWord(word, translations)) {
            System.out.println("Слово успешно добавлено");
        } else {
            System.out.println("Слово в словаре уже существует");
        }
    }

    private void appendTranslations() {
        System.out.print("Введите слово, к которому добавить переводы: ");
        String word = inputHandler.getString();
        System.out.print("Введите переводы (через запятую): ");
        String line = inputHandler.getString();
        List<String> translations = Dictionary.parseTranslations(line);
        if (dictionary.appendTranslations(word, translations)) {
            System.out.println("Переводы успешно добавлены");
        } else {
            System.out.println("Слово не найдено");
        }
    }

    private void replaceTranslations() {
        System.out.print("Введите слово, для которого заменить переводы: ");
        String word = inputHandler.getString();
        System.out.print("Введите новые переводы (через запятую): ");
        String line = inputHandler.getString();
        List<String> translations = Dictionary.parseTranslations(line);
        if (dictionary.replaceTranslations(word, translations)) {
            System.out.println("Переводы заменены");
        } else {
            System.out.println("Слово не было найдено");
        }
    }

    private void deleteWord() {
        System.out.print("Введите слово для удаления: ");
        String word = inputHandler.getString();
        if (dictionary.deleteWord(word)) {
            System.out.println("Слово успешно удалено");
        } else {
            System.out.println("Слово не было найдено");
        }
    }

    private void showEntireDictionary() {
        System.out.println("Словарь:");
        System.out.println(dictionary);
    }

    private void showTopPopular() {
        System.out.println("Топ-10 популярных слов:");
        List<WordEntry> topPopular = dictionary.getTopPopular(10);
        topPopular.forEach(entry ->
                System.out.println(entry.getWord() + " {обращений: " + entry.getAccessCount() + "}")
        );
    }

    private void showTopUnpopular() {
        System.out.println("Топ-10 непопулярных слов:");
        List<WordEntry> topUnpopular = dictionary.getTopUnpopular(10);
        topUnpopular.forEach(entry ->
                System.out.println(entry.getWord() + " {обращений: " + entry.getAccessCount() + "}")
        );
    }
}

class Dictionary {
    private final Map<String, WordEntry> data;

    public Dictionary() {
        data = new HashMap<>();
    }

    public boolean addWord(String word, List<String> translations) {
        if (data.containsKey(word)) {
            return false;
        }
        data.put(word, new WordEntry(word, translations));
        return true;
    }

    public boolean appendTranslations(String word, List<String> translations) {
        WordEntry entry = data.get(word);
        if (entry == null) {
            return false;
        }
        entry.appendTranslations(translations);
        return true;
    }

    public boolean replaceTranslations(String word, List<String> translations) {
        WordEntry entry = data.get(word);
        if (entry == null) {
            return false;
        }
        entry.replaceTranslations(translations);
        return true;
    }

    public boolean deleteWord(String word) {
        return data.remove(word) != null;
    }

    //счетчик обращений и возвращает перевод
    public List<String> getTranslations(String word) {
        WordEntry entry = data.get(word);
        if (entry != null) {
            entry.incrementAccessCount();
            return entry.getTranslations();
        }
        return null;
    }

    public List<WordEntry> getTopPopular(int n) {
        return data.values().stream()
                .sorted(Comparator.comparingInt(WordEntry::getAccessCount).reversed())
                .limit(n)
                .collect(Collectors.toList());
    }

    public List<WordEntry> getTopUnpopular(int n) {
        return data.values().stream()
                .sorted(Comparator.comparingInt(WordEntry::getAccessCount))
                .limit(n)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        if (data.isEmpty()) {
            return "Словарь пустой";
        }
        StringBuilder sb = new StringBuilder();
        data.values().forEach(entry -> {
            sb.append(entry.getWord())
                    .append(" : ")
                    .append(String.join(", ", entry.getTranslations()))
                    .append(" (обращений: ")
                    .append(entry.getAccessCount())
                    .append(")\n");
        });
        return sb.toString();
    }

    public static List<String> parseTranslations(String line) {
        List<String> result = new ArrayList<>();
        String[] parts = line.split(",");
        for (String part : parts) {
            String trimmed = part.trim();
            if (!trimmed.isEmpty()) {
                result.add(trimmed);
            }
        }
        return result;
    }
}

//является value значением для hashmap (в целом, можно было сделать внутренним)
class WordEntry {
    private final String word;
    private List<String> translations;
    private int accessCount;

    public WordEntry(String word, List<String> translations) {
        this.word = word;
        this.translations = new ArrayList<>(translations);
        this.accessCount = 0;
    }

    public String getWord() {
        return word;
    }

    public List<String> getTranslations() {
        return translations;
    }

    public int getAccessCount() {
        return accessCount;
    }

    public void incrementAccessCount() {
        accessCount++;
    }

    public void appendTranslations(List<String> additionalTranslations) {
        translations.addAll(additionalTranslations);
    }

    public void replaceTranslations(List<String> newTranslations) {
        translations = new ArrayList<>(newTranslations);
    }
}

//безопасно(чекаем) получаем данные(та же самая процедура, какая была с катером)
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
            String line = scanner.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.print("Неверный ввод " + prompt);
            }
        }
    }
}
