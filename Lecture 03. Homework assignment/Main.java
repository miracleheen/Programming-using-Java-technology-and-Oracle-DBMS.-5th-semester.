import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

//1
@Getter
class Human {
    private final String name;
    private final String surname;
    private final String gender;
    private final String nationality;
    private final LocalDate birthDate;
    private final UUID uuid;

    public Human(String name, String surname, String gender, LocalDate birthDate, String nationality) {
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.birthDate = birthDate;
        this.nationality = nationality;
        this.uuid = UUID.randomUUID();
    }

    public int getBirthDate() {
        return LocalDate.now().getYear() - birthDate.getYear();
    }

    @Override
    public String toString() {
        return String.format("%s %s, Age: %d, Nationality: %s, Gender: %s, ID: %s",
                name, surname, getBirthDate(), nationality, gender, uuid);
    }
}

@Getter
@Setter
class Builder extends Human {
    private String currentCompany;
    private String specialization;
    private int experienceAsBuilder;

    public Builder(String name, String surname, String gender, LocalDate birthDate,
                   String nationality, String currentCompany, String specialization, int experienceAsBuilder) {
        super(name, surname, gender, birthDate, nationality);
        this.currentCompany = currentCompany;
        this.specialization = specialization;
        if (experienceAsBuilder < 0)
            throw new IllegalArgumentException("Значение не может быть отрицательным (опыт).");
        this.experienceAsBuilder = experienceAsBuilder;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(
                "\n- Specialty: %s\n- Company: %s\n- Experience: %d years",
                specialization, currentCompany, experienceAsBuilder);
    }
}

@Getter
@Setter
class Pilot extends Human {
    private ArrayList<String> aircraftTypes;
    private String airline;
    private int totalFlightHours;

    public Pilot(String name, String surname, String gender, LocalDate birthDate, String nationality
            , ArrayList<String> aircraftTypes, String airline, int totalFlightHours) {
        super(name, surname, gender, birthDate, nationality);
        this.aircraftTypes = aircraftTypes;
        this.airline = airline;
        if (totalFlightHours < 0)
            throw new IllegalArgumentException("Значение не может быть отрицательным (количество часов)");
        this.totalFlightHours = totalFlightHours;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(
                "\n- Airline: %s\n- Aircraft Types: %s\n- Flight Hours: %d",
                airline, String.join(", ", aircraftTypes), totalFlightHours
        );
    }
}

@Getter
@Setter
class Sailor extends Human {
    public enum RankOfSailor {
        DECKHAND, OFFICER, CAPTAIN
    }

    private RankOfSailor currentRank;
    private String vesselName;

    public Sailor(String name, String surname, String gender, LocalDate birthDate, String nationality
            , RankOfSailor currentRank, String vesselName) {
        super(name, surname, gender, birthDate, nationality);
        this.currentRank = currentRank;
        this.vesselName = vesselName;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(
                "\n- Rank: %s\n- Vessel: %s",
                currentRank, vesselName
        );
    }
}

//2
class Animal {
    protected final String name;

    public Animal(String name) {
        this.name = name;
    }

    public void displayInfo() {
        System.out.println("Name: " + name);
    }
}

class Tiger extends Animal {
    private final int stripeCount;

    public Tiger(String name, int stripeCount) {
        super(name);
        this.stripeCount = stripeCount;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Type: Tiger");
        System.out.println("Number of stripes: " + stripeCount);
    }

    public void roar() {
        System.out.println(name + " roars loudly\n");
    }
}

class Crocodile extends Animal {
    private final double length;

    public Crocodile(String name, double length) {
        super(name);
        this.length = length;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Type: Crocodile");
        System.out.println("Length: " + length + " meters");
    }

    public void swim() {
        System.out.println(name + " is swimming in the river\n");
    }
}

class Kangaroo extends Animal {
    private final double jumpHeight;

    public Kangaroo(String name, double jumpHeight) {
        super(name);
        this.jumpHeight = jumpHeight;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Type: Kangaroo");
        System.out.println("Jump height: " + jumpHeight + " meters");
    }

    public void jump() {
        System.out.println(name + " jumps " + jumpHeight + " meters high\n");
    }
}

//3
@Setter
class Money {
    private int major;
    private int minor;

    public Money(int major, int minor) {
        if (minor >= 100) {
            this.major = major + minor / 100;
            this.minor = minor % 100;
        } else {
            this.major = major;
            this.minor = minor;
        }
    }

    public Money() {
        this(0, 0);
    }

    public void setMinor(int minor) {
        if (minor >= 100) {
            this.major += minor / 100;
            this.minor = minor % 100;
        } else {
            this.minor = minor;
        }
    }

