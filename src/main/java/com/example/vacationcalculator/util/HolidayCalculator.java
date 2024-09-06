package com.example.vacationcalculator.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Утилитарный класс для работы с праздничными и выходными днями.
 * Использует HolidayParser для получения списка праздничных дней.
 */
@Component
@Slf4j
public class HolidayCalculator {

    private final List<LocalDate> holidays;

    public HolidayCalculator(HolidayParser holidayParser) {
        this.holidays = holidayParser.loadHolidays();
    }

    /**
     * Возвращает список рабочих дней, исключая выходные и праздники из переданных дат.
     *
     * @param vacationDates список дат отпуска
     * @return список рабочих дней
     */
    public List<LocalDate> getWorkingDays(List<LocalDate> vacationDates) {
        log.info("Фильтрация рабочих дней из списка дат отпуска...");
        return vacationDates.stream()
                .filter(date -> !holidays.contains(date) && !isWeekend(date))
                .collect(Collectors.toList());
    }

    /**
     * Проверяет, является ли дата выходным днем (суббота или воскресенье).
     *
     * @param date дата для проверки
     * @return true, если дата является выходным днем
     */
    private boolean isWeekend(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        boolean isWeekend = dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
        if (isWeekend) {
            log.debug("Дата {} является выходным днем.", date);
        }
        return isWeekend;
    }
}
