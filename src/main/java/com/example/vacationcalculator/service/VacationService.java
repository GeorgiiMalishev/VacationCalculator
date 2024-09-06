package com.example.vacationcalculator.service;

import com.example.vacationcalculator.model.VacationRequest;
import com.example.vacationcalculator.util.HolidayCalculator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Сервис для расчета отпускных. Выполняет основную бизнес-логику,
 * включая расчет с учетом праздников и выходных дней.
 */
@Service
@Slf4j
public class VacationService {

    private final HolidayCalculator holidayCalculator;

    public VacationService(HolidayCalculator holidayCalculator) {
        this.holidayCalculator = holidayCalculator;
    }

    /**
     * Рассчитывает сумму отпускных на основе средней зарплаты, количества дней отпуска,
     * и, при необходимости, с учетом праздников и выходных.
     *
     * @param request запрос с данными о зарплате, днях отпуска и датах отпуска
     * @return рассчитанная сумма отпускных
     */
    public double calculateVacationPay(VacationRequest request) {
        log.info("Начало расчета отпускных для зарплаты: {}, дней отпуска: {}",
                request.getAverageSalary(), request.getVacationDays());

        double dailySalary = request.getAverageSalary() / 29.3;
        int vacationDays = request.getVacationDays();

        if (request.getVacationDates() != null && !request.getVacationDates().isEmpty()) {
            List<LocalDate> actualVacationDays = holidayCalculator.getWorkingDays(request.getVacationDates());
            vacationDays = actualVacationDays.size();
            log.info("После учета праздников и выходных дней количество отпускных дней: {}", vacationDays);
        }

        double vacationPay = dailySalary * vacationDays;
        log.info("Рассчитанная сумма отпускных: {}", vacationPay);
        return vacationPay;
    }
}