    public void print() {
        System.out.printf("%d.%02d\n", major, minor);
    }

    private int getTotalMinor() {
        return major * 100 + minor; //если нужна работа только с копейками
    }

    public Money subtract(Money other) {
        int total = this.getTotalMinor() - other.getTotalMinor();
        if (total < 0) {
            total = 0;
        }
        return new Money(total / 100, total % 100);
    }

    @Override
    public String toString() {
        return String.format("%d.%02d", major, minor);
    }
}

class Product {
    private final String name;
    private Money price;

    public Product(String name, Money price) {
        this.name = name;
        this.price = price;
    }

    public void reducePrice(Money reduction) {
        this.price = this.price.subtract(reduction);
    }

    public void printProduct() {
        System.out.println("Product: " + name + ", price: " + price);
    }
}

//4
class Device {
    protected final String name;
    protected final String specifications;

    public Device(String name, String specifications) {
        this.name = name;
        this.specifications = specifications;
    }

    public void Sound() {
        System.out.println("Device " + name + " makes a sound.");
    }

    public void Show() {
        System.out.println("Device: " + name);
    }

    public void Desc() {
        System.out.println("Specifications: " + specifications);
    }
}

class Kettle extends Device {
    public Kettle(String name, String specifications) {
        super(name, specifications);
    }

    @Override
    public void Sound() {
        System.out.println("Kettle " + name + " makes a sound: \"буль-буль\"");
    }
}

class Microwave extends Device {
    public Microwave(String name, String specifications) {
        super(name, specifications);
    }

    @Override
    public void Sound() {
        System.out.println("Microwave " + name + " makes a sound: \"Beep-beep\"");
    }
}

class Car extends Device {
    public Car(String name, String specifications) {
        super(name, specifications);
    }

    @Override
    public void Sound() {
        System.out.println("Car " + name + " makes a sound: \"Honk-honk\"");
    }
}

class Steamboat extends Device {
    public Steamboat(String name, String specifications) {
        super(name, specifications);
    }

    @Override
    public void Sound() {
        System.out.println("Steamboat " + name + " makes a sound: \"Woo-woo\"");
    }
}

//5
class MusicalInstrument {
    protected final String name;
    protected final String specifications;

    public MusicalInstrument(String name, String specifications) {
        this.name = name;
        this.specifications = specifications;
    }

    public void Sound() {
        System.out.println("The instrument " + name + " makes a sound.");
    }

    public void Show() {
        System.out.println("Instrument: " + name);
    }

    public void Desc() {
        System.out.println("Specifications: " + specifications);
    }

    public void History() {
        System.out.println("История музыкального инструмента неизвестна.");
    }
}

class Violin extends MusicalInstrument {
    public Violin(String name, String specifications) {
        super(name, specifications);
    }

    @Override
    public void Sound() {
        System.out.println("Violin " + name + " plays: \"screech\"");
    }

    @Override
    public void History() {
        System.out.println("История: Скрипка – струнный смычковый инструмент, широко используемый в классической музыке," +
                "имеет историю, уходящую корнями в эпоху Возрождения.");
    }
}

class Trombone extends MusicalInstrument {
    public Trombone(String name, String specifications) {
        super(name, specifications);
    }

    @Override
    public void Sound() {
        System.out.println("Trombone " + name + " plays: \"brassy sound\"");
    }

    @Override
    public void History() {
        System.out.println("История: Тромбон – медный духовой инструмент с выдвижной трубой, " +
                "возникший в XV веке и активно используемый в оркестрах и джазе");
    }
}

class Ukulele extends MusicalInstrument {
    public Ukulele(String name, String specifications) {
        super(name, specifications);
    }

    @Override
    public void Sound() {
        System.out.println("Ukulele " + name + " plays: \"tinkle\"");
    }

    @Override
    public void History() {
        System.out.println("История: Укулеле – струнный инструмент, родом с Гавайев, появился в XIX веке " +
                "под влиянием португальских инструментов и стал символом тропической музыки.");
    }
}

class Cello extends MusicalInstrument {
    public Cello(String name, String specifications) {
        super(name, specifications);
    }

    @Override
    public void Sound() {
        System.out.println("Cello " + name + " plays: \"deep resonant tone\"");
    }

    @Override
    public void History() {
        System.out.println("История: Виолончель – струнный смычковый инструмент с глубоким и насыщенным звуком," +
                "возникший в XVI веке и являющийся важной частью классического оркестра.");
    }
}

//6, 7
interface IMath {
    int max();

