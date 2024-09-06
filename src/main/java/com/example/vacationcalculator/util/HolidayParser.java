package com.example.vacationcalculator.util;

import com.example.vacationcalculator.model.Holidays;
import com.example.vacationcalculator.model.MonthHolidays;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Утилитарный класс для парсинга JSON с праздниками и преобразования их в список дат.
 */
@Component
@Slf4j
public class HolidayParser {

    private static final String HOLIDAYS_JSON_PATH = "/holidays.json";

    private final ObjectMapper objectMapper;

    public HolidayParser(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    /**
     * Загружает и парсит JSON-файл с праздниками.
     *
     * @return список праздников в виде List<LocalDate>
     */
    public List<LocalDate> loadHolidays() {
        List<LocalDate> holidays = new ArrayList<>();
        try {
            Holidays holidaysData = objectMapper.readValue(
                    this.getClass().getResourceAsStream(HOLIDAYS_JSON_PATH), Holidays.class);
            int year = holidaysData.getYear();
            for (MonthHolidays monthHolidays : holidaysData.getMonths()) {
                int month = monthHolidays.getMonth();
                for (String day : monthHolidays.getDays().split(",")) {
                    day = day.replaceAll("[^0-9]", "");
                    holidays.add(LocalDate.of(year, month, Integer.parseInt(day)));
                }
            }
            log.info("Успешно загружены {} праздников из JSON", holidays.size());
        } catch (IOException e) {
            log.error("Ошибка при парсинге файла с праздниками: {}", e.getMessage());
        }
        return holidays;
    }
}
