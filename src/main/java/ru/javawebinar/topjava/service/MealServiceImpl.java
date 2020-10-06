package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.dao.MealDaoImpl;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;

import java.util.List;

public class MealServiceImpl implements MealService {
    private MealDao mealDao = new MealDaoImpl();

    @Override
    public List<MealTo> getAllMeals() {
        return mealDao.getAllMeals();
    }

    @Override
    public void addMeal(Meal meal) {
        mealDao.addMeal(meal);
    }

    @Override
    public void editMeal(Meal meal) {
        mealDao.editMeal(meal);
    }

    @Override
    public Meal getMealById(int id) {
        return mealDao.getMealById(id);
    }

    @Override
    public void deleteMeal(int id) {
        mealDao.deleteMeal(id);
    }
}
