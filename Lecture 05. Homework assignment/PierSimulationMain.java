import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.Random;

public class PierSimulationMain {
    public static void main(String[] args) {
        SimulationModel simulation = new SimulationModel();
        simulation.run();
    }
}

class SimulationModel{
    private static final double SIMULATION_DURATION = 24.0;
    private final PriorityQueue<Event> eventQueue = new PriorityQueue<>();

    private final Queue<Person> waitingPersonsQueue = new LinkedList<>();
    private final Random random = new Random();

    private double totalWaitingTime = 0.0;
    private int boardedCount = 0;
    private int maxQueueSizeObserved = 0;

    // Avg время между появлениями пассажиров(min)
    private double passengerNight;
    private double passengerMorning;
    private double passengerDay;
    private double passengerEvening;

    //Avg время катеров
    private double boatNight;
    private double boatMorning;
    private double boatDay;
    private double boatEvening;

    //выборка остановки
    private String boatStopType;

    private int maxWaitAllowed;

    public void run() {
        readInput();
        scheduleInitialEvents();
        processEvents();
        printStatistics();
    }

    //входные данные
    private void readInput() {
        InputHandler input = new InputHandler(new Scanner(System.in));
        System.out.println("Введите avg время между появлениями пассажиров (в минутах):");
        this.passengerNight   = input.getDouble("Ночь (00:00–06:00): ");
        this.passengerMorning = input.getDouble("Утро (06:00–12:00): ");
        this.passengerDay     = input.getDouble("День (12:00–18:00): ");
        this.passengerEvening = input.getDouble("Вечер (18:00–24:00): ");

        System.out.println("\nВведите avg время между появлениями катеров (в минутах):");
        this.boatNight   = input.getDouble("Ночь (00:00–06:00): ");
        this.boatMorning = input.getDouble("Утро (06:00–12:00): ");
        this.boatDay     = input.getDouble("День (12:00–18:00): ");
        this.boatEvening = input.getDouble("Вечер (18:00–24:00): ");

        System.out.println("\nВведите тип остановки катера ('конечная' или 'не конечная'):");
        this.boatStopType = input.getString().toLowerCase();
        if (!boatStopType.equals("конечная") && !boatStopType.equals("не конечная")) {
            System.out.println("Некорректный тип остановки. По default выбрана 'конечная'");
            boatStopType = "конечная";
        }

        System.out.println("\nВведите максимально допустимое количество людей на остановке (N): ");
        this.maxWaitAllowed = input.getInt();
    }

    private void scheduleInitialEvents() {
        double currentTime = 0.0;

        double firstPassengerDelay = DelayGenerator.getExponentialDelay(
                DelayGenerator.getMeanDelayForTime(currentTime, passengerNight, passengerMorning, passengerDay, passengerEvening));
        double firstBoatDelay = DelayGenerator.getExponentialDelay(
                DelayGenerator.getMeanDelayForTime(currentTime, boatNight, boatMorning, boatDay, boatEvening));

        eventQueue.add(new Event(currentTime + firstPassengerDelay / 60.0, Event.EventType.PASSENGER));
        eventQueue.add(new Event(currentTime + firstBoatDelay / 60.0, Event.EventType.BOAT));
    }

    private void processEvents() {
        while (!eventQueue.isEmpty()) {
            Event currentEvent = eventQueue.poll();
            if (currentEvent.time() > SIMULATION_DURATION) {
                break;
            }
            System.out.println("[" + formatTime(currentEvent.time()) + "] " + describeEvent(currentEvent));

            //обработка событий
            if (currentEvent.type() == Event.EventType.PASSENGER) {
                handlePassengerArrival(currentEvent);
            } else if (currentEvent.type() == Event.EventType.BOAT) {
                handleBoatArrival(currentEvent);
            }

            double meanDelay = (currentEvent.type() == Event.EventType.PASSENGER)
                    ? DelayGenerator.getMeanDelayForTime(currentEvent.time(), passengerNight, passengerMorning, passengerDay, passengerEvening)
                    : DelayGenerator.getMeanDelayForTime(currentEvent.time(), boatNight, boatMorning, boatDay, boatEvening);
            double nextDelay = DelayGenerator.getExponentialDelay(meanDelay);
            double nextEventTime = currentEvent.time() + (nextDelay / 60.0);
            eventQueue.add(new Event(nextEventTime, currentEvent.type()));

            maxQueueSizeObserved = Math.max(maxQueueSizeObserved, waitingPersonsQueue.size());
        }
    }

