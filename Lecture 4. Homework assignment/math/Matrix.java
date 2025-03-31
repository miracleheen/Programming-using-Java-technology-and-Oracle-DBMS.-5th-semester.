package math;

import java.util.Random;
import java.util.Scanner;

public class Matrix<T extends Number> {
    private final int rows;
    private final int cols;
    private final double[][] data;

    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        data = new double[rows][cols];
    }

    public void fillFromKeyboard() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите элементы матрицы (" + rows + "x" + cols + "):");

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print("Элемент [" + i + "][" + j + "]: ");
                data[i][j] = sc.nextDouble();
            }
        }
    }

    public void fillRandom(double lower, double upper) {
        Random rand = new Random();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i][j] = lower + (upper - lower) * rand.nextDouble();
            }
        }
    }

    public void display() {
        System.out.println("Матрица (" + rows + "x" + cols + "):");

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.printf("%10.2f ", data[i][j]);
            }
            System.out.println();
        }
    }

    public Matrix<Double> add(Matrix<?> other) {
        if (this.rows != other.rows || this.cols != other.cols) {
            throw new IllegalArgumentException("Размеры должны совпадать");
        }

        Matrix<Double> result = new Matrix<>(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result.data[i][j] = this.data[i][j] + other.data[i][j];
            }
        }
        return result;
    }

    public Matrix<Double> subtract(Matrix<?> other) {
        if (this.rows != other.rows || this.cols != other.cols) {
            throw new IllegalArgumentException("Размеры должны совпадать");
        }

        Matrix<Double> result = new Matrix<>(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result.data[i][j] = this.data[i][j] - other.data[i][j];
            }
        }

        return result;
    }

    public Matrix<Double> multiply(Matrix<?> other) {
        if (this.cols != other.rows) {
            throw new IllegalArgumentException("Не совпадает число столбцов первой матрицы, со второй");
        }

        Matrix<Double> result = new Matrix<>(this.rows, other.cols);
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < other.cols; j++) {
                double sum = 0;
                for (int k = 0; k < this.cols; k++) {
                    sum += this.data[i][k] * other.data[k][j];
                }
                result.data[i][j] = sum;
            }
        }

        return result;
    }

    public Matrix<Double> divide(Matrix<?> other) {
        if (this.rows != other.rows || this.cols != other.cols) {
            throw new IllegalArgumentException("Размеры должны совпадать");
        }

        Matrix<Double> result = new Matrix<>(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (other.data[i][j] == 0) {
                    throw new ArithmeticException("Деление на ноль в элементе [" + i + "][" + j + "].");
                }
                result.data[i][j] = this.data[i][j] / other.data[i][j];
            }
        }

        return result;
    }

    public double getMax() {
        double max = data[0][0];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (data[i][j] > max) {
                    max = data[i][j];
                }
            }
        }

        return max;
    }

    public double getMin() {
        double min = data[0][0];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (data[i][j] < min) {
                    min = data[i][j];
                }
            }
        }

        return min;
    }

    public double getAverage() {
        double sum = 0;
        int count = rows * cols;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                sum += data[i][j];
            }
        }

        return sum / count;
    }
}
