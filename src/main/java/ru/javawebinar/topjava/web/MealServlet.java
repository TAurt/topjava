package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.service.MealServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.slf4j.Logger;
import ru.javawebinar.topjava.util.MealsUtil;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static Logger log;
    private MealService mealService;
    private static final int CALORIES_PER_DAY = 2000;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init();
        log = getLogger(MealServlet.class);
        mealService = new MealServiceImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id").trim();
        if (request.getParameter("dateTime").trim().equals("")) {
            log.info("not right date");
            response.sendRedirect("meals");
            return;
        }

        if (id.equals("-1")) {
            Meal meal = new Meal(-1, LocalDateTime.parse(request.getParameter("dateTime")),
                    request.getParameter("description"), Integer.parseInt(request.getParameter("calories")));
            if (mealService.add(meal) != null)
                log.info("meal was created");
            response.sendRedirect("meals");
        }

        if (!id.equals("-1")) {
            Meal meal = new Meal(Integer.parseInt(request.getParameter("id").trim()), LocalDateTime.parse(request.getParameter("dateTime")),
                    request.getParameter("description"), Integer.parseInt(request.getParameter("calories")));
            if (mealService.edit(meal) != null)
                log.info("meal was updated");
            response.sendRedirect("meals");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null && action.equals("delete")) {
            int id = Integer.parseInt(request.getParameter("id"));
            Meal meal = mealService.getById(id);
            if (meal != null) {
                if (mealService.delete(meal)) {
                    log.info("meal was deleted");
                } else {
                    log.info("meal wasn't deleted");
                }
            } else {
                log.info("meal is not or meal was deleted");
            }
            response.sendRedirect("meals");
            return;
        }

        List<MealTo> mealsToList = MealsUtil.filteredByStreams(mealService.getAll(), LocalTime.MIN, LocalTime.MAX, CALORIES_PER_DAY);
        request.setAttribute("mealsToList", mealsToList);
        log.info("get mealsList");
        request.getRequestDispatcher("/meals.jsp").forward(request, response);
    }


}
