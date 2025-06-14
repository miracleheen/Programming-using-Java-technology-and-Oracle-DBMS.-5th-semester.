package com.pizza.model;

import java.util.Collections;
import java.util.List;

public enum PizzaType {
    MARGHERITA("Маргарита", 500,
            List.of(Topping.EXTRA_CHEESE)),
    FOUR_CHEESE("Четыре сыра", 700,
            List.of(Topping.EXTRA_CHEESE, Topping.MUSHROOMS, Topping.OLIVES)),
    CAPRICCIOSA("Капричоза", 750,
            List.of(Topping.HAM, Topping.MUSHROOMS, Topping.OLIVES)),
    HAWAIIAN("Гавайская", 800,
            List.of(Topping.HAM, Topping.PINEAPPLE)),
    CUSTOM("Собрать свою пиццу", 200, Collections.emptyList());

    private final String displayName;
    private final int basePrice;
    private final List<Topping> defaultToppings;

    PizzaType(String displayName, int basePrice, List<Topping> defaultToppings) {
        this.displayName = displayName;
        this.basePrice = basePrice;
        this.defaultToppings = defaultToppings;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getBasePrice() {
        return basePrice;
    }

    public List<Topping> getDefaultToppings() {
        return defaultToppings;
    }
}
