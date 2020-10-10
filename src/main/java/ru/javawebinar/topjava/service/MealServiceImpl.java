package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.dao.MealDaoImpl;
import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public class MealServiceImpl implements MealService {
    private MealDao mealDao = new MealDaoImpl();

    @Override
    public List<Meal> getAll() {
        return mealDao.getAll();
    }

    @Override
    public Meal add(Meal meal) {
        mealDao.add(meal);
        return meal;
    }

    @Override
    public Meal edit(Meal meal) {
        mealDao.edit(meal);
        return meal;
    }

    @Override
    public Meal getById(int id) {
        return mealDao.getById(id);
    }

    @Override
    public boolean delete(Meal meal) {
        return mealDao.delete(meal);
    }
}
