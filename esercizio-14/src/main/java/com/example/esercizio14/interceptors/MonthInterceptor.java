package com.example.esercizio14.interceptors;

import com.example.esercizio14.entities.Month;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class MonthInterceptor implements HandlerInterceptor {

    public static List<Month> lista = new ArrayList<>(Arrays.asList(
            new Month(1, "January", "Gennaio", "Januar"),
            new Month(2,"February","Febbraio","Februar"),
            new Month(3,"March","Marzo","Marsch")
    ));

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String monthNumber = request.getHeader("monthnum");
        if (monthNumber == null||monthNumber.isEmpty()){
            response.setStatus(400);
            return true;
        }
        int monthnum = Integer.parseInt(monthNumber);
        Optional<Month> month = lista.stream().filter(singleMonth->{
            return singleMonth.getMonthNumber() == monthnum;
        }).findFirst();

        if(month.isPresent()){
            request.setAttribute("month", month.get());
        }else{
            Month meseNope = new Month();
            meseNope.setMonthNumber(0);
            meseNope.setEnglishName("nope");
            meseNope.setItalianName("nope");
            meseNope.setGermanName("nope");
            request.setAttribute("month", meseNope);

        }
        return true;

    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }
}
