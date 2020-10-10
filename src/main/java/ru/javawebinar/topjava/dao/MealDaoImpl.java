package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MealDaoImpl implements MealDao {
    private static final AtomicInteger AUTO_ID = new AtomicInteger(0);
    private static final Map<Integer, Meal> meals = new ConcurrentHashMap<>();

    {
        add(new Meal(AUTO_ID.getAndIncrement(), LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500));
        add(new Meal(AUTO_ID.getAndIncrement(), LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000));
        add(new Meal(AUTO_ID.getAndIncrement(), LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500));
        add(new Meal(AUTO_ID.getAndIncrement(), LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100));
        add(new Meal(AUTO_ID.getAndIncrement(), LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000));
        add(new Meal(AUTO_ID.getAndIncrement(), LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500));
        add(new Meal(AUTO_ID.getAndIncrement(), LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410));
    }

    @Override
    public List<Meal> getAll() {
        return new ArrayList<>(meals.values());
    }

    @Override
    public Meal add(Meal meal) {
        meal.setId(AUTO_ID.getAndIncrement());
        return meals.put(meal.getId(), meal);
    }

    @Override
    public Meal edit(Meal meal) {
        return meals.computeIfPresent(meal.getId(), (key, value) -> meal);
    }

    @Override
    public Meal getById(int id) {
        return meals.get(id);
    }

    @Override
    public boolean delete(Meal meal) {
        return meals.remove(meal.getId(), meal);
    }
}
