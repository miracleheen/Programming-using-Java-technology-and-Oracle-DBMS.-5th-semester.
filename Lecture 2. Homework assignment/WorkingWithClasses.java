import lombok.Data;
import lombok.Getter;
import lombok.Setter;

//1
@Getter
@Setter
class Human {
    private String name;
    private String surname;
    private String patronymic;
    private double weight;
    private double height;
    private int age;

    public Human() {
        this.name = "";
        this.surname = "";
        this.patronymic = "";
        this.weight = 0.0;
        this.height = 0.0;
        this.age = 0;
    }

    public Human(String name, String surname, int age) {
        this(name, surname, "", 0.0, 0.0, 0);
    }

    public Human(String name, String surname, String patronymic, double weight, double height, int age) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.weight = weight;
        this.height = height;
        this.age = age;
    }

    public void setAllData(String name, String surname, String patronymic, double weight, double height, int age) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.weight = weight;
        this.height = height;
        this.age = age;
    }

    public void setAllData(String name, String surname, String patronymic) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.weight = 0.0;
        this.height = 0.0;
        this.age = 0;
    }

    public void displayInfo() {
        System.out.println("Информация о человеке: ");
        System.out.println("Name: " + this.name);
        System.out.println("Surname: " + this.surname);
        System.out.println("Patronymic: " + this.patronymic);
        System.out.println("Weight: " + this.weight);
        System.out.println("Height: " + this.height);
        System.out.println("Age: " + this.age);
    }
}

//2
@Getter
@Setter
class City {
    private String nameOfCity;
    private String country;
    private int population;
    private double area;
    private int foundedYear;

    public City() {
        this.nameOfCity = "";
        this.country = "";
        this.population = 0;
        this.area = 0.0;
        this.foundedYear = 0;
    }

    public City(String nameOfCity, String country) {
        this(nameOfCity, country, 0, 0.0, 0);
    }

    public City(String nameOfCity, String country, int population, double area, int foundedYear) {
        this.nameOfCity = nameOfCity;
        this.country = country;
        this.population = population;
        this.area = area;
        this.foundedYear = foundedYear;
    }

    public void displayInfo() {
        System.out.println("Информация о городе: ");
        System.out.println("Название города: " + nameOfCity);
        System.out.println("Страна: " + country);
        System.out.println("Численность: " + population);
        System.out.println("Территория: " + area);
        System.out.println("Год основания: " + foundedYear);
    }

    public void setAllData(String nameOfCity, String country) {
        this.nameOfCity = nameOfCity;
        this.country = country;
    }

    public void setAllData(String nameOfCity, String country, int population, double area, int foundedYear) {
        this.nameOfCity = nameOfCity;
        this.country = country;
        this.population = population;
        this.area = area;
        this.foundedYear = foundedYear;
    }
}

//3
@Getter
@Setter
class Country {
    private String nameOfCountry;
    private String capital;
    private long population;
    private double area;

    public Country() {
        this.nameOfCountry = "";
        this.capital = "";
        this.population = 0;
        this.area = 0.0;
    }

    public Country(String nameOfCountry, String capital) {
        this(nameOfCountry, capital, 0, 0.0);
    }

    public Country(String nameOfCountry, String capital, long population, double area) {
        this.nameOfCountry = nameOfCountry;
        this.capital = capital;
        this.population = population;
        this.area = area;
    }

    public void setAllData(String nameOfCountry, String capital, long population, double area) {
        this.nameOfCountry = nameOfCountry;
        this.capital = capital;
        this.population = population;
        this.area = area;
    }

    public void setAllData(String nameOfCountry, String capital) {
        this.nameOfCountry = nameOfCountry;
        this.capital = capital;
    }

    public void displayInfo() {
        System.out.println("Информация о стране: ");
        System.out.println("Название страны: " + nameOfCountry);
        System.out.println("Столица: " + capital);
        System.out.println("Численность: " + population);
        System.out.println("Территория: " + area);
    }
}

//4
class Fraction {
    private final int numerator;
    private final int denominator;

    public Fraction() {
        this(0, 1);
    }

    public Fraction(int numerator, int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("Знаменатель не может быть нулём.");
        }

        if (denominator < 0) {
            numerator *= -1;
            denominator *= -1;
        }

        int gcd = gcd(Math.abs(numerator), denominator);
        this.numerator = numerator / gcd;
        this.denominator = denominator / gcd;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    @Override
    public String toString() {
        return denominator == 1
                ? String.valueOf(numerator)
                : numerator + "/" + denominator;
    }
}

//5
@Setter
@Getter
class Book {
    private String nameOfBook;
    private String author;
    private int numOfpages;
    private int yearOfPublication;
    private String publisherName;
    private String genre;