    int min();

    float avg();
}

interface ISort {
    void sortAsc();

    void sortDesc();
}

record ArrayImpl(int[] array) implements IMath, ISort {
    ArrayImpl(int[] array) {
        this.array = Arrays.copyOf(array, array.length);
    }

    @Override
    public int max() {
        int max = array[0];
        for (int value : array) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    @Override
    public int min() {
        int min = array[0];
        for (int value : array) {
            if (value < min) {
                min = value;
            }
        }
        return min;
    }

    @Override
    public float avg() {
        float sum = 0;
        for (int value : array) {
            sum += value;
        }
        return sum / array.length;
    }

    @Override
    public void sortAsc() {
        Arrays.sort(array);
    }

    @Override
    public void sortDesc() {
        Arrays.sort(array);
        for (int i = 0; i < array.length / 2; i++) {
            int temp = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = temp;
        }
    }
}


public class Main {
    public static void main(String[] args) {
        //1
        /*Builder builder = new Builder("Aleksandr", "Sverdlov", "male"
                , LocalDate.of(1999, 4, 21), "jew", "Ingrad"
                , "Custom home builders", 2);
        System.out.println(builder);

        System.out.println();

        Pilot pilot = new Pilot("Konstantin", "Shipovalov", "male"
                , LocalDate.of(1995, 1, 27), "russian",
                new ArrayList<>(Arrays.asList("Boeing 777", "Airbus A320")), "Russian Airways", 123);
        System.out.println(pilot);

        System.out.println();

        Sailor sailor = new Sailor("Viktoria", "Sverdlova", "female"
                , LocalDate.of(2002, 8, 2), "russian", Sailor.RankOfSailor.CAPTAIN
                , "SaveLove");
        System.out.println(sailor);*/

        //2
        /*Tiger tiger = new Tiger("Tiger", 150);
        tiger.displayInfo();
        tiger.roar();

        Crocodile crocodile = new Crocodile("Crocodile", 20);
        crocodile.displayInfo();
        crocodile.swim();

        Kangaroo kangaroo = new Kangaroo("Kangaroo", 30);
        kangaroo.displayInfo();
        kangaroo.jump();*/

        //3
        /*Money money = new Money(10, 50);
        System.out.print("Начальная сумма: ");
        money.print();

        money.setMajor(13);
        money.setMinor(75);
        System.out.print("Новая сумма: ");
        money.print();

        Product product = new Product("Milk", new Money(12, 75));
        System.out.print("Исходная цена продукта: ");
        product.printProduct();

        Money reduction = new Money(2, 30);
        product.reducePrice(reduction);
        System.out.print("Цена после уменьшения: ");
        product.printProduct();*/

        //4
        /*Device kettle = new Kettle("Bosch KX-100", "Electric kettle, 2200W, 1.7L volume");
        Device microwave = new Microwave("Samsung MW-2000", "Power 800W, grill, auto defrost");
        Device car = new Car("Toyota Camry", "Sedan, hybrid, 5.6L/100km");
        Device steamboat = new Steamboat("Titanic", "Passenger liner, 123m length, 1500 passengers");

        System.out.println("<-- Kettle -->");
        kettle.Show();
        kettle.Desc();
        kettle.Sound();

        System.out.println("\n<-- Microwave -->");
        microwave.Show();
        microwave.Desc();
        microwave.Sound();

        System.out.println("\n<-- Car -->");
        car.Show();
        car.Desc();
        car.Sound();

        System.out.println("\n<-- Steamboat -->");
        steamboat.Show();
        steamboat.Desc();
        steamboat.Sound();
*/

        //5
        /*MusicalInstrument violin = new Violin("Stradivarius", "Made of fine wood, antique design");
        MusicalInstrument trombone = new Trombone("Yamaha Trombone", "Brass instrument, slide mechanism");
        MusicalInstrument ukulele = new Ukulele("Kamaka", "Compact size, bright tone");
        MusicalInstrument cello = new Cello("Montagnana", "Large body, deep resonant sound");

        System.out.println("<-- Violin -->");
        violin.Show();
        violin.Desc();
        violin.Sound();
        violin.History();

        System.out.println("\n<--Trombone -->");
        trombone.Show();
        trombone.Desc();
        trombone.Sound();
        trombone.History();

        System.out.println("\n<-- Ukulele -->");
        ukulele.Show();
        ukulele.Desc();
        ukulele.Sound();
        ukulele.History();

        System.out.println("\n<-- Cello -->");
        cello.Show();
        cello.Desc();
        cello.Sound();
        cello.History();*/

        //6, 7 tests
    }
}