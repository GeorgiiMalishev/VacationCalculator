package com.example.vacationcalculator.model;

import lombok.Data;

import java.util.List;

/**
 * Модель для хранения информации о праздниках за год.
 */
@Data
public class Holidays {
    private int year;
    private List<MonthHolidays> months;
    private List<Transition> transitions;
    private Statistic statistic;
}

@Data
class Transition {
    private String from;
    private String to;
}

@Data
class Statistic {
    private int workdays;
    private int holidays;
    private double hours40;
    private double hours36;
    private double hours24;
}

