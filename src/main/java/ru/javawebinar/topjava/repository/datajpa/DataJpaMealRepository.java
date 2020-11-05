package ru.javawebinar.topjava.repository.datajpa;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class DataJpaMealRepository implements MealRepository {

    private final CrudMealRepository crudRepository;
    private final CrudUserRepository crudUserRepository;

    public DataJpaMealRepository(CrudMealRepository crudRepository, CrudUserRepository crudUserRepository) {
        this.crudRepository = crudRepository;
        this.crudUserRepository = crudUserRepository;
    }

    @Override
    public Meal save(Meal meal, int userId) {
        meal.setUser(crudUserRepository.getOne(userId));
        if (meal.isNew() || get(meal.id(), userId) != null) {
            return crudRepository.save(meal);
        }
        return null;
    }

    @Override
    public boolean delete(int id, int userId) {
        return crudRepository.delete(id, userId) != 0;
    }

    @Override
    public Meal get(int id, int userId) {
        return crudRepository.findById(id).filter(meal -> {
            if (meal.getUser() == null) return false;
            assert meal.getUser().getId() != null;
            return meal.getUser().getId() == userId;
        }).orElse(null);
    }

    @Override
    @Transactional
    public Meal getWithUser(int id, int userId) {
        Meal meal = get(id, userId);
        if(meal != null) {
            User user = (User)(Hibernate.unproxy(meal.getUser()));
            meal.setUser(user);
        }
        return meal;
    }

    @Override
    public List<Meal> getAll(int userId) {
        User user = crudUserRepository.getOne(userId);
        return crudRepository.findByUserOrderByDateTimeDesc(user);
    }

    @Override
    public List<Meal> getBetweenHalfOpen(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        return crudRepository.findBetween(startDateTime, endDateTime, userId);
    }
}