    private void handlePassengerArrival(Event event) {
        waitingPersonsQueue.add(new Person(event.time()));
    }

    private void handleBoatArrival(Event event) {
        int freeSeats = random.nextInt(5) + 1;
        System.out.println("    Тип остановки: " + boatStopType + ". Свободных мест: " + freeSeats);
        for (int i = 0; i < freeSeats && !waitingPersonsQueue.isEmpty(); i++) {
            Person p = waitingPersonsQueue.poll();
            double waitTime = (event.time() - p.arrivalTime()) * 60.0;
            totalWaitingTime += waitTime;
            boardedCount++;
            System.out.println("\t\tПассажир, прибыл в " + formatTime(p.arrivalTime()) +
                    ", сел на катер. Время ожидания: " + String.format("%.2f", waitTime) + " мин.");
        }
    }

    //вся итоговая статисткиа
    private void printStatistics() {
        System.out.println("\nСимуляция завершена.\n");
        if (boardedCount > 0) {
            double avgWaitingTime = totalWaitingTime / boardedCount;
            System.out.println("Avg время пребывания пассажира на остановке: " + String.format("%.2f", avgWaitingTime) + " мин.");
        } else {
            System.out.println("Ни один пассажир не сел на катер");
        }
        System.out.println("Максимальное количество ожидающих пассажиров: " + maxQueueSizeObserved);
        if (maxQueueSizeObserved <= maxWaitAllowed) {
            System.out.println("Интервал между приходами катеров достаточный: на остановке (одновременно) находилось не более " + maxWaitAllowed + " человек");
        } else {
            System.out.println("Интервал между приходами катеров недостаточный: периодически на остановке было более " + maxWaitAllowed + " человек");
        }
    }

    //метод форматирует времяя
    private String formatTime(double timeInHours) {
        int hours = (int) timeInHours;
        int minutes = (int) Math.round((timeInHours - hours) * 60);
        return String.format("%02d:%02d", hours, minutes);
    }

    private String describeEvent(Event event) {
        if (event.type() == Event.EventType.PASSENGER) {
            return "Прибытие пассажира";
        } else {
            return "Прибытие катера";
        }
    }
}

 //record, который представляет событие в модели.
record Event(double time, Event.EventType type) implements Comparable<Event> {
    public enum EventType {PASSENGER, BOAT}

    @Override
    public int compareTo(Event other) {
        return Double.compare(this.time, other.time);
    }
}

record Person(double arrivalTime) {
}

//абстрагирует ввод в консоли (безопасно получаем данные)
class InputHandler {
    private final Scanner scanner;

    public InputHandler(Scanner scanner) {
        this.scanner = scanner;
    }

    public double getDouble(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextDouble()) {
            System.out.print("Введите числовое значение: " + prompt);
            scanner.next();
        }
        double value = scanner.nextDouble();
        scanner.nextLine();
        return value;
    }

    public int getInt() {
        while (!scanner.hasNextInt()) {
            System.out.print("Введите целое число: ");
            scanner.next();
        }
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }

    public String getString() {
        return scanner.nextLine().trim();
    }
}

//расчет задержки и выбора avg интервала в зависимости от времени суток
class DelayGenerator {
    public static double getMeanDelayForTime(double currentTime, double night,
                                             double morning, double day, double evening) {
        double hour = currentTime % 24;
        if (hour >= 0 && hour < 6) {
            return night;
        } else if (hour >= 6 && hour < 12) {
            return morning;
        } else if (hour >= 12 && hour < 18) {
            return day;
        } else {
            return evening;
        }
    }

    public static double getExponentialDelay(double mean) {
        Random rand = new Random();
        double u = rand.nextDouble();
        return -mean * Math.log(1 - u);
    }
}
