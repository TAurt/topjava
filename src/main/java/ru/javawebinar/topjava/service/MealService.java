package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;

import java.util.List;

public interface MealService {
    List<Meal> getAll();

    Meal add(Meal meal);

    Meal edit(Meal meal);

    Meal getById(int id);

    boolean delete(Meal meal);
}
