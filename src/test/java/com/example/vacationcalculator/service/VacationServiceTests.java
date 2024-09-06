package com.example.vacationcalculator.service;

import com.example.vacationcalculator.model.VacationRequest;
import com.example.vacationcalculator.service.VacationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class VacationServiceTests {

    @Autowired
    private VacationService vacationService;

    @Test
    public void testCalculateVacationPayWithoutDates() {
        VacationRequest request = new VacationRequest(120000, 14, null);
        double result = vacationService.calculateVacationPay(request);
        assertEquals(57337.88, result, 1);
    }

    @Test
    public void testCalculateVacationPayWithDates() {
        List<LocalDate> vacationDates = List.of(
                LocalDate.of(2024, 5, 1),
                LocalDate.of(2024, 5, 2),
                LocalDate.of(2024, 5, 3),
                LocalDate.of(2024, 5, 9)
        );
        VacationRequest request = new VacationRequest(120000, 14, vacationDates);

        double result = vacationService.calculateVacationPay(request);
        assertEquals(8191.12, result, 1);
    }
}