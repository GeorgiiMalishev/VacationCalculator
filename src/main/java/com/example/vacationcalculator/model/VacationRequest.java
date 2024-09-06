package com.example.vacationcalculator.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

/**
 * Модель данных для запроса расчета отпускных.
 * Включает среднюю зарплату, количество дней отпуска и даты отпуска.
 */
@Getter
@AllArgsConstructor
public class VacationRequest {
    private double averageSalary;
    private int vacationDays;
    private List<LocalDate> vacationDates;
}
