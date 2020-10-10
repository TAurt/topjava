package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealDao {
    List<Meal> getAll();

    Meal add(Meal meal);

    Meal edit(Meal meal);

    Meal getById(int id);

    boolean delete(Meal meal);

}
