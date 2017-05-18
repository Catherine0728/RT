package com.example.model;

import java.util.ArrayList;

/**
 * Created by catherine on 17/5/18.
 * <p>
 * 抽象工厂
 */

public class Company implements Cloneable {

    private ArrayList<String> drinks = new ArrayList<>();

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getDrinks() {
        return drinks;
    }

    public void setDrinks(String drinks) {
        this.drinks.add(drinks);
    }

    @Override
    public Company clone() {
        Company company = null;
        try {
            company = (Company) super.clone();
            company.drinks=(ArrayList<String>) this.drinks.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return company;
    }

    @Override
    public String toString() {
        return "Company{" +
                "饮品：" + drinks +
                ", 名字：'" + name + '\'' +
                '}';
    }
}
