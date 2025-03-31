package math;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Stream;

public record Array<T extends Comparable<? super T>>(T[] array) {
    public Array {
        if (array == null) {
            throw new IllegalArgumentException("Массив не может быть null");
        }
    }

    public T maxOfThree(T a, T b, T c) {
        return Stream.of(a, b, c)
                .max(Comparator.naturalOrder())
                .orElseThrow(() -> new IllegalArgumentException("Something went wrong"));
    }

    public T minOfFive(T a, T b, T c, T d, T e) {
        return Stream.of(a, b, c, d, e)
                .min(Comparator.naturalOrder())
                .orElseThrow(() -> new IllegalArgumentException("Something went wrong"));
    }

    public double average() {
        if (array.length == 0) {
            throw new IllegalArgumentException("Массив не может быть пустым");
        }
        if (!(array[0] instanceof Number)) {
            throw new IllegalArgumentException("Тип элементов не является числовым");
        }
        return Arrays.stream(array)
                .mapToDouble(e -> ((Number) e).doubleValue())
                .average()
                .orElseThrow(() -> new IllegalArgumentException("Массив не может быть пустым"));
    }

    public int binarySearch(T key) {
        if (key == null) {
            throw new IllegalArgumentException("Ключ поиска не может быть null");
        }
        if (Arrays.stream(array).anyMatch(Objects::isNull)) {
            throw new IllegalArgumentException("У массива есть null-элементы");
        }
        int low = 0;
        int high = array.length - 1;
        while (low <= high) {
            int mid = (low + high) >>> 1;
            T midVal = array[mid];
            int cmp = midVal.compareTo(key);
            if (cmp < 0) {
                low = mid + 1;
            } else if (cmp > 0) {
                high = mid - 1;
            } else {
                return mid;
            }
        }
        return -(low + 1); // если элемент не найден.
    }

    public T maxOfArray(T[] arr) {
        return Optional.ofNullable(arr)
                .map(a -> Arrays.stream(a)
                        .peek(e -> {
                            if (e == null) {
                                throw new IllegalArgumentException("У массива есть null-элементы");
                            }
                        })
                        .max(Comparator.naturalOrder())
                        .orElseThrow(() -> new IllegalArgumentException("Массив не может быть пустым")))
                .orElseThrow(() -> new IllegalArgumentException("Массив не может быть null"));
    }

    public T minOfArray(T[] arr) {
        return Optional.ofNullable(arr)
                .map(a -> Arrays.stream(a)
                        .peek(e -> {
                            if (e == null) {
                                throw new IllegalArgumentException("У массива есть null-элементы");
                            }
                        })
                        .min(Comparator.naturalOrder())
                        .orElseThrow(() -> new IllegalArgumentException("Массив не может быть пустым")))
                .orElseThrow(() -> new IllegalArgumentException("Массив не может быть null"));
    }

    public void fillRandom(Supplier<T> supplier) {
        if (array == null) {
            throw new IllegalArgumentException("Массив не может быть null");
        }
        for (int i = 0; i < array.length; i++) {
            array[i] = supplier.get();
        }
    }

    public void display() {
        if (array == null) {
            System.out.println("Массив равен null");
        } else {
            System.out.println(Arrays.toString(array));
        }
    }

    public void sortAscending() {
        if (array == null) {
            throw new IllegalArgumentException("Массив не может быть null");
        }
        if (Arrays.stream(array).anyMatch(Objects::isNull)) {
            throw new IllegalArgumentException("У массива есть null-элементы");
        }
        Arrays.sort(array);
    }

    public void sortDescending() {
        if (array == null) {
            throw new IllegalArgumentException("Массив не может быть null");
        }
        if (Arrays.stream(array).anyMatch(Objects::isNull)) {
            throw new IllegalArgumentException("У массива есть null-элементы");
        }
        Arrays.sort(array, Collections.reverseOrder());
    }

    public void replace(int index, T newValue) {
        if (array == null) {
            throw new IllegalArgumentException("Массив не может быть null");
        }
        if (index < 0 || index >= array.length) {
            throw new IndexOutOfBoundsException("Индекс вне диапазона массива");
        }
        array[index] = newValue;
    }
}

