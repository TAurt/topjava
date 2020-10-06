package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MealDaoImpl implements MealDao {
    private static final AtomicInteger AUTO_ID = new AtomicInteger(0);
    private static Map<Integer, Meal> meals = new ConcurrentHashMap<>();
    private static final int CALORIES_PER_DAY = 2000;
    private static final LocalTime START_TIME_MIN = LocalTime.of(0, 0);
    private static final LocalTime END_TIME_MAX = LocalTime.of(23, 59);

    static {
        meals.put(AUTO_ID.get(), new Meal(AUTO_ID.getAndIncrement(), LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500));
        meals.put(AUTO_ID.get(), new Meal(AUTO_ID.getAndIncrement(), LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000));
        meals.put(AUTO_ID.get(), new Meal(AUTO_ID.getAndIncrement(), LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500));
        meals.put(AUTO_ID.get(), new Meal(AUTO_ID.getAndIncrement(), LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100));
        meals.put(AUTO_ID.get(), new Meal(AUTO_ID.getAndIncrement(), LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000));
        meals.put(AUTO_ID.get(), new Meal(AUTO_ID.getAndIncrement(), LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500));
        meals.put(AUTO_ID.get(), new Meal(AUTO_ID.getAndIncrement(), LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410));
    }

    @Override
    public List<MealTo> getAllMeals() {
        return MealsUtil.filteredByStreams(new ArrayList<>(meals.values()), START_TIME_MIN, END_TIME_MAX, CALORIES_PER_DAY);
    }

    @Override
    public void addMeal(Meal meal) {
        meal.setId(AUTO_ID.getAndIncrement());
        meals.put(meal.getId(), meal);
    }

    @Override
    public void editMeal(Meal meal) {
        meals.put(meal.getId(), meal);
    }

    @Override
    public Meal getMealById(int id) {
        return meals.get(id);
    }

    @Override
    public void deleteMeal(int id) {
        meals.remove(id);
    }
}
