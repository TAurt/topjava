package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int USER_MEAL_ID = START_SEQ + 2;
    public static final int ADMIN_MEAL_ID = START_SEQ + 3;

    public static final Meal userMeal = new Meal(USER_MEAL_ID, LocalDateTime.of(2020, 1, 31, 7, 0), "завтрак", 1000);
    public static final Meal adminMeal = new Meal(ADMIN_MEAL_ID, LocalDateTime.of(2020, 1, 30, 14, 0), "обед", 1000);

    public static Meal getNewMeal() {
        return new Meal(null, LocalDateTime.of(2020, 1, 29, 10, 0), "завтрак", 750);
    }

    public static Meal getUpdatedMeal() {
        Meal updated = new Meal();
        updated.setId(userMeal.getId());
        updated.setDateTime(userMeal.getDateTime());
        updated.setDescription("UpdatedDescription");
        updated.setCalories(500);
        return updated;
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields().isEqualTo(expected);
    }
}
