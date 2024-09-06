package com.example.vacationcalculator.controller;

import com.example.vacationcalculator.model.VacationRequest;
import com.example.vacationcalculator.service.VacationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST-контроллер для обработки запросов на расчет отпускных.
 */
@RestController
@RequestMapping("/api/vacation")
@Slf4j
public class VacationController {

    private final VacationService vacationService;

    public VacationController(VacationService vacationService) {
        this.vacationService = vacationService;
    }

    /**
     * Обрабатывает POST-запрос для расчета отпускных.
     *
     * @param request запрос, содержащий данные о зарплате и днях отпуска
     * @return рассчитанная сумма отпускных
     */
    @PostMapping("/calculate")
    public ResponseEntity<Double> calculateVacationPay(@RequestBody VacationRequest request) {
        log.info("Получен запрос на расчет отпускных...");
        double vacationPay = vacationService.calculateVacationPay(request);
        log.info("Запрос обработан успешно. Сумма отпускных: {}", vacationPay);
        return ResponseEntity.ok(vacationPay);
    }
}