    public Book() {
        this.nameOfBook = "";
        this.author = "";
        this.numOfpages = 0;
        this.yearOfPublication = 0;
        this.publisherName = "";
        this.genre = "";
    }

    public Book(String nameOfBook, String author, String genre) {
        this(nameOfBook, author, 0, 0, "", genre);
    }

    public Book(String nameOfBook, String author, int pages, int yearOfPublication, String publisherName, String genre) {
        this.nameOfBook = nameOfBook;
        this.author = author;
        this.numOfpages = pages;
        this.yearOfPublication = yearOfPublication;
        this.publisherName = publisherName;
        this.genre = genre;
    }

    public void setAllData(String nameOfBook, String author, int pages, int yearOfPublication, String publisherName, String genre) {
        this.nameOfBook = nameOfBook;
        this.author = author;
        this.numOfpages = pages;
        this.yearOfPublication = yearOfPublication;
        this.publisherName = publisherName;
        this.genre = genre;
    }

    public void setAllData(String nameOfBook, String author, String genre) {
        this.nameOfBook = nameOfBook;
        this.author = author;
        this.genre = genre;
    }

    public void displayInfo() {
        System.out.println("Информация о книге:");
        System.out.println("Название книги: " + nameOfBook);
        System.out.println("Автор: " + author);
        System.out.println("Жанр: " + genre);
        System.out.println("Дата публикации: " + yearOfPublication);
        System.out.println("Количество страниц: " + numOfpages);
        System.out.println("Издательство: " + publisherName);

    }
}

//6
@Setter
@Getter
class Automobile {
    private String nameOfAutomobile;
    private String manufacturerName;
    private int yearOfProduction;
    private double engineVolume;

    public Automobile() {
        this.nameOfAutomobile = "";
        this.manufacturerName = "";
        this.yearOfProduction = 0;
        this.engineVolume = 0.0;
    }

    public Automobile(String nameOfAutomobile, String manufacturerName) {
        this(nameOfAutomobile, manufacturerName, 0, 0.0);
    }

    public Automobile(String nameOfAutomobile, String manufacturerName, int yearOfProduction, double engineCapacity) {
        this.nameOfAutomobile = nameOfAutomobile;
        this.manufacturerName = manufacturerName;
        this.yearOfProduction = yearOfProduction;
        this.engineVolume = engineCapacity;
    }

    public void displayInfo() {
        System.out.println("Информация об автомобиле:");
        System.out.println("Модель: " + nameOfAutomobile);
        System.out.println("Производитель: " + manufacturerName);
        System.out.println("Год выпуска: " + yearOfProduction);
        System.out.println("Объем двигателя: " + engineVolume + " л");
    }

    public void setAllData(String nameOfAutomobile, String manufacturerName) {
        this.nameOfAutomobile = nameOfAutomobile;
        this.manufacturerName = manufacturerName;
    }

    public void setAllData(String nameOfAutomobile, String manufacturerName, int yearOfProduction, double engineVolume) {
        this.nameOfAutomobile = nameOfAutomobile;
        this.manufacturerName = manufacturerName;
        this.yearOfProduction = yearOfProduction;
        this.engineVolume = engineVolume;
    }
}

public class WorkingWithClasses {
    public static void main(String[] args) {
        //1
        Human bjarne = new Human("Bjarne", "Stroustrup", 74);
        bjarne.displayInfo();

        System.out.println();
        //2
        City moscow = new City();
        moscow.setAllData("Russia", "Moscow");
        moscow.setArea(2561_5);
        moscow.setFoundedYear(1147);
        moscow.setPopulation(19_100_000);
        moscow.displayInfo();

        System.out.println();
        //3
        Country russia = new Country("Russia", "Moscow");
        russia.setArea(17_098_246);
        russia.setPopulation(146_028_325);
        russia.displayInfo();

        System.out.println();
        //4
        Fraction example = new Fraction(124, 16);
        System.out.println(example);

        System.out.println();
        //5
        Book crimeAndPunishment = new Book("Crime and Punishment", "Dostoevsky", "Psychological realism");
        crimeAndPunishment.setNumOfpages(608);
        crimeAndPunishment.setPublisherName("Alphabet");
        crimeAndPunishment.setYearOfPublication(1866);
        crimeAndPunishment.displayInfo();

        System.out.println();

        //6
        Automobile porsche = new Automobile();
        porsche.setEngineVolume(3.800);
        porsche.setAllData("Porsche 911 Turbo S", "Porsche AG");
        porsche.setYearOfProduction(1992);
        porsche.displayInfo();
    }
}
