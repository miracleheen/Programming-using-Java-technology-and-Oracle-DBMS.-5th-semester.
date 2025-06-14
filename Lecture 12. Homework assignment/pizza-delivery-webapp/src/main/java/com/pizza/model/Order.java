package com.pizza.model;

import java.util.List;

public class Order {
    private PizzaType pizzaType;
    private List<Topping> extraToppings; // дополнительные топпинги, может быть пустой список
    private String customerName;
    private String phone;
    private String email;
    private String address;

    public Order() {
    }

    public PizzaType getPizzaType() {
        return pizzaType;
    }

    public List<Topping> getExtraToppings() {
        return extraToppings;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public int getTotalPrice() {
        int total = pizzaType.getBasePrice();
        if (extraToppings != null) {
            for (Topping t : extraToppings) {
                total += t.getPrice();
            }
        }
        return total;
    }

    public void setPizzaType(PizzaType pizzaType) {
        this.pizzaType = pizzaType;
    }

    public void setExtraToppings(List<Topping> extraToppings) {
        this.extraToppings = extraToppings;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
